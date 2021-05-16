package com.diploma.demo.core.landplot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
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

    @Column(name="apartmentN")
    private Long apartmentN;






    public String getCity() {
        return city;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getRegion() {
        return region;
    }

    public String getStreet() {
        return street;
    }

    public Long getApartmentN() {
        return apartmentN;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setStreet(String street) {
        this.street = street;
    }


}
