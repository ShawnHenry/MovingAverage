
## Moving Average Calculator

Implement a data structure that can provide the moving average of the last N elements added, add elements to the data structure and get access to the elements.

## Algorithm

 - Formula for moving sum and average

Let's start with the baseline scenario: 

There are a N elements in a stream as follows:
	
	E1, E2, ..., EN

In order to calculate the average of N elements, we need to perform the following math operations:
#1 Get the sum of all N elements
	
	Sum1 = E1 + E2 + ... + EN

#2 Divide the sum by N to get the result average
	
	Average1 = Sum1 / N

Now a new element E(N+1) is appended, the stream becomes
	E1, E2, ..., EN, E(N+1)
To calculate the new average of the last N elements, we repeat the two-step math operations as shown above.
	
	#1 Sum2 = E2 + E3 + ... + EN + E(N+1)
	#2 Average2 = Sum2 / N

As we can see, the divisor remains as N. The only difference is that the old dividend Sum1 is replaced with the new dividend Sum2. Since both Sum1 and Sum2 contains  E2, E3, ..., EN, Sum2 can be evaluated as follows:

	Sum2 = Sum1 - E1 + E(N+1)

When we keep adding new elements and repeat the process above, we can find the following formula:

	#1 currentSum = previousSum - previousHeadElement + newElementAdded
	#2 currentAverage = currentSum / N
	
where "currentSum" is the sum of current last N elements; "previousSum" is the sum of previous last N elements; "previousHeadElement" is the first element of previous last N elements; and "newElementAdded" is the last element newly added to current last N elements.

So all we need is to keep track of the last N elements in a stream, calculate the sum of the moving N elements, finally calculate the moving average by dividing the moving sum by N.

 - Formula for sliding window and ring buffer

This is a sliding window problem that can be resolved by a ring buffer which contains the sliding window of size N (last moving N elements) to save spaces. In the beginning, the sliding window head is increased from 0 by 1 when an element is appended to the sliding window until the sliding window is full (sliding window head is N-1). After that, the sliding window head is still incremented by 1 and becomes N; since it is a ring buffer, the window head is actually going back to the index 0 by following the formula as follows:

	currentHead = (previousHead + 1) % N

where "currentHead" is the current sliding window head; and "previousHead" is the previous sliding window head, which was holding the first element of previous last N elements and now holds the last element newly added to current last N elements.

 - Complexity

The time complexity is O(1)  as we can see from the formulas above.
The space complexity is O(1) as only the last N elements of window size in a stream is stored, rather than all elements in a stream.

## Technical debts

Below are some other technical debts present within current implementation:

 1. This implementation is NOT thread safe. Concurrency primitives and utilities like synchronized, lock, etc. can be implemented to overcome it.
 2. There is potential precision issue as the data type "double" is used to define the contract. BigDecimal can be used to resolve the issue.


## Run and test the application

**Prerequisite:**

 - JDK 8+ environment
 - Maven 3

**Run and test within IDE**

Within IDE, run the method "main" within the class "Main.java", then enter the window size according to the prompt. Test cases can be run with "MovingAverageCalculatorImplTest.java".

**Run as JAR**

To package as JAR, got to the project root folder, run the command as follows:

	mvn clean package

To run the JAR, run the command as follows:

	java -jar target/MovingAverage-1.0-SNAPSHOT.jar

then enter the window size according to the prompt.

