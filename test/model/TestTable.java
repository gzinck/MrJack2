package test.model;
import static org.junit.Assert.*;

import model.Table;

import org.junit.Test;
public class TestTable 
{
	/*
	@Test
	public void testInitializeTable()
	{
		Table t1 = new Table();
		t1.initializeTable();
		String expected = "Bert";
		assertEquals(expected, t1.charDeck.getChar(0));
	}
	public void testRemoveChar()
	{
		Table t1 = new Table();
		t1.initializeTable();
		t1.discardCharacter("Bert");
		String expected = null;
		assertEquals(expected, t1.charDeck.getChar(0));
	}
	*/
	
	@Test
	public void testInitialize()
	{
		Table t1 = new Table();
	}
}
