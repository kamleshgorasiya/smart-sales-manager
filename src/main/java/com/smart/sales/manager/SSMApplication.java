package com.smart.sales.manager;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class SSMApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSMApplication.class, args);
    }

    @Bean
    public LocaleResolver getLocalResoResolver() {
    	SessionLocaleResolver localeResolver=new SessionLocaleResolver();
    	localeResolver.setDefaultLocale(Locale.US);
    	return localeResolver;
    }
    
    @Bean
    public ResourceBundleMessageSource messageSource() {
    	//ReloadableResourceBundleMessageSource messageSource =new ReloadableResourceBundleMessageSource();
    	ResourceBundleMessageSource messageSource =new ResourceBundleMessageSource();
    	//messageSource.setBasename("classpath:messages");
    	messageSource.setBasename("messages");
    	return messageSource;
    }
   
 
}
