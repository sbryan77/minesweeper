import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TestMinesweeper
{
    static JPanel[] [] panels = new JPanel[16] [16];
    static boolean stopGame = false;
    public static void main(String [] args)
    {
        int i;


        JFrame outsideContainer = new JFrame();
        outsideContainer.setLayout(new BorderLayout());
        outsideContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         Container pane = new Container();
        GridLayout grid = new GridLayout(16, 16);
        pane.setLayout(grid);

        outsideContainer.getContentPane().add(pane,BorderLayout.CENTER);
        JButton startButton = new JButton("Start the Game");
        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                stopGame = false;
                pane.removeAll();
                System.out.println("button push");
                startTheGame(pane, outsideContainer);
                pane.setVisible(true);
                outsideContainer.validate();
                outsideContainer.repaint();
            }
        });
        outsideContainer.getContentPane().add( BorderLayout.PAGE_START, startButton);
        outsideContainer.setSize(800,800);
        startTheGame(pane, outsideContainer);

        outsideContainer.setVisible(true);
        outsideContainer.repaint();

        System.out.println("Light Grey = 0, Blue = 1, \nGreen = 2, Red = 3, \nMagenta = 4, Brown = 5, \nCyan = 6, Black = 7, \nDark Grey = 8");
    }
    private static void startTheGame(Container pane, JFrame outsideContainer)
    {

        pane.setLayout(new GridLayout(16, 16));
        Random random = new Random();
        for (int i = 0; i < 16; i++)
        {
            for (int j = 0 ; j < 16; j ++ ) {
                int num = random.nextInt(8);
                if (num == 0) {
                    ImageIcon trans = new ImageIcon("trans.jpg");
                    MineTile panel = new MineTile(trans);
                    panel.setBackground(Color.white);
                    Border border = BorderFactory.createBevelBorder(1);
                    panel.setBorder(border);
                    panel.setName(Integer.toString(i)+"-"+Integer.toString(j));
                    pane.add(panel);
                    panels[i][j] = panel;
                } else {
                    Tile panel = new Tile();
                    panel.setBackground(Color.white);

                    Border border = BorderFactory.createBevelBorder(1);
                    panel.setBorder(border);
                    panel.setName(Integer.toString(i)+"-"+Integer.toString(j));
                    pane.add(panel);
                    panels[i][j] = panel;
                }
            }
        }
        pane.repaint();
        outsideContainer.repaint();
    }
}