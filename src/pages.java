import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class pages {

   static  boolean soundon = true;

    public void chooseUserProfile(Graphics g){
        
    }

    public static void soundOption(Graphics g){//making a sound toggle switch for music
        if(soundon==true){
            g.setColor(Color.white);
        g.fillRoundRect(280, 500, 80, 50,10,10);
        g.setColor(Color.black);
        g.drawString("Sound On", 290, 520);

        g.setColor(Color.gray);
        g.fillRoundRect(360, 500, 80, 50,10,10);
        g.setColor(Color.white);
        g.drawString("Sound Off", 370, 520);
        }else{
            g.setColor(Color.gray);
        g.fillRoundRect(280, 500, 80, 50,10,10);
        g.setColor(Color.white);
        g.drawString("Sound On", 290, 520);

        g.setColor(Color.white);
        g.fillRoundRect(360, 500, 80, 50,10,10);
        g.setColor(Color.gray);
        g.drawString("Sound Off", 370, 520);
        
    }//else
        

    }
    

    
}
