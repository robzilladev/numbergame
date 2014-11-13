package numbergame;
import java.awt.*; import java.awt.event.*;
import javax.swing.*; import javax.swing.border.*;

public class ControlPanel extends JPanel
{
    public MainPanel mainpanel;
    private final int CP_WIDTH = 400;
    private final int CP_HEIGHT = 200;
    private JButton restart, repop, exit;
    private JLabel pairs, total, pairsInt, totalInt;
    
    public ControlPanel(MainPanel mainpanel)
    {
        this.mainpanel = mainpanel;
        setPreferredSize(new Dimension(CP_WIDTH,CP_HEIGHT));
        setBackground(Color.lightGray);
        
        TitledBorder title = BorderFactory.createTitledBorder("Scores");
        title.setTitleJustification(TitledBorder.LEFT);
        setBorder(title);
        
        restart = new JButton("Restart");
        restart.setPreferredSize(new Dimension(120, 30));
        repop = new JButton("Repopulate");
        repop.setPreferredSize(new Dimension(120, 30));
        exit = new JButton("Exit");
        exit.setPreferredSize(new Dimension(120, 30));
        
        pairs = new JLabel();
        pairs.setFont(new Font("Garamond", Font.PLAIN, 50));
        pairs.setText("Pairs: ");
        pairs.setPreferredSize(new Dimension (200, 60));
        pairs.setHorizontalAlignment(JLabel.LEFT);
        
        pairsInt = new JLabel();
        pairsInt.setForeground(Color.red);
        pairsInt.setFont(new Font("Garamond", Font.PLAIN, 50));
        pairsInt.setText("0");
        pairsInt.setHorizontalAlignment(JLabel.CENTER);
        pairsInt.setPreferredSize(new Dimension (100,40));
        
        total = new JLabel();
        total.setFont(new Font("Garamond", Font.PLAIN, 50));
        total.setText("Score: ");
        total.setHorizontalAlignment(JLabel.LEFT);
        total.setPreferredSize(new Dimension (200,40));
        
        totalInt = new JLabel();
        totalInt.setForeground(Color.red);
        totalInt.setFont(new Font("Garamond", Font.PLAIN, 50));
        totalInt.setText("0");
        totalInt.setHorizontalAlignment(JLabel.CENTER);
        totalInt.setPreferredSize(new Dimension (100,40));
        
        ButtonListener listener = new ButtonListener();
        restart.addActionListener(listener);
        repop.addActionListener(listener);
        exit.addActionListener(listener);
        
        add(restart);
        add(repop);
        add(exit);
        add(pairs);
        add(pairsInt);
        add(total);
        add(totalInt);
        
    }
    
    private class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent event)
        {
            if (event.getSource() == restart)
            {
                mainpanel.restartGame();
            }
            else if (event.getSource() == repop)
            {
                mainpanel.repopulate();
            }
            else 
            {
                int confirmExit;
                confirmExit = JOptionPane.showConfirmDialog (null, "Are you sure?");
                if (confirmExit == JOptionPane.YES_OPTION)
                {
                    String thanks = "Thanks for playing! You Scored: " +
                            Integer.toString(mainpanel.getScore());
                    JOptionPane.showMessageDialog(null, thanks);
                    System.exit(0); // Query this int paramter.
                }
            }
        }
    }
    
    public void updatePairs()
    {
        pairsInt.setText(Integer.toString(mainpanel.getPairs()));
    }
    public void updateScore()
    {
        totalInt.setText(Integer.toString(mainpanel.getScore()));
    }
}

