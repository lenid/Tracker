package com.gran.infotracker.workers;

import static com.gran.infotracker.constans.General.DURATION_REFRESH;
import static com.gran.infotracker.constans.Okko.OKKO_NAME;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

import com.gran.infotracker.constans.Okko;
import com.gran.infotracker.converter.OkkoToPetrolStation;
import com.gran.infotracker.converter.PetrolStationListToMessage;
import com.gran.infotracker.filter.OkkoPetrolStationFilter;
import com.gran.infotracker.model.PetrolStation;
import com.gran.infotracker.sites.OkkoSite;
import com.gran.infotracker.telegram.TelegramMessenger;
import com.gran.infotracker.util.Time;

public class OkkoWorker extends Thread {

    @Override
    public void run() {
        final OkkoSite okkoSite = new OkkoSite();
        final OkkoPetrolStationFilter okkoFilter = new OkkoPetrolStationFilter(Okko.ALLOWED_PETROL_STATIONS);

        while (true) {
            final List<WebElement> rows = okkoSite.open();

            final List<PetrolStation> petrolStations = rows.stream()
                .map(r -> OkkoToPetrolStation.convert(r))
                .collect(Collectors.toList());

            okkoSite.close();

            final List<PetrolStation> freshPetrolStations = okkoFilter.filter(petrolStations);
            final String message = PetrolStationListToMessage.convert(freshPetrolStations, OKKO_NAME);
            TelegramMessenger.sendMessage(message);

            System.out.println("==========All found============");
            petrolStations.stream().forEach(System.out::println);
            System.out.println("==========filtered============");
            if (freshPetrolStations != null) {
                freshPetrolStations.stream().forEach(System.out::println);
            }

            Time.sleep(DURATION_REFRESH);
        }
    }

}
