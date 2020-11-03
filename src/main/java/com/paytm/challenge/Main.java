package com.paytm.challenge;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Main class to run sample tests
 *
 * @author Xiao Huang
 */
public class Main {

    public static void main(String[] args) {
        Double[] testStream = { 4.0, 1.0, 2.0, 5.0, 6.0, 3.0, 9.0, 1.0, 7.0 };
        int windowSize = 3;
        System.out.println("Testing the stream: " + Arrays.asList(testStream));
        System.out.println("Please enter the number of last elements you want to calculate the moving average:");
        Scanner scanner = new Scanner(System.in);
        try {
            windowSize = scanner.nextInt();
        } catch (Exception e) {
            System.err.println("Failed to enter the window size! The default value " + windowSize + " will be used." );
        }
        MovingAverageCalculator movingAverageCalculator = new MovingAverageCalculatorImpl(windowSize);
        for (double element : testStream) {
            movingAverageCalculator.add(element);
            System.out.println("Element " + element + " added, the moving average is " + movingAverageCalculator.getAverage()
                    + " for last " + windowSize + " elements within the stream " + movingAverageCalculator.getElements());
        }
    }
}
