import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.awt.Graphics#getFont;
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
        //could also do like a 
        gameStart=true;//if a key is pressed the game starts. 
    }
   
    public void keyReleased(KeyEvent e) {
       
    }
   
    public void keyTyped(KeyEvent e) {
       
    }

/////////////////////////////////////////////////////Methods from keyListener interface

    public void draw(Graphics g, World w) {//this is where the 
        Color c = g.getColor();
       // g.setColor(color);
        
         while(gameStart==false){//while the game has not started... do some drawings
            //g.setFont(Serif);
            g.setColor(Color.red);//sets text color to red
            
            g.drawRoundRect(w.height/2, w.width/2, 300,  200, 90,  90);
            g.setColor(Color.white);
            g.drawString("Press any key to start", w.width/2, w.height/2);//text on round rectangle

        }
       
        g.setColor(c);

    }

}
