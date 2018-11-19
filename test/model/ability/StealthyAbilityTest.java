package test.model.ability;
import static org.junit.Assert.*;
import model.gameboard.*;
import org.junit.Before;
import org.junit.Test;
import model.ability.StealthyAbility;
/**
 * Test class for the stealthy ability class
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */

public class StealthyAbilityTest {
	/** StealthyAbility to be tested*/
	StealthyAbility sa;
	/** TokenFinder for constructing the ability */
	TokenFinder tk;

	@Before
	public void setUp() throws Exception {
	
		sa = new StealthyAbility();
	}
	/**
	 * Test method for the stealthy ability, checks that the ability is Stealthy
	 */
	@Test
	public void test() {
		// Assert right ability
		assertTrue(sa.isAbility(StealthyAbility.ABILITY));
		// Assert not wrong ability
		assertTrue(!sa.isAbility("Random Ability Name"));
	}
}
