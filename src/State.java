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
        length =20;

    }
    //

    //append method, just adds a blank rows
    public void append(){
        Node toAdd = new Node(length);
        toAdd.prev= end;
        end = toAdd;
        length++;

    }




    //Returns the first rowstate
    public int[] peek(){
        
        return end.rowstate;

    }

    public int[] remove(int index){
        int[] toReturn;
        Node n = end; 
        if(index == 1){
            toReturn = pop();
        }
        while(n != null){
            if(n.rownum == index){
                toReturn = n.rowstate;

            }
        }
        toReturn = n.rowstate;
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

        //Test to see if this is working and creating rows properly
        System.out.println("State Created:");
        for (int i = 0; i< 10; i++){
            rowstate[i] = i;
            System.out.print(rowstate[i]+ " ");
        }
        System.out.println("Row: " + rownum);
        



    }
}