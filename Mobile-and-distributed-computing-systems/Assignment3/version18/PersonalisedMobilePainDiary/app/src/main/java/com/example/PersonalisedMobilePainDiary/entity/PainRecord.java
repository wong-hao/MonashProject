package com.example.PersonalisedMobilePainDiary.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
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
    @ColumnInfo(name = "current_date")
    public String date ;
    @ColumnInfo(name = "current_temperature")
    public String temperature ;
    @ColumnInfo(name = "current_humidity")
    public String humidity ;
    @ColumnInfo(name = "current_pressure")
    public String  pressure;
    @ColumnInfo(name = "user_email")
    public String  email;

    public PainRecord(int intensity, String location, String mood, int steps, String date, String temperature, String humidity, String pressure, String email) {
        this.intensity=intensity;
        this.location=location;
        this.mood = mood;
        this.steps = steps;
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.email = email;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
