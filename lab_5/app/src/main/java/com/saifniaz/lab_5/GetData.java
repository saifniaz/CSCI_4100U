package com.saifniaz.lab_5;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Me on 2017-10-29.
 */

public class GetData extends AsyncTask<String, Void, ArrayList<Data>> {

    private DataObserver observer;

    @Override
    protected void onPostExecute(ArrayList<Data> data) {
        observer.dataUpdated(data);
    }

    public void setObserver(DataObserver observer){
        this.observer = observer;
    }

    @Override
    protected ArrayList<Data> doInBackground(String... urls) {
        ArrayList<Data> datas = new ArrayList<>();


        try{

            URL url = new URL("https://www.gnu.org/licenses/gpl.txt");
            HttpURLConnection conn;
            conn = (HttpsURLConnection)url.openConnection();

            InputStream inputStream = conn.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while((line = input.readLine()) != null){
                Data data = new Data(line);
                datas.add(data);
            }

            inputStream.close();
            conn.disconnect();

        }catch (Exception e){
            e.printStackTrace();
        }

        return datas;
    }
}

