package org.example;

public class Meter {

    public double calculateSpeed(double distance, double time){
        double result = distance/time;
        return (double) Math.round(result * 100) / 100;
    }
}
