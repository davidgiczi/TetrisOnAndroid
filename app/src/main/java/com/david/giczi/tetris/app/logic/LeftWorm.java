package com.david.giczi.tetris.app.logic;

import java.util.ArrayList;
import java.util.List;

class LeftWorm implements Shape {

    private List<Integer> leftWorm;
    private final int colorCode;
    private int location;
    private ShapePosition position;


    public LeftWorm() {
        this.leftWorm = new ArrayList<>();
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
        return leftWorm;
    }

    @Override
    public void create() {

    }

    @Override
    public List<Integer> stepLeft() {
        return null;
    }

    @Override
    public boolean isValidStepLeft() {
        return false;
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
