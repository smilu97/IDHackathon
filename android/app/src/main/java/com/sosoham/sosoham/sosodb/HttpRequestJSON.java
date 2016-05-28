package com.sosoham.sosoham.sosodb;

import android.util.Log;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by becxer on 2016. 5. 29..
 */
public class HttpRequestJSON {

    String URL_PREFIX = "http://192.168.43.168:4000/";

    public void sendPost(final String method, final JSONObject jsonObject, final HttpRequestJSONListener hrjl){
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url_address = URL_PREFIX + "method";
                    HttpURLConnection conn = null;
                    OutputStream os = null;
                    InputStream is = null;
                    ByteArrayOutputStream baos = null;
                    URL url =new URL(url_address);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Cache-Control", "no-cache");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    os = conn.getOutputStream();
                    os.write(jsonObject.toString().getBytes());
                    os.flush();
                    String response;
                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        is = conn.getInputStream();
                        baos = new ByteArrayOutputStream();
                        byte[] byteBuffer = new byte[1024];
                        byte[] byteData = null;
                        int nLength = 0;
                        while ((nLength = is.read(byteBuffer, 0, byteBuffer.length)) != -1) {
                            baos.write(byteBuffer, 0, nLength);
                        }
                        byteData = baos.toByteArray();
                        response = new String(byteData);
                        JSONObject responseJSON = new JSONObject(response);
                        Log.i("http send post", "DATA response = " + response);
                        hrjl.onRequestResult(responseJSON);
                    }
                }catch(Exception e){
                    Log.d("http send post", e.toString());
                }
            }
        });
        th.start();
    }

    public interface HttpRequestJSONListener{
        void onRequestResult(JSONObject jsonObject);
    }
}
