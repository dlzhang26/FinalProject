import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main extends JPanel implements KeyListener, MouseListener {
    public static final int WIDTH = 12;
    public static final int HEIGHT = 22;

    public static final int BLOCKSIZE = 30;
    public static final int FPS = 2;
    World world;
    int gamestart = 0;

    class Runner implements Runnable {//Runnable interface

        public void run() {
            while (true) {
                world.updateBlocks(1.0 / (double) FPS);
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
        System.out.println("Mouse pressed; # of clicks: "
                     + e.getClickCount());
     }
 
     public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse released; # of clicks: "
                     + e.getClickCount());
     }
 
     public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered");
     }
 
     public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited");
     }
 
     public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked (# of clicks: "
                     + e.getClickCount() + ")");
     }


    public void keyPressed(KeyEvent e) {//implementing methods from keylistener interface
        int keyCode = e.getKeyCode();
        //rotates the block if the up key is pressed
        if (keyCode == KeyEvent.VK_UP) {
            world.currentBlock.rotate();
            /*
            //need to change the for loop so it only changes the one block
            for (int i = 0; i< world.numBlocks;i++){
                world.blocks[i].rotate();
            }

             */
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            world.currentBlock.moveRight();
            /*
            for (int i = 0; i< world.numBlocks;i++){
                world.blocks[i].moveRight();
            }

             */
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            world.currentBlock.moveLeft();
            /*
            for (int i = 0; i< world.numBlocks;i++){
                world.blocks[i].moveLeft();
            }

             */
        }
        gamestart++;


    }

    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();
    }


    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public Main() {
        world = new World(WIDTH * BLOCKSIZE, HEIGHT * BLOCKSIZE);//initialize the instance of the world class
        addKeyListener(this);
        addMouseListener(this);
        this.setPreferredSize(new Dimension(WIDTH * BLOCKSIZE, HEIGHT * BLOCKSIZE));
        Thread mainThread = new Thread(new Runner());
        mainThread.start();//should we use some resources to figure out exactly what threads are?
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ARCADE!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//stops running program when JFrame is closed
        Main mainInstance = new Main();
        frame.setContentPane(mainInstance);
        frame.pack();
        frame.setVisible(true);
    }

    

    public void paintComponent(Graphics g) {//graphics method
        super.paintComponent(g);

        g.setColor(Color.BLACK);//setup background
        g.fillRect(0, 0, WIDTH * BLOCKSIZE, HEIGHT * BLOCKSIZE);
        if (gamestart == 0) {
            drawStartScreen(g);
        } else {
            gameGraphics(g);
        }
    }



    public void drawStartScreen(Graphics g){//method for drawing of the startscreen ui
        g.setColor(Color.white);
            g.drawString("Press any key to start", 130, 300);
    }

    public void gameGraphics(Graphics g){//method for drawing of the game graphics
        world.drawBoard(g);
        world.drawBlocks(g);
        g.drawString("SCORE: ", 220, 15);
        g.drawString("CURRENT PLAYER: ", 50, 15);
    }
}