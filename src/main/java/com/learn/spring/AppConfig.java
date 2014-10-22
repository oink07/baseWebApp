/**
 * 
 */
package com.learn.spring;

import java.util.Arrays;

import javax.ws.rs.ext.RuntimeDelegate;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learn.rest.JaxRsApiApplication;
import com.learn.rest.PeopleRestService;
import com.learn.services.PeopleService;

/**
 * @author HY
 *
 */
@Configuration
public class AppConfig {
	@Bean( destroyMethod = "shutdown" )
    public SpringBus cxf() {
        return new SpringBus();
    }
 
    @Bean
    public Server jaxRsServer() {
        JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance().createEndpoint( jaxRsApiApplication(), JAXRSServerFactoryBean.class );
        factory.setServiceBeans( Arrays.< Object >asList( peopleRestService() ) );
        factory.setAddress( factory.getAddress() );
        factory.setProviders( Arrays.< Object >asList( jsonProvider() ) );
        return factory.create();
    }
 
    @Bean 
    public JaxRsApiApplication jaxRsApiApplication() {
        return new JaxRsApiApplication();
    }
 
    @Bean 
    public PeopleRestService peopleRestService() {
        return new PeopleRestService();
    }
 
    @Bean 
    public PeopleService peopleService() {
        return new PeopleService();
    }
  
    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }
}
