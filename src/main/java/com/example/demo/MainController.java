package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/main")
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public String test(){
        return "index";
    }


}
