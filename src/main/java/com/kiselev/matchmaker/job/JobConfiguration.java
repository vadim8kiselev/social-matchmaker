package com.kiselev.matchmaker.job;

import com.kiselev.matchmaker.job.implementation.ClearOldSerializedFileJob;
import com.kiselev.matchmaker.job.implementation.PushDataToElasticSearchJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {

    @Bean
    public ClearOldSerializedFileJob clearOldSerializedFileJob() {
        return new ClearOldSerializedFileJob();
    }

    @Bean
    public PushDataToElasticSearchJob pushDataToElasticSearchJob() {
        return new PushDataToElasticSearchJob();
    }
}
