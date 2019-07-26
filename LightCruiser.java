
public class LightCruiser extends Ship{
    //constructor to set inherited length variable and 
    // initialize the hit array
     public LightCruiser () {
 	this.setLength(5);
 	int length = this.getLength();
 	boolean hit[] = new boolean[length]; 
 	for (int i = 0; i < length; i++) {
 	    hit[i] = false;
 	}this.setHit(hit);
     }
     
     @Override
     public String getShipType() {
 	return "lightcruiser";
 	
     }

}
