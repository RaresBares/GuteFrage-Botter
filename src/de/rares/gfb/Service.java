package de.rares.gfb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Service {


  public static int getID (String urlstr) throws IOException {
      int id = 0;
      URL url = new URL(urlstr);

      HttpURLConnection con = (HttpURLConnection) url.openConnection();


  con.setRequestProperty("authority", "www.gutefrage.net");
      con.setRequestProperty("method", "GET");
      con.setRequestProperty("scheme", "https");
      con.setRequestProperty("path", "/nmms-template/frage/werden-die-meisten-menschen-denken-dass-ich-keine-freunde-habe-wenn-ich-taeglich-alleine-in-der-mensa-sitze");
     con.setRequestProperty("origin", "https://www.gutefrage.net");
      con.setRequestProperty("accept", "*/*");
      con.setRequestProperty("accept-language", "e,en-US;q=0.9,en;q=0.8,de-DE;q=0.7,ro;q=0.6");
      con.setRequestProperty("accept-encoding", "gzip, deflate, br");
      con.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
      con.setRequestProperty("x-api-key", "dfdza43a-8560-4641-b316-ff928232734c");
      con.setRequestProperty("api-version", "1");
      con.setRequestProperty("x-client-id", "net.gutefrage.nmms.mobile");
      con.setRequestMethod("POST");
      con.setDoOutput(true);
        System.out.println(con.getResponseCode());
      Scanner sc = new Scanner(con.getInputStream());
      String resp  ="";
     while (sc.hasNext()){
         resp += sc.next();
     }

    id = Integer.parseInt(resp.trim().replace(" ", "").replace("\n", "").split("<articleid=")[1].split("Question-")[1].split("\"data-ref")[0]);
      return id;
  }
}
