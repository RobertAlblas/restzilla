/*
 * (C) 2014 42 bv (www.42.nl). All rights reserved.
 */
package io.restify.service;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

/**
 * Template implementation of the CrudService, delegates all to the repository.
 *
 * @author Jeroen van Schagen
 * @since Aug 21, 2015
 */
public abstract class AbstractCrudService<T, ID extends Serializable> implements CrudService<T, ID> {

    private final CrudRepository<T, ID> repository;
    
    private final Class<T> entityClass;

    public AbstractCrudService(Class<T> entityClass, CrudRepository<T, ID> repository) {
        this.repository = repository;
        this.entityClass = entityClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<T> findAll() {
        return (Collection<T>) repository.findAll();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public T findOne(ID id) {
        return repository.findOne(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends T> S save(S entity) {
        return repository.save(entity);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(ID id) {
        repository.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }

}