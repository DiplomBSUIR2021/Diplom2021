package com.diploma.demo.core.owner;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "business_owner")
@DiscriminatorValue("BUSINESS")
public class BusinessOwner extends Owner{

    //УНП
    @Column(name="unp")
    private String unp;
}
