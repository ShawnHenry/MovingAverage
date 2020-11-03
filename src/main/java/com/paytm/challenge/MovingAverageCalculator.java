package com.paytm.challenge;

import java.util.List;

/**
 * Interface that provides the moving average of the last N elements added,
 * add elements to the data structure and get access to the elements.
 *
 * <p>
 *      FIXME: The data type "double" provided within the contract can be replaced with "BigDecimal"
 *       if precision is required.
 * </p>
 *
 * @author Xiao Huang
 */
public interface MovingAverageCalculator {
    /**
     * get the sliding window size, which is also known as N in the context
     *
     * @return the sliding window size
     */
    public int getWindowSize();

    /**
     * provide the moving average of the last N elements added
     *
     * @return the last N elements in the same order as they are added to the stream
     */
    public double getAverage();

    /**
     * add an element to the underlying data structure
     *
     * @param element a new element to be appended to the stream
     */
    public void add(double element);

    /**
     * get access to the last N elements
     *
     * @return the last N elements in the same order as they are added to the stream
     */
    public List<Double> getElements();
}
