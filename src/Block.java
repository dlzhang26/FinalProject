import java.awt.*;
import java.sql.SQLOutput;//why are we using source querries?
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

public class Block {
    Pair position;
    Pair velocity;
    Pair acceleration;//do they really need an acceleration? can't we just increment velocity when we get beyond each score level

    int width;

    Color color;

    int [][] randomizedBlock;//define a matrix holding randomized block

    public Block() {
        Random rand = new Random();

        position = new Pair(100,25);
        velocity = new Pair(0,0);
        acceleration = new Pair(0, 0);

        color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());

        //Shouldn't this be Multiplied by 8, cuz Math.random generates a number between [0,1) so in order to generate a full 7 numbers
        // then this needs to be 8?
        int r =(int) (Math.random()*8); // (0,7) is range of values
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

    public void draw(Graphics g,World w) {
        Color c = g.getColor();

        g.setColor(color);
        
        //draws a randomized block on the screen in a random color
         

        for (int i = 0; i<4;i++){//walking through the matrix of possible locations that can be filled given the randomized block
            for(int j = 0 ; j<2; j++){
                if(randomizedBlock[i][j] == 1){
                    g.fillRect((int)position.x+i*w.size,(int)position.y+j*w.size,w.size, w.size );
                }
            }

        }
        g.setColor(c);

    }

}
/*

class RandomBlocks {

    RandomBlocks() {

    }

    /**
     generates random block configurations


    public static int[][] Blocks(int x) {
        int[][] jBlock = {{1, 1}, {1, 0}, {1, 0}, {0, 0}};
        int[][] lBlock = {{1, 1}, {0, 1}, {0, 1}, {0, 0}};
        int[][] iBlock = {{1, 0}, {1, 0}, {1, 0}, {1, 0}};
        int[][] sBlock = {{1, 0}, {1, 1}, {0, 1}, {0, 0}};
        int[][] zBlock = {{0, 1}, {1, 1}, {1, 0}, {0, 0}};
        int[][] oBlock = {{1, 1}, {1, 1}, {0, 0}, {0, 0}};
        int[][] tBlock = {{1, 0}, {1, 1}, {1, 0}, {0, 0}};

        int[][][] types = {jBlock, lBlock, iBlock, sBlock, zBlock, oBlock, tBlock};

        int[][] randomBlock = types[x];

        return randomBlock;
    }
}

 */
