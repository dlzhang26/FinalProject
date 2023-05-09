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

        System.out.println("\n");

        test.SpaceON(3, 4);
        System.out.println(test);

        test.remove(4);
        System.out.println(test);
        test.append();
        System.out.println(test);


        

    }

}
