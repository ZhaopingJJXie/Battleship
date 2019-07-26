import java.util.*;

public class Ocean {
    //Used to quickly determine which ship is in any given location.
    public Ship[][] ships = new Ship[20][20];
    
    // The total number of shots fired by the user.
    private int shotsFired; 
    
    //The number of times a shot hit a ship. 
    //If the user shoots the same part of a ship more than once, 
    //every hit is counted, even though the additional 
    //”hits” don’t do the user any good.
    private int hitCount;
    
    //The number of ships sunk. 
    //Remember that you have a total of 13 ships.   
    private int shipsSunk;
    
    /**
     * Constructor
     * Creates an ”empty” ocean (fills the ships array 
     * with a bunch of EmptySea instances).
     * Also initializes any game variables, 
     * such as how many shots have been fired.
     */

        public Ocean() {
    	EmptySea[][] es= new EmptySea[20][20];
    	
    	for (int i = 0; i < 20; i++) {
    	    for (int j = 0; j < 20; j++) {
    		es[i][j] = new EmptySea();
    		this.ships[i][j] = es[i][j];
    		
    	    }
    	}
    	this.shotsFired = 0;
    	this.hitCount = 0 ;
    	this.shipsSunk = 0 ;
        }
        
    public int getShotsFired() {
        return shotsFired;
    }

    public void setShotsFired(int shotsFired) {
        this.shotsFired = shotsFired;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }
    
    public int getShipsSunk() {
	return shipsSunk;
    }


    public void setShipsSunk(int shipsSunk) {
        this.shipsSunk = shipsSunk;
    }


    /**
     * Place all randomly on the (initially empty) ocean. 
     * Place larger ships before smaller ones, 
     * or you may end up with no legal place to put a 
     * large ship. You will want to use the Random class 
     * in the java.util package, so look that up in the Java API. 
     */
    public void placeAllShipsRandomly() {
	
	BattleShip bs1 = new BattleShip();
	BattleCruiser bc1 = new BattleCruiser();
	Cruiser c1 = new Cruiser();
	Cruiser c2 = new Cruiser();
	LightCruiser lc1 = new LightCruiser();
	LightCruiser lc2 = new LightCruiser();
	Destroyer d1 = new Destroyer();
	Destroyer d2 = new Destroyer();
	Destroyer d3 = new Destroyer();
	Submarine s1 = new Submarine();
	Submarine s2 = new Submarine();
	Submarine s3 = new Submarine();
	Submarine s4 = new Submarine();
	
	//place the battleship
	this.randPlace(bs1);
	this.randPlace(bc1);
	this.randPlace(c1);
	this.randPlace(c2);
	this.randPlace(lc1);
	this.randPlace(lc2);
	this.randPlace(d1);
	this.randPlace(d2);
	this.randPlace(d3);
	this.randPlace(s1);
	this.randPlace(s2);
	this.randPlace(s3);
	this.randPlace(s4);	
    }
    
    public void randPlace(Ship bs) {
	Random rand = new Random();
	int row;
	int column;
	double verOrHor;
	boolean horizontal;
	boolean successPut = false;
	while (!successPut) {
//	get random locations to put the ship 
	    row = rand.nextInt(20);
	    column = rand.nextInt(20);
	    verOrHor = rand.nextDouble();
	    // randomly choose horizontally or vertically
	    if (verOrHor > 0.5)
		horizontal = true;
	    else
		horizontal = false;
	    // place a ship at this location
	    // check if it is ok to place the ship at this location
	    boolean placeAvailable = bs.okToPlaceShipAt(row, column, horizontal, this);
	    if (placeAvailable) {
		// successfully place the ship at this location
		bs.placeShipAt(row, column, horizontal, this);
		successPut = true;
	    } else {
		// location not available, check for other locations
		continue;
	    }
	}
    }
    
    /**
     * Returns true if the given location contains a ship, false if it does not.
     * @param row
     * @param column
     */
    public boolean isOccupied(int row, int column) {
	boolean containsAShip ;
	if (this.ships[row][column].getShipType().equals("empty")) {
	    containsAShip = false;
	}else containsAShip = true;
	return containsAShip;
    }
    
    /**
     * Returns true if the given location contains a ”real” ship, 
     * still afloat, (not an EmptySea), false if it does not. 
     * In addition, this method updates the number of shots 
     * that have been fired, and the number of hits.
     *  Note: If a location contains a ”real” ship, 
     *  shootAt should return true every time the user 
     *  shoots at that same location. Once a ship has been ”sunk”, 
     *  additional shots at its location should return false.
     * @param row
     * @param column
     * @return
     */
    public boolean shootAt(int row, int column) {
	this.shotsFired++;
	//if you shot off the board, return false;
	if (row < 0 || row > 19 || column < 0 || column > 19) {
	    return false;
	}
	else{
	    Ship ship = this.ships[row][column];
	    if (!ship.getShipType().equals("empty")) {
	    //if location contains a ship
	    boolean shipFloatOrNot= ship.isSunk();
	    if (shipFloatOrNot == false) {
		//if the ship is still afloat,
		//shoot at it, update the hitcount 
		ship.shootAt(row, column);
		this.hitCount++;
		boolean shipSunkOrNot = this.ships[row][column].isSunk();
		//if after the shot, the ship sinks, update the number of sunk ships
		if (shipSunkOrNot == true ) shipsSunk++;  
		return true;
	    }else {
		//if the ship has already sunk, return false
		//this.hitCount++;
		return false;
	    }
	}else {
	    //location does not contain a real ship, return false, update the hit array in the empty sea.
	    ship.shootAt(row, column);
	    return false;
	}
	}
	
    }
    
    public boolean isGameOver() {
	if (shipsSunk == 13) return true;
	else return false;
    }
  
    /**
     * Returns the 20x20 array of ships. 
     * The methods in the Ship class that take an Ocean parameter 
     * really need to be able to look at the contents of this array; 
     * the placeShipAt method even needs to modify it. 
     * While it is undesirable to allow methods in one class to directly 
     * access instance variables in another class, sometimes there is just no good alternative.
     */
    public Ship[][] getShipArray(){
	return this.ships;
    }
    
    
   
    /**
     * Prints the ocean. To aid the user, row numbers should be 
     * displayed along the left edge of the array, and column numbers should be 
     * displayed along the top. Numbers should be 00 to 19, not 1 to 20.

	The top left corner square should be 0, 0.
	Use ’S’ to indicate a location that you have fired upon and hit a (real) ship, 
	’-’ to indicate a location that you have fired upon and found nothing there, 
	’x’ to indicate a location containing a sunken ship,
	and ’.’ (a period) to indicate a location that you have never fired upon.

	This is the only method in the Ocean class that does any input/output, 
	and it is never called from within the Ocean class (except possibly during debugging), 
	only from the BattleshipGame class.
     */
    public void print() {
	System.out.print("0,0"+'\t');
	for (int column = 0; column < 20; column++) {
	    System.out.print(Integer.toString(column) + '\t');
	}
	 System.out.print('\n');
	for (int row = 0; row < 20; row++) {
	    System.out.print(Integer.toString(row)+ '\t');
	    for (int column_ship = 0; column_ship < 20; column_ship++) {
		System.out.print(getLocationString(row, column_ship) + '\t');
	    }
	    System.out.print('\n');
	}
    }
    
    /**
     * return the string for each of the location in the sea.
     * location can contain a ship or not. A ship can be sunk or not sunk 
     * if a ship is not sunk, it can be hit or not hit 
     * an empty ship can be hit or not hit as well
     * @param row
     * @param column
     * @return
     */
    public String getLocationString(int row, int column) {
	Ship myShip = ships[row][column];
	//check if the location has been fired on 
//	if (myShip.getHit()[])
//	Ship es = new EmptySea();
	if (myShip.getShipType().equals("empty")) {
	    // the location is empty
	    if (myShip.getHit()[0] == true) {
		// empty sea was fired
		return "-";
	    } else {
		// empty sea was not fired
		return ".";
	    }
	}else  {
	    // if the location contains a ship
	    if (myShip.isSunk() == true) {
		// if myShip is sunk
		return "x";
	    } else {
		// ship is afloat, check if it is hit
		if (myShip.isHorizontal() == true) {
		    if (myShip.getHit()[column - myShip.getBowColumn()] == true) {
			// if the location is hit
			return "S";
		    } else {
			// the location is not hit
			return ".";
		    }

		} else {
		    // my ship is vertical
		    if (myShip.getHit()[row - myShip.getBowRow()] == true) {
			// if the location is hit
			return "S";
		    }
			else {
			// the location is not hit
			return ".";
		    }
		}
	    }
	} 

	}

    }

   
  


