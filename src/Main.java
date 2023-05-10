import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Main extends JPanel implements KeyListener, MouseListener {
    public static Image test;
    public static Image button;
    public static Image title;
    public static final int WIDTH = 24;
    public static final int HEIGHT = 22;
    public static final int BLOCKSIZE = 30;
    public static int FPS = 2;
    World world;
    boolean highlight = false;
    static int page = 0;
    boolean showStart;
    String player;

    class Runner implements Runnable {//Runnable interface

        public void run() {
            while (true) {
                if(page==2){//while we are on the game screen, run the game
                    world.updateBlocks(1.0 / (double) FPS);
                }
                repaint();
                try {
                    Thread.sleep(1000 / FPS);
                } catch (InterruptedException e) {
                }
            }

        }


    }

    /*INFO for mouselistener found @ https://docs.oracle.com/javase/tutorial/uiswing/events/mouselistener.html */

    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        System.out.println("Mouse clicked " + mouseX + "  " + mouseY);
        if(mouseX>270 && mouseX<445 && mouseY>325 && mouseY<365){//detecting start button

            if (page<1){//only increments if page is currently 0
                if(pages.soundon==true){StdAudio.play("click.wav");}
                page++;
            }

        }

        if(mouseX>80 && mouseX<240 && mouseY>100 && mouseY<125){//player 1 rectangle
            if (page <3){
                if(pages.soundon==true){StdAudio.play("click.wav");}
                page++;
                player = "Player 1";

                //create a player object that has a highscore so that when you finish the game we can write the highscores to a specific file...

            }
        }

        if(mouseX>280 && mouseX<440 && mouseY>100 && mouseY<125){//player 2 rectangle
            if (page <3){
                if(pages.soundon==true){StdAudio.play("click.wav");}
                page++;
                player = "Player 2";
            }
        }

        if(mouseX>480 && mouseX<640 && mouseY>100 && mouseY<125){//player 3 rectangle
            if (page <3){
                if(pages.soundon==true){StdAudio.play("click.wav");}
                page++;
                player = "Player 3";
            }
        }

        if(mouseX>5 && mouseX<50 && mouseY>5 && mouseY<30){//detecting back button

            if(page==1 || page==2){//to make sure it doesn't go negative and not show anything (only when page=1)
                if(pages.soundon==true){StdAudio.play("click.wav");}
                page--;

            }

            System.out.println("backButton");
        }

        if (mouseX > 545 && mouseX< 625 && mouseY > 5 && mouseY < 30) {
            if(page==1 || page==2){
                if(pages.soundon==true){StdAudio.play("click.wav");}
                world.currentBlock.pause();
            }
        
            System.out.println("Pause");
        }
        if (mouseX > 625 && mouseX< 660 && mouseY > 5 && mouseY < 30) {
            if(page==1 || page==2){
                if(pages.soundon==true){StdAudio.play("click.wav");}
                world.currentBlock.resume();
            }
        
            System.out.println("Resume");
        }


        if(mouseX>280 && mouseX<360 && mouseY>500 && mouseY<580 && page==0){//detecting soundOn Button
            pages.soundon=true;
            StdAudio.play("click.wav");
        }

        if(mouseX>360 && mouseX<440 && mouseY>500 && mouseY<580 && page==0){//detecting soundOff Button
            pages.soundon=false;
        }

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {


    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked");
    }

    /******************MouseListener Methods Implemented********************************************************/


    /******************KeyListener Implementation***************************************************************/


    public void keyPressed(KeyEvent e) {//implementing methods from keylistener interface
        int keyCode = e.getKeyCode();
        //rotates the block if the up key is pressed
        if (keyCode == KeyEvent.VK_UP) {
            world.currentBlock.rotate(world.currentState);
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            world.moveRight();
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            world.moveLeft();
        }
        if(keyCode == KeyEvent.VK_DOWN){
            FPS = 10;//this is to speed up the current block while it is falling
        }
        if(keyCode == KeyEvent.VK_SHIFT){
            //world.hold();//to add a block to the hold
        }

        if(keyCode == KeyEvent.VK_S){//method for saving the score and player --> need to include another && gameover part to if statement
            SavedScores.saveScore(player, 100);
            System.out.println("saved score");
        }


    }


    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_DOWN){
            FPS = 2;
        }

    }


    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
    }
    /****************************KeyListener Methods Implemented**************************************************/

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public Main() {
        world = new World(WIDTH * BLOCKSIZE, HEIGHT * BLOCKSIZE);//initialize the instance of the world class, dimensions are (720,660)
        addKeyListener(this);//adding key/mouselisteners so functions can be performed. 
        addMouseListener(this);
        this.setPreferredSize(new Dimension(WIDTH * BLOCKSIZE, HEIGHT * BLOCKSIZE));
        Thread mainThread = new Thread(new Runner());
        mainThread.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TETRIS!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//stops running program when JFrame is closed
        Main mainInstance = new Main();
        frame.setContentPane(mainInstance);
        frame.pack();
        frame.setVisible(true);
    }


    /************************These Methods Handle Graphics********************************************************/
    public void paintComponent(Graphics g) {//graphics method
        super.paintComponent(g);
        pages.setupBackground(g);
        if (page==0) {
            drawStartScreen(g);
            pages.soundOption(g);
        }
        if(page==1){
            pages.chooseUserProfile(g);
        }
        if (page==2){
            gameGraphics(g);
        }
    }

    public void drawStartScreen(Graphics g) {//method for drawing of the startscreen ui


        Graphics2D g2d = (Graphics2D)g;//typecasting



        try {
            title = ImageIO.read(new File("titleOrig.png")); ///reading image file
            button = ImageIO.read(new File("button.png")); ///reading image file
        } catch (IOException e) {
            System.err.println(e);
        }
        //draws image
        g2d.drawImage(title, 70,60,(int)(title.getWidth(this)/3.5),(int)(title.getHeight(this)/3.5),this);
        //g2d.drawImage(title,185, 100, this);
        //g2d.drawImage(button, 280,300,button.getWidth(this),button.getHeight(this),this);
        g2d.drawImage(button, 240,230,280,280,this);
        /*
        //360,330 is center of canvas
        g.setColor(Color.pink);
        g.fillRoundRect(270, 325, 175, 40, 10, 10);

        g.setColor(Color.black);
        g.drawString("      Click to Play", 280, 350);

         */
    }


    public void gameGraphics(Graphics g){//method for drawing of the game graphics

        world.drawBoard(g);
        world.drawBlocks(g);
        g.drawString("SCORE: " + world.currentState.score, 250, 15);
        g.drawString("CURRENT PLAYER: " + player, 70, 15);
        g.fillRoundRect(5, 5, 45, 25, 10, 10);
        g.fillRoundRect(545, 5, 45, 25, 10, 10);
        g.fillRoundRect(625, 5, 55, 25, 10, 10);
        g.setColor(Color.black);
        g.drawString(" BACK", 6, 20);
        g.drawString(" PAUSE", 545, 20);
        g.drawString(" RESUME", 625, 20);

        //for drawing images
        Graphics2D g2d = (Graphics2D)g;
        ///reading image file
        try {
            test = ImageIO.read(new File("dice.png"));
        } catch (IOException e) {
            System.err.println(e);
        }
        //draws image
        g2d.drawImage(test,test.getWidth(this), test.getHeight(this), this);
    }
}//class Main.java