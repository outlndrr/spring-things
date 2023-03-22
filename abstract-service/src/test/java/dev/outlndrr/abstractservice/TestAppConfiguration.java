package dev.outlndrr.abstractservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestAppConfiguration {

    @Autowired
    private ApplicationContext context;

    @Bean
    public JustService justService() {
        return new JustService(context);
    }
}
