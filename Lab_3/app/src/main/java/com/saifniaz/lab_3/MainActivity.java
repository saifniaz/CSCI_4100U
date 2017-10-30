package com.saifniaz.lab_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button about = (Button)findViewById(R.id.about);
        Button login = (Button)findViewById(R.id.login);

        about.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent aboutIn = new Intent(v.getContext(), AboutActivity.class);
                        startActivity(aboutIn);
                    }
                }
        );

        login.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent loginIn = new Intent(v.getContext(), LoginActivity.class);
                        startActivityForResult(loginIn,7);
                    }
                }
        );


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String msg = data.getStringExtra("results");
        Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
    }
}
