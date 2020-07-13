package de.rares.gfb;

import de.rares.gfb.bots.Bot;
import de.rares.gfb.files.BotList;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Bot b = new Bot("raresderbares@gmail.com", "rs05082005");
            b.connect();
            b.setCookies();
            b.sendAnswer(97327574);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 /*   public static void main(String[] args) {
        System.out.println("GuteFragebotter");

        System.out.println("Bots werden geladen");

        try {
            BotList.load();
        } catch (FileNotFoundException e) {
            System.out.println("Bots.txt nicht gefunden!");
        }

        System.out.println("Bots werden connectet!");
        Bot.connectAll();
    }*/

}
