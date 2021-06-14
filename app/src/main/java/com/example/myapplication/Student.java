package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity
public class Student implements Serializable{
    @PrimaryKey
    @NotNull
    public String rollNo;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "class")
    public int stdClass;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "school")
    public String school;

    /* getters */
    public String getRollNo(){
        return rollNo;
    }
    public String getName(){
        return name;
    }
    public int getStdClass(){
        return stdClass;
    }
    public String getAddress(){
        return address;
    }
    public String getSchool(){
        return school;
    }

    /* setters */
    public void setRollNo(String rollNo){
        this.rollNo = rollNo;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setStdClass(int stdClass){
        this.stdClass = stdClass;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setSchool(String school){
        this.school = school;
    }
}
