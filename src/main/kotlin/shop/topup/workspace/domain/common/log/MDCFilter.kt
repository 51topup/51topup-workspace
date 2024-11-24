package shop.topup.workspace.domain.common.log

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import shop.topup.workspace.ui.security.AuthenticatedUser
import java.io.IOException

@Component
class MDCFilter(val authenticatedUser: AuthenticatedUser) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        authenticatedUser.get()?.let {
            MDC.put("user", it.nick)
        }
        getRemoteAddress(request)?.let {
            MDC.put("remote", it)
        }
        try {
            filterChain.doFilter(request, response)
        } finally {
            MDC.remove("user")
        }
    }

    fun getRemoteAddress(request: HttpServletRequest): String? {
        var ipAddress = request.getHeader("X-FORWARDED-FOR")
        if (ipAddress == null) {
            ipAddress = request.remoteAddr
        }
        return ipAddress
    }
}