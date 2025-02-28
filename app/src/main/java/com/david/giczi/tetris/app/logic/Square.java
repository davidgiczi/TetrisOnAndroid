package com.david.giczi.tetris.app.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Square implements Shape {

    private List<Integer> square;
    private final int colorCode;
    private int location;
    private final ShapePosition position;

    public Square() {
        this.square = new ArrayList<>();
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
        return square;
    }

    @Override
    public void create() {
        this.location = (int) (Math.random() * 9);
        switch (location){
            case 1:
                square = Arrays.asList(1, 2, 11, 12);
                break;
            case 2:
                square = Arrays.asList(2, 3, 12, 13);
                break;
            case 3:
                square = Arrays.asList(3, 4, 13, 14);
                break;
            case 4:
                square = Arrays.asList(4, 5, 14, 15);
                break;
            case 5:
                square = Arrays.asList(5, 6, 15, 16);
                break;
            case 6:
                square = Arrays.asList(6, 7, 16, 17);
                break;
            case 7:
                square = Arrays.asList(7, 8, 17, 18);
                break;
            case 8:
                square = Arrays.asList(8, 9, 18, 19);
                break;
            default:
                square = Arrays.asList(0, 1, 10, 11);
        }
    }

    @Override
    public List<Integer> stepLeft() {
        if( isValidStepLeft() ){
            List<Integer> steppedShape = new ArrayList<>();
            for (Integer shapeCell : square) {
                steppedShape.add(shapeCell - 1);
            }
            List<Integer> deletedShapeCells = new ArrayList<>(square);
            square = new ArrayList<>(steppedShape);
            deletedShapeCells.removeAll(steppedShape);
            return deletedShapeCells;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isValidStepLeft() {
        int actualRowIndex = square.get(0) / GameBoard.BOARD_COL;
        int afterStepRowIndex = (square.get(0) - 1) / GameBoard.BOARD_COL;
        return 1 <= square.get(0) && actualRowIndex == afterStepRowIndex &&
                !GameBoard.TETRIS_BOARD.get(square.get(0) - 1) &&
                !GameBoard.TETRIS_BOARD.get(square.get(2) - 1);
    }

    @Override
    public List<Integer> stepRight() {
        if( isValidStepRight() ){
            List<Integer> steppedShape = new ArrayList<>();
            for (Integer shapeCell : square) {
                steppedShape.add(shapeCell + 1);
            }
            List<Integer> deletedShapeCells = new ArrayList<>(square);
            square = new ArrayList<>(steppedShape);
            deletedShapeCells.removeAll(steppedShape);
            return deletedShapeCells;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isValidStepRight() {
        int actualRowIndex = square.get(1) / GameBoard.BOARD_COL;
        int afterStepRowIndex = (square.get(1) + 1) / GameBoard.BOARD_COL;
        return actualRowIndex == afterStepRowIndex &&
                !GameBoard.TETRIS_BOARD.get(square.get(1) + 1) &&
                !GameBoard.TETRIS_BOARD.get(square.get(3) + 1);
    }

    @Override
    public List<Integer> stepDown() {
        if( isValidStepDown() ){
            List<Integer> steppedShape = new ArrayList<>();
            for (Integer shapeCell : square) {
                steppedShape.add(shapeCell + GameBoard.BOARD_COL);
            }
            List<Integer> deletedShapeCells = new ArrayList<>(square);
            square = new ArrayList<>(steppedShape);
            deletedShapeCells.removeAll(steppedShape);
            return deletedShapeCells;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isValidStepDown() {
        int afterStepRowIndex = (square.get(2) + GameBoard.BOARD_COL) / GameBoard.BOARD_COL;
        return GameBoard.BOARD_ROW - 1 >= afterStepRowIndex &&
                !GameBoard.TETRIS_BOARD.get(square.get(2) + GameBoard.BOARD_COL) &&
                !GameBoard.TETRIS_BOARD.get(square.get(3) + GameBoard.BOARD_COL);
    }

    @Override
    public List<Integer> rotate() {
        return new ArrayList<>();
    }

    @Override
    public boolean isValidRotation() {return true;}


}
