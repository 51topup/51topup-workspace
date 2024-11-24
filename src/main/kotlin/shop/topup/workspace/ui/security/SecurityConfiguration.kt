package shop.topup.workspace.ui.security

import com.vaadin.flow.spring.security.VaadinWebSecurity
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jose.jws.JwsAlgorithms
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import shop.topup.workspace.ui.views.LoginView
import java.util.*
import javax.crypto.spec.SecretKeySpec

@EnableWebSecurity
@Configuration
class SecurityConfiguration(
    @Value("\${jwt.auth.secret}") val jwtAuthSecret: String,
    @Value("\${jwt.issuer}") val jwtIssuer: String,
    @Value("\${jwt.expiration}") val expirationSeconds: Long
) : VaadinWebSecurity() {

    override fun configure(http: HttpSecurity) {
        http.authorizeHttpRequests { auth ->
            auth.requestMatchers(AntPathRequestMatcher("/assets/**")).permitAll()
            auth.requestMatchers(AntPathRequestMatcher("/goofish/**")).permitAll()
        }
        super.configure(http)
        // This is important to register your login view to the
        // navigation access control mechanism:
        setLoginView(http, LoginView::class.java)

        // https://vaadin.com/blog/jwt-authentication-with-vaadin-flow-for-better-developer-and-user-experience
        setStatelessAuthentication(
            http,
            SecretKeySpec(jwtAuthSecret.toByteArray(), JwsAlgorithms.HS256),
            jwtIssuer,
            expirationSeconds
        )
    }

    @Throws(Exception::class)
    public override fun configure(web: WebSecurity) {
        // Customize your WebSecurity configuration.
        super.configure(web)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}