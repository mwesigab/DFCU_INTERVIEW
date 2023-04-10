package com.ben.interview.helpers;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/loans.*"), regex("/customers.*"),regex("/login"),regex("/logger.*"),regex("/logs"),regex("/logout"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("DFCU BANK API")
                .description("""
                        Overview
                        
                        Over the past few decades, the technology world has witnessed the rise of Application Programming Interfaces (APIs) for enabling communication between different software systems/applications. These systems are typically independent, often with little commonality other than the interaction facilitated by the API. APIs enable systems to access services and information from one another and they have underpinned the rise of distributed software systems.
                        In the financial services industry, APIs power numerous services spanning customer-facing applications to regulatory compliance systems.
                        
                        This API will be consumed by the bank’s online banking platform and will be expected to provide information about a customer’s loan status.
                        """)
                .contact("mwesigab@gmail.com").license("DFCU Bank License")
                .licenseUrl("mwesigab@gmail.com").version("1.0").build();
    }

}
