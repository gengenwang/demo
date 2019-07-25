package api.java.collection.set;

import lombok.Data;


/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: App.java, V 0.1 2019/7/25 下午5:01 wanggengen Exp $$
 **/
@Data
public class App implements Comparable<App> {

    private String name;

    private Integer age;

    public App() {
    }

    public App(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    /**
     * 自定义比较：先比较name的长度，在比较age的大小；
     *
     * @param app
     * @return
     */
    @Override
    public int compareTo(App app) {
        //比较name的长度
        int num = this.name.length() - app.name.length();
        //如果那么长度一样，则比较年龄的大小
        return num == 0 ? this.age - app.age : num;
    }

}
