package org.example.consumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CouponCreateConsumer {
    @KafkaListener(topics = "coupon_create", groupId = "group_1")
    public void listener(Long userId) {
        System.out.println("kafka listener userId : " + userId);
    }
}
