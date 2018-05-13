package hu.bme.annaATbarbies.sokoban.view;

<<<<<<< HEAD

import java.awt.BorderLayout;
import java.awt.Dimension;

import hu.bme.annaATbarbies.sokoban.model.Floor;
import hu.bme.annaATbarbies.sokoban.model.pushable.Controller;


import javax.swing.*;
import javax.swing.table.TableModel;

=======
import hu.bme.annaATbarbies.sokoban.model.Floor;
import hu.bme.annaATbarbies.sokoban.model.pushable.Controller;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
>>>>>>> 6be2dc6218cd9133cd7be55166e35c555e4a4a78
import java.util.Collections;
import java.util.List;

public class ResultWindow extends JFrame {
	
	String[] columnNames = {"Player",
            "Points",
            "Place"};

	public ResultWindow() {
<<<<<<< HEAD
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

=======
        super("My albums");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        setMinimumSize(new Dimension(500, 200));
        setVisible(true);
    }

    private static final Logger logger = Logger.getLogger(ResultWindow.class);

    public void createAndShow() {
        List<Controller> workers = Floor.getInstance().getAllWorkers();
        Collections.sort(workers);

        for (int i = 0; i < workers.size(); i++) {
            Controller wor = workers.get(i);
            logger.debug(String.format("%d. place: player%d with %d points.", i + 1, wor.getID(), wor.getPoint()));
        }
	}
>>>>>>> 6be2dc6218cd9133cd7be55166e35c555e4a4a78
}
