import com.cn.beanlife.MySpringBean;
import com.cn.beanlife.SubMySpringBean;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-02 09:38
 **/
public class BeanLifeTest {
    @Test
    public void testBeanLife1() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Bean-LifeCycle.xml");
        MySpringBean bean = (MySpringBean) context.getBean("mySpringBean");
        System.out.println("MySpringBean的属性name的值：" + bean.getName());
        context.destroy();
    }

    /**
     * 调用MySpringBean的无参构造，进行初始化
     * 子类：调用无参构造函数
     * 子类：属性color的设置
     * 设置beanName
     * 子类：调用setBeanClassLoader方法
     * 设置 beanFactory
     * 子类：调用setEnvironment方法
     * 子类：调用setEmbeddedValueResolver方法
     * 子类：调用setResourceLoader方法
     * 子类：调用setApplicationEventPublisher方法
     * 子类：调用setMessageSource方法
     * 设置applicationContext
     * MySpringBean 调用postProcessBeforeInitialization方法
     * 调用@PostConstruct方法
     * 调用afterPropertiesSet方法
     * 调用xml中的init-method方法
     * MySpringBean 调用postProcessAfterInitialization方法
     * 11:15:47.212 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'a'
     * 调用a的无参构造
     * 11:15:47.212 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'b'
     * 调用b的无参构造
     * SubMySpringBean的属性color的值：color is beautiful
     * 11:15:47.238 [Thread-0] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext - Closing org.springframework.context.support.ClassPathXmlApplicationContext@61d47554, started on Thu Jul 02 11:15:46 CST 2020
     * 调用@PreDestroy方法
     * 调用destroy方法
     * 调用xml配置中的destroy-method方法
     */
    @Test
    public void testBeanLife2() {
        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("SubBean-LifeCycle.xml");
        SubMySpringBean bean1 = (SubMySpringBean) context1.getBean("subMySpringBean");
        System.out.println("SubMySpringBean的属性color的值：" + bean1.getColor());
        context1.registerShutdownHook();
    }
}
