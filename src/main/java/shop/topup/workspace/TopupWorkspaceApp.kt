package shop.topup.workspace

import com.vaadin.flow.component.page.AppShellConfigurator
import com.vaadin.flow.server.PWA
import com.vaadin.flow.theme.Theme
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
@PWA(name = "Topup workspace", shortName = "51Topup")
@Theme("my-theme")
open class TopupWorkspaceApp : AppShellConfigurator

fun main(args: Array<String>) {
    SpringApplication.run(TopupWorkspaceApp::class.java, *args)
}
