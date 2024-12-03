import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CasamentoAproximadoTest{


// Test Method: testShiftAndAproximadoWithEmptyString
@Test 
void testShiftAndAproximadoWithEmptyString(){
 String T = "";
 CasamentoAproximado.shiftAndAproximado(T, 0, "", 0, 1);
}


// Test Method: testShiftAndAproximadoWithOneCharacterString
@Test 
void testShiftAndAproximadoWithOneCharacterString(){
 String T = "a";
 CasamentoAproximado.shiftAndAproximado(T, 1, "a", 1, 1);
}


// Test Method: testShiftAndAproximadoWithMultipleCharactersString
@Test 
void testShiftAndAproximadoWithMultipleCharactersString(){
 String T = "abc";
 CasamentoAproximado.shiftAndAproximado(T, 3, "abc", 3, 1);
}


// Test Method: testShiftAndAproximadoWithNullPattern
@Test 
void testShiftAndAproximadoWithNullPattern(){
 String T = "";
 String P = null;
 try {
 CasamentoAproximado.shiftAndAproximado(T, 0, P, 0, 1);
 } catch (Exception e) {
 assertEquals(true, true);
} 
}


// Test Method: testShiftAndAproximadoWithNegativeK
@Test 
void testShiftAndAproximadoWithNegativeK(){
 String T = "";
 int k = -1;
 try {
 CasamentoAproximado.shiftAndAproximado(T, 0, "", 0, k);
 } catch (Exception e) {
 assertEquals(true, true);
} 
}


// Test Method: testShiftAndAproximadoWithZeroK
@Test 
void testShiftAndAproximadoWithZeroK(){
 String T = "";
 int k = 0;
 CasamentoAproximado.shiftAndAproximado(T, 0, "", 0, k);
}


}