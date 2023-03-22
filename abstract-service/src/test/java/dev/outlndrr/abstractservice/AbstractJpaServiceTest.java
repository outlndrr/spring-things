package dev.outlndrr.abstractservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@TestPropertySource(locations = "classpath:application.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class AbstractJpaServiceTest {

    @MockBean
    private JustService justService;

    @MockBean
    private JustEntityRepo justEntityRepo;

    @MockBean
    private TestEntityManager entityManager;

    @BeforeEach
    public void insertData() {
        JustEntity justEntity1 = new JustEntity(1L, "Name1");
        JustEntity justEntity2 = new JustEntity(2L, "Name2");

        entityManager.persist(justEntity1);
        entityManager.persist(justEntity2);
    }

    @Test
    public void findAll() {
        List<JustEntity> repoEntities = justEntityRepo.findAll();
        List<JustEntity> serviceEntities = justService.findAll();

        assertNotNull(serviceEntities);
        assertEquals(repoEntities, serviceEntities);

        verify(justService, times(1)).findAll();
        verify(justEntityRepo, times(1)).findAll();
    }
}
