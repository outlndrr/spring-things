package dev.outlndrr.abstractservice;

import dev.outldrr.abstractservice.AbstractJpaService;
import dev.outldrr.abstractservice.TestJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestAppConfiguration {

    @Autowired
    private ApplicationContext context;

    @Bean
    public AbstractJpaService abstractJpaService() {
        return new TestJpaService(context);
    }
}
