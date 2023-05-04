import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;

public class SavedScores {

    

    public static void saveScore(String player, int score){//method for saving scores, give user option to save or not. 

		try{
			//creates the printwriter class outside of the for loop
			PrintWriter writer = new PrintWriter("highscores");
            writer.write(player + " Score: " + score + "\n");//writes the score and the playername to a file named highscores.java
			
			writer.close();//closes the printwriter
            System.out.println("done");// so it's calling the method correctly and getting into all of this. however, it just isn't managing to write to the disc, which is very confusing. 

        }

		catch (FileNotFoundException e){//to catch exceptions if the above cannot be completed
			System.out.println("Unable to Locate File");
			System.err.println(e);
			}
		
        }
    
}
