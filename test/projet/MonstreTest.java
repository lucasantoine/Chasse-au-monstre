package projet;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MonstreTest {

	public Monstre m1, m2, m3, m4, m5, m6;

	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		m1 = new Monstre(2, 4, new Plateau(5));
		m2 = new Monstre(2, 4, new Plateau());
		m3 = new Monstre(new Plateau(10));
		m4 = new Monstre(new Plateau());
		m5 = new MonstreIAFacile(new Plateau());
		m6 = new MonstreIAMoyen(new Plateau());
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
	public void testGetCasesR() {
		System.out.println("testGetCasesR");
		assertEquals(24, m1.getCasesR());
		assertEquals(24, m2.getCasesR());
		assertEquals(99, m3.getCasesR());
		assertEquals(24, m4.getCasesR());
	}

	@Test
	public void testToString() {
		System.out.println("testToString");
		assertEquals("[Monstre : Position (2,4) Cases restantes (24)]", m1.toString());
		assertEquals("[Monstre : Position (2,4) Cases restantes (24)]", m2.toString());
		assertEquals("[Monstre : Position (" + m3.getX() + "," + m3.getY() + ") Cases restantes (99)]", m3.toString());
		assertEquals("[Monstre : Position (" + m4.getX() + "," + m4.getY() + ") Cases restantes (24)]", m4.toString());
	}
	
	@Test
	public void testVictoire() {
		System.out.println("testVictoire");
		assertFalse(m1.victoire());
		m2.setCasesRNulles();
		assertTrue(m2.victoire());
	}
	
	@Test
	public void testIsIA() {
		System.out.println("testIsIA");
		assertFalse(m1.isIA());
		assertFalse(m2.isIA());
		assertTrue(m5.isIA());
		assertTrue(m6.isIA());
	}
	
	@Test
	public void testGetImageMonstre() {
		System.out.println("testGetImageMonstre");
		assertEquals("file:./res/JoueurMonstre.png", m1.getImageMonstre());
		assertEquals("file:./res/monstreIAFacile.png", m5.getImageMonstre());
		assertEquals("file:./res/monstreIAMoyen.png", m6.getImageMonstre());
	}
}