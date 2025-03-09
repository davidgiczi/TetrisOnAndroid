package com.david.giczi.tetris.app.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Right_L implements Shape {

    private List<Integer> right_L;
    private final int colorCode;
    private int location;
    private ShapePosition position;

    public Right_L() {
        this.right_L = new ArrayList<>();
        create();
        this.colorCode = (int) (Math.random() * 6);
        this.position = ShapePosition.NORMAL;
    }

    @Override
    public int getLocation() {
        return location;
    }

    @Override
    public int getColorCode() {
        return colorCode;
    }

    @Override
    public ShapePosition getPosition() {
        return position;
    }

    @Override
    public List<Integer> getShape() {
        return right_L;
    }

    @Override
    public void create() {
        this.location = (int) (Math.random() * 8 + 10);
        switch (location){
            case 11:
                right_L = Arrays.asList(11, 12, 13, 3);
                return;
            case 12:
                right_L = Arrays.asList(12, 13, 14, 4);
                break;
            case 13:
                right_L = Arrays.asList(13, 14, 15, 5);
                break;
            case 14:
                right_L = Arrays.asList(14, 15, 16, 6);
                break;
            case 15:
                right_L = Arrays.asList(15, 16, 17, 7);
                break;
            case 16:
                right_L = Arrays.asList(16, 17, 18, 8);
                break;
            case 17:
                right_L = Arrays.asList(17, 18, 19, 9);
                break;
            default:
                right_L = Arrays.asList(10, 11, 12, 2);
        }
    }

    @Override
    public List<Integer> stepLeft() {
        return null;
    }

    @Override
    public boolean isValidStepLeft() {
        if( position == ShapePosition.NORMAL ){
            int actualRowIndex = right_L.get(0) / GameBoard.BOARD_COL;
            int afterStepRowIndex = (right_L.get(0) - 1) / GameBoard.BOARD_COL;
            return actualRowIndex == afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(0) - 1) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(3) - 1);
        }
        else if( position == ShapePosition.RIGHT_ROTATED ){
            int actualRowIndex = right_L.get(0) / GameBoard.BOARD_COL;
            int afterStepRowIndex = (right_L.get(0) - 1) / GameBoard.BOARD_COL;
            return actualRowIndex == afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(0) - 1) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(1) - 1) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(2) - 1);
        }
        else if( position == ShapePosition.UPSIDE_DOWN ){
            int actualRowIndex = right_L.get(2) / GameBoard.BOARD_COL;
            int afterStepRowIndex = (right_L.get(2) - 1) / GameBoard.BOARD_COL;
            return actualRowIndex == afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(2) - 1) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(3) - 1);
        }
        int actualRowIndex = right_L.get(3) / GameBoard.BOARD_COL;
        int afterStepRowIndex = (right_L.get(3) - 1) / GameBoard.BOARD_COL;
        return actualRowIndex == afterStepRowIndex &&
                !GameBoard.TETRIS_BOARD.get(right_L.get(0) - 1) &&
                !GameBoard.TETRIS_BOARD.get(right_L.get(1) - 1) &&
                !GameBoard.TETRIS_BOARD.get(right_L.get(3) - 1);
    }

    @Override
    public List<Integer> stepRight() {
        return null;
    }

    @Override
    public boolean isValidStepRight() {
        return false;
    }

    @Override
    public List<Integer> stepDown() {
        return null;
    }

    @Override
    public boolean isValidStepDown() {
        return false;
    }

    @Override
    public List<Integer> rotate() {
        return null;
    }

    @Override
    public boolean isValidRotation() {
        return false;
    }


}
