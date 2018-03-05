package com.kiselev.matchmaker.statistics.elasticsearch;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;
import com.kiselev.matchmaker.statistics.StatisticsService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.List;

public class ElasticSearchService implements StatisticsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchService.class);

    @Autowired
    private RestHighLevelClient client;

    @Value("${elasticsearch.bulk.max-size}")
    private Integer BULK_MAX_SIZE;

    @Override
    public void postUsers(List<User> users) {
        try {
            List<List<User>> lists = Lists.partition(users, BULK_MAX_SIZE);

            for (List<User> list : lists) {
                BulkRequest bulkRequest = new BulkRequest();

                for (User user : list) {
                    IndexRequest indexRequest = new IndexRequest("users", "doc", user.getId())
                            .source(XContentFactory.jsonBuilder()
                                    .startObject()
                                    .field("user_id", user.getId())
                                    .field("user_first_name", user.getFirstName())
                                    .field("user_last_name", user.getLastName())
                                    .field("user_screen_name", user.getScreenName())
                                    .field("user_sex", user.getSex())
                                    .field("user_online", user.getOnline())
                                    .field("user_birthday", user.getBirthday())
                                    .field("user_city", user.getCity())
                                    .field("user_country", user.getCountry())
                                    .field("user_mobile_phone", user.getMobilePhone())
                                    .field("user_home_phone", user.getHomePhone())
                                    .field("user_skype", user.getSkype())
                                    .field("user_facebook", user.getFacebook())
                                    .field("user_twitter", user.getTwitter())
                                    .field("user_livejournal", user.getLivejournal())
                                    .field("user_instagram", user.getInstagram())
                                    .field("user_status", user.getStatus())
                                    .field("user_last_seen", user.getLastSeen())
                                    .field("user_career", user.getCareer())
                                    .field("user_military", user.getMilitary())
                                    .field("user_university", user.getUniversity())
                                    .field("user_home_town", user.getHomeTown())
                                    .field("user_photo_link", user.getPhotoLink())
                                    .field("user_relation", user.getRelation())
                                    .field("user_relation_partner", user.getRelationPartner())
                                    .field("user_interests", user.getInterests())
                                    .field("user_music", user.getMusic())
                                    .field("user_activities", user.getActivities())
                                    .field("user_movies", user.getMovies())
                                    .field("user_tv", user.getTv())
                                    .field("user_books", user.getBooks())
                                    .field("user_games", user.getGames())
                                    .field("user_schools", user.getSchools())
                                    .field("user_about", user.getAbout())
                                    .field("user_quotes", user.getQuotes())
                                    .field("user_political", user.getPolitical())
                                    .field("user_languages", user.getLanguages())
                                    .field("user_religion", user.getReligion())
                                    .field("user_inspired_by", user.getInspiredBy())
                                    .field("user_people_main", user.getPeopleMain())
                                    .field("user_life_main", user.getLifeMain())
                                    .field("user_smoking", user.getSmoking())
                                    .field("user_alcohol", user.getAlcohol())
                                    .field("user_deactivated", user.getDeactivated())
                                    .endObject());

                    bulkRequest.add(indexRequest);
                }

                if (!bulkRequest.requests().isEmpty()) {
                    client.bulk(bulkRequest);
                }
            }
            LOGGER.info("[ELASTIC SEARCH] User data posted to statistics service");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postPosts(List<Post> posts) {
        try {
            List<List<Post>> lists = Lists.partition(posts, BULK_MAX_SIZE);

            for (List<Post> list : lists) {
                BulkRequest bulkRequest = new BulkRequest();

                for (Post post : list) {
                    IndexRequest indexRequest = new IndexRequest("posts", "doc", post.getId())
                            .source(XContentFactory.jsonBuilder()
                                    .startObject()
                                    .field("post_id", post.getId())
                                    .field("post_from_id", post.getFromId())
                                    .field("post_owner_id", post.getOwnerId())
                                    .field("post_text", post.getText())
                                    .field("post_date", post.getDate())
                                    .field("post_likes_count", post.getLikesCount())
                                    .field("post_shares_count", post.getSharesCount())
                                    .field("post_views_count", post.getViewsCount())
                                    .field("post_comments_count", post.getCommentsCount())
                                    .field("post_geo", post.getGeo())
                                    .field("post_attachments", post.getAttachments())
                                    .field("post_type", post.getType())
                                    .field("post_platform", post.getPlatform())
                                    .endObject());

                    bulkRequest.add(indexRequest);
                }

                if (!bulkRequest.requests().isEmpty()) {
                    client.bulk(bulkRequest);
                }
            }
            LOGGER.info("[ELASTIC SEARCH] Post data posted to statistics service");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postGroups(List<Group> groups) {
        try {
            List<List<Group>> lists = Lists.partition(groups, BULK_MAX_SIZE);

            for (List<Group> list : lists) {
                BulkRequest bulkRequest = new BulkRequest();

                for (Group group : list) {
                    IndexRequest indexRequest = new IndexRequest("groups", "doc", group.getId())
                            .source(XContentFactory.jsonBuilder()
                                    .startObject()
                                    .field("group_id", group.getId())
                                    .field("group_name", group.getName())
                                    .field("group_screen_name", group.getScreenName())
                                    .field("group_status", group.getStatus())
                                    .field("group_description", group.getDescription())
                                    .field("group_type", group.getType())
                                    .field("group_verified", group.getVerified())
                                    .field("group_is_closed", group.getIsClosed())
                                    .field("group_age_limits", group.getAgeLimits())
                                    .field("group_subscribers_count", group.getSubscribersCount())
                                    .field("group_city", group.getCity())
                                    .field("group_country", group.getCountry())
                                    .field("group_photo_link", group.getPhotoLink())
                                    .field("group_links", group.getLinks())
                                    .field("group_contacts", group.getContacts())
                                    .field("group_site", group.getSite())
                                    .field("group_deactivated", group.getDeactivated())
                                    .endObject());

                    bulkRequest.add(indexRequest);
                }

                if (!bulkRequest.requests().isEmpty()) {
                    client.bulk(bulkRequest);
                }
            }
            LOGGER.info("[ELASTIC SEARCH] Group data posted to statistics service");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
