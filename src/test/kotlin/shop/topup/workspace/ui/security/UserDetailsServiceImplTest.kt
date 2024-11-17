package shop.topup.workspace.ui.security

import com.github.database.rider.core.api.dataset.DataSet
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import shop.topup.workspace.TestcontainersBaseTest

@DataSet("/db/dataset/accounts.xml")
class UserDetailsServiceImplTest : TestcontainersBaseTest() {
    @Autowired
    lateinit var userDetailsService: UserDetailsServiceImpl

    @Test
    fun testLoadUserByUsername() {
        val userDetails = userDetailsService.loadUserByUsername("18667135137");
        println(userDetails.username)
    }
}