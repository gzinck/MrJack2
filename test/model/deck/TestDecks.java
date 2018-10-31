package test.model.deck;
/**
 * Test class for the deck classes
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import model.Table;
import model.deck.AlibiDeck;
import model.deck.CharacterDeck;
import model.token.TokenConstants;

public class TestDecks {
	/** Character deck to be tested */
	private static CharacterDeck charDeck;
	/** Alibi deck to be tested */
	private static AlibiDeck alibiDeck;
	
	/**
	 * Sets up the decks
	 */
	@Before
	public void setUp() {
		charDeck = new CharacterDeck();
		alibiDeck = new AlibiDeck();
	}
	
	/** 
	 * Test for the character deck
	 */
	@Test
	public void testCharDeck(){
		HashSet<String> alreadyDone = new HashSet<String>();
		for(int i = 0; i < TokenConstants.NUM_CHARACTERS; i++) {
			String curr = charDeck.drawCard();
			assert(!alreadyDone.contains(curr));
			alreadyDone.add(curr);
		}
	}
	/**
	 * Test for the alibi deck
	 */
	@Test
	public void testAlibiDeck() {
		HashSet<String> alreadyDone = new HashSet<String>();
		for(int i = 0; i < TokenConstants.NUM_CHARACTERS; i++) {
			String curr = charDeck.drawCard();
			assert(!alreadyDone.contains(curr));
			alreadyDone.add(curr);
		}
	}
}
