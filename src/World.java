import java.awt.*;
import java.util.ArrayList;

public class World {
    int height;
    int width;
    int size = Main.BLOCKSIZE;

    //boolean hasHoldBlock = false;

    Block currentBlock;
    Block nextBlock;

    Block holdBlock;

    State currentState;

    ArrayList<Block> blockss = new ArrayList<Block>();

    public World(int initWidth, int initHeight) {
        width = initWidth;
        height = initHeight;

        this.currentState = new State();

        //generates 2 blocks
        blockss.add(new Block(currentState));
        blockss.add(new Block(currentState));

        //sets the current block to be the first generated
        this.currentBlock = blockss.get(blockss.size()-2);

        //sets the next/preview block to the second generated block
        this.nextBlock = blockss.get(blockss.size() - 1);

        //draws current block on the board
        currentBlock.position =  new Pair(360,30);
    }

    public void addBlock() {
        //sets the position of the next block in the preview box
        nextBlock.position = new Pair (570,60);//need to change the x and y to fit the box
        while (currentBlock.isFalling == false) {
            //the next block's position is set on the top of the board and starts falling when the current block stops falling
            nextBlock.position = new Pair (360,30);
            nextBlock.isFalling = true;

            //adds a new block to the linked list
            blockss.add(new Block(currentState));

            //sets the last block to next block and current block to the second to last block
            currentBlock = blockss.get(blockss.size()-2);
            nextBlock = blockss.get(blockss.size() - 1);
        }
    }

    public void hold() {
        if (holdBlock == null) {
            holdBlock = currentBlock;
            holdBlock.position = new Pair(0,60);

            currentBlock = nextBlock;
            blockss.add(new Block(currentState));
            nextBlock = blockss.get(blockss.size() - 1);
        }
        else {
            //right now this part isn't working bc of the bounds.
            //when setting the currentBlock position, it can't set it to outside the board position because of the edge
            //of screen detection.
            Block temp = currentBlock;
            currentBlock = holdBlock;
            holdBlock = temp;

            currentBlock.position = new Pair(0,60);
            holdBlock.position = new Pair(360,30);
        }
    }


    public void drawBoard(Graphics g) {//10 by 20 board - Each square is 25 rn
        for (int i = 1; i < 21; i++) {//height of the board
            for (int j = 7; j < 17; j++) {//width of the board
                g.setColor(Color.white);
                g.drawRect(j * size, i * size, size, size);
            }
        }
        //draws the holding block place
        for (int i = 3; i < 7; i++) {//height of the board
            for (int j = 2; j < 5; j++) {//width of the board
                g.setColor(Color.white);
                g.drawRect(j * size, i * size, size, size);
            }
        }

        //draws the next block
        for (int i = 3; i < 7; i++) {//height of the board
            for (int j = 19; j < 22; j++) {//width of the board
                g.setColor(Color.white);
                g.drawRect(j * size, i * size, size, size);
            }
        }
    }

    public void drawBlocks(Graphics g) {
        //draws the all the blocks to screen
        for (int i = 0; i < blockss.size() - 1; i++) {
            blockss.get(i).draw(g, this);
        }
        //draws the current block
        currentBlock.draw(g, this);
        nextBlock.draw(g,this);
    }

    public void updateBlocks(double time) {
        addBlock();
        currentBlock.movedown();//updates the current block;
        currentBlock.update(this, time);
        
        /* im pretty sure we dont need this but im gonna comment it out just in case
        for(int i = 0; i< blockss.size()-1;i++){
            blockss.get(i).update(this,time);
        }

         */

    }
}
