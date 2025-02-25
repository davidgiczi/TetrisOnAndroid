package com.david.giczi.tetris.app.logic;

import java.util.List;

public interface Shape {

    int getLocation();
    int getColorCode();
    List<Integer> getShape();
    void create();
    List<Integer> stepLeft();
    List<Integer> stepRight();
    List<Integer> stepDown();
    List<Integer> rotate();

}
