
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class World {
    int height;
    int width;

    Random rand = new Random(key);
    static int key = 4567;
    int currentBlockKey;
    int nextBlockKey;
    private Image blockImage;
    int row, column;
    int size = Main.BLOCKSIZE;//takes static variable from Main.java named BLOCKSIZE
    Block currentBlock;
    Block nextBlock;
    Block holdBlock;

    State currentState;

    ArrayList<Block> blockss = new ArrayList<Block>();//initializing the ArrayList of Blocks

    ArrayList<Block> nextBlocks = new ArrayList<Block>();//initializing the ArrayList of next Block

    public World(int initWidth, int initHeight) {//constructor for the world class
        width = initWidth;
        height = initHeight;

        //doesn't work right
        this.key = generateKey();
        System.out.println("key is: "+key);

        try {
            blockImage = ImageIO.read(new File("BlockPicture.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.currentState = new State();

        //generates what the first block will be in the sequence
        currentBlockKey = rand.nextInt(0,7);

        //generates the second block according to the key
        nextBlockKey = rand.nextInt(0,7);
        System.out.println(currentBlockKey +" " +nextBlockKey);

        //adds a new block to the game screen with the first key
        blockss.add(new Block(currentState,currentBlockKey));

        //in the preview arraylist, add a new block with the nextBlock's key
        nextBlocks.add(new Block(nextBlockKey));

        //sets the current block to the last generated block in the blockss array list
        this.currentBlock = blockss.get(blockss.size() - 1);

        //sets the next/preview block to the last generated block in the nextBlocks array list
        this.nextBlock = nextBlocks.get(blockss.size() - 1);
        nextBlock.isFalling = false;
        //draws current block on top of board
       // currentBlock.position = new Pair(360, 30);
    }

    public void addBlock() {
        //sets the position of the next block in the preview box
        //nextBlock.position = new Pair(570, 60);//need to change the x and y to fit the box

        if (currentBlock.isFalling == false) {

            //updates the currentBlockKey to nextBlockKey
            currentBlockKey = nextBlockKey;

            nextBlockKey = rand.nextInt(0,7);
            //System.out.println( currentBlockKey + " "+ nextBlockKey);
            nextBlocks.add(new Block(nextBlockKey));
            nextBlock = nextBlocks.get(nextBlocks.size() - 1);

            blockss.add(new Block(currentState,currentBlockKey));
            currentBlock = blockss.get(blockss.size() - 1);
            currentBlock.position = new Pair(360,30);
            currentBlock.isFalling=true;

            
        }
    }

    public void hold() {
        if (holdBlock == null) {
            //sets the current block to the hold block and change the position
            holdBlock = currentBlock;
            holdBlock.position = new Pair(30, 60);

            //sets current block to next block to replace the old current block that's on hold
            currentBlock = nextBlock;//what is this here doing?

            //adds a new block to the array list and sets a new next block
            blockss.add(new Block(currentState,currentBlockKey));
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
    private static int generateKey(){
        Random randomKey = new Random();
        int r = randomKey.nextInt();
        return r;
    }


    public void drawBoard(Graphics g) {//
       
        int boardWidth = 10 * size; // width of the board
        int boardHeight = 20 * size; // height of the board
        int boardX = 7 * size; // x-coordinate of the board's top-left corner
        int boardY = 1 * size; // y-coordinate of the board's top-left corner
        
        currentState.drawState(g, blockImage, boardX, boardY, size);
        TopRow(g);
        
    }
    public void TopRow(Graphics g) {
        g.setColor(Color.white);
        g.drawString("Player 1 Score: " + currentState.Player1Score, 250, 15);
        g.drawString("Player 2 Score: " + currentState.Player2Score, 250, 45);
        if (Main.counter % 2 == 0) {
            g.drawString("CURRENT PLAYER: Player 2", 70, 15);
        }
        else {
            g.drawString("CURRENT PLAYER: Player 1", 70, 15);
        }
        
        g.fillRoundRect(5, 5, 45, 25, 10, 10);
        g.fillRoundRect(545, 5, 45, 25, 10, 10);
        g.fillRoundRect(625, 5, 55, 25, 10, 10);
        g.setColor(Color.black);
        g.drawString(" BACK", 6, 20);
        g.drawString(" PAUSE", 545, 20);
        g.drawString(" RESUME", 625, 20);
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
            /*
            if (gameBoard[column][row] != 0) {
                this.currentBlock.isFalling = false;
            }

             */
        }
       // System.out.println();


    }



    public void moveLeft() {
        currentBlock.moveLeft(currentState);
    }

    public void moveRight() {
        currentBlock.moveRight(currentState);
    }


    public void drawBlocks(Graphics g) {
        //draws the all the blocks to screen
        /*for (int i = 0; i < blockss.size() - 1; i++) {
            blockss.get(i).draw(g, this);
        }
        */
        

        //draws the current block
        //currentBlock.draw(g, this);
        //nextBlock.draw(g, this);

    }

    public void updateBlocks(double time) {
        addBlock();
        currentBlock.update(this, time,currentState);
        System.out.println("Current block falling? " + currentBlock.isFalling);
    }

}
