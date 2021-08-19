package com.mobiquity.atm.model;

import lombok.Data;

@Data
public class Address{
    public String street;
    public String housenumber;
    public String postalcode;
    public String city;
    public GeoLocation geoLocation;
}
