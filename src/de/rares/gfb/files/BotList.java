package de.rares.gfb.files;

import de.rares.gfb.bots.Bot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BotList {
    public static ArrayList<Bot> bots = new ArrayList<>();
    public static void load() throws FileNotFoundException {

        File f = new File("./bots.txt");
        Scanner sc = new Scanner(new FileInputStream(f));
        while (sc.hasNextLine()){
            String query = sc.nextLine();
            String email = query.split(":")[0];
            String password = query.split(":")[1];
            bots.add(new Bot(email, password));
        }

    }


}
