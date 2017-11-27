package com.saifniaz.lab_5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.io.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements DataObserver{

    Intent show_Intent;

    View view;

    public static String url = "https://www.gnu.org/licenses/gpl.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showLic(View v){

        view = v;

        GetData getData = new GetData();
        getData.setObserver(this);
        getData.execute(new String[] {url});


    }

    public void dataUpdated(ArrayList<Data> data){

        String message = "";

        for(int i = 0; i < data.size(); i++){
            message += data.get(i).getLines() + "\n";
        }

        show_Intent = new Intent(view.getContext(), ShowLicence.class);
        show_Intent.putExtra("licence", message);
        startActivityForResult(show_Intent,0);
    }
}
