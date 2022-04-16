package ru.zenkov.skb_dz_6.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class HeadersController {

    @GetMapping("/headers")
    public String getAllHeaders(@RequestHeader Map<String, String> headers, Model model) {
        model.addAttribute("headers",headers);
        return "headers_page";
    }

}
