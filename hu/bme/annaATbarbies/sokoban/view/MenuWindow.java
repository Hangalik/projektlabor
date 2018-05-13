package hu.bme.annaATbarbies.sokoban.view;

import javax.swing.*;
import java.awt.*;

public class MenuWindow extends JFrame{

    private Controller controller;

    public MenuWindow(Controller controller) throws HeadlessException {
        super("Menu");
        this.controller = controller;
    }

    public void createAndShow() {
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton startButton = new JButton();
        startButton.setSize(new Dimension(200, 32));
        startButton.setText("Start");

        startButton.addActionListener(e -> {
            // start the game with gameFrame
            controller.createGame();
        });

        JButton exitButton = new JButton();
        exitButton.setSize(new Dimension(200, 32));
        exitButton.setText("Exit");

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        menuPanel.add(startButton);
        menuPanel.add(exitButton);

        setContentPane(menuPanel);
        setSize(new Dimension(600, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
