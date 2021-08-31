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
        temperature[0] = array1;
        humidity[0] = array2;
        pressure[0] = array3;
        date[0] = array4;
    }

    public String getTemperature(){
        return temperature[0];
    }

    public String getHumidity(){
        return humidity[0];
    }

    public String getPressure(){
        return pressure[0];
    }

    public String getDate(){
        return date[0];
    }

}
