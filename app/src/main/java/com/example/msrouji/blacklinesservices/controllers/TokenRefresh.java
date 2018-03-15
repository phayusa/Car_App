package com.example.msrouji.blacklinesservices.controllers;

import android.os.AsyncTask;

import com.example.msrouji.blacklinesservices.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by msrouji on 19/11/2017.
 */


public class TokenRefresh extends AsyncTask<String, Void, JSONObject> {
    private ServerListener data;

    public TokenRefresh(ServerListener data) {
        this.data = data;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0] + "db/refresh/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("token", "JWT " + LoginActivity.getToken());
            conn.setDoOutput(true);

            JSONObject toSendData = new JSONObject();
            toSendData.accumulate("token", LoginActivity.getToken());

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(toSendData.toString());
            writer.flush();

            InputStreamReader reader = new InputStreamReader(conn.getInputStream());
            BufferedReader buff = new BufferedReader(reader);

            return new JSONObject(buff.readLine());

        } catch (JSONException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(JSONObject aVoid) {
        super.onPostExecute(aVoid);
        data.onDataListener(aVoid);
    }
}