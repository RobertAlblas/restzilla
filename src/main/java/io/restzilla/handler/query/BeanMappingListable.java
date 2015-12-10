package io.restzilla.handler.query;

import io.beanmapper.BeanMapper;
import io.beanmapper.spring.PageableMapper;
import io.restzilla.service.Listable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Listable adapter that performs mappings after retrieving the entities.
 *
 * @author Jeroen van Schagen
 * @since Dec 9, 2015
 */
public class BeanMappingListable<T> implements Listable<T>, Finder<T> {
    
    private final Listable<?> delegate;
    
    private final BeanMapper beanMapper;
    
    private final Class<T> resultType;
    
    public BeanMappingListable(Listable<?> delegate, BeanMapper beanMapper, Class<T> resultType) {
        this.resultType = resultType;
        this.beanMapper = beanMapper;
        this.delegate = delegate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T findOne() {
        Object entity = ((Finder<?>) delegate).findOne();
        if (entity == null) {
            return null;
        }
        return beanMapper.map(entity, resultType);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll(Sort sort) {
        List<?> entities = delegate.findAll(sort);
        return (List<T>) beanMapper.map(entities, resultType, ArrayList.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<T> findAll(Pageable pageable) {
        Page<?> entities = delegate.findAll(pageable);
        return PageableMapper.map(entities, resultType, beanMapper);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getEntityClass() {
        return delegate.getEntityClass();
    }

}