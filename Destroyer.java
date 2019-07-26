
public class Destroyer extends Ship {
    //constructor to set inherited length variable and 
    // initialize the hit array
     public Destroyer () {
 	this.setLength(4);
 	int length = this.getLength();
 	boolean hit[] = new boolean[length]; 
 	for (int i = 0; i < length; i++) {
 	    hit[i] = false;
 	}this.setHit(hit);
     }
     
     @Override
     public String getShipType() {
 	return "destroyer";
 	
     }

}
