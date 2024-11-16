package shop.topup.workspace.domain.common

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component
import shop.topup.workspace.domain.common.jooq.dao.AccountDAO
import shop.topup.workspace.domain.common.jooq.tables.records.AccountRecord

lateinit var appContext: ApplicationContext

@Component
class ExtensionsHook : ApplicationContextAware {
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        appContext = applicationContext
    }
}

val Long.account: AccountRecord?
    get() = appContext.getBean(AccountDAO::class.java).findById(this).orElse(null)