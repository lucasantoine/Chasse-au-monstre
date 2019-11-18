package projet;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JoueurTest {
	Joueur j1, j2, j3;
	Plateau p;

	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		p = new Plateau(5);
		j1 = new Monstre(2, 4, p);
		j2 = new Monstre(0, 3, p);
		j3 = new Monstre(0, 0, p);
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
	public void testGetX() {
		System.out.println("testGetX");
		assertEquals(new Position(2, 4).getX(), j1.getX());
		assertEquals(new Position(0, 3).getX(), j2.getX());
	}

	@Test
	public void testGetY() {
		System.out.println("testGetY");
		assertEquals(new Position(2, 4).getY(), j1.getY());
		assertEquals(new Position(0, 3).getY(), j2.getY());
	}

	@Test
	public void testGetCaseCourante() {
		System.out.println("testGetCaseCourante");
		Case c1 = new Case(2, 4);
		Case c2 = new Case(0, 3);
		assertEquals(c1.getX(), j1.getCaseCourante().getX());
		assertEquals(c1.getY(), j1.getCaseCourante().getY());
		assertEquals(c2.getX(), j2.getCaseCourante().getX());
		assertEquals(c2.getY(), j2.getCaseCourante().getY());
	}
	
	@Test
	public void testVerifPosition() {
		System.out.println("testVerifPosition");
		assertTrue(j1.verifPosition(Direction.NORD, p));
		assertTrue(j1.verifPosition(Direction.SUD, p));
		assertTrue(j1.verifPosition(Direction.OUEST, p));
		assertFalse(j1.verifPosition(Direction.EST, p));
		assertFalse(j3.verifPosition(Direction.NORD, p));
		assertFalse(j3.verifPosition(Direction.OUEST, p));
	}

}