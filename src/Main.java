import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JPanel implements KeyListener {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final int FPS = 1;
    World world;

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
        int keyCode = e.getKeyCode();
        //rotates the block if the up key is pressed
        if(keyCode ==KeyEvent.VK_UP){
            for (int i = 0; i< world.numBlocks;i++){
                world.blocks[i].rotate();
            }
        }


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
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        Thread mainThread = new Thread(new Runner());
        mainThread.start();//should we use some resources to figure out exactly what threads are?
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//stops running program when JFrame is closed
        Main mainInstance = new Main();
        frame.setContentPane(mainInstance);
        frame.pack();
        frame.setVisible(true);
    }


    public void paintComponent(Graphics g) {//graphics method
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        world.drawBoard(g);
        world.drawBlocks(g);

    }
}