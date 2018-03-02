package com.example.msrouji.blacklinesservices;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msrouji.blacklinesservices.adapters.Booking_Adapter;
import com.example.msrouji.blacklinesservices.adapters.Vehicle_Adapter;
import com.example.msrouji.blacklinesservices.controllers.Server_Listener;
import com.example.msrouji.blacklinesservices.controllers.Server_Request;
import com.example.msrouji.blacklinesservices.models.Booking;
import com.example.msrouji.blacklinesservices.models.Car;
import com.example.msrouji.blacklinesservices.models.Travel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class DriveActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Gson gson;
    private ListView list;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive);

//        mTextMessage = (TextView) findViewById(R.id.message);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        gson = new Gson();
        list = ((ListView) findViewById(android.R.id.list));

        try {
            new Server_Request("GET", getString(R.string.url_server) + "db/driver/travel/", new list_loader()).execute();
//            new Server_Request("GET", getString(R.string.url_server) + "db/driver/travel", new list_loader()).execute();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

//    private class travel_loader implements Server_Listener {
//        @Override
//        public void onDataListener(Object o) {
//            if (o == null) {
//                Toast.makeText(getApplicationContext(), "Une erreur est survenue", Toast.LENGTH_SHORT).show();
//                return;
//            }
////            System.err.print("ta mere kksslklsls"+o.toString());
//            travel = gson.fromJson(o.toString(), Travel.class);
//
////            try {
////                new Server_Request("GET", getString(R.string.url_server) + "db/travels", new list_adapter_listener()).execute();
////            } catch (java.io.IOException e) {
////                e.printStackTrace();
////            }
//
//        }
//    }

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

    private class list_loader implements Server_Listener {
        @Override
        public void onDataListener(Object o) {
            if (o == null) {
                Toast.makeText(getApplicationContext(), "Une erreur est survenue", Toast.LENGTH_SHORT).show();
                return;
            }
//            if (o instanceof String) {
//                    Toast.makeText(getApplicationContext(), "Une erreur est survenue : " + o, Toast.LENGTH_SHORT).show();
//                    return;
//            }
            if (o.equals("Bad Request")) {
                Toast.makeText(getApplicationContext(), "Sélectionner une voiture avant", Toast.LENGTH_SHORT).show();
                finish();
            }
            System.err.println(o);
            Booking[] bookings = gson.fromJson(o.toString(), Booking[].class);
            Booking_Adapter cars_adapter = new Booking_Adapter(getApplicationContext(), R.layout.activity_drive,
                    new ArrayList<Booking>(Arrays.asList(bookings)));
            list.setAdapter(cars_adapter);
            list.setOnItemClickListener(new list_adapter_listener());

        }
    }


}
