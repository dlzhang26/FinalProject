import java.awt.*;

public class World {
    int height;
    int width;

    int numBlocks;

    int size;
    Block blocks[];

    public World(int initWidth, int initHeight, int blockSize, int initNumBlocks) {
        width = initWidth;
        height = initHeight;
        size = blockSize;

        numBlocks = initNumBlocks;
        blocks  = new Block[numBlocks];

        for (int i = 0; i < numBlocks; i ++)
        {
            blocks[i] = new Block();
        }

    }
    public void drawBoard(Graphics g){//10 by 20 board
        for (int i = 1; i < 22-1; i++){//height of the board
            for (int j = 1; j < 12-1; j++) {//width of the board
                g.setColor(Color.white);
                g.drawRect(j*size,i*size, size,size);
            }
        }
    }


    public void drawBlocks(Graphics g) {
        for (int i = 0; i < numBlocks; i++){
            blocks[i].draw(g,this);
        }

    }

    public void updateBlocks(double time) {
        for (int i = 0; i < numBlocks; i++){
            blocks[i].update(this, time);
        }

    }



}
