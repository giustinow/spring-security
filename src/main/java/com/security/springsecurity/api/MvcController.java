package com.security.springsecurity.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc")
public class MvcController {

    private static final Logger LOGGER = LogManager.getLogger(MvcController.class);

    @GetMapping("/home")
    public String index(){
        LOGGER.error("Entered in home get mapping");
        return "/templates/index.html";
    }

}
