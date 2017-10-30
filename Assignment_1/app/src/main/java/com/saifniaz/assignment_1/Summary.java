package com.saifniaz.assignment_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Summary extends AppCompatActivity {

    Intent intent;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        result = (TextView) findViewById(R.id.display_result);

        intent = getIntent();

        String res = intent.getStringExtra("final");

        result.setText(res);
    }
}
