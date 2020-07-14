package org.traefik.demo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DemoApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

    @RequestMapping("/health")
    public String health() {
        return "OK";
    }

    @RequestMapping("/hello")
    public String home() {
        return "Hello world";
    }

}
