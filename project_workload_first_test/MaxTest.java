import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxTest{


// Test Method: testMaxWithEmptyArray
@Test 
void testMaxWithEmptyArray(){
 assertNull(Max.max(new Item[0], 0));
}


// Test Method: testMaxWithOneElementArray
@Test 
void testMaxWithOneElementArray(){
 assertEquals(item, Max.max(new Item[]{item}, 1));
}


// Test Method: testMaxWithMultipleElementsArray
@Test 
void testMaxWithMultipleElementsArray(){
 assertEquals(item3, Max.max(new Item[]{item1, item2, item3}, 3));
}


// Test Method: testMaxWithNullElementArray
@Test 
void testMaxWithNullElementArray(){
 assertThrows(NullPointerException.class, () -> Max.max(new Item[]{null, item}, 2));
}


// Test Method: testMaxWithNegativeChaveArray
@Test 
void testMaxWithNegativeChaveArray(){
 assertEquals(item1, Max.max(new Item[]{item1, item2}, 2));
}


// Test Method: testMaxWithPositiveChaveArray
@Test 
void testMaxWithPositiveChaveArray(){
 assertEquals(item3, Max.max(new Item[]{item1, item2, item3}, 3));
}


}