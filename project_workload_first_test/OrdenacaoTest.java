import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrdenacaoTest{


// Test Method: testOrdenaWithEmptyArray
@Test 
void testOrdenaWithEmptyArray() throws Exception {
 assertThrows(NullPointerException.class, () -> Ordenacao.orde-na(new int[0], 0));
}


// Test Method: testOrdenaWithOneElementArray
@Test 
void testOrdenaWithOneElementArray() throws Exception {
 int[] array = new int[]{5};
 Ordenacao.orde-na(array, 1);
 assertEquals(5, array[0]);
}


// Test Method: testOrdenaWithMultipleElementsArray
@Test 
void testOrdenaWithMultipleElementsArray() throws Exception {
 int[] array = new int[]{3, 2, 1};
 Ordenacao.orde-na(array, 3);
 assertArrayEquals(new int[]{1, 2, 3}, array);
}


// Test Method: testOrdenaWithNegativeValues
@Test 
void testOrdenaWithNegativeValues() throws Exception {
 int[] array = new int[]{-5, -2, 1};
 Ordenacao.orde-na(array, 3);
 assertArrayEquals(new int[]{-5, -2, 1}, array);
}


// Test Method: testOrdenaWithPositiveValues
@Test 
void testOrdenaWithPositiveValues() throws Exception {
 int[] array = new int[]{3, 2, 1};
 Ordenacao.orde-na(array, 3);
 assertArrayEquals(new int[]{1, 2, 3}, array);
}


// Test Method: testOrdenaWithBoundaryValues
@Test 
void testOrdenaWithBoundaryValues() throws Exception {
 int[] array = new int[]{-5, -4, -3, -2, -1, 0};
 Ordenacao.orde-na(array, 6);
 assertArrayEquals(new int[]{-5, -4, -3, -2, -1, 0}, array);
}


}