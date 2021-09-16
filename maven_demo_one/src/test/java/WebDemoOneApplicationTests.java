import com.cn.pojo.User;
import com.cn.service.UserService;
import com.cn.threadAndLock.distributedLock.redisLock.MiaoshaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WebDemoOneApplicationTests {

	long time = 0L;

	@Before
	public void start(){
		System.out.println("开始测试");
	}

	@After
	public void end(){
		System.out.println("结束测试，执行时长：" + (System.currentTimeMillis() - time));
	}

	@Autowired
	MiaoshaService miaoshaService;

	@Test
	public void contextLoads() {
		//模拟的请求数量
		final int threadNum = 20;
		//倒计时器，用于模拟高并发（信号枪机制）
		CountDownLatch cdl = new CountDownLatch(threadNum);
		Thread[] threads = new Thread[threadNum];
		for (int i = 0;i < threadNum;i++){
			String userId = "Tony";
			Thread thread = new Thread(() -> {
				try {
					cdl.await();
					//http 请求实际上就是多线程调用这个方法
					miaoshaService.miaosha("bike",userId);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			threads[i] = thread;
			thread.start();
			//倒计时器 减一
			cdl.countDown();
		}
	}

	@Autowired
	private UserService userService;

	@Test
	public void benchmarkTest() throws InterruptedException {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(2000);
		for (int i = 0;i < 2000;i++){
			new Thread(() -> {
				try {
					//等待栅栏开启
					cyclicBarrier.await();//执行到这里，不会继续往下执行
					User user = userService.selectUserByUserName("haha1");
					log.info("查询结果：{}",user.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}

		//测试一分钟，不管有没有结束，直接关闭main
		TimeUnit.SECONDS.sleep(60);
	}
}
