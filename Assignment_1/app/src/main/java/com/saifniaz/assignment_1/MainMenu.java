package com.saifniaz.assignment_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainMenu extends AppCompatActivity {

    Intent ask_Intent, summary_Intent;

    String[] questions;

    int Yes_count = 0 , No_count = 0, turn = 0;

    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button ask = (Button) findViewById(R.id.ask_button);
        questions = getResources().getStringArray(R.array.question);

        ask.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        view = v;
                        callIntent(view);
                    }
                }
        );
    }

    public void callIntent(View v){

       if(turn < 5){
           ask_Intent = new Intent(v.getContext(), AskQuestion.class);
           ask_Intent.putExtra("question", questions[turn]);
           startActivityForResult(ask_Intent, 0);
       }else{
           summary_Intent = new Intent(v.getContext(), Summary.class);
           String result = "Your number of Yes is " + Yes_count + " and the number of No is " + No_count;
           summary_Intent.putExtra("final", result);
           startActivity(summary_Intent);

       }
        turn++;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String msg = data.getStringExtra("results");
        if(msg.equals("Yes")){
            Yes_count++;
        }else{
            No_count++;
        }

        //Put the ask intent in a function so you can just call it now every time
        callIntent(view);
    }
}
