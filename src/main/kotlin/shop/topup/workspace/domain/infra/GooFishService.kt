package shop.topup.workspace.domain.infra

import com.taobao.api.DefaultTaobaoClient
import com.taobao.api.TaobaoClient
import com.taobao.api.request.AlibabaIdleIsvUserQueryRequest
import com.taobao.api.request.TopAuthTokenCreateRequest
import com.taobao.api.response.AlibabaIdleIsvUserQueryResponse
import com.taobao.api.response.TopAuthTokenCreateResponse
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

    fun getAccessTokenResult(code: String): TopAuthTokenCreateResponse {
        val req = TopAuthTokenCreateRequest()
        req.code = code
        return topClient.execute(req)
    }

    fun fetchSellerInfo(sessionKey: String): AlibabaIdleIsvUserQueryResponse {
        val req = AlibabaIdleIsvUserQueryRequest()
        return topClient.execute(req, sessionKey)
    }

}