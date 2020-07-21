package org.traefik.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@Log4j2
@RequestMapping("/demo")
@RefreshScope 
public class DemoApplication {
    @Value("${runtime.environment}")
    private String environment;

    public static void main(String[] args) {
        new SpringApplicationBuilder(DemoApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

    @RequestMapping("/v1/hello")
    public String home() {
        log.info("/v1/hello");
        return "Hello world";
    }

    @RequestMapping("/v2/hello/{name}")
    public String home(@PathVariable("name") String name) {
        String response = "/v2/hello/" + name;
        log.info(response);
        log.info(environment);
        return response;
    }
}
