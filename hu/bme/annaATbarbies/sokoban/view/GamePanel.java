package hu.bme.annaATbarbies.sokoban.view;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private int x;
    private int y;

    public GamePanel() {
        this.x = 10;
        this.y = 10;
    }

    public void increaseX(){
        x += 10;
        repaint();
    }

    public void decreaseX(){
        x -= 10;
        repaint();
    }

    public void increaseY(){
        y += 10;
        repaint();
    }

    public void decreaseY(){
        y -= 10;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.drawRect(x,y,50,50);
    }
}
