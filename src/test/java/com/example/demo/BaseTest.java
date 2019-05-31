package com.example.demo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: BaseTest.java, V 0.1 2019/5/31 下午1:59 wanggengen Exp $$
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = DemoApplication.class)
public class BaseTest {



}
