package shop.topup.workspace.ui.controller

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shop.topup.workspace.ui.security.JwtService

@RestController
@RequestMapping("/goofish")
class GooFishController(
    val jwtService: JwtService,
    @Value("\${jwt.expiration}") val expirationSeconds: Int
) {

    @GetMapping("/callback")
    fun hello(request: HttpServletRequest, response: HttpServletResponse) {
        request.getParameter("code")?.let {
            //todo validate code by TOP platform
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