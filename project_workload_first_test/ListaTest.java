import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaTest{


// Test Method: testEmptyList
@Test
void testEmptyList(){
 assertEquals(true, lista.vazia());
}



// Test Method: testInsertOneElement
@Test
void testInsertOneElement(){
 lista.insere(1);
 assertEquals(false, lista.vazia());
}



// Test Method: testInsertMultipleElements
@Test
void testInsertMultipleElements(){
 for (int i = 0; i < 5; i++){
 lista.insere(i);
 }
 assertEquals(false, lista.vazia());
}



// Test Method: testPrintList
@Test
void testPrintList(){
 lista.insere(1); lista.insere(2); lista.insere(3);
 lista.imprime();
}



}