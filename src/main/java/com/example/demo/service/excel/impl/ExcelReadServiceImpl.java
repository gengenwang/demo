package com.example.demo.service.excel.impl;

import com.example.demo.exception.ExcpUtil;
import com.example.demo.service.excel.UserExcelService;
import com.example.demo.service.excel.ExcelReadService;
import com.example.demo.util.ExcelReaderUtil;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: ExcelReadServiceImpl.java, V 0.1 2019/5/31 下午3:32 wanggengen Exp $$
 **/
@Service("excelReadService")
public class ExcelReadServiceImpl implements ExcelReadService {

    @Resource
    private UserExcelService userExcelService;

    public static final int COUNT = 1000;//每个线程处理的数量


    @Override
    public Boolean userUpload(InputStream is, boolean if2003Version) {
        try {
            List<Row> rows = ExcelReaderUtil.readContent(is, true);
            rows.remove(0);//去除首行表头

            int rowSize = rows.size();//excel数据总量
            int runSize = (rowSize / COUNT) + 1;//开启的线程数
            long time1 = System.currentTimeMillis();

            // 创建一个线程池，数量和开启线程的数量一样
            ExecutorService executor = Executors.newFixedThreadPool(runSize);
            //创建两个个计数器
            CountDownLatch begin = new CountDownLatch(1);
            CountDownLatch end = new CountDownLatch(runSize);

            //为每个线程分配要执行的数据
            for (int i = 0; i < runSize; i++) {
                int startIdx = 0;
                int endIdx = 0;
                if ((i + 1) == runSize) {
                    startIdx = (i * COUNT);
                    endIdx = rowSize;
                } else {
                    startIdx = (i * COUNT);
                    endIdx = (i + 1) * COUNT;
                }
                UserBatchInsertThread thread = new UserBatchInsertThread(rows, begin, end, userExcelService, startIdx, endIdx);
                executor.execute(thread);
            }
            begin.countDown();
            end.await();

            executor.shutdown();
            long time2 = System.currentTimeMillis();
            System.out.println("总耗时：" + (time2 - time1));

        } catch (InterruptedException e) {
            throw ExcpUtil.genServiceException("导入数据出错");
        }

        return Boolean.TRUE;
    }


}
