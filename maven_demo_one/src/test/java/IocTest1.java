import com.cn.ioc.IocConfig1;
import com.cn.ioc.Person;
import com.cn.ioc.Student;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-01 11:20
 **/
public class IocTest1 {
    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IocConfig1.class);
        Object obj = context.getBean("person");

        Student student = (Student) context.getBean("Ss");
        Student student2 = (Student) context.getBean("student");

        Person person = (Person) obj;
        System.out.println(person);
        System.out.println(person.getStudent());
    }
}
