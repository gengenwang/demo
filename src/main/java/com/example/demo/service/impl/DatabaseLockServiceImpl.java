package com.example.demo.service.impl;

import com.example.demo.dao.PriceDao;
import com.example.demo.dao.PriceVersionDao;
import com.example.demo.domain.Price;
import com.example.demo.domain.PriceVersion;
import com.example.demo.service.DatabaseLockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Random;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: DatabaseLockServiceImpl.java, V 0.1 2019/6/20 下午5:06 wanggengen Exp $$
 **/
@Slf4j
@Service(value = "databaseLock")
public class DatabaseLockServiceImpl implements DatabaseLockService {

    @Resource
    private PriceDao priceDao;

    @Resource
    private PriceVersionDao priceVersionDao;

    /**
     * 单线程处理
     */
    @Override
    public void singleCounsumer() {
        for (int i = 0; i < 100; i++) {
            Price price = priceDao.selectByPrimaryKey(1);
            int ron = 10;
            price.setFront(price.getFront().subtract(new BigDecimal(ron)));
            price.setEnd(price.getEnd().add(new BigDecimal(ron)));
            price.setTotal(price.getFront().add(price.getEnd()));
            priceDao.updateByPrimaryKey(price);
            price.setId(null);
            priceDao.insertSelective(price);
        }
    }

    /**
     * 10个线程并发去处理上面单线程的逻辑,出现并发问题
     * <p>
     * 会看到明显的数据错误，导致错误的原因自然就是有线程读取到了中间状态进行了错误的更新
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void threadPrice() {

        Price price = priceDao.selectByPrimaryKey(1);
        int ron = 10;
        price.setFront(price.getFront().subtract(new BigDecimal(ron)));
        price.setEnd(price.getEnd().add(new BigDecimal(ron)));
        priceDao.updateByPrimaryKey(price);
        price.setId(null);
        priceDao.insertSelective(price);
        System.out.println("代码执行");

    }

    /**
     * 使用select for update的方式利用数据库开启了悲观锁，锁定了id=1的这条数据
     * (注意:这里除非是使用了索引会启用行级锁，不然是会使用表锁，将整张表都锁住。)
     * 之后提交事务并释放锁，这样下一个线程过来拿到的就是正确的数据。
     * <p>
     * 悲观锁一般是用于并发不是很高，并且不允许脏读等情况。但是对数据库资源消耗较大。
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void pessimisticLockPrice() {
        Price price = priceDao.selectByPrimaryKey4Update(1);//悲观锁
        //Price price = priceDao.selectByPrimaryKey(1);
        int ron = 10;
        price.setFront(price.getFront().subtract(new BigDecimal(ron)));
        price.setEnd(price.getEnd().add(new BigDecimal(ron)));
        priceDao.updateByPrimaryKey(price);
        price.setId(null);
        priceDao.insertSelective(price);
        System.out.println("代码执行");
    }

    /**
     * 乐观锁是首先假设数据冲突很少，只有在数据提交修改的时候才进行校验，如果冲突了则不会进行更新。
     * 通常的实现方式增加一个version字段，为每一条数据加上版本。每次更新的时候version+1，并且更新时候带上版本号。
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void optimisticLockPriceVersion() {
        PriceVersion priceVersion = priceVersionDao.selectByPrimaryKey(1);
        int ron = new Random().nextInt(20);
        System.out.println(Thread.currentThread().getName() + "本次消费=" + ron);
        priceVersion.setFront(new BigDecimal(ron).add(priceVersion.getFront()));
        int count = priceVersionDao.updateByVersion(priceVersion);
        if (count == 0) {
            System.out.println(Thread.currentThread().getName() + "更新失败");
        } else {
            System.out.println(Thread.currentThread().getName() + "更新成功");
        }

    }


}
