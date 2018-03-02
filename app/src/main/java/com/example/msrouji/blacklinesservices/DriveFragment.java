package com.example.msrouji.blacklinesservices;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.msrouji.blacklinesservices.adapters.BookingAdapter;
import com.example.msrouji.blacklinesservices.controllers.ServerListener;
import com.example.msrouji.blacklinesservices.controllers.Server_Request;
import com.example.msrouji.blacklinesservices.models.Booking;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by msrouji on 02/12/2017.
 */

public class DriveFragment extends Fragment {

    private Gson gson;
    private ListView list;

    private class list_adapter_listener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//            Toast.makeText(getApplicationContext(), "Pushed " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getApplicationContext(), CarDetailActivity.class);
//            Car selected_car = ((Car) adapterView.getItemAtPosition(i));
//            intent.putExtra(key_id_vehicle, selected_car);
//            startActivity(intent);
            Booking selected_booking = ((Booking) adapterView.getItemAtPosition(i));
            String uri = "https://waze.com/ul?ll=" + selected_booking.getDestination() + "&navigate=yes";
            startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
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
//                finish();
            }
            Booking[] bookings = gson.fromJson(o.toString(), Booking[].class);
            BookingAdapter cars_adapter = new BookingAdapter(getActivity().getApplicationContext(), R.layout.drive_fragment,
                    new ArrayList<Booking>(Arrays.asList(bookings)));
            list.setAdapter(cars_adapter);
            list.setOnItemClickListener(new list_adapter_listener());

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
            new Server_Request("GET", getString(R.string.url_server) + "db/driver/travel/", new list_loader()).execute();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return view;
    }


}
