/*
 * (C) 2014 42 bv (www.42.nl). All rights reserved.
 */
package io.restify.model;

import io.restify.EnableRest;
import io.restify.model.dto.CreateUserModel;
import io.restify.model.dto.UpdateUserModel;
import io.restify.model.dto.UserModel;

import javax.persistence.Entity;

/**
 * Represents a user.
 *
 * @since Mar 11, 2015
 */
@Entity
@EnableRest(resultType = UserModel.class,
            createType = CreateUserModel.class,
            updateType = UpdateUserModel.class)
public class User extends BaseEntity {
    
    private String name;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}