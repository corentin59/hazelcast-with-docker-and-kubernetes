package com.azelart.vlille;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Bootstrap
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
public class Application {

    /**
     * This is the main method for Spring Bootstrap.
     * @param args are somes args.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
