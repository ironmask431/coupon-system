package org.example.api.kafka;

import org.example.api.kafka.producer.CouponCreateProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CouponCreateProducerTest {

    @Autowired
    private CouponCreateProducer couponCreateProducer;

    @Test
    void 카프카_프로듀서_발송_테스트(){
        couponCreateProducer.send(1L);
        couponCreateProducer.send(2L);
        couponCreateProducer.send(3L);
    }
}
