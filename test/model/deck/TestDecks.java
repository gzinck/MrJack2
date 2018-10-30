package test.model.deck;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import model.Table;
import model.deck.AlibiDeck;
import model.deck.CharacterDeck;
import model.token.TokenConstants;

public class TestDecks {
	
	private static CharacterDeck charDeck;
	private static AlibiDeck alibiDeck;
	
	@Before
	public void setUp() {
		charDeck = new CharacterDeck();
		alibiDeck = new AlibiDeck();
	}
	
	@Test
	public void testCharDeck(){
		HashSet<String> alreadyDone = new HashSet<String>();
		for(int i = 0; i < TokenConstants.NUM_CHARACTERS; i++) {
			String curr = charDeck.drawCard();
			assert(!alreadyDone.contains(curr));
			alreadyDone.add(curr);
		}
	}
	
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
