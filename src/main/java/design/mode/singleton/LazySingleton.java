package design.mode.singleton;

/**
 * <p>懒汉式--双重检验锁方式</p>
 *
 * @author gengen.wang
 * @version $$ Id: LazySingleton.java, V 0.1 2019/7/29 上午11:06 wanggengen Exp $$
 **/
public class LazySingleton {

    //私有化实例对象，volatile修饰禁止指令重排序
    private volatile static LazySingleton singleton;

    //私有化构造器
    private LazySingleton() {
    }

    //公有静态方法获取实例
    public static LazySingleton getSingleton() {
        //先判断对象是否已经实例过，没有实例化过才进入加锁代码
        //减少加锁
        if (singleton == null) {
            //类对象加锁
            synchronized (LazySingleton.class) {
                //对象未实例过,创建实例
                if (singleton == null) {
                    singleton = new LazySingleton();
                }
            }
        }

        return singleton;
    }

}
