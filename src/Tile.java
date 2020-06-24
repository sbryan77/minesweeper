import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile extends JPanel implements MouseListener {
    private int x, y;
    private Color grey = new Color(220, 220, 220);
    JLabel label;
    public boolean checked;

    public Tile() {
        setBackground(Color.white);
        x = 0;
        y = 0;
        // addMouseListener(new PanelListener());
        super.addMouseListener(this);
        label = new JLabel("");
        this.add(label);
        checked = false;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
    public void setLabel(String value)
    {
        label.setText(value);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouse clicked");
        if (!TestMinesweeper.stopGame) {
            if (SwingUtilities.isRightMouseButton(e)) {
                label.setText("?");
            } else {
                setBackground(grey);

                String name = this.getName();
                String[] parts = name.split("-");
                String xpart = parts[0];
                int x = Integer.valueOf(parts[0]);
                int y = Integer.valueOf(parts[1]);

                getMineCount(x, y);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

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

    //    private class PanelListener extends MouseAdapter
//    {
//        public void mousePressed(MouseEvent e)
//        {
//            x = e.getX();
//            y = e.getY();
//
//            setBackground(grey);
//            JLabel label = new JLabel("1");
//
//            repaint();
//        }
//    }
    private void getMineCount(int x, int y) {
        int minecount = 0;
        if (!TestMinesweeper.stopGame) {

            for (int mc = 1; mc <= 8; mc++) {
                switch (mc) {
                    case 1:
                        if (x - 1 >= 0) {
                            if (TestMinesweeper.panels[x - 1][y] instanceof MineTile) {
                                minecount++;
                            }
                        }
                        break;
                    case 2:
                        if (x - 1 >= 0 && y - 1 >= 0) {
                            if (TestMinesweeper.panels[x - 1][y - 1] instanceof MineTile) {
                                minecount++;
                            }
                        }
                        break;
                    case 3:
                        if (y - 1 >= 0) {
                            if (TestMinesweeper.panels[x][y - 1] instanceof MineTile) {
                                minecount++;
                            }
                        }
                        break;
                    case 4:
                        if (x + 1 <= 15 && y - 1 >= 0) {
                            if (TestMinesweeper.panels[x + 1][y - 1] instanceof MineTile) {
                                minecount++;
                            }
                        }
                        break;
                    case 5:
                        if (x + 1 <= 15) {
                            if (TestMinesweeper.panels[x + 1][y] instanceof MineTile) {
                                minecount++;
                            }
                        }
                        break;
                    case 6:
                        if (x + 1 <= 15 && y + 1 <= 15) {
                            if (TestMinesweeper.panels[x + 1][y + 1] instanceof MineTile) {
                                minecount++;
                            }
                        }
                        break;
                    case 7:
                        if (y + 1 <= 15) {
                            if (TestMinesweeper.panels[x][y + 1] instanceof MineTile) {
                                minecount++;
                            }
                        }
                        break;
                    case 8:
                        if (x - 1 >= 0 && y + 1 <= 15) {
                            if (TestMinesweeper.panels[x - 1][y + 1] instanceof MineTile) {
                                minecount++;
                            }
                        }
                        break;
                }
            }
            Tile aTile = (Tile)TestMinesweeper.panels[x][y];
            aTile.setLabel(Integer.toString(minecount));
            //label.setText(Integer.toString(minecount));
            repaint();
            if (minecount == 0) {
                checked = true;
                System.out.println(x + " " + y);
                if (x - 1 >= 0) {
                    if (TestMinesweeper.panels[x - 1][y] instanceof Tile) {
                        Tile checkingTile = (Tile) TestMinesweeper.panels[x - 1][y];
                        if (!checkingTile.checked) {
                            checkingTile.checked = true;
                            getMineCount(x - 1, y);
                        }
//                        else
//                        {
//                            return;
//                        }
                    }
//                    else {
//                        return;
//                    }
                }
                if (y - 1 >= 0) {
                    if (TestMinesweeper.panels[x][y -1] instanceof Tile) {
                        Tile checkingTile = (Tile) TestMinesweeper.panels[x][y-1];
                        if (!checkingTile.checked) {
                            checkingTile.checked = true;
                            getMineCount(x , y-1);
                        }
//                        else
//                        {
//                            return;
//                        }
                    }
//                    else {
//                        return;
//                    }
                }
                if (x - 1 >= 0 && y + 1 < 16) {
                    if (TestMinesweeper.panels[x - 1][y + 1] instanceof Tile) {
                        Tile checkingTile = (Tile) TestMinesweeper.panels[x - 1][y + 1];
                        if (!checkingTile.checked) {
                            checkingTile.checked = true;
                            getMineCount(x - 1, y + 1);
                        }
                    } else {
                        return;
                    }
                }
                if (y - 1 >= 0 && x + 1 < 16) {
                    if (TestMinesweeper.panels[x + 1][y - 1] instanceof Tile) {
                        Tile checkingTile = (Tile) TestMinesweeper.panels[x + 1][y - 1];
                        if (!checkingTile.checked) {
                            checkingTile.checked = true;
                            getMineCount(x + 1, y - 1);
                        }
//                        else
//                        {
//                            return;
//                        }
                    }
//                    else {
//                        return;
//                    }
                }
                if (y - 1 >= 0 && x - 1 >=0) {
                    if (TestMinesweeper.panels[x - 1][y - 1] instanceof Tile) {
                        Tile checkingTile = (Tile) TestMinesweeper.panels[x - 1][y - 1];
                        if (!checkingTile.checked) {
                            checkingTile.checked = true;
                            getMineCount(x - 1, y - 1);
                        }
//                        else
//                        {
//                            return;
//                        }
                    }
//                    else {
//                        return;
//                    }
                }
                if (y + 1 < 16 && x + 1 < 16) {
                    if (TestMinesweeper.panels[x + 1][y + 1] instanceof Tile) {
                        Tile checkingTile = (Tile) TestMinesweeper.panels[x + 1][y + 1];
                        if (!checkingTile.checked) {
                            checkingTile.checked = true;
                            getMineCount(x + 1, y + 1);
                        }
//                        else
//                        {
//                            return;
//                        }
                    }
//                    else {
//                        return;
//                    }
                }
                if (y + 1 < 16 ) {
                    if (TestMinesweeper.panels[x][y + 1] instanceof Tile) {
                        Tile checkingTile = (Tile) TestMinesweeper.panels[x][y + 1];
                        if (!checkingTile.checked) {
                            checkingTile.checked = true;
                            getMineCount(x, y + 1);
                        }
//                        else
//                        {
//                            return;
//                        }
                    }
//                    else {
//                        return;
//                    }
                }
                if (x + 1 < 16 ) {
                    if (TestMinesweeper.panels[x+1][y] instanceof Tile) {
                        Tile checkingTile = (Tile) TestMinesweeper.panels[x+1][y];
                        if (!checkingTile.checked) {
                            checkingTile.checked = true;
                            getMineCount(x+1, y );
                        }

                      else
                        {
                            return;
                        }
                    }
//                    else {
//                        return;
//                }
                }


            }

        }

  //
    }

}