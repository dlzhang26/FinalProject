import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main extends JPanel implements KeyListener, MouseListener {
    public static final int WIDTH = 24;
    public static final int HEIGHT = 22;

    public static final int BLOCKSIZE = 30;

    public static int FPS = 2;
    //public static final int FPS = 2;
    World world;
    //static int gamestart = 0;
    boolean highlight = false;
    static int page = 0;
    boolean showStart;

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
                     int mouseX = e.getX();
                     int mouseY = e.getY();
                     System.out.println("Mouse clicked " + mouseX + "  " + mouseY);
                     if(mouseX>280 && mouseX<440 && mouseY>330 && mouseY<355){//detecting start button

                        if (page<1){//only increments if page is currently 0
                        page++;
                        }

                     }

                     if(mouseX>5 && mouseX<50 && mouseY>5 && mouseY<30){//detecting back button 

                        if(page==1){//to make sure it doesn't go negative and not show anything (only when page=1)
                            page--;
                        }
                        
                        System.out.println("backButton");
                     }


     }
 
     public void mouseReleased(MouseEvent e) {
        
     }
 
     public void mouseEntered(MouseEvent e) {
       
        
     }
 
     public void mouseExited(MouseEvent e) {
        
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
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            world.currentBlock.moveRight();
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            world.currentBlock.moveLeft();
        }
        if(keyCode == KeyEvent.VK_DOWN){
            FPS = 10;
        }
        if(keyCode == KeyEvent.VK_SHIFT){
            world.hold();
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
        JFrame frame = new JFrame("ARCADE!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//stops running program when JFrame is closed
        Main mainInstance = new Main();
        frame.setContentPane(mainInstance);
        frame.pack();
        frame.setVisible(true);
    }

    

    public void paintComponent(Graphics g) {//graphics method
        super.paintComponent(g);
        setupBackground(g);
        if (page==0) {
            drawStartScreen(g);
        } else if(page==1){
            gameGraphics(g);
        }
    }


    public void setupBackground(Graphics g){
        g.setColor(Color.BLACK);//setup background
        g.fillRect(0, 0, WIDTH * BLOCKSIZE, HEIGHT * BLOCKSIZE);
    }


    public void drawStartScreen(Graphics g){//method for drawing of the startscreen ui
       //360,330 is center of canvas
        g.setColor(Color.white);
        g.fillRect(120, 285, 150, 25);
        
        g.fillRoundRect(280, 330, 160, 25, 10, 10);
        g.setColor(Color.black);
        g.drawString("      Click to Play", 280, 350);

        
        g.setColor(Color.MAGENTA);
        g.drawString("Click to start", 130, 300);

        //cannot get images to be drawn at all, not really sure why
        Image image = Toolkit.getDefaultToolkit().getImage("PNGtransparencydemonstration.png");
        g.drawImage(image,50,50,null);
    }
    



    public void gameGraphics(Graphics g){//method for drawing of the game graphics
        world.drawBoard(g);
        world.drawBlocks(g);
        g.drawString("SCORE: ", 250, 15);
        g.drawString("CURRENT PLAYER: ", 70, 15);
        g.fillRoundRect(5, 5, 45, 25, 10, 10);
        g.setColor(Color.black);
        g.drawString(" BACK", 6, 20);
    }
}