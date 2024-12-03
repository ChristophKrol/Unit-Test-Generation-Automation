import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest{


// Test Method: testFibIterWithZero
@Test 
void testFibIterWithZero(){
 assertEquals(0, Fibonacci.fibIter(0));
}


// Test Method: testFibIterWithOne
@Test 
void testFibIterWithOne(){
 assertEquals(1, Fibonacci.fibIter(1));
}


// Test Method: testFibIterWithTwo
@Test 
void testFibIterWithTwo(){
 assertEquals(1, Fibonacci.fibIter(2));
}


// Test Method: testFibIterWithNegativeOne
@Test 
void testFibIterWithNegativeOne() throws Exception {
 try {
 assertEquals(-1, Fibonacci.fibIter(-1));
 } catch (Exception e) {
 fail("Expected fibIter(-1) to return -1 but it threw an exception");
 }
}


// Test Method: testFibIterWithPositiveOne
@Test 
void testFibIterWithPositiveOne(){
 assertEquals(1, Fibonacci.fibIter(1));
}


// Test Method: testFibIterWithLargeNumber
@Test 
void testFibIterWithLargeNumber(){
 assertEquals(34, Fibonacci.fibIter(8));
}


}