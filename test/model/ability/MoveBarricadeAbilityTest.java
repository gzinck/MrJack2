package test.model.ability;
import org.junit.Before;
import org.junit.Test;
import model.ability.MoveBarricadeAbility;
import model.ability.StealthyAbility;
import static org.junit.Assert.*;
/**
 * Test class for the move barricade ability.
 * 
 * @author Josh Cookson & Graeme Zinck
 * @version 1.1
 */


public class MoveBarricadeAbilityTest {
	/** MoveBarricadeAbility to be tested*/
	MoveBarricadeAbility ability;

	@Before
	public void setUp() throws Exception {
		ability = new MoveBarricadeAbility(null);
	}
	
	/**
	 * Test method for the stealthy ability, checks that the ability is Stealthy
	 */
	@Test
	public void test() {
		// Assert right ability
		assertTrue(ability.isAbility(MoveBarricadeAbility.ABILITY));
		// Assert not wrong ability
		assertTrue(!ability.isAbility(StealthyAbility.ABILITY));
	}
}
