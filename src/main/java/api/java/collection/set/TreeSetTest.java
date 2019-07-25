package api.java.collection.set;

import org.junit.Test;

import java.util.TreeSet;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: TreeSetTest.java, V 0.1 2019/7/25 下午7:10 wanggengen Exp $$
 **/
public class TreeSetTest {

    @Test
    public void testApp() {
        TreeSet<App> treeSet = new TreeSet<App>();

        //排序对象：
        App app1 = new App("hello", 10);
        App app2 = new App("world", 20);
        App app3 = new App("my", 15);
        App app4 = new App("name", 25);

        //添加到集合：
        treeSet.add(app1);
        treeSet.add(app2);
        treeSet.add(app3);
        treeSet.add(app4);
        System.out.println("TreeSet集合顺序为：" + treeSet);
    }

    @Test
    public void testApp2() {
        TreeSet<App2> treeSet = new TreeSet<App2>(new App2Comparator());

        //排序对象：
        App2 app1 = new App2("hello", 10);
        App2 app2 = new App2("world", 20);
        App2 app3 = new App2("my", 15);
        App2 app4 = new App2("name", 25);

        //添加到集合：
        treeSet.add(app1);
        treeSet.add(app2);
        treeSet.add(app3);
        treeSet.add(app4);
        System.out.println("TreeSet集合顺序为：" + treeSet);
    }

}
