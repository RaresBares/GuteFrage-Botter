package de.rares.gfb.bots;

import de.rares.gfb.files.BotList;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Bot {
    String email;
    String password;
    String accesstoken;
    String refreshtoken;
    String cookies;

    public Bot(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void connect() throws IOException {
        URL url = new URL("https://www.gutefrage.net/graphql");
        String msg = "{\"query\":\"\\n        \\n    mutation LoginWithUsernameOrEmail($emailOrUsername: String!, $password: String!) {\\n      loginWithUsernameOrEmail(emailOrUsername: $emailOrUsername, password: $password) {\\n        accessToken\\n        refreshToken\\n      }\\n    }\\n  \\n\\n        \\n      \",\"variables\":{\"emailOrUsername\":\"" + email + "\",\"password\":\"" + password + "\"}}";
        URLConnection con = url.openConnection();

        //  con.setRequestMethod("POST");
        con.setRequestProperty("authority", "www.gutefrage.net");
        con.setRequestProperty("method", "POST");
        con.setRequestProperty("path", " /graphql");
        con.setRequestProperty("scheme", "https");
        con.setRequestProperty("cookie", "gf-AB-2096-logged-in-ads=0; gf-AB-BIT-2383-push-registrations=0; 172e5671-e922-4e73-b985-0f1f8917602efaktorId=6345e5be-589f-4cec-a0aa-3657e6098f03; 172e5671-e922-4e73-b985-0f1f8917602efaktorChecksum=2029531887; 172e5671-e922-4e73-b985-0f1f8917602ecconsent=BO2bktCO2bktCADABADEA5AAAAAOCAEgAEAAoADAAIgAwA; 172e5671-e922-4e73-b985-0f1f8917602eeuconsent=BO2bktCO2bktCADABADEDS-AAAAxJrv7_77e_9f-_fv_9ujzGr_v_f__3mccL5tn3hv7v6_7fi_-0jV4u_1tfp9ydk1-5YpDjto5w7iakiPHmqNcZ1n_mz1eJIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA; lastConsentChange=1594558342604; _ga=GA1.2.298669568.1594558343; _gid=GA1.2.1733560357.1594558343; _gat=1");
        con.setRequestProperty("origin", "https://www.gutefrage.net");
        con.setRequestProperty("sec-fetch-dest", "empty");
        con.setRequestProperty("sec-fetch-mode", "cors");
        con.setRequestProperty("sec-fetch-site", "same-origin");
        con.setRequestProperty("accept", "*/*");
        con.setRequestProperty("accept-language", "e,en-US;q=0.9,en;q=0.8,de-DE;q=0.7,ro;q=0.6 ");
        con.setRequestProperty("cache-control", "no-cache ");
        con.setRequestProperty("content-length", String.valueOf(msg.length()));
        con.setRequestProperty("content-type", "application/json ");
        con.setRequestProperty("referer", " https://www.gutefrage.net/");
        con.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
        con.setRequestProperty("x-api-key", "dfdza43a-8560-4641-b316-ff928232734c");
        con.setRequestProperty("x-client-id", "net.gutefrage.nmms.mobile");

        con.setDoOutput(true);
        con.getOutputStream().write(msg.getBytes());
        Scanner sc = new Scanner(con.getInputStream());
        String line;
        String resp = new String();
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(con.getInputStream()));
        while ((line = reader.readLine()) != null) {

            resp += line;

        }
        System.out.println(resp);
        String access = resp.split("\"accessToken\":\"")[1].split("\",\"refreshToken\":\"")[0];
        String refresh = resp.split("\"accessToken\":\"")[1].split("\",\"refreshToken\":\"")[1].split("\"}}}")[0];
        accesstoken = access;
        refreshtoken = refresh;

    }


    public void setCookies() throws IOException {
        URL url = new URL("https://www.gutefrage.net/nmms-api/users/login-with-tokens");
        String msg = "{\"accessToken\":\"" + accesstoken + "\",\"refreshToken\":\"" + refreshtoken + "\"}";
        URLConnection con = url.openConnection();

        //  con.setRequestMethod("POST");
        con.setRequestProperty("authority", "www.gutefrage.net");
        con.setRequestProperty("method", "POST");
        con.setRequestProperty("path", " /graphql");
        con.setRequestProperty("scheme", "https");
        con.setRequestProperty("origin", "https://www.gutefrage.net");
        con.setRequestProperty("sec-fetch-dest", "empty");
        con.setRequestProperty("sec-fetch-mode", "cors");
        con.setRequestProperty("sec-fetch-site", "same-origin");
        con.setRequestProperty("accept", "*/*");
        con.setRequestProperty("accept-language", "e,en-US;q=0.9,en;q=0.8,de-DE;q=0.7,ro;q=0.6 ");
        con.setRequestProperty("cache-control", "no-cache ");
        con.setRequestProperty("content-length", String.valueOf(msg.length()));
        con.setRequestProperty("content-type", "application/json ");
        con.setRequestProperty("referer", " https://www.gutefrage.net/");
        con.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
        con.setRequestProperty("x-api-key", "dfdza43a-8560-4641-b316-ff928232734c");
        con.setRequestProperty("x-client-id", "net.gutefrage.nmms.mobile");

        con.setDoOutput(true);
        con.getOutputStream().write(msg.getBytes());


        Map<String, List<String>> map = con.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            try {
                if (entry.getKey().trim().equalsIgnoreCase("set-cookie")) {
                    cookies = "gfAccessToken=" + accesstoken + "; " + "gfRefreshToken= " + refreshtoken + "; " + entry.getValue().toString().replace("]", "").replace("[", "").replace(" Path=/,", "") + ";  gf-li=1; gf-AB-2096-logged-in-ads=0; gf-AB-BIT-2383-push-registrations=0; _gat=1";
                    System.out.println(cookies);
                }
            } catch (Exception e) {

            }

        }
    }

    public void postQuestion(String title, String desc, String[] tags, String[] polls) throws IOException {

        URL url = new URL("https://www.gutefrage.net/nmms-api/questions");
        String msg = "{\"title\":\"" + title + "?\",\"body\":\"<p>" + desc + "</p>\",\"tags\":[";
        for (String tag : tags) {
            if (!tags[tags.length - 1].equals(tag)) {
                System.out.println("1" + tag);
                msg += "\"" + tag + "\",";
            } else {
                System.out.println("2" + tag);
                msg += "\"" + tag + "\"";
            }
        }
        msg += "]";
        msg += ",\"poll_choices\":[";
        for (String poll : polls) {
            if (polls[polls.length - 1].equals(poll)) {
                msg += "\"" + poll + "\"";

            } else {
                msg += "\"" + poll + "\"" + ",";

            }
        }
        msg += "],\"subscribe\":true,\"images\":[]}";
        msg = "{\"title\":\"Denkt ihr, dass jeder Mensch einen Freund finden wird?\",\"body\":\"<p>Frage steht oben</p>\",\"tags\":[\"Leben\",\"Philosophie\"],\"poll_choices\":[],\"subscribe\":true,\"images\":[]}";
        System.out.println(msg);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

     con.setRequestMethod("POST");
        con.setRequestProperty("authority", "www.gutefrage.net");
        con.setRequestProperty("method", "POST");
        con.setRequestProperty("path", " /nmms-api/questions");
        con.setRequestProperty("scheme", "https");
        con.setRequestProperty("origin", "https://www.gutefrage.net");
        con.setRequestProperty("sec-fetch-dest", "empty");
        con.setRequestProperty("sec-fetch-mode", "cors");
        con.setRequestProperty("cookie", "gf-li=1; gf-AB-2096-logged-in-ads=0; gf-AB-BIT-2383-push-registrations=0; lastConsentChange=1594585395048; _ga=GA1.2.1155394590.1594585395; _gid=GA1.2.429469856.1594585395; gfAccessToken=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNzg4MTY3NCIsInJvbGVzIjoiU3RhbmRhcmQiLCJpc3MiOiJndXRlZnJhZ2UubmV0IiwidXNlckNyZWF0ZWRBdCI6IjE1OTIwOTMxMDgwMDAiLCJ0eXAiOiJhY2Nlc3N0b2tlbiIsImV4cCI6MTU5NDY0NTM4OCwiaWF0IjoxNTk0NjQzNTg4fQ.E7uBNu_dic2MWurGT8RrpA3gZ2j1j_1Y0ZHJu5fevHw; gfRefreshToken=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNzg4MTY3NCIsInJvbGVzIjoiU3RhbmRhcmQiLCJpc3MiOiJndXRlZnJhZ2UubmV0IiwidXNlckNyZWF0ZWRBdCI6IjE1OTIwOTMxMDgwMDAiLCJ0eXAiOiJyZWZyZXNodG9rZW4iLCJleHAiOjE1OTcyMzU1ODgsImlhdCI6MTU5NDY0MzU4OH0.O20zzpKqoPB6a641cp2vB0aXrnSYGxkJb7bRrZ1hTiU; _gat=1");
        con.setRequestProperty("sec-fetch-site", "same-origin");
        con.setRequestProperty("accept", "*/*");
        con.setRequestProperty("accept-language", "e,en-US;q=0.9,en;q=0.8,de-DE;q=0.7,ro;q=0.6");
        con.setRequestProperty("cache-control", "no-cache");
     con.setRequestProperty("content-length", String.valueOf(msg.length()));
        con.setRequestProperty("content-type", "application/json");
        con.setRequestProperty("referer", "https://www.gutefrage.net/frage_hinzufuegen");
        con.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
        con.setRequestProperty("x-api-key", "dfdza43a-8560-4641-b316-ff928232734c");
        con.setRequestProperty("x-client-id", "net.gutefrage.nmms.desktop");
    //    con.setRequestProperty("x-client-id", "net.gutefrage.nmms.desktop");
        con.setRequestProperty("x-csrf-token", "null");


        con.setDoOutput(true);
        con.getOutputStream().write(msg.getBytes());


        String line;
        String resp = new String();
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(con.getInputStream()));
        while ((line = reader.readLine()) != null) {

            resp += line;

        }

        System.out.println(resp);


    }


    public void sendAnswer(String ans, int questid) throws IOException {
        URL url = new URL("https://www.gutefrage.net/graphql");
        String msg = "{\"query\":\"\\n        \\n    mutation CreateAnswer($answer: NewAnswer!) {\\n      answer {\\n        createAnswer: createAnswer(answer: $answer) {\\n          id\\n        }\\n      }\\n    }\\n  \\n\\n        \\n      \",\"variables\":{\"answer\":{\"questionId\":" + questid + ",\"body\":\"<p>" + ans + "</p>\",\"images\":[]}}}";
        URLConnection con = url.openConnection();

        //  con.setRequestMethod("POST");
        con.setRequestProperty("authority", "www.gutefrage.net");
        con.setRequestProperty("method", "POST");
        con.setRequestProperty("path", " /graphql");
        con.setRequestProperty("scheme", "https");
        con.setRequestProperty("origin", "https://www.gutefrage.net");
        con.setRequestProperty("sec-fetch-dest", "empty");
        con.setRequestProperty("sec-fetch-mode", "cors");
        con.setRequestProperty("cookie", cookies);
        con.setRequestProperty("sec-fetch-site", "same-origin");
        con.setRequestProperty("accept", "*/*");
        con.setRequestProperty("accept-language", "e,en-US;q=0.9,en;q=0.8,de-DE;q=0.7,ro;q=0.6 ");
        con.setRequestProperty("cache-control", "no-cache ");
        con.setRequestProperty("content-length", String.valueOf(msg.length()));
        con.setRequestProperty("content-type", "application/json ");
        con.setRequestProperty("referer", " https://www.gutefrage.net/");
        con.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
        con.setRequestProperty("x-api-key", "dfdza43a-8560-4641-b316-ff928232734c");
        con.setRequestProperty("x-client-id", "net.gutefrage.nmms.mobile");

        con.setDoOutput(true);
        con.getOutputStream().write(msg.getBytes());
        Scanner sc = new Scanner(con.getInputStream());
        String line;
        String resp = new String();
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(con.getInputStream()));
        while ((line = reader.readLine()) != null) {

            resp += line;

        }
        System.out.println(resp);

    }

    public static void connectAll() {
        BotList.bots.forEach(bot -> {

        });
    }


}
