package com.david.giczi.tetris.app.logic;

import java.util.ArrayList;
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
