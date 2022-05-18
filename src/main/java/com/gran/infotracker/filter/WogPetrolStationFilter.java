package com.gran.infotracker.filter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.gran.infotracker.constans.Wog;
import com.gran.infotracker.model.PetrolStation;

public class WogPetrolStationFilter {

    private Collection<PetrolStation> petrolStationsPrev = Arrays.asList();

    public List<PetrolStation> filter(List<PetrolStation> petrolStations) {
        final List<PetrolStation> filteredPetrolStations = petrolStations.stream()
            .filter(s -> filterIsFuel(s))
            .collect(Collectors.toList());

        final boolean isUpdates = !(filteredPetrolStations.containsAll(petrolStationsPrev)
            && petrolStationsPrev.containsAll(filteredPetrolStations));

        if (isUpdates) {
            petrolStationsPrev = filteredPetrolStations;
            return filteredPetrolStations;
        }

        return null;
    }

    private boolean filterIsFuel(PetrolStation petrolStation) {
        return Wog.AVAILABLE_PAT.matcher(petrolStation.getDesc().toLowerCase()).find();
    }

}
