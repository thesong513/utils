package com.thesong.aop;

import com.thesong.aop.service.IUserService;
import com.thesong.aop.service.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @Author thesong
 * @Date 2020/12/10 11:07
 * @Version 1.0
 * @Describe
 */

public class ApplicationStarter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        IUserService userService = context.getBean("userServiceImpl", IUserService.class);
        System.out.println(userService.getUserId());
        System.out.println(userService.getUserName());
    }
}
