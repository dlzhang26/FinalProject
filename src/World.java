import java.awt.*;

public class World {
    int height;
    int width;

    int numSpheres;

    int size;
    //Block blocks[];

    public World(int initWidth, int initHeight, int blockSize) {
        width = initWidth;
        height = initHeight;
        size = blockSize;


    }
    public void drawBoard(Graphics g){//10 by 20 board
        for (int i = 1; i < 22-1; i++){//height of the board
            for (int j = 1; j < 12-1; j++) {//width of the board
                g.setColor(Color.white);
                g.drawRect(j*size,i*size, size,size);
            }
        }
    }

    /*
    public void drawBlocks(Graphics g) {
        for (int i = 0; i < numSpheres; i++) {
            //blocks[i].draw(g);
        }
    }

    public void updateBlock(double time) {
        for (int i = 0; i < numSpheres; i++)
            //block[i].update(this, time);
    }

    */
}
