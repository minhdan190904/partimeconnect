package com.epu.partimeconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class PartimeconnectApplication {
    private static final Logger logger = LoggerFactory.getLogger(PartimeconnectApplication.class);

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    @Value("${spring.servlet.multipart.max-request-size}")
    private String maxRequestSize;

    @EventListener(ApplicationReadyEvent.class)
    public void logMultipartLimits() {
        logger.info("Max file size: {}", maxFileSize);
        logger.info("Max request size: {}", maxRequestSize);
    }

    public static void main(String[] args) {
        SpringApplication.run(PartimeconnectApplication.class, args);
    }

}
