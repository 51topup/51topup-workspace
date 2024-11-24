package shop.topup.workspace.ui.layout

import com.github.mvysny.karibudsl.v10.horizontalLayout
import com.github.mvysny.karibudsl.v10.icon
import com.github.mvysny.kaributools.navigateTo
import com.github.mvysny.kaributools.tooltip
import com.vaadin.flow.component.Component
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.applayout.DrawerToggle
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.html.Footer
import com.vaadin.flow.component.html.Image
import com.vaadin.flow.component.html.Span
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.menubar.MenuBar
import com.vaadin.flow.component.menubar.MenuBarVariant
import com.vaadin.flow.component.orderedlayout.Scroller
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.sidenav.SideNav
import com.vaadin.flow.component.sidenav.SideNavItem
import com.vaadin.flow.server.auth.AccessAnnotationChecker
import shop.topup.workspace.ui.security.AuthenticatedUser
import shop.topup.workspace.ui.views.*
import shop.topup.workspace.ui.views.buyer.BlackListView
import shop.topup.workspace.ui.views.buyer.GrayListView
import shop.topup.workspace.ui.views.item.ItemTemplatesView
import shop.topup.workspace.ui.views.item.ItemsView
import shop.topup.workspace.ui.views.order.OrdersView
import shop.topup.workspace.ui.views.review.ReviewStrategyView
import shop.topup.workspace.ui.views.review.ReviewsView
import shop.topup.workspace.ui.views.suppplier.SupplierAccountsView
import shop.topup.workspace.ui.views.suppplier.SupplierOrdersView
import shop.topup.workspace.ui.views.suppplier.UploadGoodsToSaleView
import shop.topup.workspace.ui.views.suppplier.VirtualGoodsView

/**
 * workspace layout
 *
 * @author linux_china
 */
class WorkspaceLayout(
    @Transient val authenticatedUser: AuthenticatedUser,
    @Transient val accessChecker: AccessAnnotationChecker
) : AppLayout() {

    private lateinit var inboxCounter: Span

    companion object {
        fun getInstance(): WorkspaceLayout {
            return UI.getCurrent().children
                .filter { component: Component -> component.javaClass == WorkspaceLayout::class.java }.findFirst()
                .orElse(null) as WorkspaceLayout
        }
    }

    init {
        addHeaderContent()
        addDrawerContent()
    }

    private fun addHeaderContent() {
        val logo = Image("/assets/images/logo.png", "Topup workspace logo").apply {
            height = "44px"
        }
        addToNavbar(true, DrawerToggle(), logo)
        val menuBar = MenuBar().apply {
            style.set("margin-left", "auto")
            style.set("padding", "15px")
            addThemeVariants(MenuBarVariant.LUMO_DROPDOWN_INDICATORS)
        }
        val nick = authenticatedUser.get()?.nick ?: "User"
        menuBar.addItem(nick).apply {
            icon(VaadinIcon.USER)
            subMenu.apply {
                addItem("个人信息") {
                    navigateTo<ProfileView>()
                }.apply {
                    icon(VaadinIcon.USER_CARD)
                }
                addItem("退出登录") {
                    authenticatedUser.logout()
                    navigateTo<LoginView>()
                }.apply {
                    icon(VaadinIcon.SIGN_OUT)
                }
            }
        }
        addToNavbar(menuBar)
    }

    private fun addDrawerContent() {
        val scroller = Scroller(createNavigation())
        addToDrawer(scroller, createFooter())
    }

    private fun createNavigation(): Div {
        val div = Div().apply {
            //订单
            val ordersNav = SideNav().apply {
                label = "订单"
                addItem(
                    SideNavItem(
                        "订单列表",
                        OrdersView::class.java,
                        VaadinIcon.CASH.create()
                    )
                )
            }
            // 宝贝
            val itemsNav = SideNav().apply {
                label = "宝贝"
                addItem(
                    SideNavItem(
                        "商品",
                        ItemsView::class.java,
                        VaadinIcon.GRID_BIG_O.create()
                    )
                )
                addItem(
                    SideNavItem(
                        "模板",
                        ItemTemplatesView::class.java,
                        VaadinIcon.CLIPBOARD_TEXT.create()
                    )
                )
            }
            // 货源
            val supplierNav = SideNav().apply {
                label = "货源"
                addItem(
                    SideNavItem(
                        "货源商品",
                        VirtualGoodsView::class.java,
                        VaadinIcon.LIST_UL.create()
                    )
                )
                addItem(
                    SideNavItem(
                        "货源账号",
                        SupplierAccountsView::class.java,
                        VaadinIcon.USERS.create()
                    )
                )
                addItem(
                    SideNavItem(
                        "货源订单",
                        SupplierOrdersView::class.java,
                        VaadinIcon.BOOK_DOLLAR.create()
                    )
                )
                addItem(
                    SideNavItem(
                        "批量上货",
                        UploadGoodsToSaleView::class.java,
                        VaadinIcon.ARROW_CIRCLE_UP.create()
                    )
                )
            }
            // 买家
            val buyerNav = SideNav().apply {
                label = "宝贝"
                addItem(
                    SideNavItem(
                        "黑名单",
                        BlackListView::class.java,
                        VaadinIcon.LIST_OL.create()
                    )
                )
                addItem(
                    SideNavItem(
                        "灰名单",
                        GrayListView::class.java,
                        VaadinIcon.LIST_SELECT.create()
                    )
                )
            }
            // 评价
            val rateNav = SideNav().apply {
                label = "评价"
                addItem(
                    SideNavItem(
                        "自动评价策略",
                        ReviewStrategyView::class.java,
                        VaadinIcon.AUTOMATION.create()
                    )
                )
                addItem(
                    SideNavItem(
                        "批量评价",
                        ReviewsView::class.java,
                        VaadinIcon.COMMENT_ELLIPSIS.create()
                    )
                )
            }
            // 其他
            val nav = SideNav()
            nav.addItem(SideNavItem("店铺", ShopsView::class.java, VaadinIcon.SHOP.create()))
            nav.addItem(SideNavItem("售后", CustomServiceView::class.java, VaadinIcon.HEADSET.create()))
            nav.addItem(
                SideNavItem(
                    "站内信", InboxView::class.java,
                    VaadinIcon.ENVELOPE.create()
                ).apply {
                    suffixComponent = Span("12").apply {
                        element.themeList.add("badge contrast pill")
                        element.setAttribute(
                            "aria-label",
                            "12 unread messages"
                        )
                    }
                    inboxCounter = suffixComponent as Span
                }
            )
            val dashboardItem = SideNavItem("Dashboard", DashboardView::class.java, VaadinIcon.DASHBOARD.create())
            // wrapper with vertical layout
            val navWrapper = VerticalLayout(
                dashboardItem,
                ordersNav,
                itemsNav,
                supplierNav,
                buyerNav,
                rateNav,
                nav
            ).apply {
                isSpacing = true
                setSizeUndefined()
                itemsNav.setWidthFull()
                ordersNav.setWidthFull()
                supplierNav.setWidthFull()
                buyerNav.setWidthFull()
                rateNav.setWidthFull()
                nav.setWidthFull()
            }
            add(navWrapper)
        }

        return div
    }

    private fun createFooter(): Footer {
        return Footer().apply {
            add(horizontalLayout {
                add(SideNavItem("设置", OrdersView::class.java, VaadinIcon.COG_O.create()).apply {
                    tooltip = "设置"
                })
                add(SideNavItem("帮助", HelpView::class.java, VaadinIcon.HEADSET.create()).apply {
                    tooltip = "帮助"
                })
            })
        }
    }

    fun resetInboxCounter(number: String) {
        inboxCounter.text = number
    }

}
