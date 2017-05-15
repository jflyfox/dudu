package com.jflyfox.dudu.module.web.action;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {

    @RequestMapping("/")
    public ModelAndView root() {
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/admin");
        return view;
    }

    @RequestMapping("/index.htm")
    public String index() {
        return "welcome to dudu world!";
    }

    @RequestMapping("/test")
    public String test() {
        return "ok";
    }
}