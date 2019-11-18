package projet;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CaseTest {

	public Case c1, c2;

	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		c1 = new Case(2, 4);
		c2 = new Case(3, 7);
		System.out.print("Debut du test ");
	}

	@After
	public void apresUnTest() {
		System.out.println("------- Fin d'un test -------");
	}

	@AfterClass
	public static void apresTest() {
		System.out.println();
		System.out.println("Fin de la serie de tests");
	}

	@Test
	public void testIsMonstre() {
		System.out.println("testIsMonstre");
		c1.setMonstre(true);
		assertTrue(c1.isMonstre());
		assertFalse(c2.isMonstre());
	}

	@Test
	public void testIsChasseur() {
		System.out.println("testIsChasseur");
		c1.setChasseur(true);
		assertTrue(c1.isChasseur());
		assertFalse(c2.isChasseur());
	}

	@Test
	public void testIsPiege() {
		System.out.println("testIsPiege");
		c1.setPiege(true);
		assertTrue(c1.isPiege());
		assertFalse(c2.isPiege());
	}

	@Test
	public void testIsVisite() {
		System.out.println("testIsVisite");
		c1.setVisite();
		assertTrue(c1.isVisite());
		assertFalse(c2.isVisite());
	}

	@Test
	public void testGetCptTours() {
		System.out.println("testGetCptTours");
		c1.incrTour();
		c1.incrTour();
		c2.incrTour();
		assertEquals(3, c1.getCptTours());
		assertEquals(2, c2.getCptTours());
	}

	@Test
	public void testGetTourVisite() {
		System.out.println("testGetTourVisite");
		c1.incrTour();
		c1.incrTour();
		c2.incrTour();
		c1.setVisite();
		assertEquals(3, c1.getTourVisite());
		assertNotEquals(2, c2.getTourVisite());
		assertEquals(-1, c2.getTourVisite());
	}

	@Test
	public void testToString() {
		System.out.println("testToString");
		assertEquals("[   ]", c1.toString());
		c1.setPiege(true);
		assertEquals("[ X ]", c1.toString());
		c1.setChasseur(true);
		assertEquals("[ C ]", c1.toString());
		c2.setVisite();
		assertEquals("[|||]", c2.toString());
		c2.setMonstre(true);
		assertEquals("[ M ]", c2.toString());
	}
	
	@Test
	public void testToStringVues() {
		System.out.println("testToStringVues");
		c1.setChasseur(true);
		assertEquals("[   ]", c1.toStringVueMonstre());
		assertEquals("[ C ]", c1.toStringVueChasseur());
		c2.setMonstre(true);
		assertEquals("[ M ]", c2.toStringVueMonstre());
		assertEquals("[   ]", c2.toStringVueChasseur());
		c1.setPiege(true);
		c1.setChasseur(false);
		assertEquals("[   ]", c1.toStringVueMonstre());
		assertEquals("[ X ]", c1.toStringVueChasseur());
	}
}