package com.kiselev.matchmaker.search.service.state;

import com.kiselev.matchmaker.search.Search;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class LoopFabric implements ApplicationContextAware {

    private ApplicationContext context;

    public Search createSearch() {
        return context.getBean(Search.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
