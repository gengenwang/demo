package com.example.demo.service;

import com.example.demo.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: DatabaseLockServiceTest.java, V 0.1 2019/6/21 上午11:05 wanggengen Exp $$
 **/
public class DatabaseLockServiceTest extends BaseTest {

    @Resource
    private DatabaseLockService service;

    @Test
    public void testSingleCounsumer(){

        service.singleCounsumer();

    }

    @Test
    public void testThreadPrice(){

        service.threadPrice();

    }


}
