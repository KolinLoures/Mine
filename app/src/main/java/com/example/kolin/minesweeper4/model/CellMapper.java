package com.example.kolin.minesweeper4.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 31.10.2016.
 */

public class CellMapper {

    public static List<Cell> convertArrayToListCell(int[][] matrix, final int size) {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                Cell cell = new Cell();
                cell.setValue(matrix[i][j]);
                cell.setPosition(i, j, size);
                cells.add(cell);
            }
        }
        return cells;
    }

    public static Cell[][] convertArrayToArrayCell(int[][] matrix, final int size) {
        Cell[][] cells = new Cell[size][size];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                Cell cell = new Cell();
                cell.setValue(matrix[i][j]);
                cell.setPosition(i, j, size);
                cells[i][j] = cell;
            }
        }
        return cells;
    }

    public static List<Cell> convertCellArrayToListCell(Cell[][] cells, final int size) {
        List<Cell> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <size; j++) {
                list.add(cells[i][j]);
            }
        }
        return list;
    }
}
