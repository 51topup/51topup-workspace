package shop.topup.workspace.ui.views

import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.navigateTo
import com.github.mvysny.kaributools.setPrimary
import com.vaadin.flow.component.Key
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.textfield.PasswordField
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.HasDynamicTitle
import com.vaadin.flow.router.Route

@Route(value = "/login")
class LoginView : KComposite(), HasDynamicTitle {
    private lateinit var nameField: TextField
    private lateinit var passwordField: PasswordField
    private lateinit var loginButton: Button

    // The main view UI definition
    private val root = ui {
        // Use custom CSS classes to apply styling. This is defined in styles.css.
        verticalLayout(classNames = "centered-content") {
            alignItems = FlexComponent.Alignment.CENTER;
            h2("机器猫登录")
            // Use TextField for standard text input
            nameField = textField("Your name:") {
                addClassName("bordered")
                require(true)
            }
            // Use TextField for standard text input
            passwordField = passwordField("Your password:") {
                addClassName("bordered")
                require(true)
            }
            // Use Button for a clickable button
            loginButton = button("Login") {
                setPrimary()
                addClickShortcut(Key.ENTER)
            }
        }
    }

    init {
        // attach functionality to the UI components.
        // It's a good practice to keep UI functionality separated from UI definition.
        loginButton.onClick {
            if (nameField.value.isEmpty() || passwordField.value.isEmpty()) {
                Notification.show("请输入用户名和密码！")
                return@onClick
            } else {
                navigateTo("/workspace")
            }
        }
    }

    override fun getPageTitle(): String {
        return "登录"
    }
}