package com.thesong.aop.service.impl;

import com.thesong.aop.service.IUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author thesong
 * @Date 2020/12/10 11:11
 * @Version 1.0
 * @Describe
 */

@Service
public class UserServiceImpl implements IUserService {
    @Override
    public String getUserName() {
        return "thesong";
    }

    @Override
    public Long getUserId() {
        return 123L;
    }
}
