import java.awt.*;
import java.util.ArrayList;
public class World {
    int height;
    int width;

    int numBlocks;

    int size = Main.BLOCKSIZE;

    Block currentBlock;

    //ArrayList<Block> deadBlocks = new ArrayList<Block>();

    State currentState;

    ArrayList<Block> blockss = new ArrayList<Block>();

    public World(int initWidth, int initHeight) {
        width = initWidth;
        height = initHeight;

        this.currentState = new State();

        blockss.add( new Block(currentState));
        this.currentBlock = blockss.get(blockss.size() - 1);

    }

    public void addBlock(){

        while(currentBlock.isFalling == false){
            blockss.add(new Block(currentState));
            currentBlock = blockss.get(blockss.size() - 1);
            System.out.println(currentBlock.isFalling);
        }
    }



    public void drawBoard(Graphics g){//10 by 20 board - Each square is 25 rn
        for (int i = 1; i < 21; i++){//height of the board
            for (int j = 1; j < 11; j++) {//width of the board
                g.setColor(Color.white);
                g.drawRect(j*size,i*size, size,size);
            }
        }
    }



    public void drawBlocks(Graphics g) {
        //draws the all the blocks to screen
        for(int i = 0; i< blockss.size()-1;i++){
            blockss.get(i).draw(g,this);
        }
        //draws the current block
        currentBlock.draw(g,this);
    }

    public void updateBlocks(double time) {
        addBlock();
        currentBlock.movedown();//updates the current block;
        currentBlock.update(this,time);

        /* im pretty sure we dont need this but im gonna comment it out just in case
        for(int i = 0; i< blockss.size()-1;i++){
            blockss.get(i).update(this,time);
        }

         */

    }
}
