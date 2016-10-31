package com.example.kolin.minesweeper4.model;

/**
 * Created by kolin on 31.10.2016.
 */

public class Cell {

    private int x;
    private int y;
    private int position;

    private boolean isBomb;
    private boolean isOpen;
    private boolean isClicked;
    private int value;

    public Cell() {
    }


    public void setBomb(boolean isBomb) {
        this.isBomb = isBomb;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public void setClicked() {
        isClicked = true;
        isOpen = true;
    }

    public void setValue(int value) {
        isBomb = false;
        isOpen = false;
        this.value = value;

        if (value == -1) {
            isBomb = true;
        }
    }

    public int getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.position = y * size + x;
    }
}
