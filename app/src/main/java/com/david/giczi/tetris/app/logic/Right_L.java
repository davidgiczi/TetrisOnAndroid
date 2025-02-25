package com.david.giczi.tetris.app.logic;

import java.util.List;

class Right_L implements Shape {

    private List<Integer> right_L;
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
    public List<Integer> stepRight() {
        return null;
    }

    @Override
    public List<Integer> stepDown() {
        return null;
    }

    @Override
    public List<Integer> rotate() {
        return null;
    }


}
