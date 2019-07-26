import static org.junit.Assert.*;

//import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class OceanTest {
    Ocean ocean;
    BattleShip bs;
    BattleCruiser bc;
    Cruiser c1, c2;
    LightCruiser lc1, lc2;
    Destroyer d1, d2, d3;
    Submarine s1, s2, s3, s4;
    EmptySea es;
   
    @Before
    public void setUp() throws Exception {
	ocean = new Ocean();
	bs = new BattleShip();
	bc = new BattleCruiser();
	c1 = new Cruiser();
	c2 = new Cruiser();
	lc1 = new LightCruiser();
	lc2 = new LightCruiser();
	d1 = new Destroyer();
	d2 = new Destroyer();
	d3 = new Destroyer();
	s1 = new Submarine();
	s2 = new Submarine();
	s3 = new Submarine();
	s4 = new Submarine();
	es = new EmptySea();
	
    }

    @Test
    public void testPlaceAllShipsRandomly() {
	ocean.placeAllShipsRandomly();
    }

    @Test
    public void testRandPlaceShip() {
	ocean.randPlace(bs);
	System.out.println( "bow column is: " + bs.getBowColumn());
	System.out.println("bow row is: " + bs.getBowRow());
	System.out.println("horizontal: " + bs.isHorizontal());
    }
    @Test
//something is wrong with the occupied    
    public void testIsOccupied() {
	bs.placeShipAt(12, 1, true, ocean);
	assertEquals(ocean.isOccupied(12, 6),true );
    }
    
    @Test
    public void testShootAt() {
	bs.placeShipAt(3, 4, true, ocean);
	ocean.shootAt(3, 4);
	ocean.shootAt(3, 5);
	ocean.shootAt(3, 6);
	ocean.shootAt(3, 7);
	ocean.shootAt(3, 8);
	ocean.shootAt(3, 9);
	ocean.shootAt(3, 10);
	ocean.shootAt(3, 10);
	ocean.shootAt(4, 10);
	
	assertEquals(ocean.getShotsFired(), 9, 0);
	assertEquals(ocean.getHitCount(), 8, 0);
	assertEquals(bs.getHit()[7], false);
	
    }
  
    @Test
    public void testGetLocationString() {
	bs.placeShipAt(3, 4, true, ocean);
	ocean.shootAt(3, 4);
	ocean.shootAt(3, 5);
	ocean.shootAt(3, 6);
	ocean.shootAt(3, 7);
	ocean.shootAt(3, 8);
	ocean.shootAt(3, 9);
	ocean.shootAt(3, 10);
	ocean.shootAt(3, 11);
	ocean.shootAt(4, 6);
	ocean.shootAt(3, 0);

	
	assertTrue(ocean.getLocationString(3, 9).equals("x"));//	ocean.getLocationString(3, 10).equals(".");
	assertEquals(ocean.ships[3][4].isSunk(), true);
	assertTrue(ocean.getLocationString(3, 8).equals("x"));
	assertTrue(ocean.getLocationString(3, 4).equals("x"));
	assertTrue(ocean.getLocationString(3, 11).equals("x"));
	assertTrue(ocean.getLocationString(0, 0).equals("."));
	assertTrue(ocean.getLocationString(3, 0).equals("-"));
	
    }
	
	@Test
	public void testGetShotsFired() {
	    ocean.shootAt(3, 4);
	    ocean.shootAt(4, 4);
	    int numOfShots = ocean.getShotsFired();
	    assertEquals(numOfShots, 2, 0 );
	}
	
	@Test
	public void intGetHitCount() {
	    bs.placeShipAt(3, 4, true, ocean);  
	    ocean.shootAt(3, 4);
	    ocean.shootAt(3, 5);
	    ocean.shootAt(3, 5);
	    ocean.shootAt(4, 5);
	    assertEquals(ocean.getHitCount(), 3, 0);
	    
	}
	
	@Test
	public void testGetShipsSunk() {
	    s3.placeShipAt(3, 4, true, ocean);
	    d1.placeShipAt(4, 5, false, ocean);
	    ocean.shootAt(4,5);
	    ocean.shootAt(5,5);
	    ocean.shootAt(6,5);
	    ocean.shootAt(7,5);
	    ocean.shootAt(3, 4);
	    ocean.shootAt(3, 5);
	    ocean.shootAt(3, 6);
	    ocean.shootAt(3, 6);
	    assertEquals(ocean.getShipsSunk(), 2, 0);
	}
	@Test
	public void testIsGameOver() {
	    assertEquals(ocean.isGameOver(),false);
	}
	
	@Test
	public void testGetShipArray() {
	    ocean.placeAllShipsRandomly();
	    for (int i = 0; i < 20; i++) {
		for (int j = 0; j < 20; j++) {
		    System.out.print(ocean.ships[i][j].getShipType() + '\t');
		}System.out.print('\n');
	    }
	}
	
	
	@Test
	public void testPrint() {
	    ocean.placeAllShipsRandomly();
//	    ocean.shootAt(0, 0);
	    ocean.shootAt(3, 5);
	    ocean.shootAt(3, 6);
	    ocean.shootAt(4, 7);
	    ocean.shootAt(5, 8);
	    ocean.shootAt(5, 9);
	    ocean.shootAt(6, 10);
	    ocean.shootAt(1, 3);
	    ocean.shootAt(2, 15);
	    ocean.shootAt(3, 6);
	    for (int i = 0; i < 20; i++) {
		for (int j = 0; j < 20; j++) {
		    System.out.print(ocean.ships[i][j].getShipType() + '\t');
		}System.out.print('\n');
	    }
	    System.out.println(ocean.ships[0][0].getShipType());
	    System.out.println(ocean.ships[0][0].getHit()[0]);
	    System.out.println(ocean.getLocationString(0, 0));
	    ocean.print();
	}
	
	
	public void main(String[] args) {
	    System.out.println("this is ut");
	}

	
}

    
    


