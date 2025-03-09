package com.david.giczi.tetris.app.logic;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    public static List<Boolean> TETRIS_BOARD;
    public static final int BOARD_ROW = 20;
    public static final int BOARD_COL = 10;
    private final String RED = "\u001B[31m";
    private final String DEFAULT = "\u001B[0m";

    public GameBoard() {
        initBoard();
    }

    public void initBoard(){
        TETRIS_BOARD = new ArrayList<>();
        for (int i = 0; i < BOARD_ROW * BOARD_COL; i++) {
            TETRIS_BOARD.add(Boolean.FALSE);
        }
    }

    public void addShape(List<Integer> shapeCells, List<Integer> deletedCells){
        for (Integer shapeCell : shapeCells) {
            TETRIS_BOARD.set(shapeCell, Boolean.TRUE);
        }
        if( deletedCells == null ){
            return;
        }
        for (Integer shapeCell : deletedCells) {
            TETRIS_BOARD.set(shapeCell, Boolean.FALSE);
        }

    }

    public void displayTetrisBoard(){
        for (int i = 0; i < BOARD_ROW; i++) {
            for (int j = 0; j < BOARD_COL; j++) {
                if( TETRIS_BOARD.get(i * BOARD_COL + j) ){
                    System.out.print(RED + "1 ");
                }
                else{
                    System.out.print(DEFAULT + "0 ");
                }
            }
            System.out.println();
        }
    }



}
