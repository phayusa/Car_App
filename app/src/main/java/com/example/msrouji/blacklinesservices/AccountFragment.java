package com.example.msrouji.blacklinesservices;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

/**
 * Created by msrouji on 02/12/2017.
 */

public class AccountFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.detail_fragment,
                container, false);
        return view;
    }


//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        ViewStub main_view = ((ViewStub) findViewById(R.id.view_main));
//
//        // Set the ressource with the correct view
//        main_view.setLayoutResource(R.layout.detail_fragment);
//        main_view.inflate();
//
//        current_id =  R.id.navigation_home;
//        updateNavigationBarState(current_id);
//    }

}
