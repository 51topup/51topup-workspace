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
class SecurityConfiguration : VaadinWebSecurity() {
    @Value("\${jwt.auth.secret}")
    lateinit var jwtAuthSecret: String

    override fun configure(http: HttpSecurity) {
        http.authorizeHttpRequests { auth ->
            auth.requestMatchers(AntPathRequestMatcher("/assets/**")).permitAll()
        }
        super.configure(http)
        // This is important to register your login view to the
        // navigation access control mechanism:
        setLoginView(http, LoginView::class.java)

        // https://vaadin.com/blog/jwt-authentication-with-vaadin-flow-for-better-developer-and-user-experience
        setStatelessAuthentication(
            http,
            SecretKeySpec(jwtAuthSecret.toByteArray(), JwsAlgorithms.HS256),
            "shop.51topup",  604800 // 7 days : 7 * 24 * 60 * 60
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