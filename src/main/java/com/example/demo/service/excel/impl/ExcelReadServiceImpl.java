package com.example.demo.service.excel.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.UserDomain;
import com.example.demo.exception.ExcpUtil;
import com.example.demo.model.excel.ExcelDownloadModel;
import com.example.demo.service.excel.UserExcelService;
import com.example.demo.service.excel.ExcelReadService;
import com.example.demo.util.ExcelReaderUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.*;

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

    @Resource
    private UserDao userDao;

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

    @Override
    public ExcelDownloadModel userDownload() {
        List<UserDomain> userDomains = userDao.selectUsers();

        HSSFWorkbook workBook = new HSSFWorkbook();//创建Excel
        HSSFSheet sheet = workBook.createSheet();//创建sheet页
        CellStyle style = workBook.createCellStyle();//创建格式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中格式

        int rowSize = userDomains.size();//excel数据总量
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
            UserDownloadThread thread = new UserDownloadThread(userDomains, begin, end, userExcelService, startIdx, endIdx, sheet);
            Thread.currentThread().setName("线程"+i);
            executor.execute(thread);
        }
        try {
            begin.countDown();
            end.await();//等待所有线程执行完毕
            executor.shutdown();//关闭线程池
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //导出Excel到本地
        FileOutputStream fou = null;
        try {
            fou = new FileOutputStream("/Users/wanggengen/Desktop/用户导出.xls");
            workBook.write(fou);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fou != null) {
                try {
                    fou.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        long time2 = System.currentTimeMillis();
        System.out.println("总耗时：" + (time2 - time1));
        return new ExcelDownloadModel(workBook.getBytes(),"用户导出.xls");
    }


}
