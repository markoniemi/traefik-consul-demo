package example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import example.selenium.SeleniumConfig;
import example.user.config.ApplicationConfig;

@Configuration
@Import({ ApplicationConfig.class, SeleniumConfig.class })
public class IntegrationTestConfig {
}
