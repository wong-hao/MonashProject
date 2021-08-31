package com.example.PersonalisedMobilePainDiary.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "PainRecord")
public class PainRecord {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "pain_intensity_level")
    public int intensity;
    @ColumnInfo(name = "location_of_pain")
    public String location;
    @ColumnInfo(name = "mood_level")
    public String mood ;
    @ColumnInfo(name = "steps_taken")
    public int steps ;

    public PainRecord(int intensity, String location, int steps) {
        this.intensity=intensity;
        this.location=location;
        this.mood = mood;
        this.steps = steps;
    }

    public int getId() {
        return uid;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}
