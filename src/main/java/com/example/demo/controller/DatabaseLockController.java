package com.example.demo.controller;

import com.example.demo.service.DatabaseLockService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: DatabaseLockController.java, V 0.1 2019/6/24 上午10:21 wanggengen Exp $$
 **/
@RestController
@RequestMapping(value = "/databaseLock")
public class DatabaseLockController {

    @Resource
    private DatabaseLockService service;

    /**
     * 多线程并发请求
     *
     * @return
     */
    @RequestMapping(value = "/threadPrice")
    public Boolean threadPrice() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    service.threadPrice();
                }
            });

            executor.execute(t);
        }
        return Boolean.TRUE;
    }

    /**
     * 多线程并发请求:悲观锁
     *
     * @return
     */
    @RequestMapping(value = "/pessimisticLockPrice")
    public Boolean pessimisticLockPrice() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    service.pessimisticLockPrice();
                }
            });

            executor.execute(t);
        }
        return Boolean.TRUE;
    }

    /**
     * 多线程并发请求:乐观锁
     *
     * @return
     */
    @RequestMapping(value = "/optimisticLockPriceVersion")
    public Boolean optimisticLockPriceVersion() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    service.optimisticLockPriceVersion();
                }
            });

            executor.execute(t);
        }
        return Boolean.TRUE;
    }


}
