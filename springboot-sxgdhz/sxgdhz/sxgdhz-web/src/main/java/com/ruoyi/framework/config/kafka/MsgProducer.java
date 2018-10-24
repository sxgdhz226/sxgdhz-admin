package com.ruoyi.framework.config.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

@Component
public class MsgProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void send(String key,String data) {
        try {
            kafkaTemplate.send("mytopic",data);
            // kafkaTemplate.send("my-replicated-topic","key","xiaojf");

            kafkaTemplate.metrics();

            kafkaTemplate.execute(new KafkaOperations.ProducerCallback<String, String, Object>() {
                @Override
                public Object doInKafka(Producer<String, String> producer) {
                    //这里可以编写kafka原生的api操作
                    return null;
                }
            });

            //消息发送的监听器，用于回调返回信息
            kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
                @Override
                public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {
                    System.out.println("topic>>>>>>>>>>>>>>>"+topic+" key"+key);
                }

                @Override
                public void onError(String topic, Integer partition, String key, String value, Exception exception) {
                    System.out.println("key>>>>>>>>>>>>>>>>>>>>>"+key);
                    System.out.println("value>>>>>>>>>>>>>>>>>>>>"+value);
                    System.out.println(exception.getMessage());
                }

                @Override
                public boolean isInterestedInSuccess() {
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
