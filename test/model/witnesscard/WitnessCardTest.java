package test.model.witnesscard;
import static org.junit.Assert.*;

import model.tile.*;
import model.token.*;
import model.*;
import org.junit.Before;
import org.junit.Test;
public class WitnessCardTest 
{
	@Test
	public void testNewCard()
	{
		RegularTile reg = new RegularTile(3,4);
		CharacterToken ct = new CharacterToken("Bill", 3, reg);
		WitnessCard wc = new WitnessCard(ct);
		assertEquals(true, wc.getWitnessed());
		assertEquals(ct, wc.getCharacterToken());
	}
	
	@Test
	public void testUpdateWitnessed()
	{
		Lamppost lamp = new Lamppost(2,4);
		GasLight gs = new GasLight(lamp);
		RegularTile reg = new RegularTile(3,4);
		CharacterToken ct = new CharacterToken("Bill", 3, reg);
		reg.setLamppost(lamp);
		WitnessCard wc = new WitnessCard(ct);
		assertEquals(true, wc.updateWitnessed());
		lamp.removeGasLight();
		assertEquals(false, wc.updateWitnessed());
	}
}
