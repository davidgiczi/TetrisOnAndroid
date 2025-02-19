package com.david.giczi.tetris.app.logic;

public interface Shape {

    void create();
    void stepLeft();
    void stepRight();
    void pullDown();
    void rotate();

}
