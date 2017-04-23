package com.vitrenko.doyouknowthis.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.vitrenko.doyouknowthis.domain")
@Import({RepositoryConfig.class})
public class RootConfig {
}
