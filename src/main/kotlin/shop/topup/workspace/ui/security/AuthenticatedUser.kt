package shop.topup.workspace.ui.security

import com.vaadin.flow.spring.security.AuthenticationContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component
import shop.topup.workspace.domain.common.jooq.dao.AccountDAO
import shop.topup.workspace.domain.common.jooq.tables.records.AccountRecord

@Component
class AuthenticatedUser {
    @Autowired
    lateinit var authenticationContext: AuthenticationContext

    @Autowired
    lateinit var accountDAO: AccountDAO

    fun get(): AccountRecord? {
        val jwt = authenticationContext.getAuthenticatedUser(Jwt::class.java)
        return if (jwt.isPresent) {
            accountDAO.findByNick(jwt.get().subject)
        } else null
    }

    fun logout() {
        authenticationContext.logout()
    }
}