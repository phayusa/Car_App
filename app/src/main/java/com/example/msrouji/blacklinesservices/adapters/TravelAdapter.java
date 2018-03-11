package com.example.msrouji.blacklinesservices.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.msrouji.blacklinesservices.R;
import com.example.msrouji.blacklinesservices.models.Booking;
import com.example.msrouji.blacklinesservices.models.Travel;

import java.util.ArrayList;

/**
 * Created by msrouji on 17/11/2017.
 */

public class TravelAdapter extends ArrayAdapter<Travel> {
    private ArrayList<Travel> data;

    public TravelAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Travel> objects) {
        super(context, resource, objects);
        data = objects;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public Travel getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getId();
    }

    private class ViewHolder {
        private TextView start;
        private TextView airport;
        private TextView destination;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        //Android nous fournit un convertView null lorsqu'il nous demande de la créer
        //dans le cas contraire, cela veux dire qu'il nous fournit une vue recyclée
        if (convertView == null) {
            //Nous récupérons notre row_tweet via un LayoutInflater,
            //qui va charger un layout xml dans un objet View
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_adapter_travel, parent, false);
        }

        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.start= convertView.findViewById(R.id.start);
            viewHolder.airport= convertView.findViewById(R.id.airport);
            viewHolder.destination= convertView.findViewById(R.id.destination);

            convertView.setTag(viewHolder);
        }

        Travel travel = getItem(position);
        viewHolder.start.setText(travel.getStart());
        viewHolder.airport.setText(travel.getAirport());

        //il ne reste plus qu'à remplir notre vue

        //nous renvoyons notre vue à l'adapter, afin qu'il l'affiche
        //et qu'il puisse la mettre à recycler lorsqu'elle sera sortie de l'écran
        return convertView;
    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
