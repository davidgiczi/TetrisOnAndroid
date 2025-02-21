package com.david.giczi.tetris.app.logic;

import java.util.List;

public interface Shape {

    void create();
    List<Shape> stepLeft();
    List<Shape> stepRight();
    List<Shape> stepDown();
    List<Shape> rotate();

}
