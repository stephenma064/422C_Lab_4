package assignment4;

import static org.junit.Assert.*;

import org.junit.Test;

public class Critter1Test {

	@Test
	public void testInstantiate() {
		Critter1 c = new Critter1();
		assertEquals(c.toString(), "1");
	
	}
	
	public void testWalk() throws InvalidCritterException {
		Critter.makeCritter("Critter1");
		MyCritter1 m1 = (MyCritter1) Critter.TestCritter.getPopulation().get(0);
		int x1a = m1.getX_coord(); int  y1a = m1.getY_coord();
		m1.doTimeStep();
		int x1b = m1.getX_coord(); int  y1b = m1.getY_coord();
		assertTrue((x1b - x1a == 1) || (x1b + Params.world_width - x1a) == 1);
		assertTrue(Math.abs(y1b - y1a) == 0);
	}
	
	public void WalkEnergyTest() throws InvalidCritterException {
		Critter.makeCritter("Critter1");
		MyCritter1 c = (MyCritter1) Critter.TestCritter.getPopulation().get(0);
		int step = 0;
		int energyUsePerStep = Params.rest_energy_cost + Params.walk_energy_cost;
		while (c.getEnergy() > 0) {
//		while (!c.isDead()) {
			assertEquals(Params.start_energy -step*energyUsePerStep, c.getEnergy());
			Critter.worldTimeStep();
			step++;
		}		
	}

}
