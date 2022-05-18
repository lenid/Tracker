package com.gran.infotracker.converter;

import static com.gran.infotracker.constans.Okko.AVAILABLE_PAT;
import static com.gran.infotracker.constans.General.LF;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gran.infotracker.model.okko.Paragraph;
import com.gran.infotracker.model.PetrolStation;

public class OkkoToPetrolStation {

    public static PetrolStation convert(WebElement row) {
        final PetrolStation petrolStation = new PetrolStation();

        petrolStation.setAddress(row.findElement(By.className("street")).getText().trim());
        petrolStation.setDesc(getDesc(row.findElement(By.className("notification"))));

        return petrolStation;
    }

    private static String getDesc(WebElement cityNotification) {
        final WebElement span = cityNotification.findElement(By.tagName("span"));
        final List<WebElement> elements = span.findElements(By.cssSelector("p, ol"));
        List<Paragraph> paragraphs = new ArrayList();

        for (int i = 0; i < elements.size(); i++) {
            if ("p".equals(elements.get(i).getTagName())) {
                final Paragraph paragraph = new Paragraph();
                paragraph.setHeader(elements.get(i).getText());

                if (elements.size() > i + 1 && "ol".equals(elements.get(i + 1).getTagName())) {
                    paragraph.setValues(getParagraphValues(elements.get(++i)));
                }

                paragraphs.add(paragraph);
            }
        }

        final Paragraph cashAvailable = paragraphs.stream()
            .filter(p -> AVAILABLE_PAT.matcher(p.getHeader().toLowerCase()).find())
            .findAny()
            .orElse(null);

        if (cashAvailable != null) {
            return cashAvailable.getValues().stream()
                .collect(Collectors.joining(LF));
        }

        if (paragraphs.size() > 1) {
            return paragraphs.get(1).getHeader();
        } else if (paragraphs.size() == 1) {
            return paragraphs.get(0).getHeader();
        }

        System.err.println("No any items in the description!");

        return "No any items in the description!";
    }

    private static List<String> getParagraphValues(WebElement cityNotification) {
        final List<WebElement> items = cityNotification.findElements(By.tagName("li"));

        if (items.isEmpty()) {
            return null;
        }

        return items.stream()
            .map(i -> i.getText())
            .collect(Collectors.toList());
    }

}
