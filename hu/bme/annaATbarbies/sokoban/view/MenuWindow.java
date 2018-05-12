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

        JButton startButton = new JButton();
        startButton.setSize(new Dimension(200, 32));
        startButton.setText("Start");
        startButton.setBackground(new Color(204,215,214));
        startButton.setForeground(new Color(164,7,40));
        startButton.setFocusPainted(false);
        startButton.setFont(new Font("Zapfino", Font.BOLD, 70));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setPreferredSize(new Dimension(300, 100));
        startButton.setMaximumSize(new Dimension(300, 100));
        startButton.setMinimumSize(new Dimension(300, 100));

        startButton.addActionListener(e -> {
            // start the game with gameFrame
            controller.createGame();
        });

        JButton exitButton = new JButton();
        exitButton.setSize(new Dimension(200, 32));
        exitButton.setText("Exit");
        exitButton.setBackground(new Color(204,215,214));
        exitButton.setForeground(new Color(164,7,40));
        exitButton.setFocusPainted(false);
        exitButton.setFont(new Font("Zapfino", Font.BOLD, 70));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setPreferredSize(new Dimension(300, 100));
        exitButton.setMaximumSize(new Dimension(300, 100));
        exitButton.setMinimumSize(new Dimension(300, 100));

        exitButton.addActionListener(e -> {
        	super.dispose();
        });

        JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createVerticalGlue());
		panel.add(startButton);
		panel.add(Box.createVerticalGlue());
		panel.add(exitButton);
		panel.add(Box.createVerticalGlue());

		this.add(panel);

        setContentPane(panel);
        getContentPane().setBackground(new Color(177,121,134));
        setSize(new Dimension(600, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
