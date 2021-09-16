package com.cn.maventwo.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.UUID;

/**
 * @Author: helisen
 * @Date 2021/7/29 14:44
 * @Description:
 */
public class KafkaProducerDemo {
    public static void main(String[] args) throws Exception{
        KafkaProducer<String, String> producer = createProducer();
        JSONObject order = createOrder();
        ProducerRecord<String, String> record = new ProducerRecord<>("test", order.getString("orderId"), order.toJSONString());
        //这是异步发送模式
        long startTime = System.currentTimeMillis();
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(e == null) {
                    //消息发送成功
                    System.out.println("消息发送成功");
                }else {
                    //消息发送失败，需要重新发送
                }
            }
        });
        long endTime = System.currentTimeMillis();
        if(endTime - startTime > 10) {
            //你应该走一个监控和报警的过程，开发一些应用程序，系统
            //metric监控和报警，小米开源的open-falcon，挺好用的，监控metric，报警
            //立马给程序员发短信，或者是发送钉钉，或者发邮件
        }

        Thread.sleep(10 * 1000);

        //这是同步发送的模式
//        producer.send(record).get();
        //你要一直等待人家后续一系列的步骤都做完，发送消息之后
        //有了消息的回应返回给你，你这个方法才会退出来
        producer.close();
    }

    /**
     * 生产者中的各种配置的默认值可以查看ProducerConfig中静态代码块
     * @return
     */
    private static KafkaProducer<String, String> createProducer() {
        Properties props = new Properties();
        //这里可以配置几台broker即可，它会自动从broker去拉取元数据进行缓存
        props.put("bootstrap.servers", "localhost:9092");
        //负责把送的key从字符串序列化成字节数组
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //负责把发送的实际的message从字符串序列化为字节数组
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("buffer.memory", 67108864);//默认是32mb
        props.put("batch.size", 131072);//一般来说要自己手动设置的额，不是纯粹依靠默认值，16kb
        props.put("linger.ms", 100);//发送一条消息出去，100ms内还没有凑成一个batch发送，必须立即发送出去
        props.put("max.request.size", 10485760);//设置最多可以发送多大的一条消息出去，默认是1mb
        props.put("acks", "1");//follower有没有同步成功你就不管了
        props.put("retries", 10);//设置重试次数，一般来说3-5次足够，可以cover住一般的异常场景
        props.put("retry.backoff.ms", 500);//每次重试间隔100ms
        //创建一个Producer实力：先出资源，跟各个broker建立socket连接资源
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        return producer;
    }

    /**
     * 创建一个测试用的订单
     * @return
     */
    private static JSONObject createOrder() {
        JSONObject order =new JSONObject();
        order.put("orderId", 63988);
        order.put("orderNo", UUID.randomUUID().toString());
        order.put("userId", 1147);
        order.put("productId", 300);
        order.put("purchaseCount", 2);
        order.put("productPrice", 50.0);
        order.put("totalAmount", 100.0);
        order.put("_OPERATION_", "PAY");
        return order;
    }
}
