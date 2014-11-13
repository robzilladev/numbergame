package numbergame;
import java.awt.*; import java.awt.event.*;
import java.util.*; import javax.swing.*;

public class NumberPanel extends JPanel
{
    private MainPanel mainpanel;
    private final static int NP_WIDTH = 40;
    private final static int NP_HEIGHT = 40;
    private Color panelColor;
    private int numValue, row, column;
    private boolean isGreen, isZero, twoValues;
    Random random;
    
    public NumberPanel(MainPanel mainpanel)
    {
        this.mainpanel = mainpanel;
        setPreferredSize(new Dimension(NP_WIDTH,NP_HEIGHT));
        panelColor = Color.red;
        setBackground(panelColor);
        
        random = new Random ();
        numValue = random.nextInt(9)+1;
        
        MouseListener mouselistener = new MouseListener();
        addMouseListener(mouselistener);
        
    }
    
    @Override
    public void paintComponent (Graphics draw)
    {
        super.paintComponent(draw);
        
        draw.setColor(Color.black);
        if (isZero != true)
        {
        draw.setFont(new Font("Garamond", Font.PLAIN, 40));
        draw.drawString(numValue+"", 10, 32);
        }
    }
    
    // Getters and setters for NumberPanel variables.
     public int getNumValue()
     {
         return numValue;
     }
     public void setNumValue0()
     {
         numValue = 0;
         panelColor = Color.orange;
         setBackground(panelColor);
         isZero = true;
         repaint();
     }
     public void setNewValue()
     {
         numValue = random.nextInt(9)+1;
         panelColor = Color.red;
         setBackground(panelColor);
         if (isZero == true)
             isZero = false;
         isGreen = false;
         repaint();
     }
     public void setEmptyNew()
     {
         if (numValue == 0)
         {
            numValue = random.nextInt(9)+1;
            panelColor = Color.red;
            setBackground(panelColor);
            isZero = false;
            isGreen = false;
            repaint();
         }
     }
     public void setRowColumn(int row_, int column_)
     {
         row = row_;
         column = column_;
     }
     public int getRow()
     {
         return row;
     }
     public int getColumn()
     {
         return column;
     }
     
    // To String method, returns a brief description of the number panel.
    @Override
    public String toString()
    {
        return "Row: " + row + " Column: " + column;
    }
    
    // Mouse Listener.
    
    private class MouseListener extends MouseAdapter
    {
        @Override
        public void mouseEntered (MouseEvent event)
        {
            if (isZero != true)
            {
            panelColor = Color.yellow;
            setBackground(panelColor);
            repaint();
            }
        }
        @Override
        public void mouseExited(MouseEvent event)
        {
            if (isZero != true)
            {
                if (isGreen == true)
                {
                    panelColor =  Color.green;
                    setBackground(panelColor);
                    repaint();
                }
                else
                {
                    panelColor =  Color.red;
                    setBackground(panelColor);
                    repaint();
                }
            }
            
        }
        @Override
        public void mouseClicked(MouseEvent event)
        {
            if (isZero != true)
            {
                if (isGreen == true)
                {
                    panelColor = Color.red;
                    setBackground(panelColor);
                    isGreen = false;
                    mainpanel.setIsSelected(false);
                    twoValues = false;
                    repaint();
                }
                else
                {
                    if (mainpanel.getIsSelected() == false)
                    {
                        panelColor = Color.green;
                        setBackground(panelColor);
                        isGreen = true;
                        mainpanel.setIsSelected(true);
                        mainpanel.setValue1(numValue);
                        mainpanel.giveRowColumn1(row, column);
                        repaint();
                    }
                    else
                    {
                        mainpanel.setValue2(numValue);
                        mainpanel.giveRowColumn2(row, column);
                        twoValues = true;
                    }
                    
                }
                
                if (twoValues == true)
                {
                    // Checks to see if they are adjacent panels.
                    // If they are, checks if they meet the criteria to be removed
                    // from the game screen. If true, applicable panels are set
                    // to zero thus painted orange and rendered out of play.
                    if (mainpanel.checkAdjValidity() == true)
                    {
                        if (mainpanel.compareValues() == true)
                        {
                            mainpanel.setIsSelected(false);
                            mainpanel.updateScore();
                        }
                    }
                    
                    if (mainpanel.checkAdjValidity() == false)
                    {
                        // Perform check to see if there are any empty squares
                        // (set to 0) in between the two clicked values.
                        if (mainpanel.checkAcrossValidity() == true)
                        {
                            if (mainpanel.compareValues() == true)
                            {
                                mainpanel.setIsSelected(false);
                                mainpanel.updateScore();
                            }
                        }
                        
                    }
                }
            }
        }
    }
}
