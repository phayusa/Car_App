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

/**
 * Created by msrouji on 02/12/2017.
 */

public class AccountFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.account_fragment,
                container, false);

        ((TextView) view.findViewById(R.id.driver)).setText(LoginActivity.getName());
        if (LoginActivity.getCar() == -1) {
            ((TextView) view.findViewById(R.id.car)).setText("Aucune");
        } else {
            ((TextView) view.findViewById(R.id.car)).setText("" + LoginActivity.getCar());
        }
        setListenersButtons(view);
        return view;
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
