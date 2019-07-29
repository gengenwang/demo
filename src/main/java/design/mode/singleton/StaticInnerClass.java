package design.mode.singleton;

/**
 * <p>懒汉式--静态内部类</p>
 *
 * @author gengen.wang
 * @version $$ Id: StaticInnerClass.java, V 0.1 2019/7/29 上午11:17 wanggengen Exp $$
 **/
public class StaticInnerClass {

    private static class LazyHolder {
        //静态内部类,创建主类实例对象
        private static final StaticInnerClass INSTANCE = new StaticInnerClass();
    }

    //私有化主类构造器
    private StaticInnerClass() {
    }

    //公有化静态工厂方法获取实例
    public static StaticInnerClass getInstance() {
        return LazyHolder.INSTANCE;
    }

}
