//Wenzhao Pan
//This program is a modified version of "Who Wants To Be a Millionaire"
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;
public class Main {
	static Scanner sc = new Scanner(System.in);
	
	//Questions array with all the possible questions inside of it
	static String[] questions = {"A person with well-developed abdominal muscles is said to have a what?",
			"In the UK, the abbreviation NHS stands for National what Service?",
			"Which Disney character famously leaves a glass slipper behind at a royal ball?",
			"What name is given to the revolving belt machinery in an airport that delivers checked luggage from the plane to baggage reclaim?",
			"Which of these brands was chiefly associated with the manufacture of household locks?",
			"The hammer and sickle is one of the most recognisable symbols of which political ideology?",
			"Which toys have been marketed with the phrase “robots in disguise”?",
			"What does the word loquacious mean",
			"Obstetrics is a branch of medicine particularly concerned with what?",
			"Construction of which of these famous landmarks was completed first",
			"In 1718, which pirate died in battle off the coast of what is now North Carolina",
			"What sort of animal is Walt Disney's Dumbo?",
			"Queen Anne was the daughter of which English Monarch?",
			"What year was Johanne Sebastian Bach born in",
			"What key is Mozart's famous Eine Kleine Nachtmusik in"};
	
	//2d array with row = question number and column = answer choice selection
	static String[][] answers = {
			{"One-pack", "Six-pack", "12-pack", "Family-pack"},
			{"Humanity", "Health", "Honour", "Household"},
			{"Elsa", "Cinderella", "Sleeping Beauty", "Pocahontas"},
			{"Hangar", "Terminal", "Carousel", "Concourse"},
			{"Phillips", "Flymo", "Chubb", "Ronseal"},
			{"Republicanism", "Communism", "Conservatism", "Liberalism"},
			{"Bratz Dolls", "Sylvanian Families", "Hatchimals", "Transformers"},
			{"Angry", "Chatty", "Beautiful", "Shy"},
			{"Childbirth", "Broken bones", "Heart conditions", "Old age"},
			{"Empire State Building", "Royal Albert Hall", "Eiffel Tower", "Big Ben Clock Tower"},
			{"Calico Jack", "Blackbeard", "Bartholomew Roberts", "Captain Kidd"},
			{"Deer", "Rabbit", "Mouse", "Elephant"},
			{"James II", "Henry VIII", "Victoria", "William I"},
			{"1827", "1495", "1685", "1750"},
			{"G Major", "C Major", "A minor", "E minor"}
	};
	
	//Array of correct answers to compare answers to
	static String[] correctAnswers = {"Six-pack",
			"Health",
			"Cinderella",
			"Carousel",
			"Chubb",
			"Communism",
			"Transformers",
			"Chatty",
			"Childbirth",
			"Big Ben Clock Tower",
			"Blackbeard",
			"Elephant",
			"James II",
			"1685",
			"G Major"};
	
	//Keep track of how many life lines are left
	static int lifelinesLeft = 2;
	
	//Method for time delays, input the number of seconds to wait
	//Method to cause time delays. Input the number of seconds you want to delay
	public static void wait(int sec) {
		try{
			Thread.sleep(sec * 1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	//Main menu of game
	public static void main(String[] args) {
		//try catch for playing music
		try{
			File myObj = new File("src/good music.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(myObj);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch(Exception e){
			System.out.println("Oops, something went wrong");
			System.out.println(e);
		}
		
		//Try catch to get input for the main menu options
		while(true) {
			String menuInput1 = "";
			int menuInput;
			while(true) {
				try {
					//Print out main menu options
					System.out.println("Welcome to");
					System.out.println("WHO WANTS TO BE A MILLIONAIRE"+"\n");
					System.out.println("[1] for rules");
					System.out.println("[2] to proceed to game");
					System.out.println("[3] to exit game" + "\n" + "\n");
					menuInput1 = sc.nextLine();
					menuInput = Integer.parseInt(menuInput1);
					if(menuInput > 3 || menuInput < 1) System.out.println("That's not a valid input, please enter either 1, 2, or 3" + "\n" + "\n");
					wait(2);
					break;
				}
				catch(Exception e){
					System.out.println("That's not a valid input, please enter either 1, 2, or 3" + "\n" + "\n");
					wait(2);
				}
			}
			
			//Rules of the game with time delays for dramatic effect and ease of reading
			if(menuInput == 1) {
				System.out.println("Hello! Here are the rules for Who Wants To Be a Millionaire: ");
				wait(2);
				System.out.print("You must answer 10 questions correctly in order to win");
				wait(2);
				System.out.print(".");
				wait(1);
				System.out.print(".");
				wait(1);
				System.out.print(".");
				wait(1);
				System.out.println("\n" + "ONE MILLION DOLLARS!");
				wait(2);
				System.out.println("For each question, you will be shown: question statement and FOUR multiple choice answers.");
				wait(2);
				System.out.println("Then you may choose an answer, or to quit and keep your earnings so far");
				wait(2);
				System.out.println("To help you along your way, you have 2 chances to ask for help.");
				wait(2);
				System.out.println("At any point, you may use one (and only one) of your lifelines.");
				wait(2);
				System.out.println("Your lifeline options are:");
				wait(2);
				System.out.println("50/50 - the host will eliminate two of the wrong answers.");
				wait(3);
				System.out.println("Ask the audience - the audience votes with their keypads on their choice of answer.");
				wait(3);
				System.out.println("Be careful though, if you answer incorrectly...");
				wait(2);
				System.out.print("you will be kicked out of the game and lose ");
				wait(2);
				System.out.print("ALL ");
				wait(1);
				System.out.print("OF YOUR EARNINGS.");
				wait(5);
				System.out.println("\n" + "\n" + "\n");
			}
			
			//Calls the game function
			//Once game function ends, prints "Thank you for playing" and returns to main menu
			else if(menuInput == 2) {
				System.out.print("loading");
				System.out.print(".");
				wait(1);
				System.out.print(".");
				wait(1);
				System.out.println(". ");
				wait(1);
				int gameInput = menuInput;
				game(gameInput);
				System.out.println("Thank you for playing!" + "\n" + "\n" + "\n");
				wait(3);
			}
			
			//Closes the game program (only way to terminate the program)
			else if(menuInput == 3) {
				System.out.println("Thank you for playing, see you later!" + "\n" + "\n" + "\n");
				break;
			}
		}
		
		//Closes scanner
		sc.close();
	}

	//Game method (called with menu input of 2)
	public static void game(int game) {
		
		//Should never theoretically happen, but just in case something does go wrong from the main menu
		if(game != 2) {
			System.out.println("Something went wrong please try again from the main menu");
			wait(2);
			System.out.println("\n" + "\n" + "\n" + "\n");
		}
		
		//If everything goes smoothly and the game is correctly called, the game will begin
		else {
			
			//Keeps track of how much money you've won
			int winnings = 0;
			
			//Resets lifelinesLeft
			lifelinesLeft = 2;
			
			//Array lists for questions and answers
			ArrayList<Integer> questionsOrder = new ArrayList<Integer>();
			ArrayList<Integer> answersOrder = new ArrayList<Integer>();
			
			//Add all of the questions into the questionsOrder array list
			for(int i = 1; i <= 15; i++) questionsOrder.add(i);
			
			//Add all of the answers into the answersOrder array list
			for(int i = 1; i <= 4; i++) answersOrder.add(i);
			
			//Shuffle the order of the questions so each run through of the game is unique
			Collections.shuffle(questionsOrder);
			
			//Prints only the first 10 questions
			for(int i = 0; i < 10; i++) {
				System.out.println("Question " + (int)(i+1) + ":");
				
				//For each question, the 4 answer choices are also shuffled
				Collections.shuffle(answersOrder);
				System.out.println(questions[questionsOrder.get(i)-1]);
				
				//Print out answer choices to the question
				for(int j = 0; j < 4; j++) {
					
					//Char for the choice (prints out the [A], [B], etc parts
					int convert = j+1;
					System.out.print("[" + convert + "] ");
					
					//Prints out the actual answers
					System.out.println(answers[questionsOrder.get(i)-1][answersOrder.get(j)-1]);
				}
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~" + "\n" + "[5] Return to main menu");
				
				//Option to use life lines for help
				if(lifelinesLeft>0) {
					System.out.println("[6] Use 50/50 lifeline");
					System.out.println("[7] Use ask the audience lifeline");
				}
				
				//The input for which answer you choose
				int ans;
				
				//Try catch to prevent invalid inputs for the answer
				while (true){
					try{
						String temp = sc.nextLine();
						ans = Integer.parseInt(temp);
						if(ans > 7 || ans < 1) System.out.println("Not a valid input, please try again");
						else break;


					} catch(Exception e){
						System.out.println("Not a valid input, please try again");
					}
				}
				
				//If statement for calling the 50/50 life line
				if(ans == 6 && lifelinesLeft>0){
					
					//Keeps track of how many life lines are left
					lifelinesLeft--;
					String[] fifty = lifeline1(answers[questionsOrder.get(i)-1][answersOrder.get(0)-1], answers[questionsOrder.get(i)-1][answersOrder.get(1)-1], answers[questionsOrder.get(i)-1][answersOrder.get(2)-1], answers[questionsOrder.get(i)-1][answersOrder.get(3)-1], questionsOrder.get(i)-1);
					System.out.println("[1] " + fifty[0]);
					System.out.println("[2] " + fifty[1]);
					System.out.println("Two of the incorrect answer choices have been eliminated");
          wait(1);
          System.out.println("What will your answer be?");
          
          //Try catch to prevent invalid input
					while (true){
						try{
							String temp = sc.nextLine();
							ans = Integer.parseInt(temp);
							if(ans > 2 || ans < 1) System.out.println("Not a valid input, please try again");
							else break;


						} catch(Exception e){
							System.out.println("Not a valid input, please try again");
						}
					}
					
					//Checks to see if the answer choice selected AFTER using the 50/50 lifeline is correct
					if(ans == 1 && fifty[0] == correctAnswers[questionsOrder.get(i)-1]) {
						winnings+=100000;
						wait(2);
						System.out.println("\n" + "Correct!");
						wait(1);
						System.out.println("Your earnings are now "+ winnings + "$" + "\n");
						wait(1);
						if(winnings == 1000000) {
							System.out.println("You have won Who Wants To Be a Millionaire!");
							wait(1);
							System.out.println("You will be directed to the main menu shortly");
							wait(1);
							break;
						}
						else{
							System.out.println("Your next question is: ");
							wait(1);
						}
					}
					else if(ans == 2 && fifty[1] == correctAnswers[questionsOrder.get(i)-1]) {
						winnings+=100000;
						wait(2);
						System.out.println("\n" + "Correct!");
						wait(1);
						System.out.println("Your earnings are now "+ winnings + "$" + "\n");
						wait(1);
						if(winnings == 1000000) {
							System.out.println("You have won Who Wants To Be a Millionaire!");
							wait(1);
							System.out.println("You will be directed to the main menu shortly");
							wait(1);
							break;
						}
						else{
							System.out.println("Your next question is: ");
							wait(1);
						}
					}
					
					//If answer choice AFTER using 50/50 life line is incorrect
					else {
						wait(2);
						System.out.println("\n" + "Oh no! You answered incorrectly.");
						wait(1);
						System.out.println("You have lost all your earnings.");
						wait(1);
						System.out.println("You will be directed to the main menu momentarily.");
						wait(1);
						System.out.println("Better luck next time!" + "\n");
						break;
					}
				}
				
				//If statement for calling the audience vote life line to help
				else if(ans == 7 && lifelinesLeft>0){
					int[] audienceVote = lifeline2(answers[questionsOrder.get(i)-1][answersOrder.get(0)-1], answers[questionsOrder.get(i)-1][answersOrder.get(1)-1], answers[questionsOrder.get(i)-1][answersOrder.get(2)-1], answers[questionsOrder.get(i)-1][answersOrder.get(3)-1], questionsOrder.get(i)-1);
					
					//Keeps track of how many life lines are left
					lifelinesLeft--;
					
					//Print out the percentages for the audience vote
					System.out.println("The audience votes are in!");
					wait(1);
					System.out.println("[1] " + answers[questionsOrder.get(i)-1][answersOrder.get(0)-1] + " - " + audienceVote[0] + "%");
					wait(1);
					System.out.println("[2] " + answers[questionsOrder.get(i)-1][answersOrder.get(1)-1] + " - " + audienceVote[1] + "%");
					wait(1);
					System.out.println("[3] " + answers[questionsOrder.get(i)-1][answersOrder.get(2)-1] + " - " + audienceVote[2] + "%");
					wait(1);
					System.out.println("[4] " + answers[questionsOrder.get(i)-1][answersOrder.get(3)-1] + " - " + audienceVote[3] + "%");

          System.out.println("Seeing these results, what is your answer?");
					
					//Try catch to prevent invalid input 
					while (true){
						try{
							String temp = sc.nextLine();
							ans = Integer.parseInt(temp);
							if(ans > 4 || ans < 1) System.out.println("Not a valid input, please try again");
							else break;
						}catch(Exception e){
							System.out.println("Not a valid input, please try again");
						}
					}
					
					//If statements to check if the answer AFTER using the audience vote life line is correct
					//First answer choice is picked and is correct:
					if(ans == 1 && answers[questionsOrder.get(i)-1][answersOrder.get(0)-1] == correctAnswers[questionsOrder.get(i)-1]) {
						winnings+=100000;
						wait(2);
						System.out.println("\n" + "Correct!");
						wait(1);
						System.out.println("Your earnings are now "+ winnings + "$" + "\n");
						wait(1);
						if(winnings == 1000000) {
							System.out.println("You have won Who Wants To Be a Millionaire!");
							wait(1);
							System.out.println("You will be directed to the main menu shortly");
							wait(1);
							break;
						}
						else{
							System.out.println("Your next question is: ");
							wait(1);
						}
					}
					
					//Second answer choice is picked and is correct:
					else if(ans == 2 && answers[questionsOrder.get(i)-1][answersOrder.get(1)-1] == correctAnswers[questionsOrder.get(i)-1]) {
						winnings+=100000;
						wait(2);
						System.out.println("\n"+ "Correct!");
						wait(1);
						System.out.println("Your earnings are now "+ winnings + "$" + "\n");
						wait(1);
						if(winnings == 1000000) {
							System.out.println("You have won Who Wants To Be a Millionaire!");
							wait(1);
							System.out.println("You will be directed to the main menu shortly");
							wait(1);
							break;
						}
						else{
							System.out.println("Your next question is: ");
							wait(1);
						}
					}
					
					//Third answer choice is picked and is correct
					else if(ans == 3 && answers[questionsOrder.get(i)-1][answersOrder.get(2)-1] == correctAnswers[questionsOrder.get(i)-1]) {
						winnings+=100000;
						wait(2);
						System.out.println("\n" + "Correct!");
						wait(1);
						System.out.println("Your earnings are now "+ winnings + "$" + "\n");
						wait(1);
						if(winnings == 1000000) {
							System.out.println("You have won Who Wants To Be a Millionaire!");
							wait(1);
							System.out.println("You will be directed to the main menu shortly");
							wait(1);
							break;
						}
						else{
							System.out.println("Your next question is: ");
							wait(1);
						}
					}
					
					//Fourth answer choice is picked and is correct:
					else if(ans == 4 && answers[questionsOrder.get(i)-1][answersOrder.get(3)-1] == correctAnswers[questionsOrder.get(i)-1]) {
						winnings+=100000;
						wait(2);
						System.out.println("\n" + "Correct!");
						wait(1);
						System.out.println("Your earnings are now "+ winnings + "$"+"\n");
						wait(1);
						if(winnings == 1000000) {
							System.out.println("You have won Who Wants To Be a Millionaire!");
							wait(1);
							System.out.println("You will be directed to the main menu shortly");
							wait(1);
							break;
						}
						else{
							System.out.println("Your next question is: ");
							wait(1);
						}
					}
					
					//Answered incorrectly
					else {
						wait(2);
						System.out.println("\n" + "Oh no! You answered incorrectly.");
						wait(1);
						System.out.println("You have lost all your earnings.");
						wait(1);
						System.out.println("You will be directed to the main menu momentarily.");
						wait(1);
						System.out.println("Better luck next time!" + "\n");
						break;
					}
				}
				
				//Checks to see if there are no life lines left
				else if(ans == 6 || ans == 7 && lifelinesLeft<1) {
					wait(2);
					System.out.println("You don't have any lifelines left!" + "\n");
					//Resets the question and scrambles the answers again
					i--;
					wait(1);
				}
				
				//Checks to see if user wants to quit game
				else if(ans == 5) {
					if(winnings==0) {
						System.out.println("Your winnings are 0$");
            break;
					}
					else {
						System.out.println("You have won " + winnings + "$");
						break;
					}
				}
				
				//Checks to see if the answer is correct (no life lines used)
				else if(answers[questionsOrder.get(i)-1][answersOrder.get(ans-1)-1] == correctAnswers[questionsOrder.get(i)-1]) {
					winnings+=100000;
					wait(2);
					System.out.println("\n" + "Correct!");
					wait(1);
					System.out.println("Your earnings are now "+ winnings + "$" + "\n");
					wait(1);
					if(winnings == 1000000) {
						System.out.println("You have won Who Wants To Be a Millionaire!");
						wait(1);
						System.out.println("You will be directed to the main menu shortly");
						wait(1);
						break;
					}
					else{
						System.out.println("Your next question is: ");
						wait(1);
					}
				}
				
				
				//Incorrect answer
				else {
					if(winnings == 0) {
						wait(2);
						System.out.println("\n" + "Oh no! You answered incorrectly.");
						wait(1);
						System.out.println("You don't lose anything because you won 0$");
						wait(1);
						System.out.println("You will be directed to the main menu momentarily.");
						wait(1);
						System.out.println("Better luck next time!" + "\n");
						break;
					}
					else {
						wait(2);
						System.out.println("\n" + "Oh no! You answered incorrectly.");
						wait(1);
						System.out.println("You have lost all your earnings.");
						wait(1);
						System.out.println("You will be directed to the main menu momentarily.");
						wait(1);
						System.out.println("Better luck next time!" + "\n");
						break;
					}
				}
			}
		}
	}
	
	//Life line 1 is the 50/50 life line
	//Input the four answer choices for the question you are using the life line on
	public static String[] lifeline1 (String ans1, String ans2, String ans3, String ans4, int question) {
		String[] fifty = new String[2];
		String[] answers = {ans1, ans2, ans3, ans4};
		
		//Continually generates random numbers until it reaches an answer choice that is incorrect
		while(true) {
			int random = (int) (Math.random()*(100-1+1)+1);
			if(answers[random%4] != correctAnswers[question]) {
				
				//Places incorrect answer choice into a random position in fifty array
				fifty[random%2] = answers[random%4];
				
				//Places correct answer into the other random position in fifty array
				fifty[(random+1)%2] = correctAnswers[question];
				break;
			}
		}

		//Returns String array with the first incorrect answer in the scrambled order, and the correct answer
		return fifty;
	}
	
	//Life line 2 is the audience vote life line
	//Input the four answer choices for the question you are using the life line on
	public static int[] lifeline2 (String ans1, String ans2, String ans3, String ans4, int question) {
		int[] audience = new int[4];
		
		//Randomly generates 4 numbers between 1 and 25
		int random1 = (int) (Math.random()*(25-1+1)+1);
		int random2 = (int) (Math.random()*(25-1+1)+1);
		int random3 = (int) (Math.random()*(25-1+1)+1);
		int random4 = (int) (Math.random()*(25-1+1)+1);

		//Assigns random numbers to each answer choice based on if it is correct or not
		//Ensures all the randomly generated numbers add up to 100%
		if(ans1 == correctAnswers[question]) audience[0] = 100-random2-random3-random4;
		else audience[0] = random1;
		if(ans2 == correctAnswers[question]) audience[1] = 100-random1-random3-random4;
		else audience[1] = random2;
		if(ans3 == correctAnswers[question]) audience[2] = 100-random1-random2-random4;
		else audience[2] = random3;
		if(ans4 == correctAnswers[question]) audience[3] = 100-random1-random2-random3;
		else audience[3] = random4;
		
		//Returns int array with the percentages of audience that voted for each question
		return audience;
	}
}