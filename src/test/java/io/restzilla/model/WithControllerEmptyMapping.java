/*
 * (C) 2014 42 bv (www.42.nl). All rights reserved.
 */
package io.restzilla.model;

import javax.persistence.Entity;

@Entity
// REST resource is defined in the controller class.
public class WithControllerEmptyMapping extends BaseEntity {
    
    private String name;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
