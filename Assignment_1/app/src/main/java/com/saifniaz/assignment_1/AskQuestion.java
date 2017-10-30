package com.saifniaz.assignment_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AskQuestion extends AppCompatActivity {

    TextView ques;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);

        ques = (TextView) findViewById(R.id.Q_view);

        intent = getIntent();

        String question = intent.getStringExtra("question");

        SetQues(question);

        Button yes = (Button) findViewById(R.id.yes);
        Button no = (Button) findViewById(R.id.no);

        yes.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent main_menu = new  Intent(v.getContext(), MainMenu.class);
                        main_menu.putExtra("results", "Yes");
                        setResult(RESULT_OK, main_menu);
                        finish();
                    }
                }
        );

        no.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent main_menu = new  Intent(v.getContext(), MainMenu.class);
                        main_menu.putExtra("results", "No");
                        setResult(RESULT_OK, main_menu);
                        finish();
                    }
                }
        );
    }

    public void SetQues(String string){
        ques.setText(string);
    }
}
