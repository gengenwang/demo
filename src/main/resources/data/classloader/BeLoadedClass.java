package api.java.jvm.classloader;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: BeLoadedClass.java, V 0.1 2019/7/31 下午5:44 wanggengen Exp $$
 **/
public class BeLoadedClass {

    public void say() {
        System.out.println("I'm Loaded by " + this.getClass().getClassLoader());
    }

}
