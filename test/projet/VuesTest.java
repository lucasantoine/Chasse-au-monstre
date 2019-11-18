package projet;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VuesTest {

	public Plateau p1;
	public VueChasseur vc; 
	public VueMonstre vm;

	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		p1 = new Plateau();
		vc = new VueChasseur();
		vm = new VueMonstre();
		p1.setChasseur(0, 0, true);
		p1.setMonstre(1, 1, true);
		p1.setVisite(2, 2);
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
	public void testToStringVues() {
		System.out.println("testToStringVues");
		Plateau tmp = new Plateau();
		tmp.setChasseur(0, 0, true);
		assertEquals(tmp.toString(), vc.toStringVueChasseur(p1));
		tmp.setChasseur(0, 0, false);
		tmp.setMonstre(1, 1, true);
		tmp.setVisite(2, 2);
		assertEquals(tmp.toString(), vm.toStringVueMonstre(p1));

	}
}
