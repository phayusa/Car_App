package com.example.msrouji.blacklinesservices.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.msrouji.blacklinesservices.R;
import com.example.msrouji.blacklinesservices.models.Booking;
import com.example.msrouji.blacklinesservices.models.Car;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by msrouji on 17/11/2017.
 */

public class BookingAdapter extends ArrayAdapter<Booking> {
    private ArrayList<Booking> data;

    public BookingAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Booking> objects) {
        super(context, resource, objects);
        data = objects;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public Booking getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getId();
    }

    private class ViewHolder {
        private TextView client_name;
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_adapter_booking, parent, false);
        }

        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.client_name= (TextView) convertView.findViewById(R.id.client_name);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Booking booking = getItem(position);

        //il ne reste plus qu'à remplir notre vue
//        if (car.getDriver() == null) {
//            viewHolder.car_is_available.setText("Disponible");
//        } else {
//            viewHolder.car_is_available.setText("Indisponible");
//        }
//        viewHolder.car_registration.setText(car.getRegistration());
        viewHolder.client_name.setText(booking.getDestination());
//        Picasso.with(getContext()).load(car.getFront()).resize(100, 100).centerInside().into(viewHolder.car_front);

        //nous renvoyons notre vue à l'adapter, afin qu'il l'affiche
        //et qu'il puisse la mettre à recycler lorsqu'elle sera sortie de l'écran
        return convertView;
    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
