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
        return null;
    }

    @Override
    public List<Integer> stepRight() {
        return null;
    }

    @Override
    public List<Integer> stepDown() {
        return null;
    }

    @Override
    public List<Integer> rotate() {
        return new ArrayList<>();
    }


}
