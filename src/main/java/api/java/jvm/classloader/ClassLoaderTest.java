package api.java.jvm.classloader;

import org.junit.Test;
import sun.net.spi.nameservice.dns.DNSNameService;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: ClassLoaderTest.java, V 0.1 2019/7/30 下午2:44 wanggengen Exp $$
 **/
public class ClassLoaderTest {

    /**
     * BootStrap ClassLoader 加载的文件
     */
    @Test
    public void testBootStrapClassLoader(){
        System.out.println(System.getProperty("sun.boot.class.path"));
    }

    /**
     * EtxClassLoader 加载文件
     */
    @Test
    public void testEtxClassLoader(){
        System.out.println(System.getProperty("java.ext.dirs"));
    }

    @Test
    public void testParentClassLoader(){
        System.out.println("ClassLoaderTest's ClassLoader is " + ClassLoaderTest.class.getClassLoader());
        System.out.println("DNSNameService's ClassLoader is " + DNSNameService.class.getClassLoader());
        System.out.println("String's ClassLoader is " + String.class.getClassLoader());
    }

    @Test
    public void testParentClassLoader2(){
        System.out.println("ClassLoaderTest's ClassLoader is " + ClassLoaderTest.class.getClassLoader());
        System.out.println("The Parent of ClassLoaderTest's ClassLoader is " + ClassLoaderTest.class.getClassLoader().getParent());
        System.out.println("The GrandParent of ClassLoaderTest's ClassLoader is " + ClassLoaderTest.class.getClassLoader().getParent().getParent());
    }

    @Test
    public void testMyClassLoader() throws Exception {
        URL path = new File("src/main/resources/data/classloader").toURI().toURL();

        DiskClassLoader diskClassLoaderA = new DiskClassLoader(path);
        Class<?> clazzA = diskClassLoaderA.loadClass("BeLoadedClass");
        Method sayA = clazzA.getMethod("say");
        Object instanceA = clazzA.newInstance();
        sayA.invoke(instanceA);
        System.out.println(diskClassLoaderA);
        System.out.println("clazzA@" + clazzA.hashCode());

        System.out.println("====");

        DiskClassLoader diskClassLoaderB = new DiskClassLoader(new URL[]{path}, diskClassLoaderA);
        Class<?> clazzB = diskClassLoaderB.loadClass("BeLoadedClass");
        Method sayB = clazzB.getMethod("say");
        Object instanceB = clazzA.newInstance();
        sayB.invoke(instanceB);
        System.out.println(diskClassLoaderB);
        System.out.println("clazzB@" + clazzB.hashCode());

        System.out.println("====");

        DiskClassLoader diskClassLoaderC = new DiskClassLoader(path);
        Class<?> clazzC = diskClassLoaderC.loadClass("BeLoadedClass");
        Method sayC = clazzC.getMethod("say");
        Object instanceC = clazzC.newInstance();
        sayC.invoke(instanceC);
        System.out.println(diskClassLoaderC);
        System.out.println("clazzC@" + clazzC.hashCode());

        System.out.println("====");

        System.out.println("clazzA == clazzB " + (clazzA == clazzB));
        System.out.println("clazzC == clazzB " + (clazzC == clazzB));
    }


}
