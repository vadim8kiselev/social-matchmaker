package com.kiselev.matchmaker.view.serialize;

import com.kiselev.matchmaker.view.serialize.implementation.csv.CSVSerializeView;
import com.kiselev.matchmaker.view.serialize.implementation.excel.ExcelSerializeView;
import com.kiselev.matchmaker.view.serialize.implementation.json.JSONSerializeView;
import com.kiselev.matchmaker.view.serialize.implementation.self.DefaultSerializeView;
import com.kiselev.matchmaker.view.serialize.implementation.xml.XMLSerializeView;
import com.kiselev.matchmaker.view.serialize.resolver.SerializeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SerializeViewConfiguration {

    @Bean
    public CSVSerializeView csvSerializeView() {
        return new CSVSerializeView();
    }

    @Bean
    public ExcelSerializeView excelSerializeView() {
        return new ExcelSerializeView();
    }

    @Bean
    public JSONSerializeView jsonSerializeView() {
        return new JSONSerializeView();
    }

    @Bean
    public XMLSerializeView xmlSerializeView() {
        return new XMLSerializeView();
    }

    @Bean
    public DefaultSerializeView defaultSerializeView() {
        return new DefaultSerializeView();
    }

    @Bean
    public SerializeResolver serializeResolver() {
        return new SerializeResolver();
    }
}
