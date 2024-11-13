package shop.topup.workspace.demo

import com.github.database.rider.core.api.dataset.DataSet
import org.jooq.DSLContext
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import shop.topup.workspace.InfraBaseTest
import shop.topup.workspace.domain.common.jooq.Tables
import shop.topup.workspace.domain.common.jooq.tables.records.AccountRecord

/**
 * jooq dsl context test
 *
 * @author linux_china
 */
@DataSet("/db/dataset/accounts.xml")
class JooqDslTest : InfraBaseTest() {
    @Autowired
    lateinit var jooq: DSLContext

    @Test
    @Throws(Exception::class)
    fun testSelect() {
        val records = jooq.selectFrom(Tables.ACCOUNT).fetch()
        records.forEach { accountRecord: AccountRecord -> println(accountRecord.id) }
    }
}