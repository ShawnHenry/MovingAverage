package com.paytm.challenge;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Test cases for {@link MovingAverageCalculatorImpl}
 *
 * @author Xiao Huang
 */
public class MovingAverageCalculatorImplTest {

    @Test
    public void testGetAverageWhenNoElementIsAppendedYet() {
        // Given
        int windowSize = 4;
        // When
        MovingAverageCalculator movingAverageCalculator = new MovingAverageCalculatorImpl(windowSize);
        // Then
        assertEquals(windowSize, movingAverageCalculator.getWindowSize());
        assertTrue(movingAverageCalculator.getAverage() == 0);
        List<Double> resultElements = movingAverageCalculator.getElements();
        assertNotNull(resultElements);
        assertTrue(resultElements.isEmpty());
    }

    @Test
    public void testGetAverageWhenOnlyOneElementIsAppended() {
        // Given
        int windowSize = 4;
        double element = 7;
        // When
        MovingAverageCalculator movingAverageCalculator = new MovingAverageCalculatorImpl(windowSize);
        movingAverageCalculator.add(element);
        // Then
        assertEquals(windowSize, movingAverageCalculator.getWindowSize());
        assertTrue(movingAverageCalculator.getAverage() == element);
        List<Double> resultElements = movingAverageCalculator.getElements();
        assertNotNull(resultElements);
        assertEquals(1, resultElements.size());
        assertTrue(resultElements.get(0).equals(element));
    }

    @Test
    public void testGetAverageWhenTwoElementsAreAppended() {
        // Given
        int windowSize = 4;
        double element0 = 5;
        double element1 = 3;
        // When
        MovingAverageCalculator movingAverageCalculator = new MovingAverageCalculatorImpl(windowSize);
        movingAverageCalculator.add(element0);
        movingAverageCalculator.add(element1);
        // Then
        assertEquals(windowSize, movingAverageCalculator.getWindowSize());
        assertTrue(movingAverageCalculator.getAverage() == 4);
        List<Double> resultElements = movingAverageCalculator.getElements();
        assertNotNull(resultElements);
        assertEquals(2, resultElements.size());
        assertTrue(resultElements.get(0).equals(element0));
        assertTrue(resultElements.get(1).equals(element1));
    }

    @Test
    public void testGetAverageWhenNegativeNumberIsAppended() {
        // Given
        int windowSize = 4;
        double element0 = 6;
        double element1 = -10;
        // When
        MovingAverageCalculator movingAverageCalculator = new MovingAverageCalculatorImpl(windowSize);
        movingAverageCalculator.add(element0);
        movingAverageCalculator.add(element1);
        // Then
        assertEquals(windowSize, movingAverageCalculator.getWindowSize());
        assertTrue(movingAverageCalculator.getAverage() == -2);
        List<Double> resultElements = movingAverageCalculator.getElements();
        assertNotNull(resultElements);
        assertEquals(2, resultElements.size());
        assertTrue(resultElements.get(0).equals(element0));
        assertTrue(resultElements.get(1).equals(element1));
    }

    @Test
    public void testGetAverageWhenElementsAreAppendedUntilWindowFull() {
        // Given
        int windowSize = 4;
        double[] testStream = { 5.0, 3.0, -5.0, 9.0, 1.0 };
        // When
        MovingAverageCalculator movingAverageCalculator = new MovingAverageCalculatorImpl(windowSize);
        for (int index = 0; index < windowSize; index++) {
            movingAverageCalculator.add(testStream[index]);
        }
        // Then
        assertEquals(windowSize, movingAverageCalculator.getWindowSize());
        assertTrue(movingAverageCalculator.getAverage() == 3);
        List<Double> resultElements = movingAverageCalculator.getElements();
        assertNotNull(resultElements);
        assertEquals(windowSize, resultElements.size());
        for (int index = 0; index < windowSize; index++) {
            assertTrue(resultElements.get(index).equals(testStream[index]));
        }
    }

    @Test
    public void testGetAverageWhenElementsAreAppendedAfterWindowFull() {
        // Given
        int windowSize = 4;
        double[] testStream = { 5.0, 3.0, -5.0, 9.0, 1.0, 7.0, 11.0, 1.0, 9.0 };
        // When
        MovingAverageCalculator movingAverageCalculator = new MovingAverageCalculatorImpl(windowSize);
        for (int index = 0; index < testStream.length; index++) {
            movingAverageCalculator.add(testStream[index]);
        }
        // Then
        assertEquals(windowSize, movingAverageCalculator.getWindowSize());
        assertTrue(movingAverageCalculator.getAverage() == 7);
        List<Double> resultElements = movingAverageCalculator.getElements();
        assertNotNull(resultElements);
        assertEquals(windowSize, resultElements.size());
        for (int index = 0; index < windowSize; index++) {
            assertTrue(resultElements.get(index).equals(testStream[testStream.length - windowSize + index]));
        }
    }

    @Test
    public void testGetAverageWhenWindowSizeIsGreaterThanStreamLength() {
        // Given
        double[] testStream = { 5.0, 3.0, -5.0, 9.0, 2.0, 7.0, 11.0, 4.0, 9.0 };
        int windowSize = testStream.length + 4;
        // When
        MovingAverageCalculator movingAverageCalculator = new MovingAverageCalculatorImpl(windowSize);
        for (int index = 0; index < testStream.length; index++) {
            movingAverageCalculator.add(testStream[index]);
        }
        // Then
        assertEquals(windowSize, movingAverageCalculator.getWindowSize());
        assertTrue(movingAverageCalculator.getAverage() == 5);
        List<Double> resultElements = movingAverageCalculator.getElements();
        assertNotNull(resultElements);
        assertEquals(testStream.length, resultElements.size());
        for (int index = 0; index < testStream.length; index++) {
            assertTrue(resultElements.get(index).equals(testStream[index]));
        }
    }

}
