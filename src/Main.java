import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JPanel implements KeyListener {
    public static final int WIDTH = 12;
    public static final int HEIGHT = 22;

    public static final int BLOCKSIZE = 30;
    public static final int FPS = 2;
    World world;
    int gamestart =0 ;

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


    public void keyPressed(KeyEvent e) {//implementing methods from keylistener interface
        int keyCode = e.getKeyCode();
        //rotates the block if the up key is pressed
        if(keyCode ==KeyEvent.VK_UP){
            //need to change the for loop so it only changes the one block
            for (int i = 0; i< world.numBlocks;i++){
                world.blocks[i].rotate();
            }
        }
        if(keyCode == KeyEvent.VK_RIGHT){
            for (int i = 0; i< world.numBlocks;i++){
                world.blocks[i].moveRight();
            }
        }
        if(keyCode == KeyEvent.VK_LEFT){
            for (int i = 0; i< world.numBlocks;i++){
                world.blocks[i].moveLeft();
            }
        }
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
        world = new World(WIDTH*BLOCKSIZE, HEIGHT*BLOCKSIZE,  1);//initialize the instance of the world class
        addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH*BLOCKSIZE, HEIGHT*BLOCKSIZE));
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

        g.setColor(Color.BLACK);//setup background
        g.fillRect(0, 0, WIDTH*BLOCKSIZE, HEIGHT*BLOCKSIZE);
        /*


        if (gamestart==0){
            g.setColor(Color.white);
            g.drawString("Press any key to start", 250, 300);
        }
        else{
            */
        world.drawBoard(g);
        world.drawBlocks(g);
        //}
    }
}