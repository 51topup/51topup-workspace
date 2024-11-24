package shop.topup.workspace.ui.controller

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/goofish")
class GooFishController {

    @GetMapping("/callback")
    fun hello(request: HttpServletRequest, response: HttpServletResponse): String {
        request.getParameter("code")?.let {
            //response.sendRedirect("/workspace")
        }
        //response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid code")
        return "callback"
    }
}