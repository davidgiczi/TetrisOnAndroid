package com.david.giczi.tetris.app.logic;

import java.util.NoSuchElementException;

public class ShapeFactory {
    public static ShapeType PREVIOUS_TYPE;

    public static Shape getShape(ShapeType shapeType){
    PREVIOUS_TYPE = shapeType;
        switch (shapeType) {
            case STICK:
                return new Stick();
            case SQUARE:
                return new Square();
            case LEFT_WORM:
                return new LeftWorm();
            case RIGHT_WORM:
                return new RightWorm();
            case LEFT_L:
                return new Left_L();
            case RIGHT_L:
                return new Right_L();
            case T_LETTER:
                return new T_Letter();
            default:
                throw new NoSuchElementException("'" + shapeType + "' Shape does not exist.");
        }
    }

}
