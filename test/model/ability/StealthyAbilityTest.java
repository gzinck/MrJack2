package test.model.ability;
/**
 * Test class for the stealthy ability class
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
import model.gameboard.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.ability.StealthyAbility;
import model.gameboard.*;
public class StealthyAbilityTest {
	/** StealthyAbility to be tested*/
	StealthyAbility sa;
	/** TokenFinder for constructing the ability */
	TokenFinder tk;

	@Before
	public void setUp() throws Exception {
	
		sa = new StealthyAbility(tk);
	}
	/**
	 * Test method for the stealthy ability, checks that the ability is Stealthy
	 */
	@Test
	public void test() {
		// Assert right ability
		assert(sa.isAbility(StealthyAbility.ABILITY));
		// Assert not wrong ability
		assert(!sa.isAbility("Random Ability Name"));
	}

}
