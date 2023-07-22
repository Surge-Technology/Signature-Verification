package com.gen.signatureverification;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SignatureVerificationApplication {

    static Logger logger = LoggerFactory.getLogger(SignatureVerificationApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(SignatureVerificationApplication.class, args);

        logger.info("SignatureVerificationApplication Server Started...");
    }

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        return modelMapper;
    }


}

