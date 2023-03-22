package dev.outlndrr.abstractservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JustEntityRepo extends JpaRepository<JustEntity, Long> {

    Optional<JustEntity> findByName(String name);
}
