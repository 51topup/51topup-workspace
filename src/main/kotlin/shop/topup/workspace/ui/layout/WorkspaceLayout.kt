package shop.topup.workspace.ui.layout

import com.github.mvysny.karibudsl.v10.icon
import com.github.mvysny.kaributools.tooltip
import com.vaadin.flow.component.Component
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.applayout.DrawerToggle
import com.vaadin.flow.component.html.*
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.menubar.MenuBar
import com.vaadin.flow.component.menubar.MenuBarVariant
import com.vaadin.flow.component.orderedlayout.Scroller
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.sidenav.SideNav
import com.vaadin.flow.component.sidenav.SideNavItem
import com.vaadin.flow.server.auth.AccessAnnotationChecker
import com.vaadin.flow.theme.lumo.LumoUtility
import shop.topup.workspace.ui.security.AuthenticatedUser
import shop.topup.workspace.ui.views.*

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
                    UI.getCurrent().navigate(ProfileView::class.java)
                }.apply {
                    icon(VaadinIcon.USER_CARD)
                }
                addItem("退出登录") {
                    authenticatedUser.logout()
                    UI.getCurrent().navigate(LoginView::class.java)
                }.apply {
                    icon(VaadinIcon.SIGN_OUT)
                }
            }
        }
        addToNavbar(menuBar)
    }

    private fun addDrawerContent() {
        val appName = H1("工作区").apply {
            addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE)
        }
        val header = Header(appName)
        val scroller = Scroller(createNavigation())
        addToDrawer(header, scroller, createFooter())
    }

    private fun createNavigation(): Div {
        val div = Div().apply {
            // 商品
            val itemsNav = SideNav().apply {
                label = "商品列表"
                addItem(
                    SideNavItem(
                        "在售商品",
                        ItemsView::class.java,
                        VaadinIcon.CUBES.create()
                    )
                )
                addItem(
                    SideNavItem(
                        "下架商品",
                        ItemsView::class.java,
                        VaadinIcon.CUBES.create()
                    )
                )
            }
            //订单
            val ordersNav = SideNav().apply {
                label = "交易管理"
                addItem(
                    SideNavItem(
                        "订单列表",
                        OrdersView::class.java,
                        VaadinIcon.CASH.create()
                    )
                )
                addItem(
                    SideNavItem(
                        "评价管理",
                        ReviewsView::class.java,
                        VaadinIcon.COMMENTS.create()
                    )
                )
            }
            // 其他
            val nav = SideNav()
            //nav.addItem(SideNavItem("服务商管理", OrdersView::class.java, VaadinIcon.ARCHIVES.create()))
            nav.addItem(SideNavItem("店铺管理", ShopsView::class.java, VaadinIcon.SHOP.create()))
            nav.addItem(
                SideNavItem(
                    "站内信", InboxView::class.java,
                    VaadinIcon.ENVELOPE.create()
                ).apply {
                    suffixComponent = Span("12").apply {
                        getElement().themeList.add("badge contrast pill")
                        getElement().setAttribute(
                            "aria-label",
                            "12 unread messages"
                        )
                    }
                    inboxCounter = suffixComponent as Span
                }
            )

            // wrapper with vertical layout
            val navWrapper = VerticalLayout(itemsNav, ordersNav, nav).apply {
                isSpacing = true
                setSizeUndefined()
                itemsNav.setWidthFull()
                ordersNav.setWidthFull()
                nav.setWidthFull()
            }
            add(navWrapper)
        }

        return div
    }

    private fun createFooter(): Footer {
        return Footer().apply {
            add(SideNavItem(null, OrdersView::class.java, VaadinIcon.COG_O.create()).apply {
                tooltip = "设置"
            })
        }
    }

    fun resetInboxCounter(number: String) {
        inboxCounter.text = number
    }

}
