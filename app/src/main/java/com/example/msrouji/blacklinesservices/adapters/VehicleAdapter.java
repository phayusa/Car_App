package com.example.msrouji.blacklinesservices.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.msrouji.blacklinesservices.R;
import com.example.msrouji.blacklinesservices.models.Car;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by msrouji on 17/11/2017.
 */

public class VehicleAdapter extends ArrayAdapter<Car> {
    private ArrayList<Car> data;

    public VehicleAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Car> objects) {
        super(context, resource, objects);
        data = objects;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public Car getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getId();
    }

    private class ViewHolder {
        private TextView car_is_available;
        private TextView car_registration;
        private ImageView car_front;
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_adapter_drivers, parent, false);
        }

        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.car_is_available = (TextView) convertView.findViewById(R.id.car_is_available);
            viewHolder.car_registration = (TextView) convertView.findViewById(R.id.car_registration);
            viewHolder.car_front = (ImageView) convertView.findViewById(R.id.car_front);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Car car = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        if (car.getDriver() == null) {
            viewHolder.car_is_available.setText("Disponible");
        } else {
            viewHolder.car_is_available.setText("Indisponible");
        }
        viewHolder.car_registration.setText(car.getRegistration());
        Picasso.with(getContext()).load(car.getFront()).error(getContext().getResources().getDrawable(R.drawable.drive)).resize(100, 100).centerInside().into(viewHolder.car_front);

        //nous renvoyons notre vue à l'adapter, afin qu'il l'affiche
        //et qu'il puisse la mettre à recycler lorsqu'elle sera sortie de l'écran
        return convertView;
    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
