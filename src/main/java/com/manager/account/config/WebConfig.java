package com.manager.account.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("📂 Static resources served from:");
        System.out.println("🛠️ /uploads/** => file:/Users/xtech/Downloads/account-service/uploads/");
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/Users/xtech/Downloads/account-service/uploads/");
    }
}
