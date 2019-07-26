import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BattleShipTest {
    BattleShip bs1;
    Ocean ocean;

    @Before
    public void setUp() throws Exception {
	bs1 = new BattleShip();
	ocean = new Ocean();
    }
    
    @Test
    public void testGetHit() {
	boolean[] hitTest = {false, false, false, false, false, false, false, false};
	bs1.getHit().equals(hitTest);
    }
    
 
    @Test
    public void testGetShipType() {
	String shipType = "battleship";
	bs1.getShipType().equals(shipType);
    }

    @Test
    public void testOkToPlaceShipAt() {
	boolean putShip1 = bs1.okToPlaceShipAt(0, 12, true, ocean); 	
//	boolean putShip = bs1.okToPlaceShipAt(12, 0, false, ocean); succeed
//	boolean putShip = bs1.okToPlaceShipAt(19, 10, true, ocean); succeed
//	boolean putShip = bs1.okToPlaceShipAt(13, 19, false, ocean);	
	assertEquals(putShip1, true);
    }

    @Test
    public void testPlaceShipAt() {
	bs1.placeShipAt(12, 19, false, ocean);
	assertEquals(bs1.getBowColumn(), 19, 0);
	assertEquals(bs1.getBowRow(), 12, 0);
	assertEquals(bs1.isHorizontal(), false);
	ocean.ships[12][19].equals(bs1);
	
    }

    @Test
    public void testShootAt() {
	bs1.placeShipAt(10, 10, false, ocean);
	boolean target = bs1.shootAt(11, 10);
	assertEquals(target, true);
	
    }
    
    @Test
    public void testIsSunk() {
	boolean[] hit_bs = 
	    {true, true, true, true, true, true, true, true};
	bs1.setHit(hit_bs);
	boolean sunk = bs1.isSunk();
	assertEquals(sunk,true);
    }

    @Test
    public void testToString() {
	"battleship".equals(bs1.getShipType());
    }
    
   

}
