package com.example.lovershopbackend.factory;

import com.example.lovershopbackend.common.enums.RegisterTypeEnum;
import com.example.lovershopbackend.strategy.register.AccountRegisterStrategy;
import com.example.lovershopbackend.strategy.register.RegisterStrategy;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Description 注册策略选择工厂
 */
@Component
public class RegisterStrategyFactory implements ApplicationContextAware {
    ApplicationContext applicationContext;

    public RegisterStrategy getStrategy(Integer type) {
        if (Objects.equals(type, RegisterTypeEnum.ACCOUNT_PASSWORD.getCode())) {
            return applicationContext.getBean(AccountRegisterStrategy.class);
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
