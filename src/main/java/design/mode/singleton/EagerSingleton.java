package design.mode.singleton;

/**
 * <p>饿汉式</p>
 *
 * @author gengen.wang
 * @version $$ Id: EagerSingleton.java, V 0.1 2019/7/29 上午11:25 wanggengen Exp $$
 **/
public class EagerSingleton {

    //私有化属性,创建实例
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    //私有构造器
    private EagerSingleton() {
    }

    //公有静态方法,获取实例
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

}
