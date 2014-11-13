package numbergame;

import java.awt.*;
import javax.swing.*;

public class NumberGame
{

    public static void main(String[] args)
    {
        JFrame frame = new JFrame ("Number Game");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        
        MainPanel mainpanel = new MainPanel();
        
        frame.getContentPane().add(mainpanel);
        
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
