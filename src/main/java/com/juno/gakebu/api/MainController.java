package com.juno.gakebu.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MainController {

    @GetMapping("/")
    public void Main(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }
}
