package org.example.api.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponCreateProducer {
    private final KafkaTemplate<String, Long> kafkaTemplate;

    public void send(Long userId) {
        kafkaTemplate.send("coupon_create", userId); // coupon_create : 토픽명
    }
}
