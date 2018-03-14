package com.example.msrouji.blacklinesservices;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msrouji.blacklinesservices.controllers.ServerListener;
import com.example.msrouji.blacklinesservices.controllers.Server_Detail_Request;
import com.example.msrouji.blacklinesservices.models.Car;

/**
 * Created by msrouji on 02/12/2017.
 */

public class AccountFragment extends Fragment {

    private View root_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.account_fragment,
                container, false);

        ((TextView) view.findViewById(R.id.driver)).setText(LoginActivity.getName());
//        UpdateCarInfo();
        setListenersButtons(view);

        root_view = view;
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        UpdateCarInfo((int) LoginActivity.getCar());
    }

    // Update the car information
    public void UpdateCarInfo(int id) {
        if (id == -1) {
            ((TextView) root_view.findViewById(R.id.car)).setText("Aucune");
        } else {
            // Getting the car detail information
            new Server_Detail_Request<Car>(new CarDetailListener(), getString(R.string.url_server) + "db/vehicle/", id, Car.class);
        }
    }

    // Private class to car detail request
    private class CarDetailListener implements ServerListener {
        @Override
        public void onDataListener(Object o) {
            // if something bad happened
            if (o == null) {
                Toast.makeText(getActivity().getApplicationContext(), "Une erreur est survenue", Toast.LENGTH_LONG).show();
                return;
            }
            // Else
            Car car = ((Car) o);
            ((TextView) root_view.findViewById(R.id.car)).setText(car.getRegistration());

        }
    }

    private void setListenersButtons(View view) {
        ((Button) view.findViewById(R.id.change_car)).setOnClickListener((view1 -> {
            startActivity(new Intent(getContext(), CarActivity.class));
        }));
    }


//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        ViewStub main_view = ((ViewStub) findViewById(R.id.view_main));
//
//        // Set the ressource with the correct view
//        main_view.setLayoutResource(R.layout.account_fragment);
//        main_view.inflate();
//
//        current_id =  R.id.navigation_home;
//        updateNavigationBarState(current_id);
//    }

}
