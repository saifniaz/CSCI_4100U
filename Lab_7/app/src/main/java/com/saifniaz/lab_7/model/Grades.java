package com.saifniaz.lab_7.model;

import java.io.Serializable;

/**
 * Created by Me on 2017-11-06.
 */

public class Grades implements Serializable{
    private long studentId;
    private String courseComponent;
    private float mark;
    private long id;

    public Grades(long studentId,
                  String courseComponent,
                  float mark){
        this.studentId = studentId;
        this.courseComponent = courseComponent;
        this.mark = mark;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getCourseComponent() {
        return courseComponent;
    }

    public void setCourseComponent(String courseComponent) {
        this.courseComponent = courseComponent;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }


    @Override
    public String toString() {
        return studentId + " " + courseComponent + " " + mark;
    }
}
