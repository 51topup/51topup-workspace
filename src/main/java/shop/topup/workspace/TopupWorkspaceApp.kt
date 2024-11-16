package shop.topup.workspace

import com.vaadin.flow.component.page.AppShellConfigurator
import com.vaadin.flow.component.page.Push
import com.vaadin.flow.server.PWA
import com.vaadin.flow.theme.Theme
import com.vaadin.flow.theme.lumo.Lumo
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * The entry point of the Topup workspace application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@PWA(name = "机器猫工作平台", shortName = "机器猫")
@Theme(themeClass = Lumo::class)
@Push
class TopupWorkspaceApp : AppShellConfigurator

fun main(args: Array<String>) {
    SpringApplication.run(TopupWorkspaceApp::class.java, *args)
}
