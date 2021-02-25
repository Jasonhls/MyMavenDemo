import com.cn.clazz.Person;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-02 16:43
 **/
public class ClazzTest {
    @Test
    public void test1() {
        try {
            Class clazz = Person.class;
            Constructor constructor = clazz.getDeclaredConstructor();

            Person p = (Person) BeanUtils.instantiateClass(constructor, new Object[0]);
            Method m = BeanUtils.findMethod(clazz, "eat", String.class);
            m.setAccessible(true);
            Object haha = m.invoke(p, "haha");
            System.out.println(haha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
