package shop.topup.workspace.ui.views

import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.setPrimary
import com.vaadin.flow.component.Key
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import jakarta.annotation.security.RolesAllowed
import shop.topup.workspace.ui.layout.WorkspaceLayout
import shop.topup.workspace.ui.security.Role

@RolesAllowed(Role.USER)
@Route(value = "/shops", layout = WorkspaceLayout::class)
@PageTitle("店铺管理")
class ShopsView : KComposite() {
    private lateinit var nameField: TextField
    private lateinit var greetButton: Button

    // The main view UI definition
    private val root = ui {
        // Use custom CSS classes to apply styling. This is defined in styles.css.
        verticalLayout(classNames = "centered-content") {

            // Use TextField for standard text input
            nameField = textField("Your name:") {
                addClassName("bordered")
            }

            // Use Button for a clickable button
            greetButton = button("Say main") {
                setPrimary()
                addClickShortcut(Key.ENTER)
            }
        }
    }

    init {
        // attach functionality to the UI components.
        // It's a good practice to keep UI functionality separated from UI definition.

        // Button click listeners can be defined as lambda expressions
        greetButton.onClick {
            Notification.show("Hello, ${nameField.value}")
        }
    }

}