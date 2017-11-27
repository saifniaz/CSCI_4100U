package com.saifniaz.lab_7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.saifniaz.lab_7.model.Grades;

import java.util.ArrayList;

public class DeleteGrades extends AppCompatActivity {

    ArrayList<Grades> grades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_grades);

        final Spinner spinner = (android.widget.Spinner) findViewById(R.id.delSpinner);

        //Intent intent = getIntent();
        Bundle bundleInput = getIntent().getExtras();

        grades = (ArrayList<Grades>) bundleInput.getSerializable("contactList");


        ArrayAdapter<Grades> adapter = new ArrayAdapter<Grades>(this,
                android.R.layout.simple_spinner_item, grades);

        spinner.setAdapter(adapter);


        Button delete = (Button) findViewById(R.id.delGrades);

        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String temp = spinner.getSelectedItem().toString();
                        String[] line = temp.split(" ");

                        Intent resultIntent = new Intent(Intent.ACTION_PICK);
                        resultIntent.putExtra("index", line[0]);
                        setResult(1, resultIntent);
                        finish();
                    }
                }
        );

    }
}
