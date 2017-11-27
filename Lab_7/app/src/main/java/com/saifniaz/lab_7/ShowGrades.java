package com.saifniaz.lab_7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.saifniaz.lab_7.model.Grades;
import com.saifniaz.lab_7.model.GradesDBHelper;

public class ShowGrades extends AppCompatActivity {

    private ListView listView;
    ArrayList<Grades> gradeList;
    ArrayAdapter<Grades> adapter;

    protected GradesDBHelper helper = new GradesDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_grades);

        listView = (ListView) findViewById(R.id.list);

        gradeList = new ArrayList<Grades>();

        adapter = new ArrayAdapter<Grades>(this, android.R.layout.simple_list_item_1,gradeList);

        listView.setAdapter(adapter);

        final Button add = (Button) findViewById(R.id.add);

        final Button delete = (Button) findViewById(R.id.delete);

        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent addIntent = new Intent(view.getContext(), AddGrade.class);
                        startActivityForResult(addIntent, 0);
                    }
                }
        );

        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent deleteIntent = new Intent(view.getContext(), DeleteGrades.class);
                        deleteIntent.putExtra("contactList", gradeList);
                        startActivityForResult(deleteIntent, 1);
                    }
                }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("SQLite", "getAllContacts():");
        List<Grades> grades = helper.getAllGrades();
        for (int i = 0; i < grades.size(); i++) {
            Grades current = grades.get(i);
            gradeList.add(current);
        }

        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 0){
            long studentId = Integer.parseInt(data.getStringExtra("studentId"));
            String courseComponent = data.getStringExtra("courseComponent");
            float marks = Float.parseFloat(data.getStringExtra("mark"));

            Grades grade1 = helper.createGrade(studentId, courseComponent, marks);

            //gradeList.add(grade1);



        }else if(resultCode == 1){
            long index = Integer.parseInt(data.getStringExtra("index"));

            for(Iterator<Grades> iterator = gradeList.iterator(); iterator.hasNext();){
                Grades count = iterator.next();
                if(count.getStudentId() ==  index){
                    boolean deleteSuccess = helper.deleteGrade(count.getStudentId());
                    Log.i("SQLite", "deleteSuccess == " + deleteSuccess);
                    adapter.remove(count);
                    //adapter.notifyDataSetChanged();
                    break;
                }
            }
        }
        //adapter.notifyDataSetChanged();
    }

    /*private void testModel() {
        ContactHelper contactHelper = new ContactHelper(this);

        contactHelper.deleteAllContacts();

        Log.i("SQLite", "getAllContacts():");
        List<Contact> allContacts = contactHelper.getAllContacts();
        for (int i = 0; i < allContacts.size(); i++) {
            Contact current = allContacts.get(i);
            Log.i("SQLite", current.getFirstName() + " " + current.getLastName() +
                    " - " + current.getPhone());
        }

        Contact lramirez2 = contactHelper.getContact(lramirez.getId());
        Log.i("SQLite", "getContact():");
        Log.i("SQLite", lramirez2.getFirstName() + " " + lramirez2.getLastName());

        lramirez2.setPhone("111-222-3333");
        boolean updateSuccess = contactHelper.updateContact(lramirez2);
        Log.i("SQLite", "updateSuccess == " + updateSuccess);

        boolean deleteSuccess = contactHelper.deleteContact(deleteMe.getId());
        Log.i("SQLite", "deleteSuccess == " + deleteSuccess);

        Log.i("SQLite", "getAllContacts():");
        allContacts = contactHelper.getAllContacts();
        for (int i = 0; i < allContacts.size(); i++) {
            Contact current = allContacts.get(i);
            Log.i("SQLite", current.getFirstName() + " " + current.getLastName() +
                    " - " + current.getPhone());
        }
    }*/
}