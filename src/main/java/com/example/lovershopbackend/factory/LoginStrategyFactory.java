package com.example.lovershopbackend.factory;

import com.example.lovershopbackend.common.enums.LoginTypeEnum;
import com.example.lovershopbackend.strategy.AccountLoginStrategy;
import com.example.lovershopbackend.strategy.LoginStrategy;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Description 登陆策略选择工厂
 */
@Component
public class LoginStrategyFactory implements ApplicationContextAware {
    ApplicationContext applicationContext;

    public LoginStrategy getStrategy(Integer type) {
        if (Objects.equals(type, LoginTypeEnum.ACCOUNT_PASSWORD.getCode())) {
            return applicationContext.getBean(AccountLoginStrategy.class);
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
