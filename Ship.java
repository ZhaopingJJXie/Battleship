import java.util.ArrayList;


public abstract class Ship {
    // the row (0-19) which contains the bow (front) of the ship
    private int bowRow;
    // the column which contains the bow(front) of the ship
    private int bowColumn;
    // the number of squares occupied by the ship. An "empty sea" location has
    // length 1
    private int length;
    // true if the ship occupies a shingle row, false otherwise.
    private boolean horizontal;
    // boolean array of size 8 that record hits.
    private boolean[] hit;
    
    public int getBowRow() {
	return bowRow;
    }

    public void setBowRow(int bowRow) {
	this.bowRow = bowRow;
    }

    public int getBowColumn() {
	return bowColumn;
    }

    public void setBowColumn(int bowColumn) {
	this.bowColumn = bowColumn;
    }

    public int getLength() {
	return length;
    }

    public void setLength(int length) {
	this.length = length;
    }

    public boolean isHorizontal() {
	return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
	this.horizontal = horizontal;
    }

    public boolean[] getHit() {
	return this.hit;
    }

    public void setHit(boolean[] hit) {
	this.hit = hit;
    }

    // abstract method thus no body
    public abstract String getShipType();

    /**
     * Returns true if it is okay to put a ship of this length with its bow in this
     * location, with the given orientation, and returns false otherwise. The ship
     * must not overlap another ship, or touch another ship (vertically,
     * horizontally, or diagonally), and it must not ”stick out” beyond the array.
     * Do not actually change either the ship or the Ocean, just says whether it is
     * legal to do so.
     * 
     * @param row
     * @param column
     * @param horizontal
     * @param Ocean
     * @return
     */

    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
	if ("empty".equals(ocean.ships[row][column].getShipType())) {
	    // if horizontal
	    ArrayList<Integer> fixedAxises = new ArrayList<Integer>();
	    // ArrayList<Integer> checkAxises = new ArrayList<Integer>();
	    int checkAxisStarts;
	    int checkAxisEnds;
	    int checkAxis;
	    int fixedAxis;
	    if (horizontal == true) {
		checkAxis = column;
		fixedAxis = row;
	    } else {
		checkAxis = row;
		fixedAxis = column;
	    }
	    if (checkAxis + length > 20) {
		// ship out of the ocean, fail
		return false;
	    } else {
		if (fixedAxis == 0) {
		    fixedAxises.add(0);
		    fixedAxises.add(1);
		} else if (fixedAxis == 19) {
		    fixedAxises.add(18);
		    fixedAxises.add(19);
		} else {
		    fixedAxises.add(fixedAxis - 1);
		    fixedAxises.add(fixedAxis);
		    fixedAxises.add(fixedAxis + 1);
		}
		checkAxisStarts = Math.max(0, checkAxis - 1);
		checkAxisEnds = Math.min(checkAxis + length, 19);
		for (int i : fixedAxises) {
		    for (int j = checkAxisStarts; j <= checkAxisEnds; j++) {
			String shipName;
			if (horizontal == true) {
			    shipName = ocean.ships[i][j].getShipType();
			} else {
			    shipName = ocean.ships[j][i].getShipType();
			}
			if ("empty".equals(shipName)) {
			    // location empty, success,continue to check
			    continue;
			} else {
			    // location occupied, fail to place
			    return false;
			}
		    }
		} // no occupied location caught,
		return true;
	    }
	} else {
	    // location occupied, can not place a ship
	    return false;
	}
    }

    /**
     * ”Puts” the ship in the ocean. This involves giving values to the bowRow,
     * bowColumn, and horizontal instance variables in the ship, it also involves
     * putting a reference to the ship in each of 1 or more locations (up to 8) in
     * the ships array in the Ocean object. (Note: This will be as many as eight
     * identical references; you can’t refer to a ”part” of a ship, only to the
     * whole ship.)
     *
     * @param row
     * @param column
     * @param horizontal
     * @param Ocean
     */
    
    //what does it mean to put a reference to the object.
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
	// gives value to the bowRow, bow Column...
	this.setBowColumn(column);
	this.setBowRow(row);
	this.setHorizontal(horizontal);
	//Ship[] this
	if (horizontal == true) {
	    //place horizontally
	    for (int i = column; i < column + length; i++) {
		ocean.ships[row][i] = this;
		
	    }
	} else {
	    //place vertically 
	    for (int j = row; j < row + length; j++) {
		ocean.ships[j][column] = this;
	    }
	}

    }

    /**
     * If a part of the ship occupies the given row and column, and the ship hasn’t
     * been sunk, mark that part of the ship as ”hit” (in the hit array, 0 indicates
     * the bow) and return true, otherwise return false.
     * 
     * @param row
     * @param column
     * @return
     */
    public boolean shootAt(int row, int column) { 
	if (isSunk() != true) {
	    if (this.horizontal == true) {
		if (row == bowRow) {
		    if (column >= bowColumn && column < bowColumn + length) {
			// within the ship's range, the ship is hit
			// update the hit[]
			hit[column - bowColumn] = true;
			return true;
		    } else {
			// not within the ship's range. the ship is not hit
			return false;
		    }
		} else {
		    // different row, return false
		    return false;
		}
	    } else {
		// vertical
		if (column == bowColumn) {
		    if (row >= bowRow && row < bowRow + length) {
			// within the ship's range, the ship is hit
			// update the hit[]
			hit[row - bowRow] = true;
			return true;
		    } else {
			// not within the ship's range. the ship is not hit
			return false;
		    }
		} else {
		 // different column, return false
		    return false;
		}
	    }
	} else {
	    // ship sunk, return false
	    return false;
	}
    }

    /**
     * Return true if every part of the ship has been hit, false otherwise.
     * 
     * @return
     */
    public boolean isSunk() {
	EmptySea es = new EmptySea();
	if (!this.equals(es)) {
	    //if this is not an empty sea
	    int count = 0;
	    for (int i = 0; i < length; i++) {
		if (hit[i] == false)
		    return false;
		else {
		    count++;
		    continue;
		}
	    }
	    if (count == length) {
//		System.out.println("ship sunk");
		return true;
	    } else {
		return false;
	    }
	}else {
	    //empty sea, always sunk
	    return true;
	    
	}
    }

    @Override
    /**
     * Returns a single-character String to use in the Ocean’s print method (see
     * below). This method should return ”x” if the ship has been sunk, ”S” if it
     * has not been sunk. This method can be used to print out locations in the
     * ocean that have been shot at; it should not be used to print locations that
     * have not been shot at.
     */
    public String toString() {
	boolean sunk = isSunk();
	if (sunk == true)
	    return "x";
	else
	    return "S";

    }

}
