/*
 * (C) 2014 42 bv (www.42.nl). All rights reserved.
 */
package io.restify.service;

import io.restify.model.WithService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WithServiceService extends AbstractCrudService<WithService, Long> {

    @Override
    public <S extends WithService> S save(S entity) {
        entity.setName(entity.getName() + "!");
        return super.save(entity);
    }

}
