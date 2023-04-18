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

public class Block {
    Pair position;
    Pair velocity;
    Pair acceleration;
    double radius;

    int width;

    Color color;

    public Block() {
        Random rand = new Random();
        position = new Pair(500.0, 500.0);
        velocity = new Pair((double) (rand.nextInt(1000) - 500), (double) (rand.nextInt(1000) - 500));
        acceleration = new Pair(0, 200.0);
        radius = 25;

        color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }


    public void update(World w, double time) {
        position = position.add(velocity.times(time));
        velocity = velocity.add(acceleration.times(time));
        bounce(w);
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

    public void draw(Graphics g) {
        Color c = g.getColor();

        g.setColor(color);
        g.drawOval((int) (position.x - radius), (int) (position.y - radius), (int) (2 * radius), (int) (2 * radius));
        g.setColor(c);
    }

    private void bounce(World w) {
        Boolean bounced = false;
        if (position.x - radius < 0) {
            velocity.flipX();
            position.x = radius;
            bounced = true;
        } else if (position.x + radius > w.width) {
            velocity.flipX();
            position.x = w.width - radius;
            bounced = true;
        }
        if (position.y - radius < 0) {
            velocity.flipY();
            position.y = radius;
            bounced = true;
        } else if (position.y + radius > w.height) {
            velocity.flipY();
            position.y = w.height - radius;
            bounced = true;
        }
        if (bounced) {
            //velocity = velocity.divide(dampening);
        }
    }
    /** Testing if the random block generation works
     **/

    public static void main(String[] args) {
        int r =(int) (Math.random()*7);
        for (int l = 0; l < 4; l++) {
            for (int w = 0; w < 2; w++) {
                System.out.print(RandomBlocks.Blocks(r)[l][w]);
            }
            System.out.println();
        }
        System.out.println();
    }


}

class RandomBlocks {

    RandomBlocks() {

    }

    /**
     generates random block configurations
     */

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
