package test.model.ability;
import org.junit.Before;
import org.junit.Test;
import model.ability.*;
import static org.junit.Assert.*;
/**
 * Test class for the move manhole cover ability.
 * 
 * @author Josh Cookson & Graeme Zinck
 * @version 1.1
 */


public class MoveCoverAbilityTest {
	/** MoveCoverAbility to be tested*/
	MoveCoverAbility ability;

	@Before
	public void setUp() throws Exception {
		ability = new MoveCoverAbility(null);
	}
	
	@Test
	public void test() {
		// Assert right ability
		assertTrue(ability.isAbility(MoveCoverAbility.ABILITY));
		// Assert not wrong ability
		assertTrue(!ability.isAbility(StealthyAbility.ABILITY));
	}
}
