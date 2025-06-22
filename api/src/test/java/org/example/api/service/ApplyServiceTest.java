package org.example.api.service;

import org.example.api.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplyServiceTest {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private CouponRepository couponRepository;

    @Test
    void 한번만_응모() {
        applyService.applyCoupon(1L);
        long count = couponRepository.count();

        assertEquals(1, count);
    }

    @Test
    void 여러명_동시_응모() throws InterruptedException {
        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        //멀티스레드가 모두 종료될때까지 대기하는 로직을 만들때 사용. (모든 스레드 실행이 종료되고 쿠폰발급 카운트를 해야하기때문)
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            long userId = i;
            executorService.submit(() -> {
                try {
                    applyService.applyCoupon(userId);
                } finally {
                    latch.countDown(); // 각 스레드가 실행될때마다 countdown
                }
            });
        }
        latch.await(); // countDown 이 모두 완료되면 종료.

        long count = couponRepository.count();
        System.out.println("여러명_동시_응모() count = " + count); // 실행결과 149.  100개를 초과하여 발급되었다.

        assertEquals(100, count);
    }

}