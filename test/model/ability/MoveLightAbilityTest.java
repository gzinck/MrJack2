package test.model.ability;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import model.ability.*;
/**
 * Test class for the move light ability.
 * 
 * @author Josh Cookson & Graeme Zinck
 * @version 1.1
 */


public class MoveLightAbilityTest {
	/** MoveLightAbility to be tested*/
	MoveLightAbility ability;

	@Before
	public void setUp() throws Exception {
		ability = new MoveLightAbility(null);
	}
	
	@Test
	public void test() {
		// Assert right ability
		assertTrue(ability.isAbility(MoveLightAbility.ABILITY));
		// Assert not wrong ability
		assertTrue(!ability.isAbility(StealthyAbility.ABILITY));
	}
}
