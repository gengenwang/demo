package com.example.demo.service.excel.impl;

import com.example.demo.domain.UserDomain;
import com.example.demo.service.excel.UserExcelService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: UserDownloadThread.java, V 0.1 2019/6/4 下午4:24 wanggengen Exp $$
 **/
public class UserDownloadThread implements Runnable {

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
    private List<UserDomain> userDomains;
    /**
     * excel
     */
    private HSSFSheet hSSFSheet;

    public UserDownloadThread() {
    }

    public UserDownloadThread(List<UserDomain> userDomains, CountDownLatch begin, CountDownLatch end,
                              UserExcelService userExcelService, int sta, int size, HSSFSheet hSSFSheet) {
        this.begin = begin;
        this.end = end;
        this.userExcelService = userExcelService;
        this.sta = sta;
        this.size = size;
        this.userDomains = userDomains;
        this.hSSFSheet = hSSFSheet;
    }

    @Override
    public void run() {
        try {
            if (!CollectionUtils.isEmpty(userDomains)) {
                //执行真正的处理
                userExcelService.createRows(hSSFSheet, sta, size, userDomains);
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕");
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
