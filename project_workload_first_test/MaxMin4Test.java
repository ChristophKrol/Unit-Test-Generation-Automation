import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxMin4Test{


// Test Method: testMaxMin4WithEmptyArray
@Test 
void testMaxMin4WithEmptyArray(){
    int[] v = new int[0];
    assertEquals(new int[]{}, MaxMin4.maxMin4(v, 0, 0));
}



// Test Method: testMaxMin4WithOneElementArray
@Test 
void testMaxMin4WithOneElementArray(){
    int[] v = new int[]{1};
    assertEquals(new int[]{1, 1}, MaxMin4.maxMin4(v, 0, 0));
}



// Test Method: testMaxMin4WithTwoElementsArray
@Test 
void testMaxMin4WithTwoElementsArray(){
    int[] v = new int[]{1, 2};
    assertEquals(new int[]{2, 1}, MaxMin4.maxMin4(v, 0, 1));
}



// Test Method: testMaxMin4WithThreeElementsArray
@Test 
void testMaxMin4WithThreeElementsArray(){
    int[] v = new int[]{1, 2, 3};
    assertEquals(new int[]{3, 1}, MaxMin4.maxMin4(v, 0, 2));
}



// Test Method: testMaxMin4WithFourElementsArray
@Test 
void testMaxMin4WithFourElementsArray(){
    int[] v = new int[]{1, 3, 2, 4};
    assertEquals(new int[]{4, 1}, MaxMin4.maxMin4(v, 0, 3));
}



// Test Method: testMaxMin4WithNegativeValues
@Test 
void testMaxMin4WithNegativeValues(){
    int[] v = new int[]{-1, -2, -3};
    assertEquals(new int[]{-1, -3}, MaxMin4.maxMin4(v, 0, 2));
}



// Test Method: testMaxMin4WithPositiveAndNegativeValues
@Test 
void testMaxMin4WithPositiveAndNegativeValues(){
    int[] v = new int[]{1, -2, 3, -4};
    assertEquals(new int[]{1, -4}, MaxMin4.maxMin4(v, 0, 3));
}



}