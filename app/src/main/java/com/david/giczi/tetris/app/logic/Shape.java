package com.david.giczi.tetris.app.logic;

import java.util.List;

public interface Shape {

    int getLocation();
    int getColorCode();
    List<Integer> getShape();
    void create();
    List<Integer> stepLeft();
    boolean isValidStepLeft();
    List<Integer> stepRight();
    boolean isValidStepRight();
    List<Integer> stepDown();
    boolean isValidStepDown();
    List<Integer> rotate();
    boolean isValidRotation();

}
