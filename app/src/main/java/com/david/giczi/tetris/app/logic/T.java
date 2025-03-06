package com.david.giczi.tetris.app.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class T implements Shape {

    private List<Integer> T;
    private final int colorCode;
    private int location;
    private ShapePosition position;

    public T() {
        this.T = new ArrayList<>();
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
        return T;
    }

    @Override
    public void create() {
        this.location = (int) (Math.random() * 8);
        switch (location){
            case 1:
                T = Arrays.asList(1, 2, 3, 12);
                return;
            case 2:
               T = Arrays.asList(2, 3, 4, 13);
                break;
            case 3:
                T = Arrays.asList(3, 4, 5, 14);
                break;
            case 4:
                T = Arrays.asList(4, 5, 6, 15);
                break;
            case 5:
                T = Arrays.asList(5, 6, 7, 16);
                break;
            case 6:
                T = Arrays.asList(6, 7, 8, 17);
                break;
            case 7:
                T = Arrays.asList(7, 8, 9, 18);
                break;
            default:
                T = Arrays.asList(0, 1, 2, 11);
        }
    }

    @Override
    public List<Integer> stepLeft() {
        if( isValidStepLeft() ){
           List<Integer> steppedShape = new ArrayList<>();
           for (Integer shapeCell : T) {
                 steppedShape.add(shapeCell - 1);
                }
            List<Integer> deletedShapeCells = new ArrayList<>(T);
            T = new ArrayList<>(steppedShape);
            deletedShapeCells.removeAll(steppedShape);
            return deletedShapeCells;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isValidStepLeft() {
        int actualRowIndex = T.get(0) / GameBoard.BOARD_COL;
        int afterStepRowIndex = (T.get(0) - 1) / GameBoard.BOARD_COL;
        if( position == ShapePosition.LEFT_ROTATED ){
            return 1 <= T.get(0) && actualRowIndex == afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(T.get(0) - 1) &&
                    !GameBoard.TETRIS_BOARD.get(T.get(1) - 1) &&
                    !GameBoard.TETRIS_BOARD.get(T.get(2) - 1);
        }
       else if( position == ShapePosition.RIGHT_ROTATED ){
            actualRowIndex = T.get(3) / GameBoard.BOARD_COL;
            afterStepRowIndex = (T.get(3) - 1) / GameBoard.BOARD_COL;
            return 1 <= T.get(3) && actualRowIndex == afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(T.get(0) - 1) &&
                    !GameBoard.TETRIS_BOARD.get(T.get(2) - 1) &&
                    !GameBoard.TETRIS_BOARD.get(T.get(3) - 1);
        }
        else if( position == ShapePosition.UPSIDE_DOWN ){
            actualRowIndex = T.get(2) / GameBoard.BOARD_COL;
            afterStepRowIndex = (T.get(2) - 1) / GameBoard.BOARD_COL;
          return  1 <= T.get(2) && actualRowIndex == afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(T.get(2) - 1) &&
                    !GameBoard.TETRIS_BOARD.get(T.get(3) - 1);
        }
        return 1 <= T.get(0) && actualRowIndex == afterStepRowIndex &&
                !GameBoard.TETRIS_BOARD.get(T.get(0) - 1) &&
                !GameBoard.TETRIS_BOARD.get(T.get(3) - 1);
    }

    @Override
    public List<Integer> stepRight() {
        if( isValidStepRight() ){
            List<Integer> steppedShape = new ArrayList<>();
            for (Integer shapeCell : T) {
                steppedShape.add(shapeCell + 1);
            }
            List<Integer> deletedShapeCells = new ArrayList<>(T);
            T = new ArrayList<>(steppedShape);
            deletedShapeCells.removeAll(steppedShape);
            return deletedShapeCells;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isValidStepRight() {
        int actualRowIndex = T.get(0) / GameBoard.BOARD_COL;
        int afterStepRowIndex = (T.get(0) + 1) / GameBoard.BOARD_COL;
        if( position == ShapePosition.RIGHT_ROTATED ){
            return actualRowIndex == afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(T.get(0) + 1) &&
                    !GameBoard.TETRIS_BOARD.get(T.get(1) + 1) &&
                    !GameBoard.TETRIS_BOARD.get(T.get(2) + 1);
        }
       else if( position == ShapePosition.NORMAL ){
            actualRowIndex = T.get(2) / GameBoard.BOARD_COL;
            afterStepRowIndex = (T.get(2) + 1) / GameBoard.BOARD_COL;
            return  actualRowIndex == afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(T.get(2) + 1) &&
                    !GameBoard.TETRIS_BOARD.get(T.get(3) + 1);
        }
        else if( position == ShapePosition.LEFT_ROTATED ){
            actualRowIndex = T.get(3) / GameBoard.BOARD_COL;
            afterStepRowIndex = (T.get(3) + 1) / GameBoard.BOARD_COL;
            return  actualRowIndex == afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(T.get(0) + 1) &&
                    !GameBoard.TETRIS_BOARD.get(T.get(2) + 1) &&
                    !GameBoard.TETRIS_BOARD.get(T.get(3) + 1);
        }
        return actualRowIndex == afterStepRowIndex &&
                !GameBoard.TETRIS_BOARD.get(T.get(0) + 1) &&
                !GameBoard.TETRIS_BOARD.get(T.get(3) + 1);
    }

    @Override
    public List<Integer> stepDown() {
        if( isValidStepDown() ){
            List<Integer> steppedShape = new ArrayList<>();
            for (Integer shapeCell : T) {
                steppedShape.add(shapeCell + GameBoard.BOARD_COL);
            }
            List<Integer> deletedShapeCells = new ArrayList<>(T);
            T = new ArrayList<>(steppedShape);
            deletedShapeCells.removeAll(steppedShape);
            return deletedShapeCells;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isValidStepDown() {
        int afterStepRowIndex = (T.get(2) + GameBoard.BOARD_COL) / GameBoard.BOARD_COL;
        if( position == ShapePosition.RIGHT_ROTATED ){
            return GameBoard.BOARD_ROW - 1 >= afterStepRowIndex  &&
                    !GameBoard.TETRIS_BOARD.get(T.get(2) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(T.get(3) + GameBoard.BOARD_COL);
        }
        else if( position == ShapePosition.NORMAL ){
            afterStepRowIndex = (T.get(3) + GameBoard.BOARD_COL) / GameBoard.BOARD_COL;
            return  GameBoard.BOARD_ROW - 1 >= afterStepRowIndex  &&
                    !GameBoard.TETRIS_BOARD.get(T.get(0) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(T.get(2) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(T.get(3) + GameBoard.BOARD_COL);
        }
        else if( position == ShapePosition.LEFT_ROTATED ){
            afterStepRowIndex = (T.get(0) + GameBoard.BOARD_COL) / GameBoard.BOARD_COL;
            return  GameBoard.BOARD_ROW - 1 >= afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(T.get(0) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(T.get(1) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(T.get(2) + GameBoard.BOARD_COL);
        }
        return GameBoard.BOARD_ROW - 1 >= afterStepRowIndex &&
                !GameBoard.TETRIS_BOARD.get(T.get(0) + 1) &&
                !GameBoard.TETRIS_BOARD.get(T.get(3) + 1);
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
