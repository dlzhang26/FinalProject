import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class pages{


    static boolean soundon = true;
    //public static Image test;

    /******************************************************Page 0*****************/

    public static void setupBackground(Graphics g) {
        g.setColor(Color.BLACK);//setup background
        g.fillRect(0, 0, Main.WIDTH * Main.BLOCKSIZE, Main.HEIGHT * Main.BLOCKSIZE);
    }

    public static void drawStartScreen(Graphics g) {//method for drawing of the startscreen ui
        //360,330 is center of canvas
        g.setColor(Color.white);
        g.fillRoundRect(280, 330, 160, 25, 10, 10);
        g.setColor(Color.black);
        g.drawString("      Click to Play", 280, 350);

        ///////////
        /*
        try {
            test = ImageIO.read(new File("dice.png"));
        } catch (IOException e) {
            System.err.println(e);
        }

         */
    }
    /*
    public void paint(Graphics g){
        BufferedImage bufferedImage = new BufferedImage(test.getWidth(this), test.getHeight(), BufferedImage.TYPE_INT_RGB);

    }
     */

    public static void soundOption(Graphics g) {//making a sound toggle switch for music
        if (soundon == true) {
            g.setColor(Color.white);
            g.fillRoundRect(280, 500, 80, 50, 10, 10);
            g.setColor(Color.black);
            g.drawString("Sound On", 290, 520);

            g.setColor(Color.gray);
            g.fillRoundRect(360, 500, 80, 50, 10, 10);
            g.setColor(Color.white);
            g.drawString("Sound Off", 370, 520);
        } else {
            g.setColor(Color.gray);
            g.fillRoundRect(280, 500, 80, 50, 10, 10);
            g.setColor(Color.white);
            g.drawString("Sound On", 290, 520);

            g.setColor(Color.white);
            g.fillRoundRect(360, 500, 80, 50, 10, 10);
            g.setColor(Color.gray);
            g.drawString("Sound Off", 370, 520);

        }//else

    }//soundoption

    /******************************************************Page 1*****************/

    public static void chooseUserProfile(Graphics g) {
        g.setColor(Color.white);
        g.fillRoundRect(280, 100, 160, 25, 10, 10);
        g.fillRoundRect(80, 100, 160, 25, 10, 10);
        g.fillRoundRect(480, 100, 160, 25, 10, 10);
        g.fillRoundRect(5, 5, 45, 25, 10, 10);


        g.setColor(Color.BLACK);
        g.drawString("     Player 2", 280, 120);
        g.drawString("     Player 1", 100, 120);
        g.drawString("     Player 3", 480, 120);
        g.drawString(" BACK", 6, 20);
    }

}