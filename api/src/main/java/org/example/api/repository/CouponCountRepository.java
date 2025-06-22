package org.example.api.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponCountRepository {
    private final RedisTemplate<String, String> redisTemplate;

    // redis 에 incr key 명령어를 수행하는 메소드
    public Long increase(String key) {
        return redisTemplate.opsForValue().increment(key);
    }
}
