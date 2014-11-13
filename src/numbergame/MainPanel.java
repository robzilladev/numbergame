package numbergame;
import java.awt.*;
import javax.swing.*;

public class MainPanel extends JPanel
{
    private final int WIDTH = 420;
    private final int HEIGHT = 750;
    private NumberPanel[][] squarePanels = new NumberPanel[12][9];
    
    private int value1, value2;
    
    // Instance variables to be used for the checking of rows and columns
    // for validity in the game. Eg: are they next to each other, or across a
    // blank section..?
    
    private int row1, column1, row2, column2;
    private boolean isSelected;
    
    // Scoring instance variables
    
    private int totalScore;
    private int matchPoints, emptyPoints;
    private int pairs = 0;
    
    public NumberPanel numberpanel;
    public ControlPanel controlpanel;
    
    
    public MainPanel()
    {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.darkGray);
        
        // Populates the 2D array with 12x9 NumberPanel objects and
        // adds the object to the MainPanel.
        for (int row = 0; row < squarePanels.length; row++)
        {
            for (int col = 0; col < squarePanels[row].length; col++)
            {
                squarePanels[row][col] = new NumberPanel(this);
                
                // Gives each panel a reference to its index in the 2D array (row + column). 
                squarePanels[row][col].setRowColumn(row, col);
                add(squarePanels[row][col]);
            }
        }
        controlpanel = new ControlPanel(this);
        add(controlpanel);
    }
    
    // Getters and setters for all game related variables in mainpanel.
    // Update method for scoring.
    
    public void setIsSelected(boolean isSelected_)
    {
        isSelected = isSelected_;
    }
    public boolean getIsSelected()
    {
        return isSelected;
    }
    public void setValue1(int value1_)
    {
        value1 = value1_;
    }
    public int getValue1()
    {
        return value1;
    }
    public void setValue2(int value2_)
    {
        value2 = value2_;
    }
    public int getValue2()
    {
        return value2;
    }
    public int updateScore()
    {
        totalScore = matchPoints + emptyPoints;
        controlpanel.updateScore();
        return totalScore;
    }
    public int getScore()
    {
        return totalScore;
    }
    public int getPairs()
    {
        return pairs;
    }
    
    // Retrieve the applicable columns and rows from the clicked number panels.
    public void giveRowColumn1(int row1_, int column1_)
    {
        row1 = row1_; 
        column1 = column1_;
        //System.out.println(row1 + " and " + column1);
    }
    public void giveRowColumn2(int row2_, int column2_)
    {
        row2 = row2_;
        column2 = column2_;
        //System.out.println(row2 + " and " + column2);
    }
    
    // Compares the columns and rows and returns true if they are valid selections.
    // (are adjacent to each other). False if invalid selections.
    public boolean checkAdjValidity()
    {
        boolean valid = false;
        if (row1 == row2 || column1 == column2)
        {
            if (row1 == (row2 + 1) || row1 == (row2 - 1) || column1 == (column2 + 1)
                    || column1 == (column2 - 1))
            {
                valid = true;
            }
            else
            {
                valid = false;
            }
        }
        return valid;    
    }
    
    // Compares the 2 selcted values to see if they meet the conditions of the game.
    // Adds applicable points.
    
    public boolean compareValues()
    {
        if (value1 == value2 || (value1 + value2) == 10)
        {
            squarePanels[row1][column1].setNumValue0();
            squarePanels[row2][column2].setNumValue0();
            matchPoints = matchPoints + 5 + 5;
            pairs++;
            controlpanel.updatePairs();
            return true;
        }
        else
            return false;
    }
    
    // Uses the row and column information from each selected panel to check if
    // there are any empty (o) panels in between. Uses a different algorithm
    // depending on the position of each row and column.
    
    public boolean checkAcrossValidity()
    {
        boolean valid = false;
        int difference;
        int zeroCheck = 0;
        
        if (row1 == row2)
        {
            if (column2 < column1)
            {
                difference = column1 - column2 - 1;
                int d = difference;
                while (squarePanels[row1][column1 - d].getNumValue() == 0)
                {
                    d--;
                    zeroCheck++;
                }
                if (zeroCheck == difference)
                {
                    valid = true;
                    emptyPoints = emptyPoints + zeroCheck * 2;
                }
                else
                    valid = false;
            }
            else
            {
                difference = column2 - column1 - 1;
                int d = difference;
                while (squarePanels[row1][column1 + d].getNumValue() == 0)
                {
                    d--;
                    zeroCheck++;
                }
                if (zeroCheck == difference)
                {
                    valid = true;
                    emptyPoints = emptyPoints + zeroCheck * 2;
                }
                else
                    valid = false;
            }
        }
        
        else if (column1 == column2)
        {
            if (row2 < row1)
            {
                difference = row1 - row2 - 1;
                int d = difference;
                while (squarePanels[row1 - d][column1].getNumValue() == 0)
                {
                    d--;
                    zeroCheck++;
                }
                if (zeroCheck == difference)
                {
                    valid = true;
                    emptyPoints = emptyPoints + zeroCheck * 2;
                }
                else
                    valid = false;
            }
            else
            {
                difference = row2 - row1 - 1;
                int d = difference;
                while (squarePanels[row1 + d][column1].getNumValue() == 0)
                {
                    d--;
                    zeroCheck++;
                }
                if (zeroCheck == difference)
                {
                    valid = true;
                    emptyPoints = emptyPoints + zeroCheck * 2;
                }
                else
                    valid = false;
            }
        }
        return valid;
    }
    
    // Loops through every array element and assigns it a new number value.
    // All boolean expressions for the applicable panel are defaulted.
    
    public void restartGame()
    {
        for (int row = 0; row < squarePanels.length; row++)
        {
            for (int col = 0; col < squarePanels[row].length; col++)
            {
                squarePanels[row][col].setNewValue();
            }
        }
        isSelected = false;
        
        String thanks = "You Scored: " + totalScore;
        JOptionPane.showMessageDialog(null, thanks);
        pairs = 0;
        totalScore = 0; matchPoints = 0; emptyPoints = 0;
        updateScore();
        controlpanel.updateScore(); controlpanel.updatePairs();
    }
    
    // Loops through all array elements, if they have a value of 0, then they
    // are given a new random value and recoloured red. All boolean expressions
    // are reset to default.
    
    public void repopulate()
    {
        for (int row = 0; row < squarePanels.length; row++)
        {
            for (int col = 0; col < squarePanels[row].length; col++)
            {
                squarePanels[row][col].setEmptyNew();
            }
        }
        isSelected = false;
    }
}
