package com.gran.infotracker;

import com.gran.infotracker.telegram.TelegramMessenger;
import com.gran.infotracker.workers.OkkoWorker;
import com.gran.infotracker.workers.WogWorker;


public class Main {

    public static void main(String[] args) {
        TelegramMessenger.sendMessage("======Starting======");
        new OkkoWorker().start();
        new WogWorker().start();
    }

}
