package org.example.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.api.domain.Coupon;
import org.example.api.repository.CouponCountRepository;
import org.example.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplyService {
    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;

    //쿠폰 발급 로직
    public void applyCoupon(Long userId)   {
        //long count = couponRepository.count();
        Long count = couponCountRepository.increase("coupon_count");
        log.info("applyCoupon - count = {}", count);
        if (count > 100) {
            return;
        }
        couponRepository.save(new Coupon(userId));
    }
}
