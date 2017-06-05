package com.wang.controller;

import com.wang.pojo.User;
import com.wang.service.IUserService;
import com.wang.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wang on 17-5-27.
 */

@Controller
@RequestMapping("/home")
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/myindex")
    public String index() {
        System.out.println("This is myindex");
        LOGGER.info("the first jsp pages");

        return "myindex";
    }

    @RequestMapping("/users")
    public String getUsers() {
        System.out.println("This is getUsers");
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/spring-context.xml");
//        IUserService userService = (IUserService) context.getBean("userService");


        User user = userService.get("1");
        System.out.println("This is user:" + user.toString());
        LOGGER.info(user.toString());
        return "myindex";
    }
}
