import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabelaTest{


// Test Method: testTabelaEmptyArray
@Test
void testTabelaEmptyArray(){
	assertNull(Tabela.registros);
}


// Test Method: testTabelaOneElementArray
@Test
void testTabelaOneElementArray(){
	Item item = new MeuItem(1);
	Tabela tabela = new Tabela();
	tabela.insere(item);
	assertEquals(item, tabela.registros[0]);
}


// Test Method: testTabelaMultipleElementsArray
@Test
void testTabelaMultipleElementsArray(){
	Item item1 = new MeuItem(1), item2 = new MeuItem(2), item3 = new MeuItem(3);
	Tabela tabela = new Tabela();
	tabela.insere(item1); tabela.insere(item2); tabela.insere(item3);
	assertEquals(item3, tabela.registros[tabela.n]);
}


// Test Method: testTabelaSearch
@Test
void testTabelaSearch(){
	Item item = new MeuItem(1), searchItem = new MeuItem(2);
	Tabela tabela = new Tabela();
	tabela.insere(item); tabela.insere(searchItem);
	assertEquals(searchItem, tabela.registros[tabela.n-1]);
}


// Test Method: testPermutacaoRandomica
@Test
void testPermutacaoRandomica(){
	MeuItem[] v = new MeuItem[20];
	for (int i = 1; i <= 20; i++) v[i] = new MeuItem(i);
	PermutacaoRandomica.permut(v, 20);
	// TODO: implement assertion for permutation
}


}