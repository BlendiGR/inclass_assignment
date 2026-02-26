package org.example;
import java.util.Scanner;


public class Main {

    static void main() {
        Meter meter = new Meter();

        Scanner scan = new Scanner(System.in);
        System.out.println("Distance (meter): ");
        double distance = Double.parseDouble(scan.nextLine());
        System.out.println("Time (second): ");
        double time = Double.parseDouble(scan.nextLine());

        System.out.println(meter.calculateSpeed(distance, time) + " m/s");

    }
}
