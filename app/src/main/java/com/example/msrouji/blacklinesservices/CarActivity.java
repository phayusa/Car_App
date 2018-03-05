package com.example.msrouji.blacklinesservices;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.msrouji.blacklinesservices.models.Car;

/**
 * Created by sokomo on 05/03/18.
 */

public class CarActivity extends AppCompatActivity {
    final FragmentManager fragmentManager = getSupportFragmentManager();

    private static Car selectedCar;

    private static int currentFrag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bis);

        setToolbar();

        fragmentManager.beginTransaction().replace(R.id.fragment_container, new CarListFragment()).commit();

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

    public static Car getSelectedCar() {
        return selectedCar;
    }

    public static void setSelectedCar(Car selectedCar) {
        CarActivity.selectedCar = selectedCar;
    }

    public static int getCurrentFrag() {
        return currentFrag;
    }

    public static void setCurrentFrag(int currentFrag) {
        CarActivity.currentFrag = currentFrag;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                switch (CarActivity.getCurrentFrag()) {
                    case 2:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new CarListFragment()).commit();
                        break;
                    case 1:
                    default:
                        finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
