package shop.topup.workspace.ui.common

import com.vaadin.flow.component.login.LoginI18n
import com.vaadin.flow.internal.JsonSerializer
import elemental.json.JsonFactory
import elemental.json.JsonValue
import elemental.json.impl.JreJsonFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import shop.topup.workspace.ui.views.LoginView

@Configuration
class I18nConfiguration {
    companion object {
        val JSON_FACTORY: JsonFactory = JreJsonFactory()
    }

    @Bean
    fun loginI18n(): LoginI18n {
        val jsonText = LoginView::class.java.getResource("/i18n/login.json")!!.readText()
        val jsonValue = JSON_FACTORY.parse<JsonValue>(jsonText)
        return JsonSerializer.toObject(LoginI18n::class.java, jsonValue)
    }
}