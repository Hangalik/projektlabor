package hu.bme.annaATbarbies.sokoban.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

public class ResultWindow extends JFrame {
	
	JTable table = new JTable();

	public ResultWindow() {
		super("My albums");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		setMinimumSize(new Dimension(500, 200));
		setVisible(true);

	}

    
}
