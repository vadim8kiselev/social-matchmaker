package com.kiselev.matchmaker.search.service.state;

import com.kiselev.matchmaker.search.service.implementation.GroupSearch;
import com.kiselev.matchmaker.search.service.implementation.PostSearch;
import com.kiselev.matchmaker.search.service.implementation.UserSearch;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class LoopFabric implements ApplicationContextAware {

    private ApplicationContext context;

    public UserSearch createUserSearch() {
        return context.getBean(UserSearch.class);
    }

    public PostSearch createPostSearch() {
        return context.getBean(PostSearch.class);
    }

    public GroupSearch createGroupSearch() {
        return context.getBean(GroupSearch.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
