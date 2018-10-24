package test.model.ability;
import model.gameboard.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.ability.StealthyAbility;
import model.gameboard.*;
public class StealthyAbilityTest {
	StealthyAbility sa;

	@Before
	public void setUp() throws Exception {

		
	}

	@Test
	public void test() {
		// Assert right ability
		assert(sa.isAbility(StealthyAbility.ABILITY));
		// Assert not wrong ability
		assert(!sa.isAbility("Random Ability Name"));
	}

}
