package com.example.msrouji.blacklinesservices;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.msrouji.blacklinesservices.adapters.TravelAdapter;
import com.example.msrouji.blacklinesservices.controllers.ServerListener;
import com.example.msrouji.blacklinesservices.controllers.Server_Detail_Request;
import com.example.msrouji.blacklinesservices.controllers.Server_Request;
import com.example.msrouji.blacklinesservices.models.Airport;
import com.example.msrouji.blacklinesservices.models.Travel;
import com.google.gson.Gson;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by msrouji on 02/12/2017.
 */

public class TravelListFragment extends Fragment {

    private Gson gson;
    private ListView list;

    public static final String key_extra_travel = "21829829829898 sksjjsj";

    private class list_adapter_listener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Travel choose = ((Travel) adapterView.getItemAtPosition(i));
            Intent intent = new Intent(getActivity(), TravelDetailActivity.class);
            MenuActivity.setTravel_choose(choose);
            startActivity(intent);
        }
    }

    private class list_loader implements ServerListener {
        @Override
        public void onDataListener(Object o) {
            if (o == null) {
                Toast.makeText(getActivity().getApplicationContext(), "Une erreur est survenue", Toast.LENGTH_SHORT).show();
                return;
            }
            if (o.equals("Bad Request")) {
                Toast.makeText(getActivity().getApplicationContext(), "Sélectionner une voiture avant", Toast.LENGTH_SHORT).show();
                return;
            }

            Travel[] bookings = gson.fromJson(o.toString(), Travel[].class);
            UpdateTravelAirportDetail(bookings);

        }
    }

    private void UpdateTravelAirportDetail(Travel[] bookings) {
        int sizeArray = bookings.length;
        for (int i = 0; i < sizeArray; i++) {
            Travel travel = bookings[i];
            new Server_Detail_Request<Airport>(new TravelAirportListener(i, bookings, sizeArray), getString(R.string.url_server) + "db/airport/", travel.getAirport(), Airport.class);
        }
    }

    // Class to apply modifcation to booking data
    private class TravelAirportListener implements ServerListener {
        private int index_travel;
        private Travel[] bookings;
        private int size;

        private TravelAirportListener(int index_travel, Travel[] bookings, int size) {
            this.index_travel = index_travel;
            this.bookings = bookings;
            this.size = size - 1;
        }

        // Tranform the datetime format into lookable format
        private String GetTimeNewFormat(String DateTime) {
            // Convert time into Instant object
            Instant instant = Instant.parse(DateTime);

            //get date time only
            LocalDateTime result = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));

            return "" + result.getDayOfMonth() + "/" + result.getMonthValue() + "/" + result.getYear() + " " + result.getHour() + ":" + result.getMinute();
        }

        @Override
        public void onDataListener(Object o) {
            if (o != null) {
                Airport airport = ((Airport) o);
                bookings[index_travel].setAirport_obj(airport);
                bookings[index_travel].setStart(GetTimeNewFormat(bookings[index_travel].getStart()));
                bookings[index_travel].setEnd(GetTimeNewFormat(bookings[index_travel].getEnd()));


                // At the last update index
                if (index_travel == size) {
                    // We set adapters and add item click listener
                    TravelAdapter cars_adapter = new TravelAdapter(getActivity().getApplicationContext(), R.layout.drive_fragment,
                            new ArrayList<Travel>(Arrays.asList(bookings)));

                    list.setAdapter(cars_adapter);
                    list.setOnItemClickListener(new list_adapter_listener());
                }
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.drive_fragment,
                container, false);

        gson = new Gson();
        list = view.findViewById(android.R.id.list);

        try {
            new Server_Request("GET", getString(R.string.url_server) + "db/travels/", new list_loader()).execute();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return view;
    }


}
