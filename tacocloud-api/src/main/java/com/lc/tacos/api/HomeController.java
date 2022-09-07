package com.lc.tacos.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author DELL
 * @date 2022/9/7 20:16
 */
@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "redirect:/";
    }
}
