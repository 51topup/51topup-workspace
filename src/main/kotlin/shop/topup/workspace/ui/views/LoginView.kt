package shop.topup.workspace.ui.views

import com.vaadin.flow.component.html.Anchor
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.login.LoginI18n
import com.vaadin.flow.component.login.LoginOverlay
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import com.vaadin.flow.router.internal.RouteUtil
import com.vaadin.flow.server.VaadinService
import com.vaadin.flow.server.auth.AnonymousAllowed
import com.vaadin.flow.theme.lumo.LumoUtility
import org.springframework.beans.factory.annotation.Autowired
import shop.topup.workspace.ui.security.AuthenticatedUser
import java.net.URLEncoder


@Route(value = "/login")
@PageTitle("Login")
@AnonymousAllowed
class LoginView(loginI18n: LoginI18n) : LoginOverlay(loginI18n), BeforeEnterObserver {
    @Autowired
    @Transient
    lateinit var authenticatedUser: AuthenticatedUser

    init {
        setTitle("机器猫自动充值管理平台")
        description = "一站式管理虚拟充值业务"
        action = RouteUtil.getRoutePath(VaadinService.getCurrent().context, javaClass)
        isForgotPasswordButtonVisible = false
        val div = Div()
        val link = Anchor(gooFishLoginUrl(), "闲鱼登录").apply {
            element.setAttribute("style", "font-size:18px")
        }
        div.add(link)
        div.addClassName(LumoUtility.TextAlignment.CENTER)
        footer.add(div)
        isOpened = true
    }

    override fun beforeEnter(beforeEnterEvent: BeforeEnterEvent) {
        if (authenticatedUser.get() != null) {
            // Already logged in
            isOpened = false
            beforeEnterEvent.forwardTo("/workspace")
        }

        isError = beforeEnterEvent.location.queryParameters.parameters.containsKey("error")
    }

    fun gooFishLoginUrl(): String {
        val redirectUrl = URLEncoder.encode("https://xianyu.51topup.com/callback", "UTF-8")
        val state = URLEncoder.encode("{\"origin\": \"main\"}", "UTF-8")
        val clientId = "34912212"
        return "https://oauth.taobao.com/authorize?response_type=code&client_id=${clientId}&redirect_uri=${redirectUrl}&state=${state}&view=web"
    }
}