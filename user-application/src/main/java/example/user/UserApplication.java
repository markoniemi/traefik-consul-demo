package example.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import example.user.config.ApplicationConfig;

@SpringBootApplication
public class UserApplication extends SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfig.class, args);
    }
}
