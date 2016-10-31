package com.example.kolin.minesweeper4.presenter;

import android.util.Log;

import com.example.kolin.minesweeper4.model.Cell;
import com.example.kolin.minesweeper4.model.CellMapper;
import com.example.kolin.minesweeper4.model.Generator;
import com.example.kolin.minesweeper4.view.game.GameView;

import java.util.List;

/**
 * Created by kolin on 31.10.2016.
 */

public class GamePresenter extends AbstractPresenter<GameView> {

    private static final String TAG = GamePresenter.class.getSimpleName();

    private int currentSize;
    private int[][] currentMatrix;
    private Cell[][] currentCells;
    private Generator generator;

    public GamePresenter() {
        generator = new Generator();
    }

    public void createMatrix(boolean flag) {
        if (flag) {
            currentMatrix = generator.createMatrix();
            currentSize = generator.getSize();
        } else {
            currentMatrix = Generator.matrix;
            currentSize = Generator.size;
        }

        currentCells = CellMapper.convertArrayToArrayCell(currentMatrix, currentSize);
        if (!isViewAttache()) {
            Log.e(TAG, "View is detach!");
        }

        getWeakreference().showGrid(CellMapper.convertArrayToListCell(currentMatrix, currentSize));
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public void onClickItemView(int x, int y) {
        open(x, y);

        if (!isViewAttache()) {
            Log.e(TAG, "View is detach!");
        }

        List<Cell> cells = CellMapper.convertCellArrayToListCell(currentCells, currentSize);
        getWeakreference().setTextScore(String.valueOf(computeScore(cells)));
        getWeakreference().showGrid(cells);

        isEnd();
    }

    private void open(int x, int y) {
        if (x >= 0 && y >= 0 && x < currentSize && y < currentSize && !getCell(x, y).isClicked()) {
            getCell(x, y).setClicked();

            if (getCell(x, y).getValue() == 0) {


                for (int xx = -1; xx < 2; xx++) {
                    for (int yy = -1; yy < 2; yy++) {
                        if (xx != yy) {
                            open(x + xx, y + yy);
                        }
                    }
                }
            }
            if (getCell(x, y).isBomb()) {
                if (!isViewAttache()) {
                    Log.e(TAG, "View is detach!");
                }

                getWeakreference().showToast("You lose!");
                getWeakreference().endGame();
            }
        }
    }

    private boolean isEnd(){
        int bomb = generator.getBombAmount();
        int cells = currentSize * currentSize;
        for (int i = 0; i < currentSize; i++){
            for (int j = 0; j < currentSize; j++){
                if (getCell(i, j).isOpen()){
                    cells--;
                }
                if (getCell(i, j).isBomb() ){
                    bomb--;
                }
                if(!getCell(i, j).isOpen() && getCell(i, j).isBomb()){
                    cells--;
                }
            }
        }

        if (bomb == cells){
            if (!isViewAttache()) {
                Log.e(TAG, "View is detach!");
            }

            getWeakreference().showToast("You won!");
            getWeakreference().endGame();
        }

        return true;
    }

    private Cell getCell(int x, int y) {
        return currentCells[x][y];
    }

    private int computeScore(List<Cell> cells){
        int sum = 0;
        for (Cell c: cells){
            if (c.isOpen() && !c.isBomb()){
                sum++;
            }
        }
        return sum;
    }
}
