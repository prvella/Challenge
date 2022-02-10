package com.soda;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.soda.controller.CoinController;
import com.soda.utility.CoinStatusHolder;

@SpringBootApplication
@ComponentScan(basePackages = {"com.soda"}, basePackageClasses = {CoinController.class})
public class SodaProjectApplication {

  public static void main(String[] args) {
    SpringApplication.run(SodaProjectApplication.class, args);
  }

  @Bean
  CommandLineRunner init(CoinStatusHolder coinStatusHolder) {
    return args -> {
      coinStatusHolder.setStatus(Boolean.FALSE);
    };
  }

}
