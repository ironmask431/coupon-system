package org.example.consumer.consumer;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.consumer.domain.Coupon;
import org.example.consumer.repository.CouponRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class CouponCreateConsumer {

    private final CouponRepository couponRepository;

    // @RequiredArgsConstructor 어노테이션이 안먹혀서 생성자로 변경..
    public CouponCreateConsumer(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @KafkaListener(topics = "coupon_create", groupId = "group_1")
    public void listener(Long userId) {
        System.out.println("kafka listener userId : " + userId);
        couponRepository.save(new Coupon(userId));
    }
}
