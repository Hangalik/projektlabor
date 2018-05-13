package hu.bme.annaATbarbies.sokoban.view;

import hu.bme.annaATbarbies.sokoban.model.Floor;
import hu.bme.annaATbarbies.sokoban.model.pushable.Controller;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class ResultWindow extends JFrame {

    private static final Logger logger = Logger.getLogger(ResultWindow.class);

    public void createAndShow() {
        List<Controller> workers = Floor.getInstance().getAllWorkers();
        Collections.sort(workers);

        for (int i = 0; i < workers.size(); i++) {
            Controller wor = workers.get(i);
            logger.debug(String.format("%d. place: player%d with %d points.", i + 1, wor.getID(), wor.getPoint()));
        }


    }
}
