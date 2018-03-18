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
            CarActivity.setSelectedCar((Car) adapterView.getItemAtPosition(i));
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CarDetailFragment()).commit();
        }
    }

    private class list_loader implements ServerListener {
        @Override
        public void onDataListener(Object o) {
            try {
                if (o == null) {
                    Toast.makeText(getActivity().getApplicationContext(), "Une erreur est survenue. Rélancer l'applicaiton", Toast.LENGTH_LONG).show();
                    getActivity().finish();
                    return;
                }
                Car[] cars = gson.fromJson(o.toString(), Car[].class);
                VehicleAdapter cars_adapter = new VehicleAdapter(getActivity().getApplicationContext(), 0, new ArrayList<Car>(Arrays.asList(cars)));
                list.setAdapter(cars_adapter);
                list.setOnItemClickListener(new list_adapter_listener());
            }catch (Exception e){
                e.printStackTrace();
                if (getActivity() != null)
                    getActivity().finish();
            }

        }
    }




}
