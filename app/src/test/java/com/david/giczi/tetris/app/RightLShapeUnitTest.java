package com.david.giczi.tetris.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import com.david.giczi.tetris.app.logic.GameBoard;
import com.david.giczi.tetris.app.logic.Shape;
import com.david.giczi.tetris.app.logic.ShapeFactory;
import com.david.giczi.tetris.app.logic.ShapePosition;
import com.david.giczi.tetris.app.logic.ShapeType;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

public class RightLShapeUnitTest {


    private static Shape rightL;
    private static GameBoard BOARD;

    @BeforeClass
    public static void createBoard(){
        BOARD = new GameBoard();
    }
    @Before
    public void initTest(){
        rightL = ShapeFactory.getShape(ShapeType.RIGHT_L);
        BOARD.initBoard();
    }

    @Test
    public void testShapeColorCode(){
        int colorCode = rightL.getColorCode();
        System.out.println("The Shape's color code: " + colorCode);
        assertTrue( "The Shape's color code:",
                colorCode == 0 || colorCode == 1 || colorCode == 2 ||
                        colorCode == 3 || colorCode == 4 || colorCode == 5);
    }

    @Test
    public void testCreateShape() {
        BOARD.addShape(rightL.getShape(), null);
        System.out.println();
        System.out.println("Shape's location: " + rightL.getLocation());
        System.out.println();
        BOARD.displayTetrisBoard();
        switch (rightL.getLocation()) {
            case 11:
                assertEquals(rightL.getShape(), Arrays.asList(11, 12, 13, 3));
                break;
            case 12:
                assertEquals(rightL.getShape(), Arrays.asList(12, 13, 14, 4));
                break;
            case 13:
                assertEquals(rightL.getShape(), Arrays.asList(13, 14, 15, 5));
                break;
            case 14:
                assertEquals(rightL.getShape(), Arrays.asList(14, 15, 16, 6));
                break;
            case 15:
                assertEquals(rightL.getShape(), Arrays.asList(15, 16, 17, 7));
                break;
            case 16:
                assertEquals(rightL.getShape(), Arrays.asList(16, 17, 18, 8));
                break;
            case 17:
                assertEquals(rightL.getShape(), Arrays.asList(17, 18, 19, 9));
                break;
            default:
                assertEquals(rightL.getShape(), Arrays.asList(10, 11, 12, 2));
        }
    }

    @Test
    public void testIsValidStepLeft(){
        Shape rightL = ShapeFactory.getShape(ShapeType.RIGHT_L);
        if( rightL.getPosition() == ShapePosition.NORMAL ){
            rightL.getShape().set(0, 10);
            assertFalse(rightL.isValidStepLeft());
            int shapeCellValue = (int) (Math.random() * 7 + 11);
            rightL.getShape().set(0, shapeCellValue);
            assertTrue(rightL.isValidStepLeft());
        }
        else if( rightL.getPosition() == ShapePosition.RIGHT_ROTATED ){
            rightL.getShape().set(0, 0);
            assertFalse(rightL.isValidStepLeft());
            int shapeCellValue = (int) (Math.random() * 8);
            rightL.getShape().set(0, shapeCellValue);
            assertTrue(rightL.isValidStepLeft());
        }
        else if( rightL.getPosition() == ShapePosition.UPSIDE_DOWN ){
            rightL.getShape().set(2, 0);
            assertFalse(rightL.isValidStepLeft());
            int shapeCellValue = (int) (Math.random() * 8);
            rightL.getShape().set(2, shapeCellValue);
            assertTrue(rightL.isValidStepLeft());
        }
        else if( rightL.getPosition() == ShapePosition.LEFT_ROTATED ){
            rightL.getShape().set(3, 0);
            assertFalse(rightL.isValidStepLeft());
            int shapeCellValue = (int) (Math.random() * 9);
            rightL.getShape().set(3, shapeCellValue);
            assertTrue(rightL.isValidStepLeft());
        }
    }


}
