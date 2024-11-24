package shop.topup.workspace.domain.infra

import org.junit.jupiter.api.Test

class GooFishServiceTest {
    private val sessionKey = "50000900824eNLY27iDP3sUCC5HTFJqcrPEc11a78d36EaP6Lw2jhFvp0jPpaBw5YuQP"
    private val gooFishService =
        GooFishService(
            "https://eco.taobao.com/router/rest",
            "34912212", "a096b05f7a806c67afd9a8762f47412d"
        )

    @Test
    fun testFetchSellerInfo() {
        val resp = gooFishService.fetchSellerInfo(sessionKey)
        println(resp.body)
    }
}