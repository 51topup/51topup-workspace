package shop.topup.workspace.domain.common.dao

import ch.martinelli.oss.jooqspring.JooqDAO
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import shop.topup.workspace.domain.common.jooq.tables.SupplierGoods
import shop.topup.workspace.domain.common.jooq.tables.records.SupplierGoodsRecord

/**
 * Supplier Goods DAO
 *
 * @author linux_china
 */
@Repository
class GoodsDAO(@Autowired dslContext: DSLContext) :
    JooqDAO<SupplierGoods, SupplierGoodsRecord, Long>(dslContext, SupplierGoods.SUPPLIER_GOODS) {

    fun countAllGoods(): Int {
        return dslContext.selectFrom(SupplierGoods.SUPPLIER_GOODS).count()
    }

    fun findGoods(offset: Int, limit: Int): List<SupplierGoodsRecord> {
        return dslContext.selectFrom(SupplierGoods.SUPPLIER_GOODS)
            .offset(offset)
            .limit(limit)
            .fetch()
    }
}