package shop.topup.workspace.ui.layout

import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.applayout.DrawerToggle
import com.vaadin.flow.component.html.Footer
import com.vaadin.flow.component.html.H1
import com.vaadin.flow.component.html.Header
import com.vaadin.flow.component.html.Image
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.orderedlayout.Scroller
import com.vaadin.flow.component.sidenav.SideNav
import com.vaadin.flow.component.sidenav.SideNavItem
import com.vaadin.flow.server.auth.AccessAnnotationChecker
import com.vaadin.flow.theme.lumo.LumoUtility
import shop.topup.workspace.ui.security.AuthenticatedUser
import shop.topup.workspace.ui.views.ItemsView
import shop.topup.workspace.ui.views.OrdersView
import shop.topup.workspace.ui.views.ReviewsView
import shop.topup.workspace.ui.views.ShopsView

/**
 * workspace layout
 *
 * @author linux_china
 */
class WorkspaceLayout(authenticatedUser: AuthenticatedUser, accessChecker: AccessAnnotationChecker) : AppLayout() {

    init {
        addHeaderContent()
        addDrawerContent()
    }

    private fun addHeaderContent() {
        val logo = Image("/assets/images/logo.png", "Topup workspace logo")
        logo.height = "44px"
        addToNavbar(true, DrawerToggle(), logo)
    }

    private fun addDrawerContent() {
        val appName = H1("工作区")
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE)
        val header = Header(appName)
        val scroller = Scroller(createNavigation())
        addToDrawer(header, scroller, createFooter())
    }

    private fun createNavigation(): SideNav {
        val nav = SideNav()
        nav.addItem(
            SideNavItem(
                "商品管理",
                ItemsView::class.java,
                VaadinIcon.CUBES.create()
            )
        )
        nav.addItem(SideNavItem("交易管理", OrdersView::class.java, VaadinIcon.CASH.create()))
        nav.addItem(SideNavItem("服务商管理", OrdersView::class.java, VaadinIcon.ARCHIVES.create()))
        nav.addItem(SideNavItem("店铺管理", ShopsView::class.java, VaadinIcon.SHOP.create()))
        nav.addItem(SideNavItem("评价管理", ReviewsView::class.java, VaadinIcon.COMMENTS.create()))
        return nav
    }

    private fun createFooter(): Footer {
        val layout = Footer()

        val loginLink = SideNavItem("settings", OrdersView::class.java, VaadinIcon.COG_O.create())
        layout.add(loginLink)

        return layout
    }
}