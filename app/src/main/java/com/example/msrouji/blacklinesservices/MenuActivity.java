package com.example.msrouji.blacklinesservices;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msrouji.blacklinesservices.models.Travel;

public class MenuActivity extends AppCompatActivity {

    protected TextView mTextMessage;
    protected BottomNavigationView bottomNavigationView;
    protected NavigationView navigationView;

//    private final Fragment account = new AccountFragment();
//    private final Fragment home = new HomeFragment();

    private Location lastKnownLocation;
    private static Travel travel_choose;

    final FragmentManager fragmentManager = getSupportFragmentManager();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = (item) -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                return true;
            case R.id.navigation_account:
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new AccountFragment()).commit();
                return true;
            case R.id.navigation_runs:
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new TravelListFragment()).commit();
                return true;
            default:
                return false;
        }
    };

    private NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = (item) -> {
        switch (item.getItemId()) {
            case R.id.nav_settings:
                startActivity(new Intent(getApplicationContext(), CarListFragment.class));
            default:
                return false;
        }
    };

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setLocationManager() {
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                if (location != null)
                    lastKnownLocation = location;
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        } catch (NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Autorisez la localisation", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setToolbar();

        setLocationManager();

        mTextMessage = (TextView) findViewById(R.id.message);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);

        // Launch by default the home fragment
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    public Location getLastKnownLocation() {
        return lastKnownLocation;
    }

    public static Travel getTravel_choose() {
        return travel_choose;
    }

    public static void setTravel_choose(Travel travel_choose) {
        MenuActivity.travel_choose = travel_choose;
    }
}
