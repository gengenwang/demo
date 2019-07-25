package com.example.demo.service.aop.jdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: DynamicAgent.java, V 0.1 2019/7/24 下午4:50 wanggengen Exp $$
 **/
public class DynamicAgent {

    //实现InvocationHandler接口，并且可以初始化被代理类的对象
    static class MyHandler implements InvocationHandler {

        private Object target;

        public MyHandler(Object target) {
            this.target = target;
        }

        public MyHandler() {
        }

        /**
         * 自定义invoke方法
         *
         * @param proxy
         * @param method
         * @param args
         * @return
         * @throws Throwable
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("调用前。。");
            //真正调用
            Object ret = method.invoke(target, args);
            System.out.println("调用后。。");

            return ret;
        }

    }

    /**
     * 返回一个修改过的对象
     *
     * @param interfaceClazz
     * @param target
     * @return
     */
    public static Object getAgent(Class interfaceClazz, Object target) {

        return Proxy.newProxyInstance(interfaceClazz.getClassLoader(), new Class[]{interfaceClazz}, new MyHandler(target));
    }

}
