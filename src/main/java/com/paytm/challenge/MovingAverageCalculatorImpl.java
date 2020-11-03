package com.paytm.challenge;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation that provides the moving average of the last N elements added,
 * add elements to the data structure and get access to the elements.
 *
 * <p>
 *      FIXME: This implementation is NOT thread safe.
 * </p>
 *
 * @author Xiao Huang
 */
public class MovingAverageCalculatorImpl implements MovingAverageCalculator {
    // the ring buffer to hold the moving last N elements
    private final double[] lastNElements;
    // the sliding window head to track the first element of moving last N elements
    private int headOfLastNElements;
    // the sum of last N elements
    private double sumOfLastNElements;
    // the total number of last N elements, increasing from 0 by 1 until reaching the window size N
    private int totalNumberOfLastNElements;

    public MovingAverageCalculatorImpl(int windowSize) {
        if (windowSize <= 0) {
            throw new IllegalArgumentException("Window size must be a positive number!");
        }
        lastNElements = new double[windowSize];
        headOfLastNElements = 0;
        sumOfLastNElements = 0;
        totalNumberOfLastNElements = 0;
    }

    /**
     * get the sliding window size, which is also known as N in the context
     *
     * @return the sliding window size
     */
    @Override
    public int getWindowSize() {
        return lastNElements.length;
    }

    /**
     * provide the moving average of the last N elements added
     *
     * @return the last N elements in the same order as they are added to the stream
     */
    @Override
    public double getAverage() {
        return totalNumberOfLastNElements == 0 ? 0 : sumOfLastNElements / totalNumberOfLastNElements;
    }

    /**
     * add an element to the underlying data structure
     *
     * @param newElement a new element to be appended to the stream
     */
    @Override
    public void add(double newElement) {
        int windowSize = getWindowSize();
        if (totalNumberOfLastNElements == windowSize) {// the ring buffer was already full
            // the first element within the previous last N element is NO longer considered as one of the current last N elements
            // since the new element "newElement" is just added as one of the current last N elements
            sumOfLastNElements -= lastNElements[headOfLastNElements];
            totalNumberOfLastNElements--;
        }
        // move the head forward and make the space for the new element just added
        lastNElements[headOfLastNElements] = newElement;
        headOfLastNElements = (headOfLastNElements + 1) % windowSize;
        // calculate the new sum of last N elements
        sumOfLastNElements += newElement;
        totalNumberOfLastNElements++;
    }

    /**
     * get access to the last N elements
     *
     * @return the last N elements in the same order as they are added to the stream
     */
    @Override
    public List<Double> getElements() {
        List<Double> elements = new ArrayList<>(totalNumberOfLastNElements);
        int windowSize = getWindowSize();
        if (totalNumberOfLastNElements == windowSize) {// the ring buffer was already full
            for (int index = 0; index < totalNumberOfLastNElements; index++) {
                elements.add(lastNElements[(headOfLastNElements + index) % windowSize]);
            }
        } else {
            for (int index = 0; index < totalNumberOfLastNElements; index++) {
                elements.add(lastNElements[index]);
            }
        }
        return elements;
    }

}
