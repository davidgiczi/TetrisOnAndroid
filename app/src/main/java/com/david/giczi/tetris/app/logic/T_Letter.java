package com.david.giczi.tetris.app.logic;

import java.util.List;

class T_Letter implements Shape {

    private List<Integer> letter_T;
    private int colorCode;
    private int location;

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
        return letter_T;
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
