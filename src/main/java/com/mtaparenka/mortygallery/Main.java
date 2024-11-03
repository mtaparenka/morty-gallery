package com.mtaparenka.mortygallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        System.out.println(success);
    }

    @EventListener
    public void onSuccess(AbstractAuthenticationFailureEvent failure) {
        System.out.println(failure);
    }

    /*@Bean
    public CommandLineRunner runner(ImageService imageService) {
        return args -> {
            var images = imageService.getAllImages();
        };
    }*/
}
