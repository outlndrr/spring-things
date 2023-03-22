package dev.outldrr.abstractservice;

import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.support.Repositories;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class AbstractJpaService<T, ID> {

    private final JpaRepository<T, ID> repository;

    @SuppressWarnings("unchecked")
    protected AbstractJpaService(@NonNull ApplicationContext context, @NonNull Class<T> entityClass) {
        this.repository = (JpaRepository<T, ID>) new Repositories(context)
                .getRepositoryFor(entityClass)
                .orElseThrow(() -> new RepositoryNotFoundException(entityClass));
    }

    public void flush() {
        repository.flush();
    }

    public void saveAndFlush(T entity) {
        repository.saveAndFlush(entity);
    }

    public void saveAllAndFlush(Iterable<T> entities) {
        repository.saveAllAndFlush(entities);
    }

    public void deleteAllInBatch(Iterable<T> entities) {
        repository.deleteAllInBatch(entities);
    }

    public void deleteAllInBatch() {
        repository.deleteAllInBatch();
    }

    public T getReferenceById(ID entityId) {
        return repository.getReferenceById(entityId);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public List<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    public List<T> findAll(Example<T> example) {
        return repository.findAll(example);
    }

    public List<T> findAll(Example<T> example, Sort sort) {
        return repository.findAll(example, sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<T> findAll(Example<T> example, Pageable pageable) {
        return repository.findAll(example, pageable);
    }

    public List<T> findAllById(Iterable<ID> ids) {
        return repository.findAllById(ids);
    }

    public void save(T entity) {
        repository.save(entity);
    }

    public void saveAll(Iterable<T> entities) {
        repository.saveAll(entities);
    }

    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    public Optional<T> findOne(Example<T> example) {
        return repository.findOne(example);
    }

    public <R> R findBy(Example<T> example, Function<FluentQuery.FetchableFluentQuery<T>, R> queryFunction) {
        return repository.findBy(example, queryFunction);
    }

    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    public boolean exists(Example<T> example) {
        return repository.exists(example);
    }

    public long count() {
        return repository.count();
    }

    public long count(Example<T> example) {
        return repository.count(example);
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends ID> ids) {
        repository.deleteAllById(ids);
    }

    public void deleteAll(Iterable<? extends T> entities) {
        repository.deleteAll(entities);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    @SuppressWarnings("unchecked")
    public <R extends JpaRepository<T, ID>> R getRepository() {
        return (R) repository;
    }
}
