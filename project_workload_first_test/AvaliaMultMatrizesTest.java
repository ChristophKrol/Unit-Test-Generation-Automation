import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvaliaMultMatrizesTest{


// Test Method: testMultmatrizeWithEmptyArray
@Test 
void testMultmatrizeWithEmptyArray() throws IOException { 
    AvaliaMultMatrizes.multmatrize(0, new int[0]); 
}


// Test Method: testMultmatrizeWithOneElementArray
@Test 
void testMultmatrizeWithOneElementArray() throws IOException { 
    AvaliaMultMatrizes.multmatrize(1, new int[]{0}); 
}


// Test Method: testMultmatrizeWithMultipleElementsArray
@Test 
void testMultmatrizeWithMultipleElementsArray() throws IOException { 
    AvaliaMultMatrizes.multmatrize(3, new int[]{0, 1, 2}); 
}


// Test Method: testMultmatrizeWithNegativeArray
@Test 
void testMultmatrizeWithNegativeArray() throws IOException { 
    AvaliaMultMatrizes.multmatrize(2, new int[]{-1, 0, 1}); 
}


// Test Method: testMultmatrizeWithLargeArray
@Test 
void testMultmatrizeWithLargeArray() throws IOException { 
    AvaliaMultMatrizes.multmatrize(5, new int[]{0, 1, 2, 3, 4, 5}); 
}


}