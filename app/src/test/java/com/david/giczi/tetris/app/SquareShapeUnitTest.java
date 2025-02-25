package com.david.giczi.tetris.app;

import com.david.giczi.tetris.app.logic.GameBoard;
import com.david.giczi.tetris.app.logic.Shape;
import com.david.giczi.tetris.app.logic.ShapeFactory;
import com.david.giczi.tetris.app.logic.ShapeType;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

public class SquareShapeUnitTest {

    private static Shape SQUARE;
    private static GameBoard BOARD;
    @BeforeClass
    public static void createBoard(){
        BOARD = new GameBoard();
    }
    @Before
    public void createShape(){
        SQUARE = ShapeFactory.getShape(ShapeType.SQUARE);
    }

    @Test
    public void testShapeColorCode(){
        int colorCode = SQUARE.getColorCode();
        System.out.println("The Shape's color code: " + colorCode);
        assertTrue( "The Shape's color code:",
        colorCode == 0 || colorCode == 1 || colorCode == 2 ||
                colorCode == 3 || colorCode == 4 || colorCode == 5);
    }

    @Test
    public void testCreateSquareShape() {
        BOARD.addShape(SQUARE);
        System.out.println("Shape's location: " + SQUARE.getLocation());
        System.out.println();
        BOARD.displayTetrisBoard();
        switch (SQUARE.getLocation()) {
            case 1:
                assertEquals(SQUARE.getShape(), Arrays.asList(1, 2, 11, 12));
                break;
            case 2:
                assertEquals(SQUARE.getShape(), Arrays.asList(2, 3, 12, 13));
                break;
            case 3:
                assertEquals(SQUARE.getShape(), Arrays.asList(3, 4, 13, 14));
                break;
            case 4:
                assertEquals(SQUARE.getShape(), Arrays.asList(4, 5, 14, 15));
                break;
            case 5:
                assertEquals(SQUARE.getShape(), Arrays.asList(5, 6, 15, 16));
                break;
            case 6:
                assertEquals(SQUARE.getShape(), Arrays.asList(6, 7, 16, 17));
                break;
            case 7:
                assertEquals(SQUARE.getShape(), Arrays.asList(7, 8, 17, 18));
                break;
            case 8:
                assertEquals(SQUARE.getShape(), Arrays.asList(8, 9, 18, 19));
                break;
            default:
                assertEquals(SQUARE.getShape(), Arrays.asList(0, 1, 10, 11));
        }
    }

    @Test
    public void testIsValidMovementMethod(){

    }
}
