package com.example.msrouji.blacklinesservices;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.msrouji.blacklinesservices.adapters.BookingAdapter;
import com.example.msrouji.blacklinesservices.controllers.ServerListener;
import com.example.msrouji.blacklinesservices.controllers.Server_Detail_Request;
import com.example.msrouji.blacklinesservices.models.Booking;
import com.example.msrouji.blacklinesservices.models.Car;
import com.example.msrouji.blacklinesservices.models.Travel;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sokomo on 05/03/18.
 */

public class TravelDetailActivity extends AppCompatActivity {

    private static Travel selectedTravel;
    private ListView list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() == null)
            finish();

        setContentView(R.layout.activity_travel_detail);

        list = findViewById(android.R.id.list);
        setToolbar();


        selectedTravel = ((Travel) getIntent().getSerializableExtra(TravelListFragment.key_extra_travel));
        selectedTravel.setBookings_obj(new ArrayList<>());


    }

    private void GetBookingsInfo() {
        int sizeArray = selectedTravel.getBookings().size();
        int lastIndex = sizeArray - 1;
        for (int index = 0; index < sizeArray; index++) {
            int id_booking = selectedTravel.getBookings().get(index).intValue();
            new Server_Detail_Request<Booking>(new UpdateInfoListener(index, lastIndex == index), getString(R.string.url_server) + "db/booking/", id_booking, Booking.class);
        }
    }

    private class UpdateInfoListener implements ServerListener {
        private int id;
        private boolean last;

        public UpdateInfoListener(int id, boolean last) {
            this.id = id;
            this.last = last;
        }

        @Override
        public void onDataListener(Object o) {
            if (o == null) {
                Toast.makeText(getApplicationContext(), "Une erreur est survenue", Toast.LENGTH_LONG).show();
//                finish();
                return;
            }
            Booking booking = ((Booking) o);
            selectedTravel.getBookings_obj().add(booking);
            if (last) {
                BookingAdapter bookingAdapter = new BookingAdapter(getApplicationContext(), R.layout.drive_fragment,
                        selectedTravel.getBookings_obj());

                list.setAdapter(bookingAdapter);
//                list.setOnItemClickListener(new list_adapter_listener());
            }
        }
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
