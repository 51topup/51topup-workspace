package shop.topup.workspace.domain.common.jooq.dao

import ch.martinelli.oss.jooqspring.JooqDAO
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import shop.topup.workspace.domain.common.jooq.tables.Account
import shop.topup.workspace.domain.common.jooq.tables.records.AccountRecord

/**
 * Account DAO
 *
 * @author linux_china
 */
@Repository
class AccountDAO(@Autowired dslContext: DSLContext) :
    JooqDAO<Account, AccountRecord, Long>(dslContext, Account.ACCOUNT) {
}