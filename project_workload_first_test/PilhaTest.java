import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PilhaTest{


// Test Method: testPilhaEmptyArray
@Test
void testPilhaEmptyArray(){
  Pilha pilha = new Pilha();
  assertEquals(0, pilha.tamanho());
}



// Test Method: testPilhaPushOneElement
@Test
void testPilhaPushOneElement(){
  Pilha pilha = new Pilha();
  pilha.empilha("item");
  assertEquals(1, pilha.tamanho());
}



// Test Method: testPilhaPopFromEmptyArray
@Test
void testPilhaPopFromEmptyArray(){
  Pilha pilha = new Pilha();
  try {
    pilha.desempilha();
    fail("Expected Exception");
  } catch (Exception e) {}
}



// Test Method: testPilhaPushMultipleElements
@Test
void testPilhaPushMultipleElements(){
  Pilha pilha = new Pilha();
  pilha.empilha("item1");
  pilha.empilha("item2");
  pilha.empilha("item3");
  assertEquals(3, pilha.tamanho());
}



// Test Method: testPilhaPopFromFullArray
@Test
void testPilhaPopFromFullArray(){
  Pilha pilha = new Pilha();
  for (int i = 0; i < 1000; i++) {
    pilha.empilha("item");
  }
  try {
    pilha.desempilha();
    fail("Expected Exception");
  } catch (Exception e) {}
}



// Test Method: testPilhaIsEmpty
@Test
void testPilhaIsEmpty(){
  Pilha pilha = new Pilha();
  assertTrue(pilha.vazia());
}



}