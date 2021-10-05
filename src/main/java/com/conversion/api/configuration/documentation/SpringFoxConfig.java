package com.conversion.api.configuration.documentation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 *  Configuration providing the docket bean for SwaggerUI.
 */
@Configuration
public class SpringFoxConfig {

    /**
     * Constructs and exposes the Docket object which will be used by Swagger when generating the API docs.
     * @return  Docket - the docket object.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.conversion.api.controllers"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Constructs the REST API information that will be displayed in SwaggerUI.
     * @return  ApiInfo - the API information.
     */
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Foreign currency exchange")
                .license("Apache 2.0 License")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0")
                .build();
    }
}
