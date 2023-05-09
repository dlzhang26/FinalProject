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
        length = 19;
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
        //System.out.println("Printing: " + column + ", " + row);
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
        //System.out.println("Removed: " + column + "," + row);
        n.rowstate[(int)column] = 0;


    }

    public int checkSpace(double column, double row){
        Node n = end;
        while(n.rownum != row){
            n=n.prev;
        }
        return n.rowstate[(int)column];
    }




    //Returns the first rowstate
    public int[] peek(){

        return end.rowstate;

    }

    public int[] remove(int index){
        int[] toReturn;
        Node removed;
        Node n = end;

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

        n = end;
        //now to update rownumbers
        while(n.rownum <= index-1){
            n.rownum++;
            n = n.prev;
        }
        length++;

        return toReturn;

    }


    //Removes the last rowstate - I think will need to add more functions such as a remove function
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
            Pair Center = new Pair((Pos.x/30)-7, (Pos.y/30)-1);
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

    public void updatePos(Pair[] block, Pair newPos){
       // System.out.println("Last pos: " + Lastpos.x + ", " + Lastpos.y + "\n" + "New Pos: " + newPos.x + ", " + newPos.y);
        //mew row is this
        int newrow = (int) (newPos.y/30)-1;
        // new column
        int newcol = ((int) (newPos.x/30))-7;
        Pair center = new Pair((double)newrow,(double) newcol);
        int origrow = (int)((Lastpos.y/30)-(newPos.y/30))+ newrow;
        int origcol = (int)((Lastpos.x/30)-(newPos.x/30)) + newcol;
        System.out.println("New row: " + newrow + "\n" + "New col: " + newcol + "\n" + "Orig row: " + origrow + "\n" + "Orig Col: " + origcol );

        for(Pair p: block){
            // space off takes column then row as parameters
            SpaceOFF(origcol + p.x, origrow + p.y);
        }

        for(Pair p: block){
            SpaceON(newcol+p.x, newrow + p.y);
        }
        this.Lastpos = new Pair(newPos.x, newPos.y);
        System.out.println("Last Pos is now: " + Lastpos.x + ", " + Lastpos.y);
        System.out.println(this);

    }

    public boolean checkCollision(boolean isFalling, State currentState, Pair[] block){
        int currRow=(int) (Lastpos.y/30)-1;
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

        //Look at the Row below each bottom block in check, if it is greater than 19, the maximum row, then stop falling. if It is occupied by a one stop
        for(Pair p: Check){
            System.out.println(p.x + ", "+ p.y);
            System.out.println("Checking: " + center.x +" " + p.x + ", " + center.y+ " " +p.y + " " +1);
            if((center.y+p.y+1)>19 || checkSpace((center.x + p.x), center.y+p.y+1) ==1){
                isFalling=false;

            }
        }


        return isFalling;
    }


    public void newblock(Pair[] block, Pair pos){
        int top = 1;
        for(Pair p : block){
            if(p.y<top){
                top = (int)p.y;
            }
        }
        Pair center = new Pair(5, top);

        for(Pair p: block){
            System.out.println("Turning Space on: " + (p.x+center.x) + ", " + (p.y-center.y));
            SpaceON(p.x+center.x, p.y-center.y);
        }
        System.out.println(this);
    }


    public int length(){
        return length;
    }

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