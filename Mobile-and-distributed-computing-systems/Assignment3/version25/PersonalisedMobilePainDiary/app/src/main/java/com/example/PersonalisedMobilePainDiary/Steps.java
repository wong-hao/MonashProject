package com.example.PersonalisedMobilePainDiary;

public class Steps {
    int[] steps_taken = new int[14400];
    int[] goal = new int[14400];
    int m=0;
    int n=0;

    private Steps(){}

    private static Steps instance;

    public static Steps getInstance(){
        if(instance == null){
            instance = new Steps();
        }
        return instance;
    }

    public void setSteps_taken(int number1){
        steps_taken[m++] = number1;
    }

    public void setGoal(int number1){
        goal[n++] = number1;
    }

    public int getSteps_taken(){
        return steps_taken[m];
    }

    public int getGoal(){
        return goal[n];
    }

}
