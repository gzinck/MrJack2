package test.model.ability;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.ability.StealthyAbility;

public class StealthyAbilityTest {
	StealthyAbility sa;

	@Before
	public void setUp() throws Exception {
		sa = new StealthyAbility();
	}

	@Test
	public void test() {
		// Assert right ability
		assert(sa.isAbility(StealthyAbility.ABILITY));
		// Assert not wrong ability
		assert(!sa.isAbility("Random Ability Name"));
	}

}
