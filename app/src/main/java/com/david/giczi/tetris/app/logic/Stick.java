package com.david.giczi.tetris.app.logic;

import java.util.List;

public class Stick implements Shape{

    public List<Integer> stick;
    public int colorCode;

    @Override
    public void create() {

    }

    @Override
    public List<Shape> stepLeft() {
        return null;
    }

    @Override
    public List<Shape> stepRight() {
        return null;
    }

    @Override
    public List<Shape> stepDown() {
        return null;
    }

    @Override
    public List<Shape> rotate() {
        return null;
    }
}
