package com.gran.infotracker.sites;

import static com.gran.infotracker.constans.Okko.LOCATION_SEARCH;
import static com.gran.infotracker.constans.Okko.UKRAINE_PAT;
import static com.gran.infotracker.util.Time.sleep;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.gran.infotracker.constans.Okko;

public class OkkoSite extends Site {

    @Override
    protected String getSiteUrl() {
        return Okko.PAGE_MAP_URL;
    }

    @Override
    protected List<WebElement> run() {
        try {
            setLocationFilter();
            showMore();

            WebElement table = getDriver().findElement(By.tagName("tbody"));
            final List<WebElement> rows = table.findElements(By.tagName("tr"));

            return rows;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Arrays.asList();
    }

    private void setLocationFilter() {
        ChromeDriver driver = getDriver();
        final WebElement filterButton = driver.findElement(By.className("filter-mob-name"));

        try {
            filterButton.click();
        } catch (ElementNotInteractableException ex) {
        }

        sleep(500);

        final WebElement searchInput = driver.findElementByCssSelector(
            "div.element-wrap.anim-block.with-z-1.text.full-size.lightMode>input.input.element.pac-target-input");
        searchInput.sendKeys(LOCATION_SEARCH);
        sleep(100);
        searchInput.sendKeys(Keys.ENTER);
        sleep(100);
        final List<WebElement> foundMatches = driver.findElements(By.className("pac-item"));

        for (int i = 0; i < foundMatches.size(); i++) {
            final WebElement foundContainerMatches = foundMatches.get(i);
            final List<WebElement> foundClarificationMatches = foundContainerMatches.findElements(By.tagName("span"));
            sleep(100);

            if (UKRAINE_PAT.matcher(foundClarificationMatches.get(3).getText().toLowerCase()).matches()) {
                foundContainerMatches.click();
                break;
            }
        }
    }

    private void showMore() {
        ChromeDriver driver = getDriver();

        while (true) {
            try {
                sleep(500);
                driver.findElement(By.cssSelector("body")).sendKeys(Keys.END);
                sleep(200);
                driver.findElement(By.cssSelector("body")).sendKeys(Keys.END);
                sleep(200);
                driver.findElement(By.cssSelector("body")).sendKeys(Keys.END);
                sleep(500);
                driver.findElement(By.className("show-more")).click();
            } catch (Exception ex) {
                return;
            }
        }
    }

}
