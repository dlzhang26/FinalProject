import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class pages {

    boolean soundon = true;

    public void chooseUserProfile(Graphics g){
        
    }

    public void soundOption(Graphics g){//making a sound toggle switch for music
        if(soundon==true){
            g.setColor(Color.white);
        g.fillRect(280, 500, 80, 50);
        g.setColor(Color.black);
        g.drawString("Sound On", 300, 520);
        }
        g.fillRect(360, 500, 80, 50);

    }
    

    
}
