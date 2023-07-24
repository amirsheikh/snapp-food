package com.example.snappfood.utils;


import com.example.snappfood.exceptions.PropertyNotFoundException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public String getProperty(String key) {
        String property = applicationContext.getEnvironment().getProperty(key);

        if (property == null)
            throw new PropertyNotFoundException("No property found with key {} ", key);

        return property;
    }

    public ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}