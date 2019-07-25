package api.java.collection.set;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

/**
 * <p>自定义App22类的比较器</p>
 *
 * @author gengen.wang
 * @version $$ Id: App2Comparator.java, V 0.1 2019/7/25 下午7:16 wanggengen Exp $$
 **/
@Data
@NoArgsConstructor
public class App2Comparator implements Comparator<App2> {
    @Override
    public int compare(App2 o1, App2 o2) {
        //比较方法：先比较年龄，年龄若相同在比较名字长度
        int num = o1.getAge() - o2.getAge();
        return num == 0 ? o1.getName().length() - o2.getName().length() : num;
    }
}
