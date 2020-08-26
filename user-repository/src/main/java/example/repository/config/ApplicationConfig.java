package example.repository.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "example")
@EnableWebMvc
@Import({ DiscoveryClientConfig.class, UserRepositoryConfig.class })
public class ApplicationConfig {
}