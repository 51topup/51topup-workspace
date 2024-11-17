package shop.topup.workspace.ui.i18n

import com.vaadin.flow.i18n.I18NProvider
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.text.MessageFormat
import java.util.*

/**
 * All the texts are written in English.
 * This I18NProvider only translates messages when the Locale is not English.
 */
@Component
class TranslationProvider : I18NProvider {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(TranslationProvider::class.java)
    }

    override fun getProvidedLocales(): List<Locale> {
        return listOf(Locale.of("en"), Locale.of("zh"))
    }

    override fun getTranslation(key: String?, locale: Locale, vararg params: Any): String {
        if (key == null) {
            log.warn("Translation called with empty key!")
            return ""
        }

        if (locale.language == "en") {
            // This is the default language. The key is in English, so we don't need to translate it
            return if (params.isNotEmpty()) {
                MessageFormat(key, locale).format(params)
            } else {
                key
            }
        }

        val bundle = ResourceBundle.getBundle("i18n.messages", locale) ?: return key

        try {
            val value = bundle.getString(key)
            return if (params.isNotEmpty()) {
                MessageFormat(value, locale).format(params)
            } else {
                value
            }
        } catch (e: MissingResourceException) {
            log.warn("Missing translation for key {}", key)
            return "!" + locale.language + ": " + key
        }
    }


}
