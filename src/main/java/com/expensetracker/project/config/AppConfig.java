package com.expensetracker.project.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//need configuration annotation to indicate bean definitions reside here
@Configuration
public class AppConfig {
    //Creating a ModelMapper bean for dependency injection
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
