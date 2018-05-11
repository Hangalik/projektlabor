package hu.bme.annaATbarbies.sokoban.view;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowController {

    private static final Logger logger = Logger.getLogger(WindowController.class);
    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    private static final String MOVE_UP = "move up";
    private static final String MOVE_DOWN = "move down";
    private static final String MOVE_RIGHT = "move right";
    private static final String MOVE_LEFT = "move left";

    private JFrame menuFrame;
    private JFrame gameFrame;

    public WindowController() {
        menuFrame = new JFrame("Menu");
        gameFrame = new JFrame("Game");
    }

    public void Start() {
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton startButton = new JButton();
        startButton.setSize(new Dimension(200, 32));
        startButton.setText("Start");
        GamePanel gamePanel = new GamePanel();

        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("W"), MOVE_UP);
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("A"), MOVE_LEFT);
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("S"), MOVE_DOWN);
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("D"), MOVE_RIGHT);

        gamePanel.getActionMap().put(MOVE_UP, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.decreaseY();
            }
        });
        gamePanel.getActionMap().put(MOVE_DOWN, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.increaseY();
            }
        });
        gamePanel.getActionMap().put(MOVE_RIGHT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.increaseX();
            }
        });
        gamePanel.getActionMap().put(MOVE_LEFT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.decreaseX();
                logger.debug(e.getActionCommand());
            }
        });

        startButton.addActionListener(e -> {
            // start the game with gameFrame

            gameFrame.setContentPane(gamePanel);
            gameFrame.setSize(new Dimension(600, 600));
            gameFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            gameFrame.setVisible(true);
        });

        JButton whatButton = new JButton();
        whatButton.setSize(new Dimension(200, 32));
        whatButton.setText("What");

        whatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.increaseX();
            }
        });

        menuPanel.add(startButton);
        menuPanel.add(whatButton);

        menuFrame.setContentPane(menuPanel);
        menuFrame.setSize(new Dimension(600, 600));
        menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menuFrame.setVisible(true);
    }
}
