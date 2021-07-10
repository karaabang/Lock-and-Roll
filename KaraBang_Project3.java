package Project3;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KaraBang_Project3 {

	public static void main(String[] args) {

		// Driver Variables  ********************************
		
		String name;
		String dateTimeFormat;
		String yesNo;
		String playAgain;
				
		// Invoke constructors - Scanner, P2Roll, P2Turn, P2Game, DateTime, DateTimeFormatter
		Scanner input = new Scanner(System.in);
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter frmt = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
		dateTimeFormat = dateTime.format(frmt);
		Roll roll = new Roll();
		Turn turn = new Turn();
		
// Print rules, prompt and get Name and Number of Turns
				
		// Introduction and Rules  ***************************
		
		System.out.println("             Welcome to the Java Dice Game, Lock N Roll!");
		System.out.println("The object of the game is to roll 3 dice and earn the most points.");
		System.out.println("Player earns points by maximizing the sum of the dice and "
				+ "earning bonus points.");
		System.out.println("Bonus points are earned when rolling a pair, triples, or a straight:");
		System.out.println("	o Pair Bonus: 2 points");
		System.out.println("	o Triple Bonus: 30 points");
		System.out.println("	o Straight Bonus: 10 points");
		System.out.println("Player gets 2 rolls. After the first roll, player chooses to either");
		System.out.println("\"Lock\" or \"Roll\" a die again, for each of the 3 dice.");
		System.out.println("Scoring for the turn is complete after the second roll.");
		System.out.println();
		System.out.println();
		

		// Prompt and get inputs for Name and Number of Turns
		System.out.print("What is your name: ");
		name = input.next();
		name = name.toUpperCase();
		System.out.println();

		
// Start game

		// Start do-while loop for playAgain
	do {
			
		// Start turns loop  ****************************************************
		// Set number of turns in the game
		turn.setTurns();
	
	do {
		
// Print turn number header for the turn
		System.out.println();
		System.out.println("**************  Begin Turn " + (turn.getCntTurns() + 1) + " ***************");
		System.out.println();
		
		// Get random values for first roll and then sort it
		roll.setRoll1();		
		roll.sortRoll(roll.getRoll1());
		
		
		// Calculate sum, pair bonus, trip bonus, straight bonus and total points for roll1
		roll.setSum1();
		roll.setPairBonus1();
		roll.setTripBonus1();
		roll.setStrBonus1();
		roll.setPoints1();
		
		
		// Print roll1 results with headers
		System.out.println("          1  2  3  Sum  Pair  Trip Strait Points");
		System.out.println("         -- -- --  ---  ----  ----   ---   ---");
		try { TimeUnit.SECONDS.sleep(2); 
		} 
		catch (InterruptedException ie) { Thread.currentThread().interrupt(); 
		}
		System.out.printf("Roll-1    %d  %d  %d   %d    %d     %d     %d     %d \n", 
				roll.getRoll1(0), roll.getRoll1(1), roll.getRoll1(2),
				roll.getSum1(), roll.getPairBonus1(), roll.getTripBonus1(), 
				roll.getStrBonus1(), roll.getPoints1());
		System.out.println();
		System.out.println();
		
		
		// Get L or R inputs for each die
		System.out.println("Now it's time to LOCK N ROLL!");
		System.out.println();
		System.out.println();
		turn.setDieLR(roll);
		
		
		// Re-roll (set roll2) for all R-input dice and then sort roll2
		roll.setRoll2(turn);  // Passing the entire object Turn turn in this method
		roll.sortRoll(roll.getRoll2());  // Sorting roll2
		
		
		// Calculate sum, pair bonus, trip bonus, straight bonus, and total points for roll2
		roll.setSum2();
		roll.setPairBonus2();
		roll.setTripBonus2();
		roll.setStrBonus2();
		roll.setPoints2();
		
		
		// Print roll2 results with headers
		System.out.println("          1  2  3  Sum  Pair  Trip Strait Points");
		System.out.println("         -- -- --  ---  ----  ----   ---   ---");
		try { TimeUnit.SECONDS.sleep(2); 
		} 
		catch (InterruptedException ie) { Thread.currentThread().interrupt(); 
		}
		
		System.out.printf("Roll-2    %d  %d  %d   %d    %d     %d     %d     %d \n", 
				roll.getRoll2()[0], roll.getRoll2()[1], roll.getRoll2()[2],
				roll.getSum2(), roll.getPairBonus2(), roll.getTripBonus2(), 
				roll.getStrBonus2(), roll.getPoints2());
		
		
// Print turn results including roll1 and roll2 and improved points
		// Keep turn score and print turn stats
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("        --Roll1--  --Roll2--   Sum  Pair  Trip Strait Total Imprv");
		System.out.println("         -- -- --   -- -- --   ---  ----  ----   ----  ----  ----");
		System.out.printf("Turn-%d    %d  %d  %d    %d  %d  %d    %d    %d     %d      %d     %d    %d \n",  
					(turn.getCntTurns() + 1), roll.getRoll1()[0], roll.getRoll1()[1], roll.getRoll1()[2], 
					roll.getRoll2()[0], roll.getRoll2()[1], roll.getRoll2()[2], 
					roll.getSum2(), roll.getPairBonus2(), roll.getTripBonus2(),  
					roll.getStrBonus2(), roll.getPoints2(), (roll.getPoints2() - roll.getPoints1()));
		System.out.println();
		System.out.println();
		 
// Create turn record
		turn.setTurnRecord(name, roll, dateTimeFormat);
		System.out.println();
		System.out.println("**************************************************************");
		System.out.println();

		// Update the turn history array
		turn.setTurnHist();
		
		
	// Increment number of turns
	turn.incremCntTurn();
	} while(turn.getCntTurns() < turn.getTurns());  // End of do-while loop 2 - cntTurns
	
// **********************************************************************************************
	
	// End of turn processing
	// Print the turn history array
	for (int i = 0; i < turn.getTurns(); i++) {
		System.out.println(turn.getTurnHist()[i]);
	}
	
	// Play again or not - end of do-while loop
		YorN_Exception YesOrNo = new YorN_Exception();
		String YorN = YesOrNo.getYorN();
		
		playAgain = YorN;
		
	} while (playAgain.equals ("Y"));  // End of do-while loop 1 - playAgain
	
	System.out.println();
	System.out.println("Thanks for playing, " + name + "!  Come back and play again soon!");
	System.out.println();
	
	input.close();	
	turn.closeScanner();
	
	}

}

class DiceGame {
	
	// Variables
	int[] roll1 = new int[3];
	int[] roll2 = new int[3];
	
	int sum1;
	int pairBonus1;
	int tripBonus1;
	int strBonus1;
	int points1;
	int sum2;
	int pairBonus2;
	int tripBonus2;
	int strBonus2;
	int points2;
	boolean reroll;
	
	public void setRoll1() {
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			roll1[i] = random.nextInt(6) + 1;
		}
	}
	
	
	
	// Set roll2 with random values for R-input dice
	public void setRoll2(Turn turn) {
		reroll = false;
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			roll2[i] = roll1[i];
		}
		for (int i = 0; i < 3; i++) {
			if (turn.getDieLR()[i].equals("R")) {
				roll2[i] = random.nextInt(6) + 1;
				reroll = true;
			}
		}
	}
	
}

class Roll extends DiceGame{
	
	// Methods
		
		// Set roll1 random values 
	public void setRoll1(int x, int y, int z) {
		roll1[0] = x;
		roll1[1] = y;
		roll1[2] = z;
	}
	
	// Get roll1 array
	public int[] getRoll1() {
		return roll1;
	}
	
	// Get roll1 individual die values
	public int getRoll1(int index) {
		return roll1[index];
	}
	
	// Sort roll1 or roll2
	public void sortRoll(int[] roll) {
		Arrays.sort(roll);
	}
	
	// Set roll1 sum
	public void setSum1() {
		sum1 = roll1[0] + roll1[1] + roll1[2];
	}
	
	// Get roll1 sum
	public int getSum1() {
		return sum1;
	}
	
	// Set roll1 pair bonus
	public void setPairBonus1() {
		pairBonus1 = 0;
		if (roll1[0] == roll1[1] || roll1[0] == roll1[2] || roll1[1] == roll1[2]) {
			pairBonus1 = 2;
		}
	}
	
	// Get roll1 pair bonus
	public int getPairBonus1() {
		return pairBonus1;
	}
	
	// Set roll1 trip bonus
	public void setTripBonus1() {
		tripBonus1 = 0;
		if (roll1[0] == roll1[1] && roll1[0] == roll1[2]) {
			tripBonus1 = 30;
			pairBonus1 = 0;
		}
	}
	
	// Get roll1 trip bonus
	public int getTripBonus1() {
		return tripBonus1;
	}
	
	// Set roll1 straight bonus
	public void setStrBonus1() {
		strBonus1 = 0;
		if (roll1[1] == roll1[0] + 1 && roll1[2] == roll1[1] + 1) {
			strBonus1 = 10;
		}
	}
	
	// Get roll1 straight bonus
	public int getStrBonus1() {
		return strBonus1;
	}
	
	// Set roll1 points
	public void setPoints1() {
		points1 = sum1 + pairBonus1 + tripBonus1 + strBonus1;
	}
	
	// Get roll1 points
	public int getPoints1() {
		return points1;
	}
	
	// Get roll2 array
	public int[] getRoll2() {
		return roll2;
	}
			
	// Set roll2 sum
	public void setSum2() {
		sum2 = roll2[0] + roll2[1] + roll2[2];
	}
			
	// Get roll2 sum
	public int getSum2() {
		return sum2;
	}
			
	// Set roll2 pair bonus
	public void setPairBonus2() {
		pairBonus1 = 0;
		if (roll2[0] == roll2[1] || roll2[0] == roll2[2] || roll2[1] == roll2[2]) {
		pairBonus2 = 2;
	}
		}
			
	// Get roll2 pair bonus
	public int getPairBonus2() {
		return pairBonus2;
	}
			
	// Set roll2 trip bonus
	public void setTripBonus2() {
		tripBonus2 = 0;
		if (roll2[0] == roll2[1] && roll2[0] == roll2[2]) {
		tripBonus2 = 30;
		pairBonus2 = 0;
	}
		}
			
	// Get roll2 trip bonus
	public int getTripBonus2() {
		return tripBonus2;
	}
			
	// Set roll2 straight bonus
	public void setStrBonus2() {
		strBonus2 = 0;
		if (roll2[1] == roll2[0] + 1 && roll2[2] == roll2[1] + 1) {
		strBonus2 = 10;
	}
		}
			
	// Get roll2 straight bonus
	public int getStrBonus2() {
		return strBonus2;
	}
			
	// Set roll2 points
	public void setPoints2() {
		points2 = sum2 + pairBonus2 + tripBonus2 + strBonus2;
	}
			
	// Get roll1 points
	public int getPoints2() {
		return points2;
	}
		
		
	
	}
class Turn {
	
	// Variables
		private double turns;
		private int cntTurns;
		private String[] dieLR = new String[3];
		private String turnRecord;
		private String[] turnHist;
		
		Scanner input = new Scanner(System.in);
		turns_Exception turn = new turns_Exception();
		
		
	// Methods
		
		// Set turns from user input
		public void setTurns() {
			//Scanner input = new Scanner(System.in)
			turns = turn.getTurn();
			cntTurns = 0;
			turnHist = new String[(int) turns];
			System.out.println();
		}
		
		// Get turns
		public int getTurns() {
			return (int) turns;
		}
		
		// Increm turns
		public void incremCntTurn() {
			cntTurns++;
		}
		
		// Get cntTurns
		public int getCntTurns() {
			return cntTurns;
		}
		
		// Set L or R flag for each die
		public void setDieLR(Roll roll) {
			//Scanner input = new Scanner(System.in);
			for (int i = 0; i < 3; i++) {		// This loop set the flag to L or R for each die
				System.out.print("Die" + (i+1) + "  value: " + roll.getRoll1(i) + " ");

				lockAndRoll_Exception LOrR = new lockAndRoll_Exception();
				String lockOrRoll = LOrR.getLorR();
				
				dieLR[i] = lockOrRoll;
				
			}
			System.out.println();
			System.out.println();
		}
		
		// Get dieLR array
		public String[] getDieLR() {
			return dieLR;
		}
		
		// Set turn record
		public void setTurnRecord(String name, Roll roll, String dateTimeFormat) {
			turnRecord = name + " " + dateTimeFormat + " Turn-" + (cntTurns+1) + "  " + 
					roll.getRoll1()[0] + " " + roll.getRoll1()[1] + " " + roll.getRoll1()[2] + "  " +
					roll.getRoll2()[0] + " " + roll.getRoll2()[1] + " " + roll.getRoll2()[2] + 
					"  Pts: " + roll.getPoints2() + "  Imprv: " + (roll.getPoints2() - roll.getPoints1());
		}
		
		// Get turn record
		public String getTurnRecord() {
			return turnRecord;
		}
		
		// Update turn history array
		public void setTurnHist() {
			turnHist[cntTurns] = turnRecord;
		}
		
		// Get turn history array
		public String[] getTurnHist() {
			return turnHist;
		}
		
		// Print turn history
		public void prtTurnHist(Turn turn, String name, Roll roll, String dateTimeFormat) {
			System.out.println(name + " " + dateTimeFormat + " Turn-" + (this.cntTurns+1) + "  " + 
					roll.getRoll1()[0] + " " + roll.getRoll1()[1] + " " + roll.getRoll1()[2] + "  " +
					roll.getRoll2()[0] + " " + roll.getRoll2()[1] + " " + roll.getRoll2()[2] + 
					"  Pts: " + roll.getPoints2() + "  Imprv: " + (roll.getPoints2() - roll.getPoints1()));   
		}
		
		// Close Scanner object input
		public void closeScanner() {
			input.close();
		}
	}
class turns_Exception{

	public double getTurn() {
		
		boolean tryAgain = false;
		Scanner input = new Scanner(System.in);
		double x = 0;
		
		do {
			System.out.print("Enter how many turns would you like in this game: ");
			double turnNum = input.nextDouble();
			x = turnNum;
	
			if (turnNum > 1 && turnNum % 1 == 0) {
				tryAgain = false;
			}
			else {
				System.out.println("Entry must be a number higher than 1. Try again.");
				tryAgain = true;
			}
			} while(tryAgain);

		return x;
	}
}
class lockAndRoll_Exception{

	public String getLorR() {
		
		boolean tryAgain = false;
		String lockOrRoll;
	
		Scanner input = new Scanner(System.in);
		
		do {
			System.out.print("Enter L or R: ");
			String line = input.nextLine();
			char charEntry = line.charAt(0);
			lockOrRoll = Character.toString(charEntry);
			lockOrRoll = lockOrRoll.toUpperCase();
		
			if (lockOrRoll.equals("L") || lockOrRoll.equals("R")) {
				tryAgain = false;
			}
			else {
				System.out.println("Entry must be L or R");
				tryAgain = true;
				}
			} while(tryAgain); 
		
		
		return lockOrRoll;
		
			}
		}
		
class YorN_Exception{

	public String getYorN() {
		
		boolean tryAgain = false;
		String YorN;
	
		Scanner input = new Scanner(System.in);
		
		do {
			System.out.print("Would you like to play again? Enter Y or N: ");
			String line = input.nextLine();
			char charEntry = line.charAt(0);
			YorN = Character.toString(charEntry);
			YorN = YorN.toUpperCase();
		
			if (YorN.equals("Y") || YorN.equals("N")) {
				tryAgain = false;
			}
			else {
				System.out.println("Entry must be Y or N");
				tryAgain = true;
				}
			} while(tryAgain); 
		
		return YorN;
		
			}
		}