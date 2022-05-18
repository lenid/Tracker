package com.gran.infotracker.sites;

import static com.gran.infotracker.constans.General.SELENIUM_DRIVER_PATH;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class Site {

    private ChromeDriver driver;

    protected abstract String getSiteUrl();
    protected abstract List<WebElement> run();

    public List<WebElement> open() {
        System.setProperty("webdriver.chrome.driver", SELENIUM_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.get(getSiteUrl());
        driver.manage().timeouts().implicitlyWait(500L, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        Dimension dimension = new Dimension(1920, 1080);
        driver.manage().window().setSize(dimension);

        return run();
    }

    public void close() {
        driver.quit();
    }

    public ChromeDriver getDriver() {
        return driver;
    }

}
