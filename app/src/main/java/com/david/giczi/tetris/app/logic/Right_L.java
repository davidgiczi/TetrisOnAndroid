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

        if( isValidStepLeft() ){
            List<Integer> steppedShape = new ArrayList<>();
            for (Integer shapeCell : right_L) {
                steppedShape.add(shapeCell - 1);
            }
            List<Integer> deletedShapeCells = new ArrayList<>(right_L);
            right_L = new ArrayList<>(steppedShape);
            deletedShapeCells.removeAll(steppedShape);
            return deletedShapeCells;
        }
        return new ArrayList<>();
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
            int afterStepRowIndex = (right_L.get(0) - 1) / GameBoard.BOARD_COL;
            return  GameBoard.BOARD_COL - 1 >= afterStepRowIndex  &&
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
        if( isValidStepRight() ){
            List<Integer> steppedShape = new ArrayList<>();
            for (Integer shapeCell : right_L) {
                steppedShape.add(shapeCell + 1);
            }
            List<Integer> deletedShapeCells = new ArrayList<>(right_L);
            right_L = new ArrayList<>(steppedShape);
            deletedShapeCells.removeAll(steppedShape);
            return deletedShapeCells;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isValidStepRight() {
        if( position == ShapePosition.NORMAL ){
            int actualRowIndex = right_L.get(3) / GameBoard.BOARD_COL;
            int afterStepRowIndex = (right_L.get(3) + 1) / GameBoard.BOARD_COL;
            return actualRowIndex == afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(2) + 1) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(3) + 1);
        }
        else if( position == ShapePosition.RIGHT_ROTATED ){
            int afterStepRowIndex = (right_L.get(3) + 1) / GameBoard.BOARD_COL;
            return GameBoard.BOARD_COL - 1 >= afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(0) + 1) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(1) + 1) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(3) + 1);
        }
        else if( position == ShapePosition.UPSIDE_DOWN ){
            int actualRowIndex = right_L.get(0) / GameBoard.BOARD_COL;
            int afterStepRowIndex = (right_L.get(0) + 1) / GameBoard.BOARD_COL;
            return actualRowIndex == afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(0) + 1) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(3) + 1);
        }
        int actualRowIndex = right_L.get(0) / GameBoard.BOARD_COL;
        int afterStepRowIndex = (right_L.get(0) + 1) / GameBoard.BOARD_COL;
        return actualRowIndex == afterStepRowIndex &&
                !GameBoard.TETRIS_BOARD.get(right_L.get(1) + 1) &&
                !GameBoard.TETRIS_BOARD.get(right_L.get(2) + 1) &&
                !GameBoard.TETRIS_BOARD.get(right_L.get(3) + 1);
    }

    @Override
    public List<Integer> stepDown() {
        if( isValidStepDown() ){
            List<Integer> steppedShape = new ArrayList<>();
            for (Integer shapeCell : right_L) {
                steppedShape.add(shapeCell + GameBoard.BOARD_COL);
            }
            List<Integer> deletedShapeCells = new ArrayList<>(right_L);
            right_L = new ArrayList<>(steppedShape);
            deletedShapeCells.removeAll(steppedShape);
            return deletedShapeCells;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isValidStepDown() {
        if( position == ShapePosition.NORMAL ){
            int afterStepRowIndex = (right_L.get(0) + GameBoard.BOARD_COL) / GameBoard.BOARD_COL;
            return  GameBoard.BOARD_ROW - 1 >= afterStepRowIndex  &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(0) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(1) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(2) + GameBoard.BOARD_COL);
        }
        else if( position == ShapePosition.RIGHT_ROTATED ){
            int afterStepRowIndex = (right_L.get(3) + GameBoard.BOARD_COL) / GameBoard.BOARD_COL;
            return GameBoard.BOARD_ROW - 1 >= afterStepRowIndex  &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(2) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(3) + GameBoard.BOARD_COL);
        }
        else if( position == ShapePosition.UPSIDE_DOWN ){
          int afterStepRowIndex = (right_L.get(3) + GameBoard.BOARD_COL) / GameBoard.BOARD_COL;
            return  GameBoard.BOARD_ROW - 1 >= afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(0) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(1) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(3) + GameBoard.BOARD_COL);
        }
        int afterStepRowIndex = (right_L.get(0) + GameBoard.BOARD_COL) / GameBoard.BOARD_COL;
        return GameBoard.BOARD_ROW - 1 >= afterStepRowIndex &&
                !GameBoard.TETRIS_BOARD.get(right_L.get(0) + GameBoard.BOARD_COL) &&
                !GameBoard.TETRIS_BOARD.get(right_L.get(3) + GameBoard.BOARD_COL);
    }

    @Override
    public List<Integer> rotate() {
        if( isValidRotation() ){
            List<Integer> rotatedShape = new ArrayList<>();
            List<Integer> deletedShapeCells = new ArrayList<>(right_L);
            if( position == ShapePosition.NORMAL ){
                rotatedShape.add(right_L.get(1) - GameBoard.BOARD_COL);
                rotatedShape.add(right_L.get(1));
                rotatedShape.add(right_L.get(1) + GameBoard.BOARD_COL);
                rotatedShape.add(right_L.get(1) + GameBoard.BOARD_COL + 1);
                right_L = new ArrayList<>(rotatedShape);
                deletedShapeCells.removeAll(rotatedShape);
                position = ShapePosition.RIGHT_ROTATED;
            }
            else if( position == ShapePosition.RIGHT_ROTATED ){
                rotatedShape.add(right_L.get(1) + 1);
                rotatedShape.add(right_L.get(1));
                rotatedShape.add(right_L.get(1) - 1);
                rotatedShape.add(right_L.get(1) + GameBoard.BOARD_COL - 1);
                right_L = new ArrayList<>(rotatedShape);
                deletedShapeCells.removeAll(rotatedShape);
                position = ShapePosition.UPSIDE_DOWN;
            }
            else if( position == ShapePosition.UPSIDE_DOWN ){
                rotatedShape.add(right_L.get(1) + GameBoard.BOARD_COL);
                rotatedShape.add(right_L.get(1));
                rotatedShape.add(right_L.get(1) - GameBoard.BOARD_COL);
                rotatedShape.add(right_L.get(1) - GameBoard.BOARD_COL - 1);
                right_L = new ArrayList<>(rotatedShape);
                deletedShapeCells.removeAll(rotatedShape);
                position = ShapePosition.LEFT_ROTATED;
            }
            else if( position == ShapePosition.LEFT_ROTATED ){
                rotatedShape.add(right_L.get(1) - 1);
                rotatedShape.add(right_L.get(1));
                rotatedShape.add(right_L.get(1) + 1);
                rotatedShape.add(right_L.get(1) - GameBoard.BOARD_COL + 1);
                right_L = new ArrayList<>(rotatedShape);
                deletedShapeCells.removeAll(rotatedShape);
                position = ShapePosition.NORMAL;
            }
            return deletedShapeCells;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isValidRotation() {
        if( position == ShapePosition.NORMAL ){
            return right_L.get(1) - GameBoard.BOARD_COL > 0 &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(0) - GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(1) - GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(1) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(2) + GameBoard.BOARD_COL);
        }
        else if( position == ShapePosition.RIGHT_ROTATED ){
            int beforeRotationIndex = right_L.get(1) / GameBoard.BOARD_COL;
            int afterRotationIndex1 = (right_L.get(1) + 1) / GameBoard.BOARD_COL;
            int afterRotationIndex2 = (right_L.get(1) - 1) / GameBoard.BOARD_COL;
            return  beforeRotationIndex == afterRotationIndex1 &&
                    beforeRotationIndex == afterRotationIndex2 &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(0) + 1) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(1) + 1) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(1) - 1) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(2) - 1);
        }
        else if( position == ShapePosition.UPSIDE_DOWN ){
            return right_L.get(1) - GameBoard.BOARD_COL > 0 &&
                   GameBoard.BOARD_ROW - 1  >= (right_L.get(1) + GameBoard.BOARD_COL) / GameBoard.BOARD_COL &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(0) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(1) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(1) - GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(right_L.get(2) - GameBoard.BOARD_COL);
        }
        int beforeRotationIndex = right_L.get(1) / GameBoard.BOARD_COL;
        int afterRotationIndex1 = (right_L.get(1) - 1) / GameBoard.BOARD_COL;
        int afterRotationIndex2 = (right_L.get(1) + 1) / GameBoard.BOARD_COL;
        return beforeRotationIndex == afterRotationIndex1 &&
               beforeRotationIndex == afterRotationIndex2 &&
               !GameBoard.TETRIS_BOARD.get(right_L.get(0) - 1) &&
               !GameBoard.TETRIS_BOARD.get(right_L.get(1) - 1) &&
               !GameBoard.TETRIS_BOARD.get(right_L.get(1) + 1) &&
               !GameBoard.TETRIS_BOARD.get(right_L.get(2) + 1);
    }


}
