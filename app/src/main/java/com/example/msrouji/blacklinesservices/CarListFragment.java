package com.example.msrouji.blacklinesservices;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class CarListFragment extends android.support.v4.app.Fragment {

    private ListView list;
    private Gson gson;
    public final static String key_id_vehicle = "vehicle_id";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.drive_fragment,
                container, false);

        gson = new Gson();

        list = view.findViewById(android.R.id.list);

        CarActivity.setCurrentFrag(1);
//        ((TextView) view.findViewById(R.id.toolbar_title)).setText("Choix voiture");


        try {
            new Server_Request("GET", getString(R.string.url_server) + "db/vehicles/", new list_loader()).execute();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return view;
    }


    private class list_adapter_listener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(getActivity().getApplicationContext(), "Pushed " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            CarActivity.setSelectedCar((Car) adapterView.getItemAtPosition(i));
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CarDetailFragment()).commit();

//            Intent intent = new Intent(getActivity().getApplicationContext(), CarDetailFragment.class);
//            Car selected_car = ((Car) adapterView.getItemAtPosition(i));
//            intent.putExtra(key_id_vehicle, selected_car);
//            startActivity(intent);
        }
    }

    private class list_loader implements ServerListener {
        @Override
        public void onDataListener(Object o) {
            if (o == null) {
                Toast.makeText(getActivity().getApplicationContext(), "Une erreur est survenue. Rélancer l'applicaiton", Toast.LENGTH_LONG).show();
                getActivity().finish();
                return;
            }
            Car[] cars = gson.fromJson(o.toString(), Car[].class);
//            ArrayList<String> registrations = new ArrayList<>();
//            for (Car car : cars){
//                registrations.add(car.getRegistration());
//            }
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, registrations);
//            list.setAdapter(adapter);
            VehicleAdapter cars_adapter = new VehicleAdapter(getActivity().getApplicationContext(), 0, new ArrayList<Car>(Arrays.asList(cars)));
            list.setAdapter(cars_adapter);
            list.setOnItemClickListener(new list_adapter_listener());
//            System.err.println(cars[0].getRegistration());
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>();

        }
    }




}
