/*
 * (C) 2014 42 bv (www.42.nl). All rights reserved.
 */
package io.restzilla.builder;

import io.restzilla.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 *
 * @author jeroen
 * @since Aug 24, 2015
 */
@Component
@Transactional
public class UserBuilder {
    
    @PersistenceContext
    private EntityManager entityManager;

    public User createUser(String name) {
        User user = new User();
        user.setName(name);
        entityManager.persist(user);
        return user;
    }

}
