package com.gran.infotracker.filter;

import static com.gran.infotracker.constans.Okko.ONLY_GOODS_PAT;
import static com.gran.infotracker.constans.Okko.UNAVAILABLE_PAT;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.gran.infotracker.model.PetrolStation;

public class OkkoPetrolStationFilter {

    private Collection<PetrolStation> petrolStationsPrev = Arrays.asList();
    private Collection<Pattern> allowedPetrolStations;

    public OkkoPetrolStationFilter(final Collection<Pattern> allowedPetrolStations) {
        this.allowedPetrolStations = allowedPetrolStations;
    }

    public List<PetrolStation> filter(List<PetrolStation> petrolStations) {
        final List<PetrolStation> filteredPetrolStations = petrolStations.stream()
            .filter(s -> filterLocation(s))
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

    private boolean filterLocation(PetrolStation petrolStation) {
        final String address = petrolStation.getAddress().toLowerCase();

        final Pattern pattern = allowedPetrolStations.stream()
            .filter(p -> p.matcher(address).find())
            .findAny().orElse(null);

        return pattern != null;
    }

    private boolean filterIsFuel(PetrolStation petrolStation) {
        return !(UNAVAILABLE_PAT.matcher(petrolStation.getDesc().toLowerCase()).find()
            || ONLY_GOODS_PAT.matcher(petrolStation.getDesc().toLowerCase()).find());
    }

}
