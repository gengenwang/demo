package com.example.demo.service;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: DatabaseLockServiceImpl.java, V 0.1 2019/6/20 下午5:05 wanggengen Exp $$
 **/
public interface DatabaseLockService {

    void singleCounsumer();

    void threadPrice();

    void pessimisticLockPrice();

    void optimisticLockPriceVersion();

}
