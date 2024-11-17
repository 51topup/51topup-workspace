package shop.topup.workspace.domain.common

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component
import shop.topup.workspace.domain.common.dao.AccountDAO
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


val String.account: AccountRecord?
    get() {
        return if (this.contains('@')) {
            appContext.getBean(AccountDAO::class.java).findByEmail(this)
        } else if (this.matches(Regex("^[0-9]{11}$"))) {
            appContext.getBean(AccountDAO::class.java).findByPhone(this)
        } else {
            appContext.getBean(AccountDAO::class.java).findByNick(this)
        }
    }

