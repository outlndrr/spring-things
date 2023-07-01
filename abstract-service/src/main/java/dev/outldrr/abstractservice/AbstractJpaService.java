package dev.outldrr.abstractservice;

import jakarta.persistence.Entity;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.support.Repositories;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@SuppressWarnings("unused")
public abstract class AbstractJpaService {

    private final Repositories repositories;

    public AbstractJpaService(@NonNull ApplicationContext context) {
        this.repositories = new Repositories(context);
    }

    public <T extends Serializable> void flush(Class<T> entityClass) {
        findRepo(entityClass).flush();
    }

    public <T extends Serializable, ID> void saveAndFlush(Class<T> entityClass, T entity) {
        findRepo(entityClass).saveAndFlush(entity);
    }

    public <T extends Serializable, ID> void saveAllAndFlush(Class<T> entityClass, Iterable<T> entities) {
        findRepo(entityClass).saveAllAndFlush(entities);
    }

    public <T extends Serializable, ID> void deleteAllInBatch(Class<T> entityClass, Iterable<T> entities) {
        findRepo(entityClass).deleteAllInBatch(entities);
    }

    public <T extends Serializable, ID> void deleteAllInBatch(Class<T> entityClass) {
        findRepo(entityClass).deleteAllInBatch();
    }

    public <T extends Serializable, ID> T getReferenceById(Class<T> entityClass, ID entityId) {
        return findRepo(entityClass).getReferenceById(entityId);
    }

    public <T extends Serializable> List<T> findAll(Class<T> entityClass) {
        return findRepo(entityClass).findAll();
    }

    public <T extends Serializable> List<T> findAll(Class<T> entityClass, Sort sort) {
        return findRepo(entityClass).findAll(sort);
    }

    public <T extends Serializable> List<T> findAll(Class<T> entityClass, Example<T> example) {
        return findRepo(entityClass).findAll(example);
    }

    public <T extends Serializable> List<T> findAll(Class<T> entityClass, Example<T> example, Sort sort) {
        return findRepo(entityClass).findAll(example, sort);
    }

    public <T extends Serializable> Page<T> findAll(Class<T> entityClass, Pageable pageable) {
        return findRepo(entityClass).findAll(pageable);
    }

    public <T extends Serializable> Page<T> findAll(Class<T> entityClass, Example<T> example, Pageable pageable) {
        return findRepo(entityClass).findAll(example, pageable);
    }

    public <T extends Serializable> List<T> findAllById(Class<T> entityClass, Iterable<Object> ids) {
        return findRepo(entityClass).findAllById(ids);
    }

    public <T extends Serializable> void save(Class<T> entityClass, T entity) {
        findRepo(entityClass).save(entity);
    }

    public <T extends Serializable> void saveAll(Class<T> entityClass, Iterable<T> entities) {
        findRepo(entityClass).saveAll(entities);
    }

    public <T extends Serializable, ID> Optional<T> findById(Class<T> entityClass, ID id) {
        return findRepo(entityClass).findById(id);
    }

    public <T extends Serializable> Optional<T> findOne(Class<T> entityClass, Example<T> example) {
        return findRepo(entityClass).findOne(example);
    }

    public <T extends Serializable, R> R findBy(
            Class<T> entityClass,
            Example<T> example,
            Function<FluentQuery.FetchableFluentQuery<T>, R> queryFunction
    ) {
        return findRepo(entityClass).findBy(example, queryFunction);
    }

    public <T extends Serializable, ID> boolean existsById(Class<T> entityClass, ID id) {
        return findRepo(entityClass).existsById(id);
    }

    public <T extends Serializable> boolean exists(Class<T> entityClass, Example<T> example) {
        return findRepo(entityClass).exists(example);
    }

    public <T extends Serializable> long count(Class<T> entityClass) {
        return findRepo(entityClass).count();
    }

    public <T extends Serializable> long count(Class<T> entityClass, Example<T> example) {
        return findRepo(entityClass).count(example);
    }

    public <T extends Serializable, ID> void deleteById(Class<T> entityClass, ID id) {
        findRepo(entityClass).deleteById(id);
    }

    public <T extends Serializable> void delete(Class<T> entityClass, T entity) {
        findRepo(entityClass).delete(entity);
    }

    public <T extends Serializable, ID> void deleteAllById(Class<T> entityClass, Iterable<? extends ID> ids) {
        findRepo(entityClass).deleteAllById(ids);
    }

    public <T extends Serializable> void deleteAll(Class<T> entityClass, Iterable<? extends T> entities) {
        findRepo(entityClass).deleteAll(entities);
    }

    public <T extends Serializable> void deleteAll(Class<T> entityClass) {
        findRepo(entityClass).deleteAll();
    }

    @SuppressWarnings("unchecked")
    private <T extends Serializable, ID> JpaRepository<T, ID> findRepo(Class<T> entityClass) {
        Assert.isTrue(entityClass.isAnnotationPresent(Entity.class), "Provided [" + entityClass
                + "] does not contain Entity annotation!");

        return (JpaRepository<T, ID>) repositories
                .getRepositoryFor(entityClass)
                .orElseThrow(() -> new RepositoryNotFoundException(entityClass));
    }
}
