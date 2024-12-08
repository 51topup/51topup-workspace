package shop.topup.workspace.domain.infra

import com.taobao.api.DefaultTaobaoClient
import com.taobao.api.TaobaoClient
import com.taobao.api.request.AlibabaIdleIsvRateCreateRequest
import com.taobao.api.request.AlibabaIdleIsvUserQueryRequest
import com.taobao.api.request.AlibabaIdleUserPermitRequest
import com.taobao.api.request.AlibabaIdleUserPermitRequest.UserGrantRequest
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

    /**
     * 闲鱼卖家与服务商关系绑定，用于业务数据分发/授权校验/消息通知鉴权
     */
    fun allowPermits(sessionKey: String) {
        val request = AlibabaIdleUserPermitRequest().apply {
            val obj1 = UserGrantRequest()
            obj1.bizCode = "IDLE_TOP"
            obj1.sceneType = "22"
            setParamUserGrantRequest(obj1)
        }
        topClient.execute(request, sessionKey)
    }

    fun fetchSellerInfo(sessionKey: String): AlibabaIdleIsvUserQueryResponse {
        val req = AlibabaIdleIsvUserQueryRequest()
        return topClient.execute(req, sessionKey)
    }

    fun addSellerRate(sessionKey: String, orderId: Long, rate: Long, feedback: String): Boolean {
        val req = AlibabaIdleIsvRateCreateRequest()
        req.tradeId = orderId
        req.rate = rate
        req.feedback = feedback
        req.rateType = 0L
        val rsp = topClient.execute(req, sessionKey)
        return rsp.isSuccess
    }

}