package shop.topup.workspace.ui.security

import com.vaadin.flow.spring.security.AuthenticationContext
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component
import shop.topup.workspace.domain.common.account
import shop.topup.workspace.domain.common.jooq.tables.records.AccountRecord

@Component
class AuthenticatedUser(val authenticationContext: AuthenticationContext) {

    fun get(): AccountRecord? {
        val jwt = authenticationContext.getAuthenticatedUser(Jwt::class.java)
        return if (jwt.isPresent) {
            (jwt.get().claims["sub"] as String).account
        } else null
    }

    fun logout() {
        authenticationContext.logout()
    }
}