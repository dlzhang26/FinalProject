import java.awt.*;
import java.util.Random;

class Block {
    Color color;

    Pair position;
    boolean isFalling;

    Pair[] randomizedBlock;//array of pairs that is holding a randomized block

    Pair[] jBlock, lBlock, sBlock, zBlock, oBlock, iBlock, tBlock;

    public Block() {//constructor
        Random rand = new Random();
        color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        int r = (int) (Math.random() * 7); // (0,7) is range of values
        this.randomizedBlock = setBlock(r);//gets random block from Blocks array (sort of)
        position = new Pair( 60,60);
    }

    public Pair[] setBlock(int random) {//chooses a block
        jBlock = new Pair[]{new Pair(0, 1), new Pair(0, 0), new Pair(0, -1), new Pair(-1, -1)};
        lBlock = new Pair[]{new Pair(-1, 0), new Pair(0, 0), new Pair(1, 0), new Pair(1, 1)};
        sBlock = new Pair[]{new Pair(-1, 0), new Pair(0, 0), new Pair(0, 1), new Pair(1, 1)};
        zBlock = new Pair[]{new Pair(-1, 1), new Pair(0, 1), new Pair(0, 0), new Pair(1, 0)};
        tBlock = new Pair[]{new Pair(0, 1), new Pair(-1, 0), new Pair(0, 0), new Pair(1, 0)};
        oBlock = new Pair[]{new Pair(0, 1), new Pair(1, 1), new Pair(0, 0), new Pair(1, 0)};
        iBlock = new Pair[]{new Pair(-1, 0), new Pair(0, 0), new Pair(1, 0), new Pair(2, 0)};

        Pair[][] types = {jBlock, lBlock, iBlock, sBlock, zBlock, oBlock, tBlock};//creating basically a matrix that has the type of blocks but only using 1 dimension?
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
        for (int i = 0; i < 4; i++) {
            //initializes hold[i] x and y to randomizedBlock[i]
            hold.randomizedBlock[i].x = randomizedBlock[i].x;
            hold.randomizedBlock[i].y = randomizedBlock[i].y;

            randomizedBlock[i].x = hold.randomizedBlock[i].y;//replaces the randomized block y to the x
            randomizedBlock[i].y = hold.randomizedBlock[i].x;//replaces the randomized block x to the y

            randomizedBlock[i].flipY(); //flips the sign of y
        }

        // resumeDownwardMotion();

        return randomizedBlock;
    }

    public void update(World w, double time) {


    }

    public Pair[] moveRight() {
        this.position.x = this.position.x + 30;//30 is the block size
        return randomizedBlock;
    }

    public Pair[] moveLeft() {
        this.position.x = this.position.x - 30;//30 is the block size
        return randomizedBlock;
    }


    // Moves all of the positions down
    public Pair[] movedown() {
        this.position.y = this.position.y + 30;//30 is the block size
        for (int i = 0; i < 4; i++) {
           /*  if (randomizedBlock[i].isFalling = true) {
                randomizedBlock[i].y = randomizedBlock[i].y + 1;
            }*/
        }
        return randomizedBlock;
    }


    public void draw(Graphics g, World w) {
        Color c = g.getColor();

        g.setColor(color);

        //draws a randomized block on the screen in a random color

        for (int i = 0; i < 4; i++) {
            g.fillRect((int) ((position.x + randomizedBlock[i].x * w.size)), (int) ((position.y + randomizedBlock[i].y * w.size)),  w.size,  w.size);
        }
        g.setColor(c);

    }

    public void updateBlocks(World w){
        boolean isColliding = false;
        for (int i = 0; i < 4; i++) {
            int x = (int) (position.x + randomizedBlock[i].x);
            int y = (int)(position.y + randomizedBlock[i].y);

        }
    }



}
/*
public class Block {
    Pair position;
    Pair velocity;
    Pair acceleration;//do they really need an acceleration? can't we just increment velocity when we get beyond each score level

    // size of the square
    int size;

    Color color;

    int[][] randomizedBlock;//define a matrix holding randomized block

    public Block() {
        Random rand = new Random();

        position = new Pair(100, 25);
        velocity = new Pair(0, 0);
        acceleration = new Pair(0, 0);
        size = 25;

        color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());

        //Shouldn't this be Multiplied by 8, cuz Math.random generates a number between [0,1) so in order to generate a full 7 numbers
        // then this needs to be 8?
        int r = (int) (Math.random() * 7); // (0,7) is range of values
        randomizedBlock = Blocks(r);//gets random block from Blocks array (sort of)
    }

    public static int[][] Blocks(int x) {//mapping for each of the block types
        int[][] jBlock = {{1, 1}, {1, 0}, {1, 0}, {0, 0}};
        int[][] lBlock = {{1, 1}, {0, 1}, {0, 1}, {0, 0}};
        int[][] iBlock = {{1, 0}, {1, 0}, {1, 0}, {1, 0}};
        int[][] sBlock = {{1, 0}, {1, 1}, {0, 1}, {0, 0}};
        int[][] zBlock = {{0, 1}, {1, 1}, {1, 0}, {0, 0}};
        int[][] oBlock = {{1, 1}, {1, 1}, {0, 0}, {0, 0}};
        int[][] tBlock = {{1, 0}, {1, 1}, {1, 0}, {0, 0}};

        int[][][] types = {jBlock, lBlock, iBlock, sBlock, zBlock, oBlock, tBlock};//an array of matricies

        int[][] randomBlock = types[x];//picks one of the matricies from types and sets the variable randomBlock equal to it.

        return randomBlock;//return random block
    }


    public void update(World w, double time) {


    }

    public void setPosition(Pair p) {
        position = p;
    }

    public void setVelocity(Pair v) {
        velocity = v;
    }

    public void setAcceleration(Pair a) {
        acceleration = a;
    }

    public Pair getPosition() {
        return position;
    }

    public Pair getVelocity() {
        return velocity;
    }

    public Pair getAcceleration() {
        return acceleration;
    }

    public double flipX() {
        acceleration.flipX();
        return 0.0;
    }

    public double flipY() {
        acceleration.flipY();
        return 0.0;
    }

    public void draw(Graphics g, World w) {
        Color c = g.getColor();

        g.setColor(color);

        //draws a randomized block on the screen in a random color


        for (int i = 0; i < 4; i++) {//walking through the matrix of possible locations that can be filled given the randomized block
            for (int j = 0; j < 2; j++) {
                if (randomizedBlock[i][j] == 1) {
                    g.fillRect((int) position.x + i * w.size, (int) position.y + j * w.size, w.size, w.size);
                }
            }

        }
        g.setColor(c);

    }

}

 */
