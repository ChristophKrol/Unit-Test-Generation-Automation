import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxMin3Test{


// Test Method: testMaxMin3WithEmptyArray
@Test 
void testMaxMin3WithEmptyArray(){
    int[] v = new int[0];
    assertEquals(new int[]{}, MaxMin3.maxMin3(v, 0));
}



// Test Method: testMaxMin3WithOneElementArray
@Test 
void testMaxMin3WithOneElementArray(){
    int[] v = new int[1];
    assertEquals(new int[]{v[0], v[0]}, MaxMin3.maxMin3(v, 1));
}



// Test Method: testMaxMin3WithTwoElementsArray
@Test 
void testMaxMin3WithTwoElementsArray(){
    int[] v = new int[2];
    assertEquals(new int[]{v[0], v[1]}, MaxMin3.maxMin3(v, 2));
}



// Test Method: testMaxMin3WithThreeElementsArray
@Test 
void testMaxMin3WithThreeElementsArray(){
    int[] v = new int[3];
    assertEquals(new int[]{v[0], v[2]}, MaxMin3.maxMin3(v, 3));
}



// Test Method: testMaxMin3WithNegativeValues
@Test 
void testMaxMin3WithNegativeValues(){
    int[] v = new int[]{1, -2, 3};
    assertEquals(new int[]{-2, 3}, MaxMin3.maxMin3(v, 3));
}



// Test Method: testMaxMin3WithPositiveAndNegativeValues
@Test 
void testMaxMin3WithPositiveAndNegativeValues(){
    int[] v = new int[]{-1, -2, 0, 1};
    assertEquals(new int[]{1, -2}, MaxMin3.maxMin3(v, 4));
}



}