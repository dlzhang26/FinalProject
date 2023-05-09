import java.awt.*;
import java.util.Random;

class Block {
    Color color;

   Pair position;

    boolean isFalling;
    boolean isPaused;

    private int key = World.key;
    Random ran = new Random(key);
    Random rand = new Random(key);


    Pair[] randomizedBlock;//array of pairs that is holding a randomized block

    Pair[] jBlock, lBlock, sBlock, zBlock, oBlock, iBlock, tBlock;

    //this constructor is used for the preview/next block. We need 2 constructors so one stores the state and the other
    //does not so when we generate the current and next block, the next block does not interfere with the current state
    public Block(int key) {//constructor
        //sets the color for the next block
        color = setColor(key);
        //gets the random block from the setBlock method and sets the next block
        this.randomizedBlock = setBlock(key);
        this.position = new Pair(570, 60);
        //initializing falling
        this.isFalling = false;
        this.isPaused = false;

    }

    public Block(State currenState, int key) {//constructor
        //sets the color for the next block
        color = setColor(key);
        //gets the random block from the setBlock method and sets the next block
        this.randomizedBlock = setBlock(key);
        //initializing falling
        this.position = new Pair(360,30);
        this.isFalling = true;

        currenState.newblock(randomizedBlock, position);
    }
    public Color setColor(int random){
        Color[] allColors = {new Color(255, 0, 128), new Color(128, 0, 255), new Color(255, 165, 0), new Color(0, 255, 255), new Color(255, 0, 255), new Color(255, 255, 0), new Color(0, 255, 0)};
        Color chosenColor = allColors[random];
        return chosenColor;
    }

    public Pair[] setBlock(int random) {//chooses a block
        jBlock = new Pair[]{new Pair(-1, 0), new Pair(0, 0), new Pair(1, 0), new Pair(-1, 1)};
        lBlock = new Pair[]{new Pair(-1, 0), new Pair(0, 0), new Pair(1, 0), new Pair(1, 1)};
        sBlock = new Pair[]{new Pair(-1, 0), new Pair(0, 0), new Pair(0, 1), new Pair(1, 1)};
        zBlock = new Pair[]{new Pair(-1, 1), new Pair(0, 1), new Pair(0, 0), new Pair(1, 0)};
        tBlock = new Pair[]{new Pair(0, 1), new Pair(-1, 0), new Pair(0, 0), new Pair(1, 0)};
        oBlock = new Pair[]{new Pair(0, 1), new Pair(1, 1), new Pair(0, 0), new Pair(1, 0)};
        iBlock = new Pair[]{new Pair(-1, 0), new Pair(0, 0), new Pair(1, 0), new Pair(2, 0)};

        Pair[][] types = {jBlock, lBlock, iBlock, sBlock, zBlock, oBlock, tBlock};//creating basically a matrix that has the type of blocks but only using 1 dimension?


        //******Currently Changed to debug, spawning issues in state class******/
        System.out.println(random);
        Pair[] randomBlock = types[random];//picks one of the matricies from types and sets the variable randomBlock equal to it.

        return randomBlock;//return random block
    }


    // goes through the array and switches x and y and multiples -1*y to rotate
    public Pair[] rotate(State cState) {//-1*y and switch x and y to rotate everything

        //if the block is the oBlock, do not rotate
        if (randomizedBlock == oBlock) {
            return randomizedBlock;
        }
        Block temp = new Block(0);
        Block copyhold = new Block(0);
        
        if (this.isFalling) {
            for (int i = 0; i < 4; i++) {
                //initializes hold[i] x and y to randomizedBlock[i]
                temp.randomizedBlock[i].x = randomizedBlock[i].x;
                temp.randomizedBlock[i].y = randomizedBlock[i].y;
                copyhold.randomizedBlock[i].x = temp.randomizedBlock[i].x;
                copyhold.randomizedBlock[i].y = temp.randomizedBlock[i].y;

                randomizedBlock[i].x = temp.randomizedBlock[i].y;//replaces the randomized block y to the x
                randomizedBlock[i].y = temp.randomizedBlock[i].x;//replaces the randomized block x to the y

                randomizedBlock[i].flipY(); //flips the sign of y

            }
            cState.rotateBlock(copyhold.randomizedBlock, randomizedBlock, position);
        }
        return randomizedBlock;
    }

    public void update(World w, double time, State cState) {
        isFalling= cState.checkCollision(isFalling, cState, randomizedBlock);
        if(isFalling){
            movedown(cState);//updates the current block;
        }
        

    }



    public Pair[] moveRight(State cstate) {
        int right = 0;
        //identifies the rightmost component of each block
        for(Pair p : randomizedBlock){
            if (p.x>right){
                right = (int)p.x;
            }
        }
        if (this.isFalling && !this.isPaused) {
            if( position.x + right * 30 > 450){
                position.x = 480-right *30;
                return randomizedBlock;
            }
            position.x += 30;//30 is the block size
            cstate.updatePos(randomizedBlock, position);
        }
        return randomizedBlock;
    }

    public Pair[] moveLeft(State cstate) {
        int left = 0;
        for(Pair p : randomizedBlock){
            if (p.x < left) {
                left = (int) p.x;
            }
        }
        if (this.isFalling && !this.isPaused) {
            if (position.x + left * 30 < 240) {
                position.x = 240 - left * 30;
            }
            position.x -= 30;//30 is the block size
            cstate.updatePos(randomizedBlock, position);
        }
        return randomizedBlock;
    }

    // Moves all of the positions down
    public Pair[] movedown(State cstate) {
        int bottom = 0;
        for (Pair p : randomizedBlock) {
            if (p.y > bottom) {
                bottom = (int) p.y;
            }
        }
        if (this.isFalling && !this.isPaused) {
            if (position.y + bottom * 30 > 570) {
                position.y = 570 - bottom * 30;
                this.isFalling = false;
            }
            position.y += 30;//30 is the block size
            cstate.updatePos(randomizedBlock, position);
        }
        return randomizedBlock;
    }
    public Pair getPosition(){
        return position;
    }

    public void pause() {
        this.isPaused = true;
    }
    
    public void resume() {
        this.isPaused = false;
    }

    public void draw(Graphics g, World w) {
        Color c = g.getColor();

        g.setColor(color);

        //draws a randomized block on the screen in a random color

        for (int i = 0; i < 4; i++) {
            g.fillRect((int) ((position.x + randomizedBlock[i].x * w.size)), (int) ((position.y + randomizedBlock[i].y * w.size)), w.size, w.size);
        }
        g.setColor(c);

    }

    private void edgeOfScreen(Pair[] randomizedBlock) {
        int bottom = 0;
        int top = 0;
        int left = 0;
        int right = 0;
        for (Pair p : randomizedBlock) {
            if (p.y > bottom) {
                bottom = (int) p.y;
            }//sets the bottom value to the largest y value
            if (p.y < top) {
                top = (int) p.y;
            }//sets the top value to the smallest y value, will be useful for game over conditions later
            if (p.x > right) {
                right = (int) p.x;
            }//sets the rightmost value to the largest x value
            if (p.x < left) {
                left = (int) p.x;
            }//sets the leftmost value to teh smallest x value
        }

        //keeps the blocks within the screen by "bouncing" it back to within the bounds of the board
        // bottom, left, and right * 30 because that is the block size
        if (isFalling) {
            if (position.y + bottom * 30 > 600) {
                position.y = 600 - bottom * 30;
                this.isFalling = false;
            }

            if (position.x + left * 30 < 210) {
                position.x = 210 - left * 30;
            }


            if (position.x + right * 30 > 480) {
                position.x = 480 - right * 30;
            }
        }


    }


}
