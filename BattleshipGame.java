import java.util.*;

/**
 * The BattleshipGame class is the ”main” class–that is, it contains a main
 * method. In this class you will set up the game; accept ”shots” from the user;
 * display the results; print final scores; and ask the user if he/she wants to
 * play again. All input/output is done here (although some of it is done by
 * calling a print() method in the Ocean class.) All computation will be done in
 * the Ocean class and the various Ship classes.
 * 
 * @author jojo
 *
 */

public class BattleshipGame {
    

    public static void main(String[] args) {
	Ocean myOcean = new Ocean();
	//put the ships randomly in the ocean
	myOcean.placeAllShipsRandomly();
	//if the game is not over
	boolean gameOverOrNot = myOcean.isGameOver();
	while (gameOverOrNot == false) {
	//Accept shots from the user
	System.out.println("inpiut five locations to fire on");
	System.out.println("The input format should look like this: 1, 1; 0, 3; 7, 3; 9, 11; 12, 17");
	try {
	    Scanner inputLocation = new Scanner(System.in);
	
	String location = inputLocation.nextLine();
	String[] locationArray = location.split(",\\s*|;\\s*");
//	System.out.println("array length is: "+ locationArray.length);
	for (int k = 0; k < 10; k++) {
	    System.out.println(locationArray[k]);
	}
	int row, column;
	for (int i = 0; i < 5; i++) {
	    row = Integer.parseInt(locationArray[2*i]);
	    column = Integer.parseInt(locationArray[2*i+1]);
//	    System.out.println("row is: "+ row);
//	    System.out.println("column is: "+ column);
	    //shoot at this position
	    myOcean.shootAt(row, column);
	}
	}catch(InputMismatchException e1) {
	    System.out.println("input mismatch, please make sure you have the same format as example");
	}
	System.out.println("number of shoots: " + myOcean.getShotsFired());
	System.out.println("number of hits: " + myOcean.getHitCount());
	System.out.println("number of ships sunk: "+ myOcean.getShipsSunk());
	myOcean.print();
	//check if the game is over
	
	if (gameOverOrNot == true) {
	    System.out.println("GameOver, you win!");
	}else {
	  //ask if the user wants to continue playing
	    System.out.println("do you still want to play? please answer 'yes' or 'no': ");
		Scanner answer = new Scanner(System.in);
		String  yesOrNo = answer.nextLine();
		if (yesOrNo.equals("yes")) continue;
		if (yesOrNo.equals("no")) {
		    System.out.println("you want to terminate the game");
		    break;
		}
		answer.close();
	}
	
	}
    }
}
