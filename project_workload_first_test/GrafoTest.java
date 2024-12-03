import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrafoTest{


// Test Method: testGrafoTranspostoWithEmptyGraph
@Test
void testGrafoTranspostoWithEmptyGraph(){
	Grafo grafo = new Grafo();
	Grafo transposed = grafo.grafoTransposto();
	assertEquals(grafo, transposed);
}



// Test Method: testGrafoTranspostoWithSingleEdge
@Test
void testGrafoTranspostoWithSingleEdge(){
	Grafo grafo = new Grafo();
	grafo.insereAresta(0, 1, 5);
	Grafo transposed = grafo.grafoTransposto();
	assertEquals(new Grafo(), transposed);
}



// Test Method: testGrafoTranspostoWithMultipleEdges
@Test
void testGrafoTranspostoWithMultipleEdges(){
	Grafo grafo = new Grafo();
	grafo.insereAresta(0, 1, 5);
	grafo.insereAresta(1, 2, 3);
	grafo.insereAresta(2, 0, 4);
	Grafo transposed = grafo.grafoTransposto();
	assertEquals(new Grafo(), transposed);
}



}