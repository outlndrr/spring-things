package dev.outlndrr.abstractservice;

import dev.outldrr.abstractservice.AbstractJpaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource(locations = "classpath:application.yml")
@DataJpaTest
@Import(TestAppConfiguration.class)
public class AbstractJpaServiceTest {

    @Autowired
    private JustEntityRepo justEntityRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AbstractJpaService abstractJpaService;

    @BeforeEach
    public void insertData() {
        JustEntity justEntity1 = new JustEntity(1L, "Name1");
        JustEntity justEntity2 = new JustEntity(2L, "Name2");

        entityManager.persistAndFlush(justEntity1);
        entityManager.persistAndFlush(justEntity2);
    }

    @Test
    public void findAllMethod_shouldNotBeNull_sameResults() {
        List<JustEntity> repoEntities = justEntityRepo.findAll();
        List<JustEntity> serviceEntities = abstractJpaService.findAll(JustEntity.class);

        assertNotNull(serviceEntities);
        assertEquals(repoEntities, serviceEntities);
        assertEquals(repoEntities.size(), serviceEntities.size());
    }

    @Test
    public void customQuery_shouldNotBeNullAndEquals() {
        JustEntity repoEntity = justEntityRepo.findById(1L).orElse(null);
        JustEntity serviceEntity = abstractJpaService.findById(JustEntity.class, 1L).orElse(null);

        assertNotNull(repoEntity);
        assertNotNull(serviceEntity);
        assertEquals(repoEntity, serviceEntity);
        assertEquals(repoEntity.getId(), serviceEntity.getId());
    }

    @Test
    public void nonEntityService_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> abstractJpaService.findAll(NotEntity.class));
    }
}
