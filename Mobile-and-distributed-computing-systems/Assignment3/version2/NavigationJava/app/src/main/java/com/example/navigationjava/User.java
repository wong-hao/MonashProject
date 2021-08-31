package com.example.navigationjava;

public class User {

    String[] username = new String[14400];
    String[] password = new String[14400];
    int m=0;
    int n=0;

    private User(){}

    private static User instance;

    public static User getInstance(){
        if(instance == null){
            instance = new User();
        }
        return instance;
    }
    public void register(String array1, String array2){
        username[m++] = array1;
        password[n++] = array2;
    }

    public boolean login(String array1, String array2){
        for(int i=0;i<m;i++){
            if(username[i].equals(array1)){
                if(password[i].equals(array2)){
                    return true;
                }
            }
        }

        return false;
    }

}
