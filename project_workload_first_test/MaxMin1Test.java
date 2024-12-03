import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxMin1Test{


// Test Method: testMaxMinWithEmptyArray
@Test 
void testMaxMinWithEmptyArray(){
 assertArrayEquals(new int[2], MaxMin1.maxMin1(new int[0], 0));
}


// Test Method: testMaxMinWithOneElementArray
@Test 
void testMaxMinWithOneElementArray(){
 int[] expected = new int[]{v, v};
 assertArrayEquals(expected, MaxMin1.maxMin1(new int[]{v}, 1));
}


// Test Method: testMaxMinWithMultipleElementsArray
@Test 
void testMaxMinWithMultipleElementsArray(){
 int[] expected = new int[]{max3, min1};
 assertArrayEquals(expected, MaxMin1.maxMin1(new int[]{min1, max2, min2, max3}, 4));
}


// Test Method: testMaxMinWithNegativeValues
@Test 
void testMaxMinWithNegativeValues(){
 int[] expected = new int[]{-10, -20};
 assertArrayEquals(expected, MaxMin1.maxMin1(new int[]{-20, -10}, 2));
}


// Test Method: testMaxMinWithPositiveValues
@Test 
void testMaxMinWithPositiveValues(){
 int[] expected = new int[]{100, 50};
 assertArrayEquals(expected, MaxMin1.maxMin1(new int[]{50, 100}, 2));
}


// Test Method: testMaxMinWithBoundaryValues
@Test 
void testMaxMinWithBoundaryValues(){
 int[] expected = new int[]{0, 0};
 assertArrayEquals(expected, MaxMin1.maxMin1(new int[]{0}, 1));
}


}