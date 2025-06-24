package org.example.api.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponCountRepository {
    private final RedisTemplate<String, String> redisTemplate;
    // 별다른 설정없이 local redis와 springboot 프로젝트가 연동되는 이유 = 기본적으로 localhost:6379 를 바라보도록 세팅되어 있기 때문.
    // 변경이 필요할 경우 별도 설정이 필요하다.

    // redis 에 incr key 명령어를 수행하는 메소드
    public Long increase(String key) {
        return redisTemplate.opsForValue().increment(key);
    }
}
