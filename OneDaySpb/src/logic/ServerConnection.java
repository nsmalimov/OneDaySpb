package logic;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import android.util.Log;

import fragments.ResultFragment;

/**
 * Created by Alexander on 18.10.2014.
 */
public class ServerConnection {
    public static void post(String auth_key, String data) throws Exception {

    	Log.d("post", data);
        URL url = new URL("http://onedayinspb.cloudapp.net:80/" + auth_key);
        URLConnection conn = url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        OutputStream d = conn.getOutputStream();
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        String content = data;

        out.writeBytes(content);
        out.flush();
        out.close();

        DataInputStream in = new DataInputStream(conn.getInputStream());
        in.close();
    }

    public static String getJSON(int timeout, String auth_key) {
        try {
        	Log.d("getJSON", "flag1");
            URL u = new URL("http://onedayinspb.cloudapp.net:80/" + auth_key);
            Log.d("getJSON", "flag2");
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
            Log.d("getJSON", "flag3");
            c.setRequestMethod("GET");
            Log.d("getJSON", "flag4");
            c.setRequestProperty("Content-length", "0");
            Log.d("getJSON", "flag5");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);
            c.connect();
            int status = c.getResponseCode();
            Log.d("getJSON", "status="+Integer.toString(status));
            switch (status) {
                case 200:
                case 201:
                	Log.d("getJSON", "here!");
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    String line;
                    while ((line = br.readLine()) != null) {
                        ResultFragment.stringBuilder.append(line + "\n");
                    }
                    
                    br.close();
                    return "";
            }

        } catch (Throwable r) {
            System.out.println("111");
            Log.d("getJSON", "fail status!");
        }

        return null;
    }

    public static StringBuilder output;
    private static Random random = new Random();
}