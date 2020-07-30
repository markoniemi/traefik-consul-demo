package org.traefik.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@Log4j2
@RequestMapping("/client")
@RefreshScope
public class DemoClientApplication {
    @Value("${runtime.environment:dev}")
    private String environment;
    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        new SpringApplicationBuilder(DemoClientApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

    @RequestMapping("/v1/hello")
    public String home() {
    	log.info(environment);
    	log.info("/v1/hello/");
        return this.restTemplate.getForObject("http://demo-service-v1/demo/v1/hello", String.class);
    }
    
    @Configuration
    public class DemoClientApplicationConfig {
        @LoadBalanced
        @Bean
        public RestTemplate loadbalancedRestTemplate() {
             return new RestTemplate();
        }        
    }
}
