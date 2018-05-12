package hu.bme.annaATbarbies.sokoban.view;

import hu.bme.annaATbarbies.sokoban.model.Floor;
import hu.bme.annaATbarbies.sokoban.model.pushable.Controller;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class ResultWindow extends JFrame {

    public void createAndShow() {
        List<Controller> workers = Floor.getInstance().getAllWorkers();
        Collections.sort(workers);
    }
}
