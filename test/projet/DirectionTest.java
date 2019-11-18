package projet;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DirectionTest {

	public Case c1, c2;

	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
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
	public void testGet() {
		System.out.println("testGet");
		assertEquals(Direction.NORD, Direction.get("N"));
		assertEquals(Direction.SUD, Direction.get("S"));
		assertEquals(Direction.EST, Direction.get("E"));
		assertEquals(Direction.OUEST, Direction.get("O"));
		assertEquals(Direction.ICI, Direction.get("I"));
		assertEquals(null, Direction.get("K"));
	}
}