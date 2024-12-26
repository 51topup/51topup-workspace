package shop.topup.workspace.domain.common.dao

import ch.martinelli.oss.jooqspring.JooqDAO
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import shop.topup.workspace.domain.common.jooq.tables.Account
import shop.topup.workspace.domain.common.jooq.tables.Supplier
import shop.topup.workspace.domain.common.jooq.tables.records.AccountRecord
import shop.topup.workspace.domain.common.jooq.tables.records.SupplierRecord

/**
 * Supplier DAO
 *
 * @author linux_china
 */
@Repository
class SupplierDAO(@Autowired dslContext: DSLContext) :
    JooqDAO<Supplier, SupplierRecord, Long>(dslContext, Supplier.SUPPLIER) {


}