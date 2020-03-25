/**
 * @program: test4allproject
 * @description:
 * @author: KaiTao.wu
 * @create: 2018-10-23 10:46
 **/
package com.example.controller;


import com.example.demo.DemoApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThisWillActuallyRun   {

    @RequestMapping("/hello")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/exception")
    String exception() {

        throw new RuntimeException("test");

    }

}
