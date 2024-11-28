package shop.topup.workspace.ui.views

import com.github.mvysny.karibudsl.v10.KComposite
import com.github.mvysny.karibudsl.v10.h2
import com.github.mvysny.karibudsl.v10.verticalLayout
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import jakarta.annotation.security.RolesAllowed
import shop.topup.workspace.ui.layout.WorkspaceLayout
import shop.topup.workspace.ui.security.Role

@RolesAllowed(Role.USER)
@Route(value = "/custom-service", layout = WorkspaceLayout::class)
@PageTitle("售后")
class CustomServiceView : KComposite() {
    private lateinit var nameField: TextField
    private lateinit var greetButton: Button

    // The main view UI definition
    private val root = ui {
        // Use custom CSS classes to apply styling. This is defined in styles.css.
        verticalLayout(classNames = "centered-content") {
            h2("售后服务")
        }
    }

    init {
        // attach functionality to the UI components.
        // It's a good practice to keep UI functionality separated from UI definition.
    }

}