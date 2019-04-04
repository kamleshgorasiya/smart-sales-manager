package com.smart.sales.manager;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.smart.sales.manager.request.model.Constants;

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
   
    public static PageRequest getPageRequest(int page, String order, String field) {
    	Sort sort = null;
		if (order.equalsIgnoreCase("DESC")) {
			sort = new Sort(Direction.DESC, field);
		} else {
			sort = new Sort(Direction.ASC, field);
		}
		PageRequest request = PageRequest.of(page - 1, Constants.PAGESIZE, sort);
		return request;
    }
 
}
