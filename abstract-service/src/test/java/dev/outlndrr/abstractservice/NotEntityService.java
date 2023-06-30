package dev.outlndrr.abstractservice;

import dev.outldrr.abstractservice.AbstractJpaService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class NotEntityService extends AbstractJpaService<NotEntity, Long> {
    public NotEntityService(ApplicationContext context) {
        super(context, NotEntity.class);
    }
}
