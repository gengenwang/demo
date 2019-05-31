package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: IndexController.java, V 0.1 2019/5/31 下午1:45 wanggengen Exp $$
 **/
@RestController
public class IndexController {

    @RequestMapping("/hello")
    public ModelAndView index(){
        return new ModelAndView("/hello");
    }

}
