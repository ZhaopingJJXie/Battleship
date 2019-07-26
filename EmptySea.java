
public class EmptySea extends Ship{
    public EmptySea() {
	this.setLength(1);
	boolean[] hit = {false};
	this.setHit(hit);
    }
    
    @Override
   public boolean shootAt(int row, int column) {
	boolean[] newhit = {true};
	this.setHit(newhit);
	return false;
    }
    
    @Override 
    public boolean isSunk() {
	return false;
    }
    
    @Override 
    public String toString() {
	if (this.getHit()[0] == true) {
	    return "-";
	}else {
	return ".";
	}
    }
    
    @Override
    public String getShipType() {
	return "empty";
    }

}
