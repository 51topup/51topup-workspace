package shop.topup.workspace.ui.i18n

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*


class TranslationProviderTest {

    @Test
    fun testGetTranslation() {
        val translationProvider = TranslationProvider()
        val translation = translationProvider.getTranslation("Hello World", Locale.of("zh"))
        assertThat(translation).isEqualTo("你好，世界")
    }
}