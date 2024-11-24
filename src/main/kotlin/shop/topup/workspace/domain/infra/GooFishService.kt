package shop.topup.workspace.domain.infra

import com.taobao.api.DefaultTaobaoClient
import com.taobao.api.TaobaoClient
import com.taobao.api.request.TopAuthTokenCreateRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

/**
 * 闲鱼服务接口
 */
@Service
class GooFishService(
    @Value("\${top.url}") val topApiUrl: String,
    @Value("\${top.app.key}") val topAppKey: String,
    @Value("\${top.app.secret}") val topAppSecret: String,
) {
    @Suppress("LeakingThis")
    var topClient: TaobaoClient = DefaultTaobaoClient(topApiUrl, topAppKey, topAppSecret)

    fun getAccessTokenResult(code: String): String {
        val req = TopAuthTokenCreateRequest()
        req.code = code
        val rsp = topClient.execute(req)
        return rsp.tokenResult
    }

}