package hu.bme.annaATbarbies.sokoban.view;

import hu.bme.annaATbarbies.sokoban.model.Floor;
import hu.bme.annaATbarbies.sokoban.model.field.Field;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {

    private static final Logger logger = Logger.getLogger(GamePanel.class);

    private static final int minPanelWidth = 640;
    private static final int minPanelHeight = 480;
    private static final int maxPanelWidth = 1920;
    private static final int maxPanelHeight = 1080;

    private static final int minCellWidth = 38;
    private static final int maxCellWidth = 500;
    private static final int optimalCellWidth = 100;

    private static BufferedImage placeHolderOriginal = null;

    static {
        try {
            placeHolderOriginal = ImageIO.read(new File("src/res/placeholder.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int cellWidth;
    private int cellHeight;
    private int panelWidth;
    private int panelHeight;

    public GamePanel() {
        super();

        Field[][] floor = Floor.getInstance().getFloor();
        int x = floor.length, y = floor[0].length;

        // Optimalis cella szelesseg kiszamolasa.
        int i = 0; //minCellWidth;
        while (i * x < minPanelWidth && i < maxCellWidth) {
            i++;
        }
        int minimalCellWidth = i;

        while (i * x < maxPanelWidth && i < maxCellWidth) {
            i++;
        }
        int maximalCellWidth = i;

        this.cellWidth = (minimalCellWidth + maximalCellWidth) / 2;

        // Optimalis cella magassag kiszamolasa.
        i = 0; //minCellHeight;
        while(i * y < minPanelHeight && i < maxCellWidth) {
            i++;
        }
        int minimalCellHeight = i;

        while (i * y < maxPanelHeight && i < maxCellWidth) {
            i++;
        }
        int maximalCellHeight = i;

        this.cellHeight = (minimalCellHeight + maximalCellHeight) / 2;

        // A ketto kozul a kisebbik ervenyesul.
        cellHeight = cellWidth = cellHeight < cellWidth ? cellHeight : cellWidth;

        panelWidth = cellWidth * x < minPanelWidth ? minPanelWidth : cellWidth * x;
        panelHeight = cellHeight * y < minPanelHeight ? minPanelHeight : cellHeight * y;

        logger.debug(String.format("cella magassag: %d, szelesseg: %d; panel magassag: %d, szelesseg %d;", cellHeight, cellWidth, panelHeight, panelWidth));
    }

    public Dimension getOptimalDimension() {
        return new Dimension(panelWidth, panelHeight + 29); // ~30 magas az ablak felso csikja.
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Field[][] floor = Floor.getInstance().getFloor();
        int x = floor.length, y = floor[0].length;

        int xOffset = (panelWidth - x * cellWidth) / 2;
        int yOffset = (panelHeight - y * cellHeight) / 2;

        logger.debug(String.format("xOffset: %d; yOffset: %d;", xOffset, yOffset));

        Image placeHolderScaled = placeHolderOriginal.getScaledInstance(cellWidth, cellHeight, Image.SCALE_DEFAULT);

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int xPos = xOffset + i * cellWidth, yPos = yOffset + j * cellHeight;

                // Ha nem lett inicializalva a mezo, akkor egy alapertelmezett placeholder kep jelenik meg helyette.
                if (floor[i][j] == null) {
                    g.drawImage(placeHolderScaled, xPos, yPos, null);
                } else {
                    // Eloszor a mezot rajzoljuk ki.
                    Image scaledFieldImg = floor[i][j].getImg().getScaledInstance(cellWidth, cellHeight, Image.SCALE_DEFAULT);
                    g.drawImage(scaledFieldImg, xPos, yPos, null);

                    // Ha pedig van rajta valami, akkor azt rarajzoljuk.
                    if (floor[i][j].getObstacle() != null) {
                        Image scaledObstacleImage = floor[i][j].getObstacle().getImg().getScaledInstance(cellWidth, cellHeight, Image.SCALE_DEFAULT);
                        g.drawImage(scaledObstacleImage, xPos, yPos, null);
                    }
                }
            }
        }
    }
}
