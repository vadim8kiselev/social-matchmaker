package com.kiselev.matchmaker.view;

import com.kiselev.matchmaker.view.rest.RestViewConfiguration;
import com.kiselev.matchmaker.view.serialize.SerializeViewConfiguration;
import com.kiselev.matchmaker.view.web.WebViewConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RestViewConfiguration.class, SerializeViewConfiguration.class, WebViewConfiguration.class})
public class ViewConfiguration {
}
