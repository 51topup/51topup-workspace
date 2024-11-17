package shop.topup.workspace.demo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import shop.topup.workspace.TestcontainersBaseTest

class RedisTest : TestcontainersBaseTest() {
    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, String>

    @Test
    fun testRedis() {
        val redis = redisTemplate.opsForValue()
        redis.set("name", "linux_china")
        val name = redis.get("name")
        println(name)
    }
}