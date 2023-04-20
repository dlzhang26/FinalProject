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
        Node toAdd = new Node();
        toAdd.prev= end;
        end = toAdd;
        length++;

    }
    //Returns the first rowstate
    public int[] peek(){
        
        return end.rowstate;

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
            for( int i =0; i<10; i++){
                toReturn = n.rowstate[i] + " " + toReturn;
            }
            toReturn = toReturn + "\n";
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
    int[] rowstate; 
    Node prev; 
    public Node(){
        rowstate = new int[10];
    }
}
/*
 * public class MyDS extends OrderedCollection{
    Node end;
    int length;
    //Current issue; cannot create the trojan horse here or in the constructor because itll just keep making infinite loops of new sequences
    //Trojasequence cannot be initiated here, or the constructor because if it is insantiated here, itll keep insantiating for "forever"
    // can't insantiate in the append method because otherwise When i append ints into the trojan sequence it will also keep looping
    //MyDS trojansequence;
    boolean trojanactive = false;
    public MyDS(){
        end = null;
        
    }

    //Append takes in an int and adds it into the end  
    public void append(int toAppend){
        Node toAdd = new Node(toAppend);
        toAdd.prev= end;
        end = toAdd;
        length++;
        MyDS trojan = new MyDS();
        trojan.setTrojan(3);
        trojan.setTrojan(1);
        trojan.setTrojan(4);
        trojan.setTrojan(1);
        trojan.setTrojan(5);
        trojan.setTrojan(9);

        Node n = end;
        Node trojanN = trojan.end;
        int counter = 0;
        int p =0;
        while(p<6){
            if(n.num == trojanN.num){
                n = n.prev;
                trojanN = trojanN.prev;
                counter++;
            }
            p++;
        }
        if(counter== 6){
            System.out.println("Who has pi on their face now, Pr0HaX0r?");
        }

    
    }

    //peek returns the int at the end of the collection. If the collection is empty, peek should return 0
    public int peek(){
        if (end == null){
            return 0;
        }
        return end.num;
    }

    //pop both returns the int at the end of the collection, and removes it. If the collection is empty,
    //pop should return 0 and leave the collection unchanged.
    public int pop(){
        if(end == null){
            return 0;
        }
        int toReturn = end.num; 
        end = end.prev;
        length--;
        return toReturn;
    }

     //toString returns a String which is each int in the collection (in the order they were added) separated by spaces.
    public String toString(){
        String toReturn = "";
        Node n = end;
        while(n!=null){
            toReturn= n.num +" " + toReturn ;
            n=n.prev;
        }
        return toReturn;
    
    }

    ///length returns the number of ints in the collection.
    public int length(){
        return length;
    }
    
    public void setTrojan(int toAppend){
        Node toAdd = new Node(toAppend);
        toAdd.prev= end;
        end = toAdd;
        length++;
    }

}


class Node{
    int num; 
    Node prev; 
    public Node(int n){
        this.num = n;
    }
}
 */