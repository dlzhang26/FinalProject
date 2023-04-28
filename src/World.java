import java.awt.*;
import java.util.ArrayList;
public class World {
    int boardHeight;
    int boardWidth;


    int numBlocks;

    int size = Main.BLOCKSIZE;

    Block blocks[];

    ArrayList<Block> blockss = new ArrayList<Block>();

    public World(int initWidth, int initHeight, int initNumBlocks) {
        boardWidth = initWidth;
        boardHeight = initHeight;

        numBlocks = initNumBlocks;
        blocks  = new Block[numBlocks];

        

        for (int i = 0; i < numBlocks; i ++)
        {
            blocks[i] = new Block();
        }

    }
    public void drawBoard(Graphics g){//10 by 20 board - Each square is 25 rn
        for (int i = 1; i < 21; i++){//adds boarders to the height of the board
            for (int j = 1; j < 11; j++) {//adds boarders to the width of the board
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
            blocks[i].movedown();
            blocks[i].update(this, time);
        }

    }



}
