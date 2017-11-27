package com.saifniaz.lab_7.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Me on 2017-11-12.
 */

public class GradesDBHelper extends SQLiteOpenHelper{
    static final int DATABASE_VERSION = 1;

    static final String TABLE = "Grades";

    static final String CREATE_STATEMENT = "CREATE TABLE Grades (\n" +
            "      studentId integer primary key,\n" +
            "      courseComponent varchar(100) not null,\n" +
            "      mark decimal not null\n" +
            ")\n";

    static final String DROP_STATEMENT = "DROP TABLE contacts";

    public GradesDBHelper(Context context) {
        super(context, TABLE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Grades createGrade(long studentId,
                              String courseComponent,
                              float mark){

        Grades grades = new Grades(studentId, courseComponent, mark);

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues newValue = new ContentValues();
        newValue.put("studentId", studentId);
        newValue.put("courseComponent", courseComponent);
        newValue.put("mark", mark);

        database.insert(TABLE, null, newValue);

        return grades;
    }

    public List<Grades> getAllGrades(){
        List<Grades> grades = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        String[] columns = new String[]{"studentId", "courseComponent", " mark"};
        String where = "";
        String[] whereArgs = new String[]{  };
        Cursor cursor = database.query(TABLE, columns, null, null,  "", "", "courseComponent");

        cursor.moveToFirst();
        do{
            if(!cursor.isAfterLast()){
                long id = cursor.getLong(0);
                String courseComponent = cursor.getString(1);
                float mark = cursor.getFloat(2);

                Grades grade = new Grades(id, courseComponent, mark);

                grades.add(grade);
            }
            cursor.moveToNext();
        }while (!cursor.isAfterLast());

        Log.i("SQLite", "getAllContacts(): num = " + grades.size());

        return grades;
    }

    public boolean deleteGrade(long id){
        SQLiteDatabase database = this.getWritableDatabase();

        int numRows = database.delete(TABLE, "studentId = ?", new String[] {"" + id});

        return (numRows == 1);
    }

}
