package guru.bonacci.heroes.tippurger;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TIPRepository {
  
  private final StringRedisTemplate redisTemplate;
  
  
  public String getValue(String key) {
    return redisTemplate.opsForValue().get(key);
  }

  public Boolean delete(String key) {
    log.info("about to delete key {}", key);
    return redisTemplate.delete(key);
  }
}