package com.example.msrouji.blacklinesservices.controllers;

import android.widget.Toast;

import com.example.msrouji.blacklinesservices.R;
import com.example.msrouji.blacklinesservices.TravelListFragment;
import com.example.msrouji.blacklinesservices.adapters.TravelAdapter;
import com.example.msrouji.blacklinesservices.models.Car;
import com.example.msrouji.blacklinesservices.models.Travel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by msrouji on 14/03/2018.
 */

public class Server_Detail_Request<T> {
    private Gson gson;
    private ServerListener listener;
    private Class<T> type;

    public Server_Detail_Request(ServerListener listener, String url, int id, Class<T> type) {
        this.listener = listener;
        this.gson = new Gson();
        this.type = type;

        try {
            new Server_Request("GET", url + id, new ListenerRequest()).execute();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }


    private class ListenerRequest implements ServerListener {
        @Override
        public void onDataListener(Object o) {
            System.err.println(o);

            // Check if we have a correct result
            if (o == null) {
                listener.onDataListener(null);
                return;
            }
            if (o.equals("Bad Request")) {
                listener.onDataListener(null);
                return;
            }

            try {
                // Getting the type of the template
//                Type collectionType = new TypeToken<T>() {}.getType();

                // Getting the object from json and send it
                T object = gson.fromJson(o.toString(), type);
                listener.onDataListener(object);
            } catch (Exception e) {
                e.printStackTrace();
                listener.onDataListener(null);
            }


        }
    }
}
