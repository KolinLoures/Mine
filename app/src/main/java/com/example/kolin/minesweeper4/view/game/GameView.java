package com.example.kolin.minesweeper4.view.game;

import com.example.kolin.minesweeper4.model.Cell;

import java.util.List;

/**
 * Created by kolin on 31.10.2016.
 */

public interface GameView {

    void showGrid(List<Cell> cells);

    void showToast(String text);

    void setTextScore(String text);

    void endGame();
}
