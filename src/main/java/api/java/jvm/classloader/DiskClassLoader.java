package api.java.jvm.classloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: DiskClassLoader.java, V 0.1 2019/7/31 下午5:45 wanggengen Exp $$
 **/
public class DiskClassLoader extends URLClassLoader{

    public DiskClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public DiskClassLoader(URL path) throws MalformedURLException {
        super(new URL[]{path});
    }

}
