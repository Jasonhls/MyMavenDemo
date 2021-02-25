import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DistributeQueueTest {

    //线程数量
    public static final int THREAD_NUM = 300;

    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void concrrentTest(){
        CountDownLatch countDownLatch = new CountDownLatch(1);

        for (int i = 0;i < THREAD_NUM;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //调用接口
                    ResponseEntity<String> result = restTemplate.getForEntity(
                            "http://localhost:8080/user/json",
                            String.class
                    );
                    log.info(result.toString());
                }
            }).start();
        }
        countDownLatch.countDown();
    }
}
