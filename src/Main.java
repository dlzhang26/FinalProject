import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JPanel implements KeyListener {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final int FPS = 60;
    World world;//instance of world class
    StartScreen start;//instance of the startscreen class
    int gamestart = 0 ;
    class Runner implements Runnable {
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


    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        System.out.println("You pressed down: " + c);
        gamestart++;
    
    }

    public void keyReleased(KeyEvent e) {
        char c=e.getKeyChar();
    }


    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public Main(){
        world = new World(WIDTH, HEIGHT, 25, 1);//initialize the instance of the world class

        addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));//600 x 600
        Thread mainThread = new Thread(new Runner());
        mainThread.start();//should we use some resources to figure out exactly what threads are?
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Tetris!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//stops running program when JFrame is closed
        Main mainInstance = new Main();
        frame.setContentPane(mainInstance);
        frame.pack();
        frame.setVisible(true);
    }

    public void drawStartScreen(Graphics g){
        g.setColor(Color.red);//sets text color to red
        g.fillRect((WIDTH/2)-150, (HEIGHT/2)-100, 300,  200);
        g.setColor(Color.white);
        g.drawString("Press any key to start", WIDTH/2-100, HEIGHT/2);//text on round rectangle
    }


    public void paintComponent(Graphics g) {//graphics method
        super.paintComponent(g);
        //start.draw(g, world);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        if(gamestart==0){
        drawStartScreen(g);
        }
        //this section gonna draw the stuff to the background.    
        if(gamestart>0){
            world.drawBoard(g);
            world.drawBlocks(g);
        }

    }

}