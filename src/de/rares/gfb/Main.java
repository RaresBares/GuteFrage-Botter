package de.rares.gfb;

import de.rares.gfb.bots.Bot;
import de.rares.gfb.files.BotList;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {

            Bot b = new Bot("EMAIL", "PASSWORD");
            b.connect();
            b.setCookies();
         //   b.postQuestion("Hey Das ist ein Test", "", new String[]{"Leben", "Philosophie"}, new String[]{});
        b.sendAnswer( "Das ist nur ein Test",Service.getID("https://www.gutefrage.net/frage/wie-wird-der-iq-test-standard-gemessen"));
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
