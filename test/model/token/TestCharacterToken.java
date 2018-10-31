/**
 * Test class for CharacterToken
 * 
 * @author Graeme Zinck and Charles Jobin
 */
package test.model.token;

import static org.junit.Assert.*; 
import model.ability.*;
import model.tile.*;
import model.token.*;
import org.junit.Test;
public class TestCharacterToken 
{
	MoveBarricadeAbility mb;
	/**
	 * Test assuring a new token will have the input name given
	 */
	@Test
	public void testNewToken()
	{
		RegularTile rt = new RegularTile(2,4);
		CharacterToken ct = new CharacterToken("Buddy", 3, rt);
		assertEquals("Buddy", ct.getName());
	}
	
	/** 
	 * test assuring selectMrJack assigns MrJack to a character
	 */
	@Test 
	public void testSelectMrJack()
	{
		RegularTile rt = new RegularTile(2,4);
		CharacterToken ct = new CharacterToken("Buddy", 3, rt);
		ct.selectMrJack();
		assertEquals(true, ct.isMrJack());
	}
	
	/**
	 * Test for setting an ability for a character
	 */
	@Test
	public void testSetAbility()
	{
		RegularTile rt = new RegularTile(2,4);
		CharacterToken ct = new CharacterToken("Buddy", 3, rt);
		ct.setAbility(mb);
		assertEquals(true, ct.getAbility()==mb);
	}	
	
}
