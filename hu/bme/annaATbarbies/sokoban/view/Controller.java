package hu.bme.annaATbarbies.sokoban.view;

import hu.bme.annaATbarbies.sokoban.model.Floor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Controller extends AbstractAction{

    private MenuWindow menu;
    private GameWindow game;
    private ResultWindow result;

    public void appStarted() {
        menu = new MenuWindow(this);
        menu.createAndShow();
    }

    public void createGame() {
        Floor.getInstance().Initialize("Test_Lvl1.txt");
        game = new GameWindow(this);
        game.createAndShow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "w":

                break;

            case "a":

                break;

            case "s":

                break;

            case "d":

                break;

            case "q":

                break;

            case "e":

                break;

            default:
                return;
        }
        game.repaintNeeded();
    }
}
