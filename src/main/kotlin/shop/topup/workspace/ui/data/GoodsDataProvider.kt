package shop.topup.workspace.ui.data

import com.vaadin.flow.data.provider.AbstractBackEndDataProvider
import com.vaadin.flow.data.provider.Query
import shop.topup.workspace.domain.common.dao.GoodsDAO
import shop.topup.workspace.domain.common.dao.SupplierDAO
import shop.topup.workspace.domain.common.jooq.tables.records.SupplierGoodsRecord
import java.util.stream.Stream

class GoodsDataProvider(
    val supplierDAO: SupplierDAO,
    val goodsDAO: GoodsDAO
) : AbstractBackEndDataProvider<SupplierGoodsRecord, Void>() {

    override fun fetchFromBackEnd(query: Query<SupplierGoodsRecord?, Void?>): Stream<SupplierGoodsRecord>? {
        query.page
        return goodsDAO.findGoods(query.offset, query.limit).stream()
    }

    override fun sizeInBackEnd(query: Query<SupplierGoodsRecord?, Void?>): Int {
        return goodsDAO.countAllGoods()
    }
}