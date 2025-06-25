package org.example.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.api.domain.Coupon;
import org.example.api.kafka.producer.CouponCreateProducer;
import org.example.api.repository.CouponCountRepository;
import org.example.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplyService {
    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;

    //쿠폰 발급 로직
    public void applyCoupon(Long userId)   {
//        long count = couponRepository.count(); // db에서 조회하는 방식

        // count를 redis "coupon_count" 에서 조회
        // 주의 * : 매번 실행 시 마다 redis의 coupon_count 초기화 해줘야함. 명렁어 : flushall
        Long count = couponCountRepository.increase("coupon_count");
        log.info("applyCoupon - count = {}", count);
        if (count > 100) {
            return;
        }
//        couponRepository.save(new Coupon(userId));
        couponCreateProducer.send(userId); // 쿠폰 생성 요청을 kakfa 로 보내도록 적용.
    }
}
