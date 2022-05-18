package com.gran.infotracker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PetrolStation {

    private String address;
    private String desc;

    @Override
    public String toString() {
        return desc + " -||- " + address;
    }
}
