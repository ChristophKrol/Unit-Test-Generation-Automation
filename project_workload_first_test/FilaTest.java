import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilaTest{


// Test Method: testEmptyQueue
@Test
void testEmptyQueue(){
    Fila fila = new Fila();
    assertNull(fila.item);
}



// Test Method: testEnqueue
@Test
void testEnqueue() throws Exception{
    Fila fila = new Fila();
    Object item1 = "Item 1";
    Object item2 = "Item 2";
    fila.enfileira(item1);
    assertEquals(item1, fila.desenfileira());
}



// Test Method: testDequeue
@Test
void testDequeue() throws Exception{
    Fila fila = new Fila();
    Object item1 = "Item 1";
    Object item2 = "Item 2";
    fila.enfileira(item1);
    fila.enfileira(item2);
    assertEquals(item1, fila.desenfileira());
}



// Test Method: testQueueFull
@Test
void testQueueFull() throws Exception{
    Fila fila = new Fila();
    for (int i = 0; i < 1000; i++) {
        Object item = "Item" + i;
        fila.enfileira(item);
    }
    try {
        fila.enfileira("Item 1001");
        fail("Expected an exception");
    } catch (Exception e) {
        assertEquals("Erro: A fila esta cheia", e.getMessage());
    }
}



// Test Method: testQueueEmpty
@Test
void testQueueEmpty() throws Exception{
    Fila fila = new Fila();
    try {
        fila.desenfileira();
        fail("Expected an exception");
    } catch (Exception e) {
        assertEquals("Erro: A fila esta vazia", e.getMessage());
    }
}



// Test Method: testPrintQueue
@Test
void testPrintQueue() throws Exception{
    Fila fila = new Fila();
    Object item1 = "Item 1";
    Object item2 = "Item 2";
    fila.enfileira(item1);
    fila.enfileira(item2);
    fila.imprime();
}



}