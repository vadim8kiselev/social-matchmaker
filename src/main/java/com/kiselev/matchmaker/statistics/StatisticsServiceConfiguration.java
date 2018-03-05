package com.kiselev.matchmaker.statistics;

import com.kiselev.matchmaker.statistics.db.DBConfiguration;
import com.kiselev.matchmaker.statistics.elasticsearch.ElasticSearchService;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DBConfiguration.class)
public class StatisticsServiceConfiguration {

    @Value("${elasticsearch.hostname}")
    private String elasticSearchHostName;

    @Value("${elasticsearch.port}")
    private Integer elasticSearchHostPort;

    @Value("${elasticsearch.protocol}")
    private String elasticSearchProtocol;

    @Bean
    public ElasticSearchService elasticSearchService() {
        return new ElasticSearchService();
    }

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(elasticSearchHostName, elasticSearchHostPort, elasticSearchProtocol)));
    }
}
