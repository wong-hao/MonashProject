package com.example.twoactivitiesjava;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    private int id = 0;
    private String name = "";
    public Student(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(name);
    }
    public int describeContents() {
        return 0;
    }
    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }
        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
    public void setId(int id) { this.id = id; }
    public void setName(String name){ this.name = name; }
    public int getId(){ return id; }
    public String getName(){ return name; }
}

