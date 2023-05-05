// I am thinking that we can possibly make a class to hold the current state of the entire board in an ordered colleciton, this way 
// if a row fills up then we can just remove and then its easier to move
public class State extends OrderedCollection{
    Node end;
    int length;

    // Constructor - Creates the first end, but then appends 20 rows
    public State(){
        end = null;
        for(int i =0; i<20; i++){
            append();
        }


    }
    //

    //append method, just adds a blank rows
    public void append(){
        length++;
        Node toAdd = new Node(length);
        toAdd.prev= end;
        end = toAdd;
    }

    public void SpaceON(double column, double row){
        Node n = end; 
        while(n.rownum != row){
            n=n.prev;
        }
        n.rowstate[(int)column] = 1;
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
        while(n.prev.rownum != index+1){
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
        while(n.rownum >= index+1){
            n.rownum--;
            n = n.prev;
        }

        return toReturn;

    }


    //Removes the last rowstate - I think will need to add more functions such as a remove function
    public int[] pop(){
        int[] toReturn = end.rowstate; 
        end = end.prev;
        length--;
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


    public void newblock(Pair[] block){
        int top = 0;
        for (Pair p : block) {
            if (p.y > top) {
                top = (int) p.y;
            }
        }
        Pair center = new Pair(5, 20-top);
        for(Pair p: block){
            SpaceON(p.x+center.x, p.y + center.y);
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