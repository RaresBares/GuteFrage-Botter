package de.rares.gfb.bots;

import de.rares.gfb.files.BotList;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        String msg = "{\"query\":\"\\n        \\n    mutation LoginWithUsernameOrEmail($emailOrUsername: String!, $password: String!) {\\n      loginWithUsernameOrEmail(emailOrUsername: $emailOrUsername, password: $password) {\\n        accessToken\\n        refreshToken\\n      }\\n    }\\n  \\n\\n        \\n      \",\"variables\":{\"emailOrUsername\":\"raresderbares@gmail.com\",\"password\":\"rs05082005\"}}";
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
        con.setRequestProperty("content-length", "365");
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
        String access = resp.split("\"accessToken\":\"")[1].split("\",\"refreshToken\":\"")[0];
        String refresh = resp.split("\"accessToken\":\"")[1].split("\",\"refreshToken\":\"")[1].split("\"}}}")[0];
        accesstoken = access;
        refreshtoken = refresh;

    }


    public void setCookies() throws IOException {
        URL url = new URL("https://www.gutefrage.net/nmms-api/users/login-with-tokens");
        String msg = "{\"accessToken\":\"" +accesstoken +"\",\"refreshToken\":\"" + refreshtoken +"\"}";
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
        con.setRequestProperty("content-length", "365");
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
                if(entry.getKey().trim().equalsIgnoreCase("set-cookie")){
                   cookies = "gfAccessToken=" + accesstoken + "; "+ "gfRefreshToken= " + refreshtoken + "; " + entry.getValue().toString().replace("]", "").replace("[", "").replace(" Path=/,", "") + ";  gf-li=1; gf-AB-2096-logged-in-ads=0; gf-AB-BIT-2383-push-registrations=0; _gat=1";
                    System.out.println(cookies);
                }
            }catch (Exception e){

            }

        }
    }
    public  void sendAnswer(int questid) throws IOException {
        URL url = new URL("https://www.gutefrage.net/graphql");
        String msg = "{\"query\":\"\\n        \\n    mutation CreateAnswer($answer: NewAnswer!) {\\n      answer {\\n        createAnswer: createAnswer(answer: $answer) {\\n          id\\n        }\\n      }\\n    }\\n  \\n\\n        \\n      \",\"variables\":{\"answer\":{\"questionId\":" + questid + ",\"body\":\"<p>sehr warm</p>\",\"images\":[]}}}";
        URLConnection con = url.openConnection();

        //  con.setRequestMethod("POST");
        con.setRequestProperty("authority", "www.gutefrage.net");
        con.setRequestProperty("method", "POST");
        con.setRequestProperty("path", " /graphql");
        con.setRequestProperty("scheme", "https");
        con.setRequestProperty("origin", "https://www.gutefrage.net");
        con.setRequestProperty("sec-fetch-dest", "empty");
        con.setRequestProperty("sec-fetch-mode", "cors");
        con.setRequestProperty("cookie" , cookies);
        con.setRequestProperty("sec-fetch-site", "same-origin");
        con.setRequestProperty("accept", "*/*");
        con.setRequestProperty("accept-language", "e,en-US;q=0.9,en;q=0.8,de-DE;q=0.7,ro;q=0.6 ");
        con.setRequestProperty("cache-control", "no-cache ");
        con.setRequestProperty("content-length", "365");
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
