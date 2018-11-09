package com.packag.yvyor.projecttouristhelper;

import android.os.AsyncTask;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class jsonCurrency extends AsyncTask<Void,Void,Void> {
    String data = "";
    String cu1, cur2;
    frg_exchange frgExchange;
    public jsonCurrency(String cu1, String cur2, frg_exchange frgExchange) {
        this.cu1 = cu1;
        this.cur2 = cur2;
        this.frgExchange = frgExchange;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://free.currencyconverterapi.com/api/v6/convert?q="+cu1+"_"+cur2+"&compact=ultra&callback");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            data = bufferedReader.readLine().replaceAll("[^0-9.]", "");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        frgExchange.displayResults(data);
    }
}

