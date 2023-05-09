import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Test {
    public static void main(String [] args){

        
        State test = new State();
        //Block teste = new Block(test);
        System.out.println(test);
        //System.out.println(test);
        //test.append();
        int[] tester;

        System.out.println("\n");
        for(int i = 0; i<10; i++){
            test.SpaceON(i, 19);
        }
        System.out.println(test);
        test.remove(19);

        System.out.println(test);


        

    }

}
