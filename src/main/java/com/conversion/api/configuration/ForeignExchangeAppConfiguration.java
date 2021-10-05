package com.conversion.api.configuration;
import com.conversion.api.filters.RedirectFilter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

/**
 * The foreign exchange app configuration. Creates manageable beans that will be handled by the IoC container.
 */
@Configuration
public class ForeignExchangeAppConfiguration {

    /**
     * The rest template object that will be used when communicating with the third party currency provider.
     * @param   builder - the rest template builder.
     * @return  the rest template object.
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * The message source which will serve us in case of an error.
     * @return  the message source.
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * The factory used for validation. Configured to operate as per the fail_fast property.
     * @return  the validator factory.
     */
    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        bean.getValidationPropertyMap().put("hibernate.validator.fail_fast", "true");
        return bean;
    }

    /**
     * The bean representing filter registration.
     * @return the filter registration bean.
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        RedirectFilter redirectFilter = new RedirectFilter();
        filterRegistrationBean.setFilter(redirectFilter);
        return filterRegistrationBean;
    }
}
