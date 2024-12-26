package shop.topup.workspace.ui.views.suppplier

import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.karibudsl.v23.tab
import com.github.mvysny.karibudsl.v23.tabSheet
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import jakarta.annotation.security.RolesAllowed
import shop.topup.workspace.domain.common.dao.GoodsDAO
import shop.topup.workspace.domain.common.dao.SupplierDAO
import shop.topup.workspace.domain.common.jooq.tables.records.SupplierGoodsRecord
import shop.topup.workspace.ui.data.GoodsDataProvider
import shop.topup.workspace.ui.layout.WorkspaceLayout
import shop.topup.workspace.ui.security.Role

@RolesAllowed(Role.SELLER)
@Route(value = "/supplier/virtual-accounts", layout = WorkspaceLayout::class)
@PageTitle("货源商品")
class VirtualGoodsView(
    val supplierDAO: SupplierDAO,
    val goodsDAO: GoodsDAO
) : KComposite() {
    private lateinit var goodsGrid: Grid<SupplierGoodsRecord>
    private lateinit var goodsDataProvider: GoodsDataProvider
    private lateinit var basicIfo: Div

    // The main view UI definition
    private val root = ui {
        // Use custom CSS classes to apply styling. This is defined in styles.css.
        verticalLayout(classNames = "centered-content") {
            h2("货源商品")
            goodsDataProvider = GoodsDataProvider(supplierDAO, goodsDAO)
            goodsGrid = grid {
                addColumn { it.name }.setHeader("商品名称")
                addColumn { it.price }.setHeader("商品价格")
                addColumn { it.marketPrice }.setHeader("商品市场价")
                addColumn { it.stock }.setHeader("库存状态")
                addColumn { it.buyMinNum }.setHeader("起购数量")
                dataProvider = goodsDataProvider
            }
            tabSheet {
                width = "100%"
                tab("基本信息") {
                    verticalLayout {
                        basicIfo = div()
                    }
                }
            }
        }
    }

    init {
        // attach functionality to the UI components.
        // It's a good practice to keep UI functionality separated from UI definition.
        //basicIfo.html("<h2>标题</h2>")
        goodsGrid.addItemClickListener {
            val goods = it.item
            basicIfo.removeAll()
            basicIfo.html("<h2>${goods.name}</h2>")
        }
    }

}