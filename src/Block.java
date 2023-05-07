import java.awt.*;
import java.util.Random;

class Block {
    Color color;

    Pair position = new Pair(570, 60);

    boolean isFalling;

    Pair[] randomizedBlock;//array of pairs that is holding a randomized block

    Pair[] jBlock, lBlock, sBlock, zBlock, oBlock, iBlock, tBlock;

    public Block() {//constructor
        Random rand = new Random();
        color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        this.isFalling = true;
        int r = (int) (Math.random() * 7); // (0,6) is range of values
        this.randomizedBlock = setBlock(r);//gets random block from Blocks array
    }

    public Block(State currenState) {//constructor
        Random rand = new Random();
        int r = rand.nextInt(0, 7);
        //color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        this.isFalling = true;
        //int r = (int) (Math.random() * 7); // (0,7) is range of values
        this.randomizedBlock = setBlock(r);//gets random block from Blocks array (sort of)

        Color[] colors = {new Color(255, 0, 128), new Color(128, 0, 255), new Color(255, 165, 0), new Color(0, 255, 255), new Color(255, 0, 255), new Color(255, 255, 0), new Color(0, 255, 0)};
        color = colors[r];
        currenState.newblock(randomizedBlock);
    


    }

    public Pair[] setBlock(int random) {//chooses a block
        jBlock = new Pair[]{new Pair(0, 1), new Pair(0, 0), new Pair(0, -1), new Pair(-1, -1)};
        lBlock = new Pair[]{new Pair(-1, 0), new Pair(0, 0), new Pair(1, 0), new Pair(1, 1)};
        sBlock = new Pair[]{new Pair(-1, 0), new Pair(0, 0), new Pair(0, 1), new Pair(1, 1)};
        zBlock = new Pair[]{new Pair(-1, 1), new Pair(0, 1), new Pair(0, 0), new Pair(1, 0)};
        tBlock = new Pair[]{new Pair(0, 1), new Pair(-1, 0), new Pair(0, 0), new Pair(1, 0)};
        oBlock = new Pair[]{new Pair(0, 1), new Pair(1, 1), new Pair(0, 0), new Pair(1, 0)};
        iBlock = new Pair[]{new Pair(-1, 0), new Pair(0, 0), new Pair(1, 0), new Pair(2, 0)};

        Pair[][] types = {jBlock, lBlock, iBlock, sBlock, zBlock, oBlock, tBlock};//array of arrays that contain pair locations of block pieces


        Pair[] randomBlock = types[random];//picks one of the matricies from types and sets the variable randomBlock equal to it.

        return randomBlock;//return random block
    }


    // goes through the array and switches x and y and multiples -1*y to rotate
    public Pair[] rotate() {//-1*y and switch x and y to rotate everything

        //if the block is the oBlock, do not rotate
        if (randomizedBlock == oBlock) {
            return randomizedBlock;
        }
        Block hold = new Block();
        if (this.isFalling) {
            for (int i = 0; i < 4; i++) {
                //initializes hold[i] x and y to randomizedBlock[i]
                hold.randomizedBlock[i].x = randomizedBlock[i].x;
                hold.randomizedBlock[i].y = randomizedBlock[i].y;

                randomizedBlock[i].x = hold.randomizedBlock[i].y;//replaces the randomized block y to the x
                randomizedBlock[i].y = hold.randomizedBlock[i].x;//replaces the randomized block x to the y

                randomizedBlock[i].flipY(); //flips the sign of y
            }
        }
        return randomizedBlock;
    }

    public void update(World w, double time) {
        edgeOfScreen(this.randomizedBlock);
    }

    public Pair[] moveRight() {
        if (this.isFalling) {
            position.x += 30;//30 is the block size
        }
        return randomizedBlock;
    }

    public Pair[] moveLeft() {
        if (this.isFalling) {
            position.x -= 30;//30 is the block size
        }
        return randomizedBlock;
    }

    // Moves all of the positions down
    public Pair[] movedown() {
        if (this.isFalling) {
            position.y += 30;//30 is the block size
        }
        return randomizedBlock;
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
        if(isFalling){
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

