package com.mobiquity.atm.model; 
import java.util.List;

import lombok.Data; 
@Data
public class Atms{
    public Address address;
    public int distance;
    public List<OpeningHour> openingHours;
    public String functionality;
    public String type;
}
