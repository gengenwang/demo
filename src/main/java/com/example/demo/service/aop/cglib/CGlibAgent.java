package com.example.demo.service.aop.cglib;

import com.example.demo.service.aop.Apple;
import com.example.demo.service.aop.Fruit;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>cglib代理</p>
 *
 * @author gengen.wang
 * @version $$ Id: CGlibAgent.java, V 0.1 2019/7/24 下午4:26 wanggengen Exp $$
 **/
public class CGlibAgent implements MethodInterceptor {

    private Object proxy;

    public Object getInstance(Object proxy) {
        this.proxy = proxy;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(proxy.getClass());
        //回调方法
        enhancer.setCallback(this);
        //创建代理队形
        Object o = enhancer.create();
        return o;
    }

    /**
     * 回调方法
     *
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("调用前。。");
        //真正调用
        Object ret = methodProxy.invokeSuper(o, objects);
        System.out.println("调用后。。");

        return ret;
    }

    @Test
    public void test() {
        CGlibAgent agent = new CGlibAgent();
        Apple apple = (Apple) agent.getInstance(new Apple());
        apple.show();
    }

}
