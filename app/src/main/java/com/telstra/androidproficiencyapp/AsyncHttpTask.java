package com.telstra.androidproficiencyapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.telstra.androidproficiencyapp.beans.Facts;
import com.telstra.androidproficiencyapp.constants.Constants;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncHttpTask extends AsyncTask<Object, Void, Integer> {

    private final boolean showDialog;
    FactsActivity factsActivity;
    private Facts factsResponse;
    private ProgressDialog mProgressDialog;
    private Context context;

    public AsyncHttpTask(Context context, boolean showDialog) {
        this.context = context;
        this.showDialog = showDialog;

        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setTitle("Loading...");
        mProgressDialog.setMessage("Loading data, please wait..");
        mProgressDialog.setCancelable(true);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (showDialog)
            mProgressDialog.show();
    }

    @Override
    protected Integer doInBackground(Object... params) {
        Integer result = 0;
        HttpURLConnection urlConnection = null;

        try {
                /* forming the java.net.URL object */
            URL url = new URL((String) params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            factsActivity = (FactsActivity) context;

                /* for Get request */
            urlConnection.setRequestMethod("GET");
            int statusCode = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
            if (statusCode == 200) {
                BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    response.append(line);
                }

                // Load the response string into a Facts Object
                Gson gsonBuilder = new GsonBuilder().create();
                factsResponse = gsonBuilder.fromJson(response.toString(), Facts.class);

                result = 1; // Successful
            } else {
                result = 0; //"Failed to fetch data!";
            }

        } catch (Exception e) {
            Log.d(Constants.TAG, e.getLocalizedMessage());
        }
        return result; //"Failed to fetch data!";
    }

    @Override
    protected void onPostExecute(Integer result) {
//            setProgressBarIndeterminateVisibility(false);
        mProgressDialog.dismiss();
            /* Download complete. Lets update UI */

        if (result == 1) {
            factsActivity.populateListData(factsResponse);
        } else {
            Log.e(Constants.TAG, "Failed to fetch data!");
        }
    }

}