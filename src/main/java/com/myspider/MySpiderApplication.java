package com.myspider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@PropertySource(value = {"file:${APP_HOME_CONF}/application.properties"})
@EnableCaching
@ComponentScan(value = {"com.myspider.component", "com.myspider"})
@EntityScan(basePackages = "com.myspider.entity")
@EnableJpaRepositories(basePackages = "com.myspider.repository")
@EnableFeignClients
@EnableTransactionManagement
public class MySpiderApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MySpiderApplication.class);
        application.run(args);
    }
}