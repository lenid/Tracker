package com.gran.infotracker.constans;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

public interface Okko {

    Pattern AVAILABLE_PAT = Pattern.compile("(за готівку і банківські картки доступно)");
    Pattern UNAVAILABLE_PAT = Pattern.compile("(за готівку і банківські картки не доступно жодного виду пального|при досягненні залишків пального на азк критичного рівня|з паливною карткою і талонами доступно)");
    Pattern ONLY_GOODS_PAT = Pattern.compile("(продаж лише товарів магазину та кафе)");
    Pattern UKRAINE_PAT = Pattern.compile("(ukraine|украина|україна)");
    Pattern KIEV_PAT = Pattern.compile("(kiev|киев|київ)");
    Pattern TALON_PAT = Pattern.compile("(з паливною карткою і талонами доступно)");

    String OKKO_NAME = "Okko";
    String PAGE_MAP_URL = "https://www.okko.ua/fuel-map";
    String LOCATION_SEARCH = "Киев";

    Collection<Pattern> ALLOWED_PETROL_STATIONS = Arrays.asList(
        Pattern.compile("(нижній вал)"),
        Pattern.compile("(чоколівський)"),
        Pattern.compile("(володимира брожка)"),
        Pattern.compile("(святошинська)")
    );

}
