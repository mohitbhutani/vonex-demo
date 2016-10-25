package demo.vonex.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mohit bhutani on 25/10/16.
 */
@RequestMapping("/api")
@Controller
public class ApiController {

    @RequestMapping("/rest")
    @ResponseBody
    public String rest() {
        return "Welcome in Rest controller";
    }
}
