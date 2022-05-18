package com.gran.infotracker.converter;

import static com.gran.infotracker.constans.General.LF;
import static com.gran.infotracker.constans.Wog.CASH_PAT;
import static com.gran.infotracker.constans.Wog.FUEL_LIST_PAT;
import static com.gran.infotracker.util.Time.sleep;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.gran.infotracker.model.PetrolStation;

public class WogToPetrolStation {

    public static PetrolStation convert(WebElement row, ChromeDriver driver) {
        final PetrolStation petrolStation = new PetrolStation();

        try {
            sleep(100);
            row.click();
            sleep(200);
            final WebElement popupBlock = driver.findElement(By.className("s_active__6MN6-"));

            petrolStation.setAddress(popupBlock.findElement(By.className("address")).getText());
            petrolStation.setDesc(getDesc(popupBlock));

            final WebElement closeButton = popupBlock.findElement(By.className("popup_block")).findElement(
                By.className("s_close__1h4oB"));
            closeButton.click();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return petrolStation;
    }

    private static String getDesc(WebElement webElement) {
        final String description = webElement.findElement(By.className("schedule-description")).getText();
        final String[] lines = description.split(LF);

        return Stream.of(lines)
            .filter(l -> FUEL_LIST_PAT.matcher(l).find() && CASH_PAT.matcher(l).find())
            .map(l -> l.split(" Гаманець")[0])
            .collect(Collectors.joining(LF));
    }

}
