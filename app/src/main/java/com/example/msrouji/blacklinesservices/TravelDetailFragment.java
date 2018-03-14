package com.example.msrouji.blacklinesservices;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.msrouji.blacklinesservices.controllers.ServerListener;
import com.example.msrouji.blacklinesservices.controllers.Server_Request;
import com.example.msrouji.blacklinesservices.models.Car;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sokomo on 10/03/18.
 */

public class TravelDetailFragment extends android.support.v4.app.Fragment  {
    private View root_view;
    private Car selected_car;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.car_detail_fragment,
                container, false);

        root_view = view;
        selected_car = CarActivity.getSelectedCar();

        if(selected_car.getId() == LoginActivity.getCar()){
            ((TextView) view.findViewById(R.id.select_car)).setText(getString(R.string.car_detail_exit));
        }

        CarActivity.setCurrentFrag(2);

        ((TextView) view.findViewById(R.id.car_registration)).setText(selected_car.getRegistration());
        ((TextView) view.findViewById(R.id.car_color)).setText(selected_car.getColor());
//        selected_car = ((Car) getIntent().getSerializableExtra(CarListFragment.key_id_vehicle));

        Picasso.with(getActivity().getApplicationContext()).load(selected_car.getFront()).resize(250, 200).centerInside().into((ImageView) view.findViewById(R.id.car_front));
        Picasso.with(getActivity().getApplicationContext()).load(selected_car.getBack()).resize(250, 200).centerInside().into((ImageView) view.findViewById(R.id.car_back));
        ((ViewFlipper) view.findViewById(R.id.flipperid)).startFlipping();

        return view;
    }


    public void onClick(View view) {
        int id_view = view.getId();
        if (id_view == R.id.registration_card) {
            new DownloadTask(getActivity().getApplicationContext()).execute(selected_car.getRegistration_card(), "carte grise.jpg");
        } else if (id_view == R.id.assurance_card) {
            new DownloadTask(getActivity().getApplicationContext()).execute(selected_car.getInsurance_card(), "carte verte.jpg");
        } else if (id_view == R.id.select_car) {
            try {
                Server_Request send = new Server_Request("GET", getString(R.string.url_server)
                        + "db/vehicle/" + selected_car.getId() + "/driver/", new listener());
                send.execute();
            } catch (java.io.IOException e) {
                e.printStackTrace();
                Toast.makeText(getActivity().getApplicationContext(), "Une erreur est survenue", Toast.LENGTH_LONG).show();
            }
        }
    }

    private class listener implements ServerListener {
        @Override
        public void onDataListener(Object o) {
            if (o == null) {
                Toast.makeText(getActivity().getApplicationContext(), "Une erreur est survenue", Toast.LENGTH_LONG).show();
                return;
            }
            String result = ((String) o);

            if (result.equals("Ok")) {
                Toast.makeText(getActivity().getApplicationContext(), "Réserver", Toast.LENGTH_LONG).show();
                LoginActivity.setCar(selected_car.getId());
                getActivity().finish();
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Une erreur est survenue", Toast.LENGTH_LONG).show();

            }
        }
    }

    // usually, subclasses of AsyncTask are declared inside the activity class.
// that way, you can easily modify the UI thread from here
    private class DownloadTask extends AsyncTask<String, Integer, String> {

        private Context context;
        private PowerManager.WakeLock mWakeLock;
        private String path;

        public DownloadTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(sUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + sUrl[1];
                output = new FileOutputStream(path);

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//            mWakeLock.release();
//            mProgressDialog.dismiss();
            if (result != null)
                Toast.makeText(context, "Download error: " + result, Toast.LENGTH_LONG).show();
            else {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider",
                        new File(path)), "image/*");
                startActivity(intent);
            }

        }
    }
}
