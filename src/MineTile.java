import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MineTile extends JPanel implements MouseListener
{
    private int x, y;
    private ImageIcon image;
    JLabel label;

    public MineTile(ImageIcon i)
    {
        setBackground(Color.white);
        x = 0;
        y = 0;
        //addMouseListener(new PanelListener());
        super.addMouseListener(this);
        image = i;
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int x  = (getWidth() - image.getIconWidth()) / 2;
        int y  = (getHeight() - image.getIconHeight()) / 2;
        image.paintIcon(this, g, x, y);
        label = new JLabel("");
        this.add(label);
    }
    public void setImage(ImageIcon i)
    {
        image = i;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (!TestMinesweeper.stopGame)
        {
            if (SwingUtilities.isRightMouseButton(e)) {
                label.setText("?");
                repaint();
            } else {
                image = new ImageIcon("mine.jpg");

                findMines();
                setBackground(Color.red);
                repaint();
                System.out.println("\nYou hit a mine!");
                TestMinesweeper.stopGame = true;
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    private void findMines()
    {
        for (int i = 0; i < 16; i++)
        {
            for (int j = 0; j < 16; j++)
            {
                if (TestMinesweeper.panels[i][j] instanceof MineTile)
                {
                    TestMinesweeper.panels[i][j].setBackground(Color.gray);
                    MineTile mt = (MineTile)TestMinesweeper.panels[i][j];
                    mt.setImage(new ImageIcon("mine.jpg"));
                    repaint();

                }
            }
        }
    }
}