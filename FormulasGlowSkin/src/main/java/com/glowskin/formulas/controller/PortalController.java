package com.glowskin.formulas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Raúl Gómez
 */

@Controller
@RequestMapping("/")
public class PortalController {

       @GetMapping("/")
    public String index() {

        return "index.html";
    }
}
