package shop.topup.workspace.domain.common.jooq.dao

import com.github.database.rider.core.api.dataset.DataSet
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import shop.topup.workspace.TestcontainersBaseTest
import shop.topup.workspace.domain.common.account

@DataSet("/db/dataset/accounts.xml")
class AccountDAOTest : TestcontainersBaseTest() {
    @Autowired
    lateinit var accountDAO: AccountDAO

    @Test
    fun testFindById() {
        val account = accountDAO.findById(1L)
        println(account.get().id)
        println(1L.account?.id)
    }

    @Test
    fun testFindByPhone() {
        val account = accountDAO.findByPhone("18667135137")!!
        println(account.id)
    }

}