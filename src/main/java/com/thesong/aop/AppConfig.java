package com.thesong.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author thesong
 * @Date 2020/12/10 11:19
 * @Version 1.0
 * @Describe
 */

@Configuration
@ComponentScan("com.thesong.aop")
@EnableAspectJAutoProxy
public class AppConfig {
}
