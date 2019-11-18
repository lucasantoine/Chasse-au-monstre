package projet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChasseurTest {

	public Chasseur c1, c2, c3, c4, c5;

	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		c1 = new Chasseur(2, 4, 8);
		c2 = new Chasseur(2, 5, 3);
		c3 = new ChasseurIAFacile(4, 3, 5);
		c4 = new ChasseurIAMoyen(5, 7, 4);
		c5 = new Chasseur(new Plateau());
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
	public void testVictoire() {
		System.out.println("testVictoire");
		Monstre m1 = new Monstre(2, 4, new Plateau());
		assertTrue(c1.victoire(m1));
		assertFalse(c2.victoire(m1));
	}

	@Test
	public void testToString() {
		System.out.println("testToString");
		assertEquals("[Chasseur : Position (2,4); Nombre de pieges : 8]", c1.toString());
		assertEquals("[Chasseur : Position (2,5); Nombre de pieges : 3]", c2.toString());
	}
	
	@Test
	public void testIsIA() {
		System.out.println("testIsIA");
		assertFalse(c1.isIA());
		assertTrue(c3.isIA());
		assertTrue(c4.isIA());
	}
	
	@Test
	public void testGetPieges() {
		System.out.println("testGetPieges");
		assertEquals(8, c1.getPieges());
		assertEquals(3, c2.getPieges());
		assertEquals(5, c3.getPieges());
		assertEquals(4, c4.getPieges());
		assertEquals(3, c5.getPieges());

	}
	
	@Test
	public void testGetImageChasseur() {
		System.out.println("testGetImageChasseur");
		assertEquals("file:./res/JoueurChasseur.png", c1.getImageChasseur());
		assertEquals("file:./res/chasseurIAFacile.png", c3.getImageChasseur());
		assertEquals("file:./res/chasseurIAMoyen.png", c4.getImageChasseur());
		
	}
}