package com.diploma.demo.core.landplot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Column(name="region")
    private String region;

    @Column(name = "city")
    private String city;

    @Column(name ="street")
    private String street;

    @Column(name="home_number")
    private String homeNumber;

    @Column(name="apartment_n")
    private Long apartmentn;


}
