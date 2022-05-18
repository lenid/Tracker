package com.gran.infotracker.sites;

import static com.gran.infotracker.constans.Wog.PETROL_STATION_LIST_PAT;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gran.infotracker.constans.Wog;

public class WogSite extends Site {

    @Override
    protected String getSiteUrl() {
        return Wog.WOG_MAP_URL;
    }

    @Override
    protected List<WebElement> run() {
        goToStationList();

        return getDriver().findElement(By.className("azk-list")).findElements(By.className("item"));
    }

    private void goToStationList() {
        final WebElement switcher = getDriver().findElement(By.className("switcher"));
        final List<WebElement> items = switcher.findElements(By.className("item"));
        final WebElement petrolStationListButton = items.stream()
            .filter(i -> PETROL_STATION_LIST_PAT.matcher(i.getText().toLowerCase()).find())
            .findAny().orElse(null);

        petrolStationListButton.click();
    }

}
