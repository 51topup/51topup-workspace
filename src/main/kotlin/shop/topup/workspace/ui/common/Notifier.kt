package shop.topup.workspace.ui.common

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.html.NativeLabel
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.notification.NotificationVariant
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.theme.lumo.LumoUtility

object Notifier : Notification() {
    private fun readResolve(): Any = Notifier
    private const val DURATION: Int = 3000

    fun info(message: String) {
        showNotification(message)
    }

    fun success(message: String) {
        val notification = showNotification(message)
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS)
    }

    fun warn(message: String) {
        val notification = showNotification(message)
        notification.addThemeVariants(NotificationVariant.LUMO_WARNING)
    }

    fun error(message: String?) {
        val text = NativeLabel(message)
        val close = Button("OK")

        val content = HorizontalLayout(text, close)
        content.addClassName(LumoUtility.AlignItems.CENTER)

        val notification = Notification(content)
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR)
        notification.position = Position.TOP_END

        close.addClickListener { notification.close() }
        notification.open()
        close.focus()
    }

    private fun showNotification(message: String): Notification {
        return show(message, DURATION, Position.TOP_END)
    }
}
