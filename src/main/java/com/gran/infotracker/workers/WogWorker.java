package com.gran.infotracker.workers;

import static com.gran.infotracker.constans.General.DURATION_REFRESH;
import static com.gran.infotracker.constans.Wog.WOG_NAME;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gran.infotracker.constans.Wog;
import com.gran.infotracker.converter.PetrolStationListToMessage;
import com.gran.infotracker.converter.WogToPetrolStation;
import com.gran.infotracker.filter.WogPetrolStationFilter;
import com.gran.infotracker.model.PetrolStation;
import com.gran.infotracker.sites.WogSite;
import com.gran.infotracker.telegram.TelegramMessenger;
import com.gran.infotracker.util.Time;

public class WogWorker extends Thread {

    @Override
    public void run() {
        final WogSite wogSite = new WogSite();
        final WogPetrolStationFilter wogFilter = new WogPetrolStationFilter();

        while (true) {
            final List<WebElement> rows = wogSite.open();

            final List<PetrolStation> petrolStations = rows.stream()
                .filter(r -> filterLocation(r))
                .map(r -> WogToPetrolStation.convert(r, wogSite.getDriver()))
                .collect(Collectors.toList());

            wogSite.close();

            final List<PetrolStation> freshPetrolStations = wogFilter.filter(petrolStations);
            final String message = PetrolStationListToMessage.convert(freshPetrolStations, WOG_NAME);
            TelegramMessenger.sendMessage(message);

            System.out.println("==========filtered WOG============");
            if (freshPetrolStations != null) {
                freshPetrolStations.stream().forEach(System.out::println);
            }

            Time.sleep(DURATION_REFRESH);
        }
    }

    private static boolean filterLocation(WebElement row) {
        final String addr = row.findElement(By.className("addr")).getText().toLowerCase();

        final Pattern pattern = Wog.ALLOWED_PETROL_STATIONS.stream()
            .filter(p -> p.matcher(addr).find())
            .findAny().orElse(null);

        return pattern != null;
    }

}
