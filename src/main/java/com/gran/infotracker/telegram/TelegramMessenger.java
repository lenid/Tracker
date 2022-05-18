package com.gran.infotracker.telegram;

import static com.gran.infotracker.constans.Telegram.API_TOKEN;
import static com.gran.infotracker.constans.Telegram.CHAT_ID;
import static com.gran.infotracker.constans.Telegram.TELEGRAM_URL;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TelegramMessenger {

    public static void sendMessage(String text) {
        if (text == null || text.isBlank()) {
            return;
        }

        try {
            String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8.toString());
            String urlString = String.format(TELEGRAM_URL, API_TOKEN, CHAT_ID, encodedText);
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            conn.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
