package dev.outldrr.abstractservice;

public class RepositoryNotFoundException extends RuntimeException {
    public RepositoryNotFoundException(Class<?> entityClass) {
        super("Repository not found for " + entityClass);
    }
}
