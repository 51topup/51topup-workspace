package shop.topup.workspace.ui.views

import com.vaadin.flow.component.login.LoginOverlay
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import com.vaadin.flow.router.internal.RouteUtil
import com.vaadin.flow.server.VaadinService
import com.vaadin.flow.server.auth.AnonymousAllowed
import org.springframework.beans.factory.annotation.Autowired
import shop.topup.workspace.ui.security.AuthenticatedUser


@Route(value = "/login")
@PageTitle("Login")
@AnonymousAllowed
class LoginView : LoginOverlay(), BeforeEnterObserver {
    @Autowired
    @Transient
    lateinit var authenticatedUser: AuthenticatedUser

    init {
        setTitle("机器猫自动充值管理平台")
        description = "一站式管理虚拟充值业务"
        action = RouteUtil.getRoutePath(VaadinService.getCurrent().context, javaClass)
        isForgotPasswordButtonVisible = false
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
}