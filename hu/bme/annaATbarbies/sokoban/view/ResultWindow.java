package hu.bme.annaATbarbies.sokoban.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import hu.bme.annaATbarbies.sokoban.model.Floor;
import hu.bme.annaATbarbies.sokoban.model.pushable.Controller;


import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import hu.bme.annaATbarbies.sokoban.model.Floor;
import hu.bme.annaATbarbies.sokoban.model.pushable.Controller;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class ResultWindow extends JFrame {

	JTable table;
	JScrollPane scrollPane;
	
	String[] columnNames = {"Place",
            "Player ID",
            "Points"};
	Object[][] data = {
		    {0,"", 0},
		    {0, "", 0},
		    {0, "", 0},
		    {0, "", 0}
		};
	public ResultWindow() {
        super("Results");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        setMinimumSize(new Dimension(600,280));
        createAndShow();
        Font f = new Font("Zapfino", Font.BOLD, 30);
        JTableHeader header = table.getTableHeader();
        header.setFont(f);
        table.setFont(f);
        table.setRowHeight(50);
        this.add(scrollPane);

        setVisible(true);}

    private static final Logger logger = Logger.getLogger(ResultWindow.class);

    public void createAndShow() {
    	List<Controller> workers = Floor.getInstance().getAllWorkers();
        Collections.sort(workers);

        for (int i = 0; i < workers.size(); i++) {
            Controller wor = workers.get(i);
            data[i][0]= i+1;
            data[i][1]=wor.getID();
            data[i][2]=wor.getPoint(); 
            
            logger.debug(String.format("%d. place: player%d with %d points.", i + 1, wor.getID(), wor.getPoint()));
        }
       table = new JTable(data, columnNames);
       scrollPane = new JScrollPane(table);
	}
    


}
