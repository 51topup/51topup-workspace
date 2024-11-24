package shop.topup.workspace.ui.controller

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shop.topup.workspace.domain.infra.GooFishService
import shop.topup.workspace.ui.security.JwtService

@RestController
@RequestMapping("/goofish")
class GooFishController(
    val jwtService: JwtService,
    val goofishService: GooFishService,
    val objectMapper: ObjectMapper,
    @Value("\${jwt.expiration}") val expirationSeconds: Int
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class AccessTokenResult(
        @JsonProperty("access_token") var accessToken: String,
        @JsonProperty("expires_in") var accessTokenExpiresIn: Int,
        @JsonProperty("refresh_token") var refreshToken: String,
        @JsonProperty("re_expires_in") var refreshTokenExpiresIn: Int,
        @JsonProperty("taobao_user_nick") var userNick: String,
        @JsonProperty("taobao_user_id") var userId: String,
        @JsonProperty("taobao_open_uid") var openUid: String,
        @JsonProperty("sub_taobao_user_nick") var subUserNick: String,
        @JsonProperty("sub_taobao_user_id") var subUserId: String,
    )

    @GetMapping("/callback")
    fun hello(request: HttpServletRequest, response: HttpServletResponse) {
        request.getParameter("code")?.let {
            //todo validate code by TOP platform
//            val resultJsonText = goofishService.getAccessTokenResult(it)
//            OBJECT_MAPPER.readValue<AccessTokenResult>(resultJsonText)
            val jwt = jwtService.generate("seller1", "SELLER")
            val headerAndPayload = jwt.substring(0, jwt.lastIndexOf("."))
            val signature = jwt.substring(jwt.lastIndexOf(".") + 1)
            response.addCookie(
                Cookie(
                    "jwt.headerAndPayload",
                    headerAndPayload
                ).apply {
                    maxAge = expirationSeconds
                    path = "/"
                })
            response.addCookie(Cookie("jwt.signature", signature).apply {
                isHttpOnly = true
                maxAge = expirationSeconds
                path = "/"
            })
            response.sendRedirect("/workspace")
            return
        }
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid code")
    }
}