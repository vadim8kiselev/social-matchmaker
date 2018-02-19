package com.kiselev.matchmaker.search;

import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;
import com.kiselev.matchmaker.search.cache.SearchCacheConfiguration;
import com.kiselev.matchmaker.search.condition.SearchConditionConfiguration;
import com.kiselev.matchmaker.search.condition.applier.ConditionApplier;
import com.kiselev.matchmaker.search.service.Search;
import com.kiselev.matchmaker.search.service.target.FromSearch;
import com.kiselev.matchmaker.search.service.target.general.passive.PassiveGeneralGroupSearch;
import com.kiselev.matchmaker.search.service.target.general.passive.PassiveGeneralPostSearch;
import com.kiselev.matchmaker.search.service.target.general.passive.PassiveGeneralUserSearch;
import com.kiselev.matchmaker.search.service.target.implementation.GroupSearch;
import com.kiselev.matchmaker.search.service.target.implementation.PostSearch;
import com.kiselev.matchmaker.search.service.target.implementation.UserSearch;
import org.springframework.context.annotation.*;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
@Configuration
@Import({SearchCacheConfiguration.class, SearchConditionConfiguration.class})
public class SearchConfiguration {

    @Bean
    @Scope(scopeName = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Search search(PassiveGeneralUserSearch userSearch, PassiveGeneralPostSearch postSearch, PassiveGeneralGroupSearch groupSearch) {
        FromSearch search = new FromSearch();
        search.setUserSearch(userSearch);
        search.setPostSearch(postSearch);
        search.setGroupSearch(groupSearch);

        userSearch.setPostSearch(postSearch);
        userSearch.setGroupSearch(groupSearch);

        postSearch.setUserSearch(userSearch);

        groupSearch.setUserSearch(userSearch);
        groupSearch.setPostSearch(postSearch);

        return search;
    }

    @Bean
    @Scope("prototype")
    public UserSearch userSearch(SocialNetworkAPI socialNetworkAPI, ConditionApplier<User> conditionApplier) {
        UserSearch userSearch = new UserSearch();
        userSearch.setSocialNetworkAPI(socialNetworkAPI);
        userSearch.setUserConditionApplier(conditionApplier);
        return userSearch;
    }

    @Bean
    @Scope("prototype")
    public PostSearch postSearch(SocialNetworkAPI socialNetworkAPI, ConditionApplier<Post> conditionApplier) {
        PostSearch postSearch = new PostSearch();
        postSearch.setSocialNetworkAPI(socialNetworkAPI);
        postSearch.setPostConditionApplier(conditionApplier);
        return postSearch;
    }

    @Bean
    @Scope("prototype")
    public GroupSearch groupSearch(SocialNetworkAPI socialNetworkAPI, ConditionApplier<Group> conditionApplier) {
        GroupSearch groupSearch = new GroupSearch();
        groupSearch.setSocialNetworkAPI(socialNetworkAPI);
        groupSearch.setGroupConditionApplier(conditionApplier);
        return groupSearch;
    }
}
