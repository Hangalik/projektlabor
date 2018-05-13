package hu.bme.annaATbarbies.sokoban.view;

import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.Floor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Controller {

    private MenuWindow menu;
    private GameWindow game;
    private ResultWindow result;

    public void appStarted() {
        menu = new MenuWindow(this);
        menu.createAndShow();
    }

    public void createGame() {
        Floor.getInstance().Initialize("src/res/Test_Lvl4.txt");
        game = new GameWindow(this);
        game.createAndShow();
    }

    public final AbstractAction stepAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Floor.getInstance().setActiveWorker(Floor.getInstance().getActiveWorker());
            switch (e.getActionCommand()) {
                case "w":
                    Floor.getInstance().activePlayerMoves(Direction.UP);
                    break;

                case "a":
                    Floor.getInstance().activePlayerMoves(Direction.LEFT);
                    break;

                case "s":
                    Floor.getInstance().activePlayerMoves(Direction.DOWN);
                    break;

                case "d":
                    Floor.getInstance().activePlayerMoves(Direction.RIGHT);
                    break;

                default:
                    return;
            }
            game.repaintNeeded();

            if (Floor.getInstance().gameOver()) {
                game.disableInput();
                result = new ResultWindow();
                result.createAndShow();
            } else {
                Floor.getInstance().nextTurn();
            }
        }
    };


    public final AbstractAction lubricateAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Floor.getInstance().setActiveWorker(Floor.getInstance().getActiveWorker());
            switch (e.getActionCommand()) {
                case "q":
                    Floor.getInstance().activePlayerlubricates("oil");
                    break;

                case "e":
                    Floor.getInstance().activePlayerlubricates("honey");
                    break;

                default:
                    return;
            }
            game.repaintNeeded();
        }
    };
}
