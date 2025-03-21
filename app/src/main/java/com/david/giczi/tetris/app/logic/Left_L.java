package com.david.giczi.tetris.app.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Left_L implements Shape {

    private List<Integer> left_L;
    private final int colorCode;
    private int location;
    private ShapePosition position;

    public Left_L() {
        this.left_L = new ArrayList<>();
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
        return left_L;
    }

    @Override
    public void create() {
        this.location = (int) (Math.random() * 8);
        switch (location){
            case 1:
                left_L = Arrays.asList(1, 2, 3, 13);
                break;
            case 2:
                left_L = Arrays.asList(2, 3, 4, 14);
                break;
            case 3:
                left_L = Arrays.asList(3, 4, 5, 15);
                break;
            case 4:
                left_L = Arrays.asList(4, 5, 6, 16);
                break;
            case 5:
                left_L = Arrays.asList(5, 6, 7, 17);
                break;
            case 6:
                left_L = Arrays.asList(6, 7, 8, 18);
                break;
            case 7:
                left_L = Arrays.asList(7, 8, 9, 19);
                break;
            default:
                left_L = Arrays.asList(0, 1, 2, 12);
        }
    }

    @Override
    public List<Integer> stepLeft() {

        if( isValidStepLeft() ){
            List<Integer> steppedShape = new ArrayList<>();
            for (Integer shapeCell : left_L) {
                steppedShape.add(shapeCell - 1);
            }
            List<Integer> deletedShapeCells = new ArrayList<>(left_L);
            left_L = new ArrayList<>(steppedShape);
            deletedShapeCells.removeAll(steppedShape);
            return deletedShapeCells;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isValidStepLeft() {

        if( position == ShapePosition.NORMAL ){
            int actualRowIndex = left_L.get(0) / GameBoard.BOARD_COL;
            int afterStepRowIndex = (left_L.get(0) - 1) / GameBoard.BOARD_COL;
            return 1 <= left_L.get(0) && actualRowIndex == afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(0) - 1) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(3) - 1);
        }
        else if( position == ShapePosition.RIGHT_ROTATED ){
            int actualRowIndex = left_L.get(3) / GameBoard.BOARD_COL;
            int afterStepRowIndex = (left_L.get(3) - 1) / GameBoard.BOARD_COL;
            return 1 <= left_L.get(3) && actualRowIndex == afterStepRowIndex  &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(0) - 1) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(1) - 1) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(3) - 1);
        }
        else if( position == ShapePosition.UPSIDE_DOWN ){
            int actualRowIndex = left_L.get(2) / GameBoard.BOARD_COL;
            int afterStepRowIndex = (left_L.get(2) - 1) / GameBoard.BOARD_COL;
            return 1 <= left_L.get(2) && actualRowIndex == afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(2) - 1) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(3) - 1);
        }
        int actualRowIndex = left_L.get(0) / GameBoard.BOARD_COL;
        int afterStepRowIndex = (left_L.get(0) - 1) / GameBoard.BOARD_COL;
        return 1 <= left_L.get(0) && actualRowIndex == afterStepRowIndex &&
                !GameBoard.TETRIS_BOARD.get(left_L.get(0) - 1) &&
                !GameBoard.TETRIS_BOARD.get(left_L.get(1) - 1) &&
                !GameBoard.TETRIS_BOARD.get(left_L.get(2) - 1);

    }

    @Override
    public List<Integer> stepRight() {
        if( isValidStepRight() ){
            List<Integer> steppedShape = new ArrayList<>();
            for (Integer shapeCell : left_L) {
                steppedShape.add(shapeCell + 1);
            }
            List<Integer> deletedShapeCells = new ArrayList<>(left_L);
            left_L = new ArrayList<>(steppedShape);
            deletedShapeCells.removeAll(steppedShape);
            return deletedShapeCells;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isValidStepRight() {

        if( position == ShapePosition.NORMAL ){
            int actualRowIndex = left_L.get(3) / GameBoard.BOARD_COL;
            int afterStepRowIndex = (left_L.get(3) + 1) / GameBoard.BOARD_COL;
            return actualRowIndex == afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(2) + 1) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(3) + 1);
        }
        else if( position == ShapePosition.RIGHT_ROTATED ){
            int actualRowIndex = left_L.get(0) / GameBoard.BOARD_COL;
            int afterStepRowIndex = (left_L.get(0) + 1) / GameBoard.BOARD_COL;
            return actualRowIndex == afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(0) + 1) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(1) + 1) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(2) + 1);
        }
        else if( position == ShapePosition.UPSIDE_DOWN ){
            int actualRowIndex = left_L.get(0) / GameBoard.BOARD_COL;
            int afterStepRowIndex = (left_L.get(0) + 1) / GameBoard.BOARD_COL;
            return actualRowIndex == afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(0) + 1) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(3) + 1);
        }
        int actualRowIndex = left_L.get(3) / GameBoard.BOARD_COL;
        int afterStepRowIndex = (left_L.get(3) + 1) / GameBoard.BOARD_COL;
        return actualRowIndex == afterStepRowIndex &&
                !GameBoard.TETRIS_BOARD.get(left_L.get(0) + 1) &&
                !GameBoard.TETRIS_BOARD.get(left_L.get(1) + 1) &&
                !GameBoard.TETRIS_BOARD.get(left_L.get(3) + 1);
    }

    @Override
    public List<Integer> stepDown() {
        if( isValidStepDown() ){
            List<Integer> steppedShape = new ArrayList<>();
            for (Integer shapeCell : left_L) {
                steppedShape.add(shapeCell + GameBoard.BOARD_COL);
            }
            List<Integer> deletedShapeCells = new ArrayList<>(left_L);
            left_L = new ArrayList<>(steppedShape);
            deletedShapeCells.removeAll(steppedShape);
            return deletedShapeCells;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isValidStepDown() {

        if( position == ShapePosition.NORMAL ){
            int afterStepRowIndex = (left_L.get(3) + GameBoard.BOARD_COL) / GameBoard.BOARD_COL;
            return  GameBoard.BOARD_ROW - 1 >= afterStepRowIndex  &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(0) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(1) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(3) + GameBoard.BOARD_COL);
        }
        else if( position == ShapePosition.RIGHT_ROTATED ){
            int afterStepRowIndex = (left_L.get(3) + GameBoard.BOARD_COL) / GameBoard.BOARD_COL;
            return GameBoard.BOARD_ROW - 1 >= afterStepRowIndex  &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(2) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(3) + GameBoard.BOARD_COL);
        }
        else if( position == ShapePosition.UPSIDE_DOWN ){
            int afterStepRowIndex = (left_L.get(0) + GameBoard.BOARD_COL) / GameBoard.BOARD_COL;
            return  GameBoard.BOARD_ROW - 1 >= afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(0) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(1) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(2) + GameBoard.BOARD_COL);
        }
        int afterStepRowIndex = (left_L.get(0) + GameBoard.BOARD_COL) / GameBoard.BOARD_COL;
        return GameBoard.BOARD_ROW - 1 >= afterStepRowIndex &&
                !GameBoard.TETRIS_BOARD.get(left_L.get(0) + GameBoard.BOARD_COL) &&
                !GameBoard.TETRIS_BOARD.get(left_L.get(3) + GameBoard.BOARD_COL);
    }

    @Override
    public List<Integer> rotate() {
        if( isValidRotation() ){
            List<Integer> rotatedShape = new ArrayList<>();
            List<Integer> deletedShapeCells = new ArrayList<>(left_L);
            if( position == ShapePosition.NORMAL ){
                rotatedShape.add(left_L.get(1) - GameBoard.BOARD_COL);
                rotatedShape.add(left_L.get(1));
                rotatedShape.add(left_L.get(1) + GameBoard.BOARD_COL);
                rotatedShape.add(left_L.get(1) + GameBoard.BOARD_COL - 1);
                left_L = new ArrayList<>(rotatedShape);
                deletedShapeCells.removeAll(rotatedShape);
                position = ShapePosition.RIGHT_ROTATED;
            }
            else if( position == ShapePosition.RIGHT_ROTATED ){
                rotatedShape.add(left_L.get(1) + 1);
                rotatedShape.add(left_L.get(1));
                rotatedShape.add(left_L.get(1) - 1);
                rotatedShape.add(left_L.get(1) - GameBoard.BOARD_COL - 1);
                left_L = new ArrayList<>(rotatedShape);
                deletedShapeCells.removeAll(rotatedShape);
                position = ShapePosition.UPSIDE_DOWN;
            }
            else if( position == ShapePosition.UPSIDE_DOWN ){
                rotatedShape.add(left_L.get(1) + GameBoard.BOARD_COL);
                rotatedShape.add(left_L.get(1));
                rotatedShape.add(left_L.get(1) - GameBoard.BOARD_COL);
                rotatedShape.add(left_L.get(1) - GameBoard.BOARD_COL + 1);
                left_L = new ArrayList<>(rotatedShape);
                deletedShapeCells.removeAll(rotatedShape);
                position = ShapePosition.LEFT_ROTATED;
            }
            else if( position == ShapePosition.LEFT_ROTATED ){
                rotatedShape.add(left_L.get(1) - 1);
                rotatedShape.add(left_L.get(1));
                rotatedShape.add(left_L.get(1) + 1);
                rotatedShape.add(left_L.get(1) + GameBoard.BOARD_COL + 1);
                left_L = new ArrayList<>(rotatedShape);
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
            return left_L.get(1) - GameBoard.BOARD_COL > 0 &&
                    GameBoard.BOARD_ROW - 1 >= (left_L.get(1) + GameBoard.BOARD_COL) / GameBoard.BOARD_COL &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(0) - GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(1) - GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(0) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(1) + GameBoard.BOARD_COL);
        }
        else if( position == ShapePosition.RIGHT_ROTATED ){
            int beforeRotationIndex = left_L.get(1) / GameBoard.BOARD_COL;
            int afterRotationIndex = (left_L.get(1) + 1) / GameBoard.BOARD_COL;
            return beforeRotationIndex == afterRotationIndex &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(0) + 1) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(0) - 1) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(1) + 1) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(1) - 1);
        }
        else if( position == ShapePosition.UPSIDE_DOWN ){
            int afterRotationIndex = (left_L.get(1) + GameBoard.BOARD_COL) / GameBoard.BOARD_COL;
            return GameBoard.BOARD_ROW - 1  >= afterRotationIndex &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(0) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(2) - 1) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(1) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(left_L.get(1) - GameBoard.BOARD_COL);
        }
        int beforeRotationIndex = left_L.get(1) / GameBoard.BOARD_COL;
        int afterRotationIndex = (left_L.get(1) - 1) / GameBoard.BOARD_COL;
        return beforeRotationIndex >= 1 &&  beforeRotationIndex == afterRotationIndex &&
                !GameBoard.TETRIS_BOARD.get(left_L.get(0) - 1) &&
                !GameBoard.TETRIS_BOARD.get(left_L.get(1) - 1) &&
                !GameBoard.TETRIS_BOARD.get(left_L.get(1) + 1) &&
                !GameBoard.TETRIS_BOARD.get(left_L.get(3) + 1);
    }

}
