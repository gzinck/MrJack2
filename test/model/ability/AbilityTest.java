package test.model.ability;
import static org.junit.Assert.*;
/**
 * Test class for the ability classes
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */

import model.gameboard.*;
import model.ability.*;
import org.junit.Test;
public class AbilityTest {
	/** MoveBarricadeAbility to be tested */
	private MoveBarricadeAbility mba;
	/** MoveCoverAbility to be tested */
	private MoveCoverAbility mca;
	/** MoveLightAbility to be tested */
	private MoveLightAbility mla;
	/** TokenFinder used to construct the testing abilities */
	private TokenFinder tk;
	/**
	 * Test method for the move barricade ability
	 */
	@Test
	public void testMoveBarricade()
	{
		mba = new MoveBarricadeAbility(tk);
		assertEquals(Ability.Timing.BEFORAFTER, mba.whenUseAbility());
	}
	/**
	 * Test method for the move cover ability
	 */
	@Test
	public void testMoveCover()
	{
		mca = new MoveCoverAbility(tk);
		assertEquals(Ability.Timing.BEFORAFTER, mca.whenUseAbility());
	}
	/**
	 * Test method for the move light ability
	 */
	@Test
	public void testMoveLight()
	{
		mla = new MoveLightAbility(tk);
		assertEquals(Ability.Timing.BEFORAFTER, mla.whenUseAbility());
	}
}
