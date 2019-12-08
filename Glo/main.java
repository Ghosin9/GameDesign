import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class main extends JFrame
{
    private JButton play, inst, ctrl, exit;
    public static int p1, p2;
    public static ArrayList<Integer> maps;
    
    public static void main (String []args)
    {
        main game = new main();
    }
    
    public main()
    {
        JFrame frame = new JFrame();
        p1 = 0;
        p2 = 0;
        
        maps = new ArrayList<Integer>();
        maps.add(0);
        maps.add(1);
        maps.add(2);
        maps.add(3);
        maps.add(4);
        //first menu
        Menu menu = new Menu(frame);
    }
}
