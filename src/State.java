import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;

// I am thinking that we can possibly make a class to hold the current state of the entire board in an ordered colleciton, this way
// if a row fills up then we can just remove and then its easier to move
public class State extends OrderedCollection{
    Node end;
    //length is backwards, indicates if there is less than 20
    int length;
    Pair Lastpos;

    // Constructor - Creates the first end, but then appends 20 rows
    public State(){
        end = null;
        length = 20;
        Lastpos = new Pair(360, 30);
        for(int i =0; i<20; i++){
            append();
        }


    }
    //

    //append method, just adds a blank rows
    public void append(){
        Node toAdd = new Node(length);
        toAdd.prev= end;
        end = toAdd;
        length--;
    }
    
    public void SpaceON(double column, double row){
        Node n = end;
        System.out.println("Printing: " + column + ", " + row);
        while(n.rownum != row){
            n=n.prev;
        }
        n.rowstate[(int)column] = 1;
    }

    public void SpaceOFF(double column, double row){
        Node n = end;
        while(n.rownum != row){
            n=n.prev;
        }
        System.out.println("Removed: " + column + "," + row);
        n.rowstate[(int)column] = 0;


    }

    public int checkSpace(double column, double row){
        Node n = end;
        while(n.rownum != row){
            n=n.prev;
        }
        return n.rowstate[(int)column];
    }
    public boolean checkLeft(Pair[] block){
        boolean goleft =true;
        int currRow=(int) (Lastpos.y/30);
        // new column
        int currCol = (int) (((Lastpos.x/30))-7);
        System.out.println("Current Column: "+ currCol+ "\n" + "Current Row: " + currRow);
        Pair center = new Pair((double) currCol,(double)currRow); 
        
         /******************************************************************
          * This part of the method is to find the lowest blocks, basically the blocks that would be interacting with lower blocks
          */
        LinkedList <Pair> Check= new LinkedList<Pair>();
        double biggesty = 0;
        double smallesty = 0;

        //Identify the bounds for the shape,
        for(Pair p: block){
            if(p.y>biggesty){
                biggesty = p.y;
            }
            if(p.y<smallesty){
                smallesty = p.y;
            }
        }
        //find the lowest point for each row
        for(double i = smallesty; i<biggesty+1; i++){
            Pair lowestPoint = new Pair(2, i);
            for(Pair p: block){
                if(p.y==lowestPoint.y && p.x<lowestPoint.x){
                    lowestPoint = p;
                }
            }
            Check.add(lowestPoint);
        }
        System.out.println("The Leftmost Block is at: ");
        for(Pair p: Check){
            System.out.println(p.x + ", " + p.y);
        }
        for(Pair p: Check){
            System.out.println("Check " + p.x + ", "+ p.y);
            System.out.println("Checking: " + (center.x+ p.x) + ", " + (center.y+p.y));
            if(p.x+center.x<0 || checkSpace((center.x + p.x-1), center.y+p.y) ==1){
                System.out.println("called checkleft!!!!");
                goleft=false;
                System.out.println("goLeft Set: "+ goleft);
            }
        }
        return goleft;
    }

    public boolean checkRight(Pair[] block){
        boolean goRight =true;
        int currRow=(int) (Lastpos.y/30);
        // new column
        int currCol = (int) (((Lastpos.x/30))-7);
        System.out.println("Current Column: "+ currCol+ "\n" + "Current Row: " + currRow);
        Pair center = new Pair((double) currCol,(double)currRow); 
        
         /******************************************************************
          * This part of the method is to find the lowest blocks, basically the blocks that would be interacting with lower blocks
          */
        LinkedList <Pair> Check= new LinkedList<Pair>();
        double biggesty = 0;
        double smallesty = 0;

        //Identify the bounds for the shape,
        for(Pair p: block){
            if(p.y>biggesty){
                biggesty = p.y;
            }
            if(p.y<smallesty){
                smallesty = p.y;
            }
        }
        //find the lowest point for each row
        for(double i = smallesty; i<biggesty+1; i++){
            Pair rightPoint = new Pair(-2, i);
            for(Pair p: block){
                if(p.y==rightPoint.y && p.x>rightPoint.x){
                    rightPoint = p;
                }
            }
            Check.add(rightPoint);
        }
        System.out.println("The RightMost Block is at: ");
        for(Pair p: Check){
            System.out.println(p.x + ", " + p.y);
        }
        for(Pair p: Check){
            System.out.println("Check " + p.x + ", "+ p.y);
            System.out.println("Checking: " + (center.x+ p.x) + ", " + (center.y+p.y));
            if(p.x+center.x>9 || checkSpace((center.x + p.x+1), center.y+p.y) ==1){
                System.out.println("called checkRight!!!!");
                goRight=false;
                System.out.println("goLeft Set: "+ goRight);
            }
        }
        return goRight;
    }




    //Returns the first rowstate
    public int[] peek(){

        return end.rowstate;

    }

    public int[] remove(int index){
        int[] toReturn;
        Node removed;
        Node n = end;

        //Different process for removing the end
        if(index == 0){
            toReturn = n.rowstate;
            System.out.println("Removed Row: " + index);
            end = n.prev;
        }else{
        //Go through datastructure until the one we want to remove + 1
            while(n.prev.rownum != index){
                n=n.prev;
            }
            toReturn = n.prev.rowstate;
        //
            removed = n.prev;
        //In order to remove it we set the previous to the previous of the one before
            System.out.println("Removed Row: " + removed.rownum);


            n.prev = n.prev.prev;
        }



        n = end;
        System.out.println(n.rownum);
        //now to update rownumbers
        if(index==20){
            while(n.prev!= null){
                n.rownum++;
                n = n.prev;
            }
            n.rownum++;
            length++;

        }else{
            while(n.rownum <= index-1){
                n.rownum++;
                n = n.prev;
            }
            length++;
        }
        
        append();

        return toReturn;

    }


    public int[] pop(){
        int[] toReturn = end.rowstate;
        end = end.prev;
        length++;
        return toReturn;
    }
    public String toString(){
        String toReturn = "";
        Node n = end;
        while(n!=null){
            for (int j =0; j<1; j++){//why is this a nested for loop if you're only doing it for 1 row?
                for( int i =0; i<10; i++){
                    toReturn =toReturn + " " + n.rowstate[i] + " ";
                }
                toReturn = toReturn + "Row: " + n.rownum + "\n";
            }

            n=n.prev;
        }
        return toReturn;
    }
    public void rotateBlock(Pair[] origBlock, Pair[] newBlock, Pair Pos){
            //First need to erase Where the block originally was
            Pair Center = new Pair((Pos.x/30)-7, (Pos.y/30));
            for(Pair p : origBlock){
                //System.out.println("Turning Off: " + (p.x+Center.x)+ ", "+ (p.y + Center.y));
                SpaceOFF(p.x+Center.x, p.y+Center.y);
            }
            //System.out.println("Line 119" + "\n" + this);
            //Now turn on the spaces where the New Block is 
            for(Pair p: newBlock){
                SpaceON(Center.x+p.x, Center.y+p.y);
            }
            //System.out.println("Line 124" + "\n" + this);

    }

    public void checkComplete(){
        Node n = end;
        LinkedList <Integer> complete = new LinkedList<>();
        while(n!=null){
            int count = 0;
            for( int i =0; i<10; i++){
                if (n.rowstate[i] == 1){
                    count++;
                }
                if(count==10){
                    complete.add(n.rownum);
                }
            }
            n=n.prev;
        }
        for(int p: complete){
            remove(p);
        }
    }
    

    public void updatePos(Pair[] block, Pair newPos){
       // System.out.println("Last pos: " + Lastpos.x + ", " + Lastpos.y + "\n" + "New Pos: " + newPos.x + ", " + newPos.y);
        //mew row is this
        int newrow = (int) (newPos.y/30);
        System.out.println("New Row: " + newrow + "\n" + "Last Row: " + Lastpos.y/30);
        // new column
        int newcol = ((int) (newPos.x/30))-7;
        Pair center = new Pair((double)newrow,(double) newcol);
        int origrow = (int)(Lastpos.y/30);
        int origcol = (int)((Lastpos.x/30)-(newPos.x/30)) + newcol;
        System.out.println("New row: " + newrow + "\n" + "New col: " + newcol + "\n" + "Orig row: " + origrow + "\n" + "Orig Col: " + origcol );

        for(Pair p: block){
            // space off takes column then row as parameters
            System.out.println("Removed: " + (origcol + p.x) + ", " + (origrow + p.y));
            SpaceOFF(origcol + p.x, origrow + p.y);
        }

        for(Pair p: block){
            SpaceON(newcol+p.x, newrow + p.y);
        }
        this.Lastpos = new Pair(newPos.x, newPos.y);
        System.out.println("Last Pos is now: " + Lastpos.x + ", " + Lastpos.y);
        System.out.println(this);

    }
    
/****************************************************************************************************/
/////Check Collision
    public boolean checkCollision(boolean isFalling, State currentState, Pair[] block){
        int currRow=(int) (Lastpos.y/30);
        // new column
        int currCol = (int) (((Lastpos.x/30))-7);
        System.out.println("Current Column: "+ currCol+ "\n" + "Current Row: " + currRow);
        Pair center = new Pair((double) currCol,(double)currRow); 
        
         /******************************************************************
          * This part of the method is to find the lowest blocks, basically the blocks that would be interacting with lower blocks
          */
        LinkedList <Pair> Check= new LinkedList<Pair>();
        double biggestx = 0;
        double smallestx= 0;

        //Identify the bounds for the shape,
        for(Pair p: block){
            if(p.x>biggestx){
                biggestx = p.x;
            }
            if(p.x<smallestx){
                smallestx = p.x;
            }
        }
        //find the lowest point for each row
        for(double i = smallestx; i<biggestx+1; i++){
            Pair lowestPoint = new Pair(i, -2);
            for(Pair p: block){
                if(p.x==lowestPoint.x && p.y>lowestPoint.y){
                    lowestPoint = p;
                }
            }
            Check.add(lowestPoint);
        }
        System.out.println("The Bottom Block is at: ");
        for(Pair p: Check){
            System.out.println(p.x + ", " + p.y);
        }
        /******************************************************************* */

        //Look at the Row below each bottom block in check, if it is greater than 20, the maximum row, then stop falling. if It is occupied by a one stop
        for(Pair p: Check){
            System.out.println("Check " + p.x + ", "+ p.y);
            System.out.println("Checking: " + (center.x+ p.x) + ", " + (center.y+p.y));
            if((center.y+p.y+1)>20 || checkSpace((center.x + p.x), center.y+p.y+1) ==1){
                isFalling=false;
                System.out.println("isFalling Set: "+ isFalling);
            }
        }


        return isFalling;
    }
    /**************************************************************************************************** */


    public void newblock(Pair[] block, Pair pos){
        int top = 0;
        for(Pair p : block){
            if(p.y<top){
                top = (int)p.y;
            }
        }
        System.out.println("Top is: " + top);
        Pair center = new Pair(5, top +pos.y/30);

        for(Pair p: block){
            System.out.println("Turning Space on: " + (p.x+center.x) + ", " + (p.y+center.y)); // Test to see which space it is turning on
            SpaceON(p.x+center.x, p.y+center.y);
        }
        Lastpos=new Pair(360, 30);
        System.out.println(this);
    }


    public int length(){
        return length;
    }

    public void drawState(Graphics g, Image blockImage, int boardX, int boardY, int size){
        Node n = end;
        
        for(int p =0; p<20; p++){
            for(int i =0; i<10; i++){
                g.drawImage(blockImage, boardX + i * size, boardY + (n.rownum+1) * size, size, size, null);
                if(n.rowstate[i]==1){
                    g.setColor(new Color(200, 0,0));
                    g.fillRoundRect( (int) i*size + 210, (int) n.rownum*size+30, size, size, 10, 10);
                    g.setColor(new Color(255, 255, 255));
                }
            }
            n=n.prev;
        }
    }

    /*public void drawBlocks(Graphics g, Image blockImage, Pair[] block){
        
    }
    */



}





class Node{
    //each node will hold the state of the row in an array
    int rownum;
    int[] rowstate;
    Node prev;
    public Node(int num){
        rowstate = new int[10];
        this.rownum = num;

        //Test to see if this is working and creating rows properly/
        // System.out.println("Row: " + rownum);




    }
}