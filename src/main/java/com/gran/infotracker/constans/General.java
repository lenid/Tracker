package com.gran.infotracker.constans;

import java.time.Duration;

public interface General {

    String SELENIUM_DRIVER_PATH = "D:\\chromedriver_win32\\chromedriver.exe";
    String LF = "\n";
    String NO_ANY_FUEL = "НЕ ДОСТУПНО ЖОДНОГО ВИДУ ПАЛЬНОГО!";
//    Duration DURATION_REFRESH = Duration.ofSeconds(10);
    Duration DURATION_REFRESH = Duration.ofMinutes(10);
}
