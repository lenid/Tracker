package com.gran.infotracker.converter;

import static com.gran.infotracker.constans.General.LF;
import static com.gran.infotracker.constans.General.NO_ANY_FUEL;

import java.util.List;
import java.util.stream.Collectors;

import com.gran.infotracker.model.PetrolStation;

public class PetrolStationListToMessage {

    public static String convert(List<PetrolStation> petrolStations, String petrolName) {
        if (petrolStations == null) {
            return null;
        }

        if (petrolStations.isEmpty()) {
            return NO_ANY_FUEL + LF + petrolName;
        }

        return petrolStations.stream()
            .map(s -> s.getDesc() + LF + "(" + s.getAddress() + ")")
            .collect(Collectors.joining(LF + LF));
    }

}
