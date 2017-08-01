package org.robsoft.rabbitmq.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rest {

    @RequestMapping(method = RequestMethod.GET)
    public String hello(){

        return "Hello";
    }

}
