package com.example.demo.service.aop.jdk;

import com.example.demo.service.aop.Apple;
import com.example.demo.service.aop.Fruit;
import org.junit.Test;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: DynamicAgentTest.java, V 0.1 2019/7/24 下午5:30 wanggengen Exp $$
 **/
public class DynamicAgentTest {

    @Test
    public void test() {
        Fruit fruit = (Fruit) DynamicAgent.getAgent(Fruit.class, new Apple());
        fruit.show();
    }


}
