package com.saifniaz.lab_7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddGrade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade);

        Button addContact = (Button) findViewById(R.id.addGrade);

        final EditText idInput = (EditText) findViewById(R.id.idInput);
        final EditText courseInput = (EditText) findViewById(R.id.courseInput);
        final EditText markInput = (EditText) findViewById(R.id.markInput);

        addContact.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent resultIntent = new Intent(Intent.ACTION_PICK);
                        resultIntent.putExtra("studentId", idInput.getText().toString());
                        resultIntent.putExtra("courseComponent", courseInput.getText().toString());
                        resultIntent.putExtra("mark", markInput.getText().toString());
                        setResult(0, resultIntent);
                        finish();
                    }
                }
        );
    }
}
