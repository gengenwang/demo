package com.example.demo.service.excel.impl;

import com.example.demo.service.excel.UserExcelService;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: UserBatchInsertThread.java, V 0.1 2019/5/31 下午4:11 wanggengen Exp $$
 **/
public class UserBatchInsertThread implements Runnable {

    private UserExcelService userExcelService;
    /**
     * 每个线程处理的起始数据
     */
    private CountDownLatch begin;
    /**
     * 每个线程处理的结束数据
     */
    private CountDownLatch end;
    /**
     * excel中读取的首行
     */
    private int sta;
    /**
     * excel中读取的条数
     */
    private int size;
    /**
     * 数据集合
     */
    private List<Row> rows;

    public UserBatchInsertThread() {
    }

    public UserBatchInsertThread(List<Row> rows, CountDownLatch begin, CountDownLatch end,
                                 UserExcelService userExcelService, int sta, int size) {
        this.begin = begin;
        this.end = end;
        this.userExcelService = userExcelService;
        this.sta = sta;
        this.size = size;
        this.rows = rows;
    }

    @Override
    public void run() {
        try {
            if (!CollectionUtils.isEmpty(rows)) {
                //执行真正的处理
                userExcelService.ExcelDoing(rows, sta, size);
            }
            //执行完让线程直接进入等待
            begin.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //当一个线程执行完 了计数要减一不然这个线程会被一直挂起
            end.countDown();
        }
    }
}

