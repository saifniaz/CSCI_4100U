package com.saifniaz.lab_5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowLicence extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_licence);

        TextView show = (TextView) findViewById(R.id.show_licence);

        Intent intent = getIntent();

        String licence = intent.getStringExtra("licence");

        show.setText(licence);

    }
}
