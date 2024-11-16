package shop.topup.workspace.ui.security

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import shop.topup.workspace.domain.common.account
import shop.topup.workspace.domain.common.jooq.tables.records.AccountRecord

@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = username.account ?: throw UsernameNotFoundException("No user present with username: $username")
        return User(user.mobilePhone, user.hashedPassword, getAuthorities(user))
    }

    private fun getAuthorities(account: AccountRecord): List<SimpleGrantedAuthority> {
        return account.roles.split(",").map { role -> SimpleGrantedAuthority("ROLE_$role") }
    }

}