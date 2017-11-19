package com.example.msrouji.blacklinesservices;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.ViewStub;
import android.widget.Toast;

import com.example.msrouji.blacklinesservices.controllers.Server_Listener;
import com.example.msrouji.blacklinesservices.controllers.Server_Request;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewStub stub = ((ViewStub) findViewById(R.id.view_main));
        stub.setLayoutResource(R.layout.content_main);
        stub.inflate();

//        findViewById(R.id.action_logout).setVisibility(View.VISIBLE);
    }



    public boolean onNavigationItemSelected(View item) {
        // Handle navigation view item clicks here.
        int id = item.getId();

        if (id == R.id.drive_menu) {
            startActivity(new Intent(getApplicationContext(), CarsListActivity.class));
        }else if(id == R.id.booking_menu){
            startActivity(new Intent(getApplicationContext(), DriveActivity.class));
        }

//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
