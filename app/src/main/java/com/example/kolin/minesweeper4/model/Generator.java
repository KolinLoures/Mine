package com.example.kolin.minesweeper4.model;

import java.util.Random;

/**
 * Created by kolin on 31.10.2016.
 */

public class Generator {

    public static int[][] matrix;
    public static int size;
    public static int bombAmount;


    public int[][] createMatrix() {
        Random random = new Random();
        size = random.nextInt(11) + 10;
        bombAmount = size;
        matrix = new int[size][size];
        putBombs(bombAmount, size);
        calculateNearBombs(size);
        return matrix;
    }


    public void putBombs(int bombAmount, int size) {
        Random random = new Random();
        while (bombAmount > 0) {
            int x = random.nextInt(size);
            int y = random.nextInt(size);

            if (matrix[x][y] != -1) {
                matrix[x][y] = -1;
                bombAmount--;
            }
        }
    }

    public void calculateNearBombs(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = getNearAmountBomb(size, i, j);
            }
        }
    }

    public int getNearAmountBomb(int size, int x, int y) {
        if (matrix[x][y] == -1) {
            return -1;
        }

        int count = 0;

        if (isBomb(size, x + 1, y + 1)) count++;
        if (isBomb(size, x + 1, y - 1)) count++;
        if (isBomb(size, x + 1, y)) count++;
        if (isBomb(size, x, y + 1)) count++;
        if (isBomb(size, x, y - 1)) count++;
        if (isBomb(size, x - 1, y + 1)) count++;
        if (isBomb(size, x - 1, y - 1)) count++;
        if (isBomb(size, x - 1, y)) count++;

        return count;
    }

    public boolean isBomb(int size, int x, int y) {
        if (x >= 0 && y >= 0 && x < size && y < size) {
            if (matrix[x][y] == -1)
                return true;
        }
        return false;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getBombAmount() {
        return bombAmount;
    }
}
