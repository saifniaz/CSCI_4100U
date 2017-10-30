package com.saifniaz.lab_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText uname = (EditText)findViewById(R.id.uname);
        final EditText pass = (EditText)findViewById(R.id.pass);
        Button signIn = (Button)findViewById(R.id.sign_in);

        signIn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent goBack = new Intent(v.getContext(), MainActivity.class);
                        if(uname.getText().toString().equals("Saif") && pass.getText().toString().equals("Niaz")){
                            goBack.putExtra("results", "Successful");
                            setResult(RESULT_OK, goBack);
                            finish();
                        }else{
                            goBack.putExtra("results", "Unsuccessful");
                            setResult(RESULT_OK,goBack);
                            finish();
                        }
                    }
                }
        );
    }

}
