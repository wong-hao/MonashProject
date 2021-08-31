package com.example.PersonalisedMobilePainDiary;

public class Weather {
    String[] temperature = new String[14400];
    String[] humidity = new String[14400];
    String[] pressure = new String[14400];
    String[] date = new String[14400];
    int m=0;
    int n=0;
    int o=0;
    int p=0;


    private Weather(){}

    private static Weather instance;

    public static Weather getInstance(){
        if(instance == null){
            instance = new Weather();
        }
        return instance;
    }

    public void setMessage(String array1, String array2, String array3, String array4){
        temperature[m++] = array1;
        humidity[n++] = array2;
        pressure[o++] = array3;
        date[p++] = array4;
    }

    public String getTemperature(){
        return temperature[m];
    }

    public String getHumidity(){
        return humidity[n];
    }

    public String getPressure(){
        return pressure[o];
    }

    public String getDate(){
        return date[p];
    }

}
