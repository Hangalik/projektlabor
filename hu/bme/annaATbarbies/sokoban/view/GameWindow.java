package hu.bme.annaATbarbies.sokoban.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {

    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    private static final String MOVE = "move";
    private static final String LUBRICATE = "lubricate";

    private GamePanel gamePanel;
    private Controller controller;

    public GameWindow(Controller controller) {
        super("Game");
        this.controller = controller;
        setLayout(new FlowLayout());
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                new ResultWindow();
            }
        });
    }

    public void createAndShow() {
        gamePanel = new GamePanel();

        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("W"), MOVE);
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("A"), MOVE);
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("S"), MOVE);
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("D"), MOVE);
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("Q"), LUBRICATE);
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("E"), LUBRICATE);

        gamePanel.getActionMap().put(MOVE, controller.stepAction);
        gamePanel.getActionMap().put(LUBRICATE, controller.lubricateAction);

        setResizable(false);
        setContentPane(gamePanel);
        setSize(gamePanel.getOptimalDimension());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        
    }

    public void repaintNeeded() {
        gamePanel.repaint();
    }
    
    public void disableInput() {
    	gamePanel.getInputMap(IFW).clear();
    }

}
