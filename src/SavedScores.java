import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class SavedScores {


    
    public static void saveScore(String player, int score){//method for saving scores, give user option to save or not. 

		try{
			PrintWriter writer = new PrintWriter("highscores");//creates the printwriter class outside of the for loop
			
            writer.write(player + " Score: " + score);//writes the score 
			
			writer.close();//closes the printwriter

        }

		catch (FileNotFoundException e){//to catch exceptions if the above cannot be completed
			System.out.println("Badness in savePointToFile");
			System.err.println(e);
			}
		
        }

    
	// Your code here
    
}
