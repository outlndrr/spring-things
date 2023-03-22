package dev.outlndrr.abstractservice;

import dev.outldrr.abstractservice.AbstractJpaService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class JustService extends AbstractJpaService<JustEntity, Long> {
    public JustService(ApplicationContext context) {
        super(context, JustEntity.class);
    }
}
