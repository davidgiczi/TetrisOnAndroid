package com.david.giczi.tetris.app.logic;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    public static List<Boolean> TETRIS_BOARD;
    public static final int BOARD_ROW = 20;
    public static final int BOARD_COL = 10;

    public GameBoard() {
        initBoard();
    }

    public void initBoard(){
        TETRIS_BOARD = new ArrayList<>();
        for (int i = 0; i < BOARD_ROW * BOARD_COL; i++) {
            TETRIS_BOARD.add(Boolean.FALSE);
        }
    }

    public void addShape(Shape shape){
        for (Integer shapeCell : shape.getShape()) {
            TETRIS_BOARD.set(shapeCell, Boolean.TRUE);
        }
    }

    public void displayTetrisBoard(){
        for (int i = 0; i < BOARD_ROW; i++) {
            for (int j = 0; j < BOARD_COL; j++) {
                if( TETRIS_BOARD.get(i * BOARD_COL + j) ){
                    System.out.print("1 ");
                }
                else{
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

}
