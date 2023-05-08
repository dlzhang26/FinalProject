
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class World {
    int height;
    int width;

    int row, column;
    int size = Main.BLOCKSIZE;//takes static variable from Main.java named BLOCKSIZE

    Block currentBlock;
    Block nextBlock;

    Block holdBlock;

    State currentState;

    int[][] gameBoard = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0, 0, 0}};
    ArrayList<Block> blockss = new ArrayList<Block>();//initializing the ArrayList of Blocks 

    public World(int initWidth, int initHeight) {//constructor for the world class
        width = initWidth;
        height = initHeight;

        this.currentState = new State();

        //generates 2 blocks
        blockss.add(new Block(currentState));
        blockss.add(new Block(currentState));

        this.currentBlock = blockss.get(blockss.size() - 2);//sets the current block to be the first generated


        this.nextBlock = blockss.get(blockss.size() - 1);//sets the next/preview block to the second generated block

        currentBlock.position = new Pair(360, 30);//draws current block on top of board
    }

    public void addBlock() {
        //sets the position of the next block in the preview box
        nextBlock.position = new Pair(570, 60);//need to change the x and y to fit the box

        while (currentBlock.isFalling == false) {
            //the next block's position is set on the top of the board and starts falling when the current block stops falling
            nextBlock.position = new Pair(360, 30);
            nextBlock.isFalling = true;

            //adds a new block to the linked list
            blockss.add(new Block(currentState));

            //sets the last block to next block and current block to the second to last block
            currentBlock = blockss.get(blockss.size() - 2);
            nextBlock = blockss.get(blockss.size() - 1);
        }
    }

    public void hold() {
        if (holdBlock == null) {
            //sets the current block to the hold block and change the position
            holdBlock = currentBlock;
            holdBlock.position = new Pair(0, 60);

            //sets current block to next block to replace the old current block that's on hold
            currentBlock = nextBlock;//what is this here doing?

            //adds a new block to the array list and sets a new next block
            blockss.add(new Block(currentState));
            nextBlock = blockss.get(blockss.size() - 1);
        } else {
            //switches current blocks and hold block and sets the new position
            Block temp = currentBlock;
            currentBlock = holdBlock;
            holdBlock = temp;

            //setting the current and hold block's position
            //current block draws on the board and hold block in the holding zone
            currentBlock.position = new Pair(360, 30);
            holdBlock.position = new Pair(0, 60);
        }
    }


    public void drawBoard(Graphics g) {
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

        //draws the next block holding area
        for (int i = 3; i < 7; i++) {//height of the board
            for (int j = 19; j < 22; j++) {//width of the board
                g.setColor(Color.white);
                g.drawRect(j * size, i * size, size, size);
            }
        }
    }
    public void checkCollision() {

        //goes through each of the four squares of the current block and tracks which row and column it is on the board
        for (int i = 0; i < 4; i++) {
            //calculates the row that the block is actually in by doing some scaling
            row = (int) (currentBlock.randomizedBlock[i].x * size + currentBlock.getPosition().x) - 7 * size;
            column = Math.abs((int) (currentBlock.randomizedBlock[i].y * size + currentBlock.getPosition().y)-size);
            row = row / 30;
            column = column / 30;
            System.out.println(row + " " + column);

            if (gameBoard[column][row] != 0) {
                this.currentBlock.isFalling = false;
            }
        }
        System.out.println();


    }

    public void moveLeft() {
        currentBlock.moveLeft(currentState);
    }

    public void moveRight() {
        currentBlock.moveRight(currentState);
    }


    public void drawBlocks(Graphics g) {
        //draws the all the blocks to screen
        for (int i = 0; i < blockss.size() - 1; i++) {
            blockss.get(i).draw(g, this);
        }
        //draws the current block
        currentBlock.draw(g, this);
        nextBlock.draw(g, this);

    }

    public void updateBlocks(double time) {
        addBlock();
        checkCollision();
        currentBlock.movedown(currentState);//updates the current block;
        currentBlock.update(this, time);

    }

}
