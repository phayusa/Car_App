package com.example.msrouji.blacklinesservices.controllers;

import android.os.AsyncTask;

import com.example.msrouji.blacklinesservices.LoginActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by msrouji on 17/11/2017.
 */

public class Server_Request extends AsyncTask<String,Void,Object> {

    private HttpURLConnection connection;
    private String method;
    private Server_Listener end_data;

    public Server_Request(String method, String url, Server_Listener listener) throws java.io.IOException {
        try {
            this.method = method;
            connection = ((HttpURLConnection) new URL(url).openConnection());
            connection.setRequestMethod(method);
            connection.setRequestProperty("Authorization", "JWT " + LoginActivity.token);
            if (method.equals("POST") || method.equals("PUT")) {
                connection.setDoOutput(true);
                connection.setDoInput(true);
            }
            System.err.println("token "+LoginActivity.token);
            end_data = listener;

        } catch (java.io.IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    protected Object doInBackground(String... data) {
        try {
            if (method.equals("PUT") || method.equals("POST")){

                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(data[0]);
                writer.flush();
            }

            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            BufferedReader buff = new BufferedReader(reader);
            return buff.readLine();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        end_data.onDataListener(o);
    }
}

