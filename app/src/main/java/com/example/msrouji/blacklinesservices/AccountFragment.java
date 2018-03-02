package com.example.msrouji.blacklinesservices;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return view;
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
