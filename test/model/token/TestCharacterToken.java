package test.model.token;

import static org.junit.Assert.*; 
import model.tile.*;
import model.token.*;
import org.junit.Test;
public class TestCharacterToken 
{
	@Test
	public void testNewToken()
	{
		RegularTile rt = new RegularTile(2,4);
		CharacterToken ct = new CharacterToken("true", 3, rt);
		assertEquals("true", ct.getName());
	}
}
