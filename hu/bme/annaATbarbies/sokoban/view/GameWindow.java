package hu.bme.annaATbarbies.sokoban.view;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    private static final String MOVE = "move";
    public static final String LUBRICATE = "lubricate";

    private GamePanel gamePanel;
    private Controller controller;

    public GameWindow(Controller controller) throws HeadlessException {
        super("Game");
        this.controller = controller;
    }

    public void createAndShow() {
        gamePanel = new GamePanel();

        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("W"), MOVE);
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("A"), MOVE);
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("S"), MOVE);
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("D"), MOVE);
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("Q"), LUBRICATE);
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("E"), LUBRICATE);

        gamePanel.getActionMap().put(MOVE, controller);
        gamePanel.getActionMap().put(LUBRICATE, controller);

        setContentPane(gamePanel);
        setSize(new Dimension(600, 600));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void repaintNeeded() {
        gamePanel.repaint();
    }
}
