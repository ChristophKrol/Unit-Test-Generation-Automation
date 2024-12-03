import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxMin2Test{


// Test Method: testMaxMin2WithEmptyArray
@Test 
void testMaxMin2WithEmptyArray(){
 int[] result = MaxMin2.maxMin2(new int[0], 0);
 assertArrayEquals(new int[]{}, result);
}


// Test Method: testMaxMin2WithOneElementArray
@Test 
void testMaxMin2WithOneElementArray(){
 int[] result = MaxMin2.maxMin2(new int[]{5}, 1);
 assertArrayEquals(new int[]{5, 5}, result);
}


// Test Method: testMaxMin2WithMultipleElementsArray
@Test 
void testMaxMin2WithMultipleElementsArray(){
 int[] result = MaxMin2.maxMin2(new int[]{-3, 1, 5}, 3);
 assertArrayEquals(new int[]{5, -3}, result);
}


// Test Method: testMaxMin2WithNegativeValues
@Test 
void testMaxMin2WithNegativeValues(){
 int[] result = MaxMin2.maxMin2(new int[]{-10, -20, 0}, 3);
 assertArrayEquals(new int[]{0, -20}, result);
}


// Test Method: testMaxMin2WithEqualElements
@Test 
void testMaxMin2WithEqualElements(){
 int[] result = MaxMin2.maxMin2(new int[]{5, 5, 5}, 3);
 assertArrayEquals(new int[]{5, 5}, result);
}


// Test Method: testMaxMin2WithUninitializedArray
@Test 
void testMaxMin2WithUninitializedArray(){
 try { MaxMin2.maxMin2(null, 0); fail(); } catch (NullPointerException e) {}
}


}