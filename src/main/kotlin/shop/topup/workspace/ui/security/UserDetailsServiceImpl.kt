package shop.topup.workspace.ui.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import shop.topup.workspace.domain.common.jooq.dao.AccountDAO
import shop.topup.workspace.domain.common.jooq.tables.records.AccountRecord

@Service
class UserDetailsServiceImpl : UserDetailsService {
    @Autowired
    lateinit var accountDAO: AccountDAO

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = if (username.matches(Regex("^[0-9]{11}$"))) {
            accountDAO.findByPhone(username)
                ?: throw UsernameNotFoundException("No user present with username: $username")
        } else {
            accountDAO.findByNick(username)
                ?: throw UsernameNotFoundException("No user present with username: $username")
        }
        return User(user.mobilePhone, user.hashedPassword, getAuthorities(user))
    }

    private fun getAuthorities(account: AccountRecord): List<SimpleGrantedAuthority> {
        return account.roles.split(",").map { role -> SimpleGrantedAuthority("ROLE_$role") }
    }

}