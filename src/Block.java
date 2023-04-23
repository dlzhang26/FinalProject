import java.awt.*;
import java.util.Random;

class Pair {
    public double x;
    public double y;

    public Pair(double initX, double initY) {
        x = initX;
        y = initY;
    }

    public Pair add(Pair toAdd) {
        return new Pair(x + toAdd.x, y + toAdd.y);
    }

    public Pair divide(double denom) {
        return new Pair(x / denom, y / denom);
    }

    public Pair times(double val) {
        return new Pair(x * val, y * val);
    }

    public void flipX() {
        x = -x;
    }

    public void flipY() {
        y = -y;
    }
}

class Block {
    Color color;

    double radius;
    Pair position;

    Pair[] randomizedBlock;//array of pairs that is holding a randomized block

    Pair[] jBlock, lBlock, sBlock, zBlock, oBlock, iBlock,tBlock;
    public Block() {
        Random rand = new Random();
        color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        int r = (int) (Math.random() * 7); // (0,7) is range of values
        randomizedBlock = setBlock(r);//gets random block from Blocks array (sort of)
        radius = 12.5;
        position = new Pair(300, 300);

    }

    public Pair[] setBlock(int random) {
        jBlock = new Pair[]{new Pair(0, 1), new Pair(0, 0), new Pair(0, -1), new Pair(-1, -1)};
        lBlock = new Pair[]{new Pair(-1, 0), new Pair(0,0), new Pair(1,0), new Pair(1,1)};
        sBlock = new Pair[]{new Pair(-1, 0), new Pair(0,0), new Pair(0,1), new Pair(1,1)};
        zBlock = new Pair[]{new Pair(-1, 1), new Pair(0,1), new Pair(0,0), new Pair(1,0)};
        tBlock = new Pair[]{new Pair(0, 1), new Pair(-1,0), new Pair(0,0), new Pair(1,0)};
        oBlock = new Pair[]{new Pair(0, 1), new Pair(1,1), new Pair(0,0), new Pair(1,0)};
        iBlock = new Pair[]{new Pair(-1, 0), new Pair(0,0), new Pair(1,0), new Pair(2,0)};

        Pair[][] types = {jBlock, lBlock, iBlock, sBlock, zBlock, oBlock, tBlock};//creating basically a matrix that has the type of blocks but only using 1 dimension?
        Pair[] randomBlock = types[random];//picks one of the matricies from types and sets the variable randomBlock equal to it.

        return randomBlock;//return random block
    }


    // goes through the array and switches x and y and multiples -1*y to rotate
    public Pair[] rotate() {//-1*y and switch x and y to rotate everything

        //if the block is the oBlock, do not rotate
        if(randomizedBlock == oBlock){//square block
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
        return randomizedBlock;
    }
    public void update(World w, double time) {


    }

    public Pair[] movedown() {
        for (int i = 0; i < 4; i++) {
            randomizedBlock[i].y = randomizedBlock[i].y + 1;
        }
        return randomizedBlock;
    }

    public Pair[] moveRight(){
        for (int i = 0; i < 4; i++) {
            randomizedBlock[i].x = randomizedBlock[i].x + 1;
        }
        return randomizedBlock;
    }
    public Pair[] moveLeft(){
        for (int i = 0; i < 4; i++) {
            randomizedBlock[i].x = randomizedBlock[i].x - 1;
        }
        return randomizedBlock;
    }




    public void draw(Graphics g, World w) {
        Color c = g.getColor();

        g.setColor(color);

        //draws a randomized block on the screen in a random color

        for (int i = 0; i < 4; i++) {
            g.fillRect((int)((position.x + randomizedBlock[i].x*w.size)),(int)((position.y + randomizedBlock[i].y*w.size)),w.size,w.size);
        }
        g.setColor(c);

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
