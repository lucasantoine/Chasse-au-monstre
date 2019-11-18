package projet;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlateauTest {

	public Plateau p1, p2, p3, p4;

	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		p1 = new Plateau(2);
		p2 = new Plateau(5);
		p3 = new Plateau();
		p4 = new Plateau(250);
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
	public void testToString() {
		System.out.println("testToString");
		assertEquals(p1.toString(), p2.toString());
		assertEquals(new Plateau(5).toString(), p3.toString());
		assertEquals(new Plateau(10).toString(), p4.toString());
	}

	@Test
	public void testGetTaillePlateau() {
		System.out.println("testGetTaillePlateau");
		assertEquals(25, p1.getTaillePlateau());
		assertEquals(p1.getTaillePlateau(), p2.getTaillePlateau());
		assertEquals(25, p3.getTaillePlateau());
		assertEquals(100, p4.getTaillePlateau());
	}

	@Test
	public void testGetCote() {
		System.out.println("testGetCote");
		assertEquals(5, p1.getCote());
		assertEquals(p1.getCote(), p2.getCote());
		assertEquals(5, p3.getCote());
		assertEquals(10, p4.getCote());
	}

	@Test
	public void testPlateauVisite() {
		System.out.println("testPlateauVisite");
		p1.setVisite();
		assertTrue(p1.plateauVisite());
		assertFalse(p2.plateauVisite());
	}
}
