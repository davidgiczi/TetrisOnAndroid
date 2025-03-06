package com.david.giczi.tetris.app.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stick implements Shape {

    private List<Integer> stick;
    private final int colorCode;
    private int location;
    private ShapePosition position;

    public Stick() {
        this.stick = new ArrayList<>();
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
        return stick;
    }

    @Override
    public void create() {
        this.location = (int) (Math.random() * 7);
        switch (location) {
            case 1:
                stick = Arrays.asList(1, 2, 3, 4);
                break;
            case 2:
                stick = Arrays.asList(2, 3, 4, 5);
                break;
            case 3:
                stick = Arrays.asList(3, 4, 5, 6);
                break;
            case 4:
                stick = Arrays.asList(4, 5, 6, 7);
                break;
            case 5:
                stick = Arrays.asList(5, 6, 7, 8);
                break;
            case 6:
                stick = Arrays.asList(6, 7, 8, 9);
                break;
            default:
                stick = Arrays.asList(0, 1, 2, 3);
        }
    }

    @Override
    public List<Integer> stepLeft() {
        if (isValidStepLeft()) {
            List<Integer> steppedShape = new ArrayList<>();
            for (Integer shapeCell : stick) {
                steppedShape.add(shapeCell - 1);
            }
            List<Integer> deletedShapeCells = new ArrayList<>(stick);
            stick = new ArrayList<>(steppedShape);
            deletedShapeCells.removeAll(steppedShape);
            return deletedShapeCells;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isValidStepLeft() {
        int actualRowIndex = stick.get(0) / GameBoard.BOARD_COL;
        int afterStepRowIndex = (stick.get(0) - 1) / GameBoard.BOARD_COL;
        if (position == ShapePosition.NORMAL) {
            return 1 <= stick.get(0) && actualRowIndex == afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(stick.get(0) - 1);
        }
        return 1 <= stick.get(0) && actualRowIndex == afterStepRowIndex &&
                !GameBoard.TETRIS_BOARD.get(stick.get(0) - 1) &&
                !GameBoard.TETRIS_BOARD.get(stick.get(1) - 1) &&
                !GameBoard.TETRIS_BOARD.get(stick.get(2) - 1) &&
                !GameBoard.TETRIS_BOARD.get(stick.get(3) - 1);
    }

    @Override
    public List<Integer> stepRight() {
        if (isValidStepRight()) {
            List<Integer> steppedShape = new ArrayList<>();
            for (Integer shapeCell : stick) {
                steppedShape.add(shapeCell + 1);
            }
            List<Integer> deletedShapeCells = new ArrayList<>(stick);
            stick = new ArrayList<>(steppedShape);
            deletedShapeCells.removeAll(steppedShape);
            return deletedShapeCells;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isValidStepRight() {
        int actualRowIndex = stick.get(3) / GameBoard.BOARD_COL;
        int afterStepRowIndex = (stick.get(3) + 1) / GameBoard.BOARD_COL;
        if (position == ShapePosition.NORMAL) {
            return actualRowIndex == afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(stick.get(3) + 1);
        }
        return actualRowIndex == afterStepRowIndex &&
                !GameBoard.TETRIS_BOARD.get(stick.get(0) + 1) &&
                !GameBoard.TETRIS_BOARD.get(stick.get(1) + 1) &&
                !GameBoard.TETRIS_BOARD.get(stick.get(2) + 1) &&
                !GameBoard.TETRIS_BOARD.get(stick.get(3) + 1);
    }

    @Override
    public List<Integer> stepDown() {
        if (isValidStepDown()) {
            List<Integer> steppedShape = new ArrayList<>();
            for (Integer shapeCell : stick) {
                steppedShape.add(shapeCell + GameBoard.BOARD_COL);
            }
            List<Integer> deletedShapeCells = new ArrayList<>(stick);
            stick = new ArrayList<>(steppedShape);
            deletedShapeCells.removeAll(steppedShape);
            return deletedShapeCells;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isValidStepDown() {
        int afterStepRowIndex = (stick.get(3) + GameBoard.BOARD_COL) / GameBoard.BOARD_COL;
        if (position == ShapePosition.NORMAL) {
            return GameBoard.BOARD_ROW - 1 >= afterStepRowIndex &&
                    !GameBoard.TETRIS_BOARD.get(stick.get(0) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(stick.get(1) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(stick.get(2) + GameBoard.BOARD_COL) &&
                    !GameBoard.TETRIS_BOARD.get(stick.get(3) + GameBoard.BOARD_COL);
        }
        return GameBoard.BOARD_ROW - 1 >= afterStepRowIndex &&
                !GameBoard.TETRIS_BOARD.get(stick.get(3) + GameBoard.BOARD_COL);
    }

    @Override
    public List<Integer> rotate() {
        if( isValidRotation() ){
            List<Integer> rotatedShape = new ArrayList<>();
            List<Integer> deletedShapeCells = new ArrayList<>(stick);
            if( position == ShapePosition.NORMAL ) {
                rotatedShape.add(stick.get(0));
                rotatedShape.add(stick.get(0) + GameBoard.BOARD_COL);
                rotatedShape.add(stick.get(0) + 2 * GameBoard.BOARD_COL);
                rotatedShape.add(stick.get(0) + 3 * GameBoard.BOARD_COL);
                stick = new ArrayList<>(rotatedShape);
                deletedShapeCells.removeAll(rotatedShape);
                position = ShapePosition.LEFT_ROTATED;
            }
            else {
                rotatedShape.add(stick.get(0));
                rotatedShape.add(stick.get(0) + 1);
                rotatedShape.add(stick.get(0) + 2);
                rotatedShape.add(stick.get(0) + 3);
                stick = new ArrayList<>(rotatedShape);
                deletedShapeCells.removeAll(rotatedShape);
                position = ShapePosition.NORMAL;
            }
            return deletedShapeCells;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isValidRotation() {
        int afterRotationValue1;
        int afterRotationValue2;
        if (position == ShapePosition.NORMAL) {
            afterRotationValue1 = stick.get(0) + GameBoard.BOARD_COL;
            afterRotationValue2 = stick.get(0) + 2 * GameBoard.BOARD_COL;
            int afterRotationIndex = (stick.get(0) + 3 * GameBoard.BOARD_COL) / GameBoard.BOARD_COL;
            return GameBoard.BOARD_ROW - 1 >= afterRotationIndex &&
                    !GameBoard.TETRIS_BOARD.get(afterRotationValue1) &&
                    !GameBoard.TETRIS_BOARD.get(afterRotationValue2) &&
                    !GameBoard.TETRIS_BOARD.get(stick.get(0) + 3 * GameBoard.BOARD_COL);
        }
        afterRotationValue1 = stick.get(0) + 1;
        afterRotationValue2 = stick.get(0) + 2;
        int afterRotationValue3 = stick.get(0) + 3;
        int beforeRotationIndex = stick.get(0) / GameBoard.BOARD_COL;
        int afterRotationIndex = afterRotationValue3 / GameBoard.BOARD_COL;
        return beforeRotationIndex == afterRotationIndex &&
                !GameBoard.TETRIS_BOARD.get(afterRotationValue1) &&
                !GameBoard.TETRIS_BOARD.get(afterRotationValue2) &&
                !GameBoard.TETRIS_BOARD.get(afterRotationValue3);
    }
}