package shop.topup.workspace.domain.common.dao

import com.github.database.rider.core.api.dataset.DataSet
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import shop.topup.workspace.TestcontainersBaseTest

@DataSet("/db/dataset/suppliers.xml")
class SupplierDAOTest : TestcontainersBaseTest() {
    @Autowired
    lateinit var goodsDAO: GoodsDAO

    @Test
    fun testGoodsCount() {
        println(goodsDAO.countAllGoods())
    }
}