package com.david.giczi.tetris.app.logic;

import java.util.List;

class Square implements Shape {

    public List<Integer> square;
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
