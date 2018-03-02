package com.example.msrouji.blacklinesservices;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.msrouji.blacklinesservices.adapters.VehicleAdapter;
import com.example.msrouji.blacklinesservices.controllers.ServerListener;
import com.example.msrouji.blacklinesservices.controllers.Server_Request;
import com.example.msrouji.blacklinesservices.models.Car;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class CarsListActivity extends BaseActivity {

    private ListView list;
    private Gson gson;
    public final static String key_id_vehicle = "vehicle_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        ViewStub stub = ((ViewStub) findViewById(R.id.view_main));
//        stub.setLayoutResource(R.layout.content_drive);
//        stub.inflate();

        gson = new Gson();

        list = ((ListView) findViewById(android.R.id.list));

        // set the list adapter
//        String[] entities = {"Users", "Books", "Orders", "States"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, entities);
//        list.setAdapter(adapter);
//        list.setOnItemClickListener(new list_adapter_listener());

        try {
            new Server_Request("GET", getString(R.string.url_server) + "db/vehicles/", new list_loader()).execute();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

//        try {
//            System.err.println(getString(R.string.url_server) + "/db/vehicles/");
//        } catch (java.io.IOException e) {
//            e.printStackTrace();
//        }
    }

    private class list_adapter_listener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(getApplicationContext(), "Pushed " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), CarDetailActivity.class);
            Car selected_car = ((Car) adapterView.getItemAtPosition(i));
            intent.putExtra(key_id_vehicle, selected_car);
            startActivity(intent);
        }
    }

    private class list_loader implements ServerListener {
        @Override
        public void onDataListener(Object o) {
            System.err.println(o);
            if (o == null){
                Toast.makeText(getApplicationContext(), "Une erreur est survenue. Rélancer l'applicaiton", Toast.LENGTH_LONG);
                finish();
                return;
            }
            Car[] cars = gson.fromJson(o.toString(), Car[].class);
//            ArrayList<String> registrations = new ArrayList<>();
//            for (Car car : cars){
//                registrations.add(car.getRegistration());
//            }
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, registrations);
//            list.setAdapter(adapter);
            VehicleAdapter cars_adapter = new VehicleAdapter(getApplicationContext(), 0, new ArrayList<Car>(Arrays.asList(cars)));
            list.setAdapter(cars_adapter);
            list.setOnItemClickListener(new list_adapter_listener());
//            System.err.println(cars[0].getRegistration());
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>();

        }
    }


}
