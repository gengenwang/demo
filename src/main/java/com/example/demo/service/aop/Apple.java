package com.example.demo.service.aop;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: Apple.java, V 0.1 2019/7/24 下午4:35 wanggengen Exp $$
 **/
public class Apple implements Fruit {

    @Override
    public void show() {
        System.out.println("<<<<show method is invoked");
    }

}
