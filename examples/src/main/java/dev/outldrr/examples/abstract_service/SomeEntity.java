package dev.outldrr.examples.abstract_service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SomeEntity {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
