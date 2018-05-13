package hu.bme.annaATbarbies.sokoban.view;


import java.awt.BorderLayout;
import java.awt.Dimension;

import hu.bme.annaATbarbies.sokoban.model.Floor;
import hu.bme.annaATbarbies.sokoban.model.pushable.Controller;


import javax.swing.*;
import javax.swing.table.TableModel;

import java.util.Collections;
import java.util.List;

public class ResultWindow extends JFrame {
	
	String[] columnNames = {"Player",
            "Points",
            "Place"};

	public ResultWindow() {
		super("My albums");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(new Dimension(600, 600));
		this.setLayout(new BorderLayout());
		createAndShow();
		setVisible(true);


	}

    

    public void createAndShow() {
        List<Controller> workers = Floor.getInstance().getAllWorkers();
        Collections.sort(workers);
        
        Object[][] data = {
        	    {workers.get(0).getPoint(),"1st"},
        	    {workers.get(0).getPoint(),"2nd"},
        	    {workers.get(0).getPoint(),"3rd"},
        	    {workers.get(0).getPoint(),"4th"}
        	};

        JTable table = new JTable(data, columnNames);
    }

}
