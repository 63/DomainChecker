package wtf.autistic.checker;

import org.json.*;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Checker {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        } finally {
            is.close();
        }
    }


    public static boolean isAvailable(String domain)  {
        try {
            JSONObject json = readJsonFromUrl("https://madchecker.com/domain/api/" + domain);
            try {
                System.out.println("Domain: " + domain + " Error: " + json.getString("error"));
                return false;
            } catch (JSONException e) {
                if (json.getJSONObject("data").getBoolean("available")) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
