package projet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PositionTest {
    public Position p1,p2,p3,p4;
    
    @BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}
    
    @Before
    public void initialisation() {
        p1=new Position(7,7);
        p2=new Position();
        p3=new Position(0,0);
        p4=new Position(6,3);
        System.out.println("Debut du test");
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
        assertEquals(7, p1.getX());
        assertEquals(p2.getX(), p3.getX());
        assertNotEquals(p4.getX(), p1.getX());
    }
    
    @Test
    public void testGetY() {
    	System.out.println("testGetY");
        assertEquals(7, p1.getY());
        assertEquals(p2.getY(), p3.getY());
        assertNotEquals(p4.getY(), p1.getY());
    }
    
    @Test
    public void testToString() {
    	System.out.println("testToString");
        assertEquals("(7,7)", p1.toString());
        assertEquals(p2.toString(), p3.toString());
        assertNotEquals(p4.toString(), p1.toString());
    }
}
