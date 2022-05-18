package com.gran.infotracker.constans;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

public interface Wog {

    String WOG_NAME = "WOG";
    String WOG_MAP_URL = "https://wog.ua/ua/map/";
    Pattern AVAILABLE_PAT = Pattern.compile("(готівка)");
    Pattern PETROL_STATION_LIST_PAT = Pattern.compile("(список азк)");
    Pattern FUEL_LIST_PAT = Pattern.compile("(М95|А95|ДП|МДП)");
    Pattern CASH_PAT = Pattern.compile("(Готівка)");

    Collection<Pattern> ALLOWED_PETROL_STATIONS = Arrays.asList(
        Pattern.compile("(севастопольська площа)")
    );
}
