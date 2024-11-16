package shop.topup.workspace.ui.views

import com.github.mvysny.karibudsl.v10.KComposite
import com.github.mvysny.karibudsl.v10.h2
import com.github.mvysny.karibudsl.v10.verticalLayout
import com.vaadin.flow.router.Route
import com.vaadin.flow.router.RouteAlias
import jakarta.annotation.security.RolesAllowed
import shop.topup.workspace.ui.layout.WorkspaceLayout
import shop.topup.workspace.ui.security.Role


@RolesAllowed(Role.SELLER)
@Route(value = "", layout = WorkspaceLayout::class)
@RouteAlias(value = "/workspace", layout = WorkspaceLayout::class)
class DashboardView : KComposite() {
    // The main view UI definition
    private val root = ui {
        // Use custom CSS classes to apply styling. This is defined in styles.css.
        verticalLayout(classNames = "centered-content") {
            h2("Dashboard")
        }
    }

    init {
        // attach functionality to the UI components.
        // It's a good practice to keep UI functionality separated from UI definition.

    }
}