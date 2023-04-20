import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;

public class StartScreen implements KeyListener {
//fields
Boolean gameStart;

    public StartScreen(){//default constructor
        gameStart=false;//when leaderboard is first created, gameStart is false. 
    }

    
/////////////////////////////////////////////////////
    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();//when key is pressed it gets defined as char c, can use that to then do some things
    }
   
    public void keyReleased(KeyEvent e) {
       
    }
   
    public void keyTyped(KeyEvent e) {
       
    }

/////////////////////////////////////////////////////Methods from keyListener interface

    public void draw(Graphics g,World w) {//this is where the 
        Color c = g.getColor();

       // g.setColor(color);
        
         while(gameStart==false){//while the game has not started... do some drawings

        }
       
        g.setColor(c);

    }

}
