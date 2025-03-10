package com.david.giczi.tetris.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.david.giczi.tetris.app.logic.GameBoard;
import com.david.giczi.tetris.app.logic.Shape;
import com.david.giczi.tetris.app.logic.ShapeFactory;
import com.david.giczi.tetris.app.logic.ShapePosition;
import com.david.giczi.tetris.app.logic.ShapeType;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TShapeUnitTest {

    private static Shape T;
    private static GameBoard BOARD;

    @BeforeClass
    public static void createBoard(){
        BOARD = new GameBoard();
    }
    @Before
    public void initTest(){
        T = ShapeFactory.getShape(ShapeType.T);
        BOARD.initBoard();
    }

    @Test
    public void testShapeColorCode(){
        int colorCode = T.getColorCode();
        System.out.println("The Shape's color code: " + colorCode);
        assertTrue( "The Shape's color code:",
                colorCode == 0 || colorCode == 1 || colorCode == 2 ||
                        colorCode == 3 || colorCode == 4 || colorCode == 5);
    }

    @Test
    public void testCreateShape() {
        BOARD.addShape(T.getShape(), null);
        System.out.println();
        System.out.println("Shape's location: " + T.getLocation());
        System.out.println();
        BOARD.displayTetrisBoard();
        switch (T.getLocation()) {
            case 1:
                assertEquals(T.getShape(), Arrays.asList(1, 2, 3, 12));
                break;
            case 2:
                assertEquals(T.getShape(), Arrays.asList(2, 3, 4, 13));
                break;
            case 3:
                assertEquals(T.getShape(), Arrays.asList(3, 4, 5, 14));
                break;
            case 4:
                assertEquals(T.getShape(), Arrays.asList(4, 5, 6, 15));
                break;
            case 5:
                assertEquals(T.getShape(), Arrays.asList(5, 6, 7, 16));
                break;
            case 6:
                assertEquals(T.getShape(), Arrays.asList(6, 7, 8, 17));
                break;
            case 7:
                assertEquals(T.getShape(), Arrays.asList(7, 8, 9, 18));
                break;
            default:
                assertEquals(T.getShape(), Arrays.asList(0, 1, 2, 11));
        }
    }

    @Test
    public void testIsValidStepLeft(){
        Shape shapeT = ShapeFactory.getShape(ShapeType.T);
        shapeT.getShape().set(0, 0);
        assertFalse(shapeT.isValidStepLeft());
        int shapeCellValue = (int) (Math.random() * 7 + 1);
        shapeT.getShape().set(0, shapeCellValue);
        assertTrue(shapeT.isValidStepLeft());
    }

    @Test
    public void testStepLeft(){
        List<Integer> temp = new ArrayList<>(T.getShape());
        System.out.println("Shape's location: " + T.getLocation());
        if( T.isValidStepLeft() ) {
            List<Integer> deletedShapeCell = T.stepLeft();
            temp.replaceAll(cellValue -> cellValue - 1);
            assertEquals(T.getShape(), temp);
            System.out.println("Step LEFT, deleted cell: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(T.getShape(), null);
            BOARD.displayTetrisBoard();
        }
        else {
            List<Integer> deletedShapeCell = T.stepLeft();
            assertEquals(T.getShape(), temp);
            System.out.println("NO step LEFT, Deleted cell: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(T.getShape(), null);
            BOARD.displayTetrisBoard();
        }
    }

    @Test
    public void testIsValidStepRight(){
        Shape shapeT = ShapeFactory.getShape(ShapeType.T);
            shapeT.getShape().set(2, 9);
            assertFalse(shapeT.isValidStepRight());
            int shapeCellValue = (int) (Math.random() * 7);
            shapeT.getShape().set(2, shapeCellValue);
            assertTrue(shapeT.isValidStepRight());
    }

    @Test
    public void testStepRight(){
        List<Integer> temp = new ArrayList<>(T.getShape());
        System.out.println("Shape's location: " + T.getLocation());
        if( T.isValidStepRight() ) {
            List<Integer> deletedShapeCell = T.stepRight();
            temp.replaceAll(cellValue -> cellValue + 1);
            assertEquals(T.getShape(), temp);
            System.out.println("Step RIGHT, deleted cell: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(T.getShape(), null);
            BOARD.displayTetrisBoard();
        }
        else {
            List<Integer> deletedShapeCell = T.stepRight();
            assertEquals(T.getShape(), temp);
            System.out.println("NO step RIGHT, Deleted cell: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(T.getShape(), null);
            BOARD.displayTetrisBoard();
        }
    }

    @Test
    public void testIsValidStepDown(){
        Shape shapeT = ShapeFactory.getShape(ShapeType.T);
        int shapeCellValue = (int) (Math.random() *  8 + 190);
        shapeT.getShape().set(3, shapeCellValue);
        assertFalse(shapeT.isValidStepDown());
        shapeCellValue = (int) (Math.random() * 8);
        shapeT.getShape().set(3, shapeCellValue);
        assertTrue(shapeT.isValidStepDown());
    }
    @Test
    public void testStepDown(){
        List<Integer> temp = new ArrayList<>(T.getShape());
        System.out.println("Shape's location: " + T.getLocation());
        if( T.isValidStepDown() ) {
            List<Integer> deletedShapeCell = T.stepDown();
            temp.replaceAll(cellValue -> cellValue + GameBoard.BOARD_COL);
            assertEquals(T.getShape(), temp);
            System.out.println("Step DOWN, deleted cells: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(T.getShape(), null);
            BOARD.displayTetrisBoard();
        }
        else {
            List<Integer> deletedShapeCell = T.stepDown();
            assertEquals(T.getShape(), temp);
            System.out.println("NO step DOWN, deleted cells: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(T.getShape(), null);
            BOARD.displayTetrisBoard();
        }
    }

    @Test
    public void testIsValidRotation(){
        Shape T = ShapeFactory.getShape(ShapeType.T);
        assertFalse(T.isValidRotation());
        int shapeCellValue = (int) (Math.random() * 178 + 10);
        T.getShape().set(0, shapeCellValue);
        T.getShape().set(1, shapeCellValue + 1);
        T.getShape().set(2, shapeCellValue + 2);
        T.getShape().set(3, shapeCellValue + 1 + GameBoard.BOARD_COL);
        assertTrue(T.isValidRotation());
    }

    @Test
    public void testRotationInNormalPosition(){
        List<Integer> temp = new ArrayList<>();
        int rowValue = (int) (Math.random() * 19);
        int colValue = (int) (Math.random() * 8);
        T.getShape().set(0, rowValue * GameBoard.BOARD_COL + colValue);
        T.getShape().set(1, rowValue * GameBoard.BOARD_COL + colValue + 1);
        T.getShape().set(2, rowValue * GameBoard.BOARD_COL + colValue + 2);
        T.getShape().set(3, rowValue * GameBoard.BOARD_COL + colValue + 1 + GameBoard.BOARD_COL);
        System.out.println("Shape's location: " + T.getShape().get(0));
        if( T.isValidRotation() ) {
            temp.add(T.getShape().get(1) - GameBoard.BOARD_COL);
            temp.add(T.getShape().get(1));
            temp.add(T.getShape().get(1) + GameBoard.BOARD_COL);
            temp.add(T.getShape().get(1) - 1);
            List<Integer> deletedShapeCell = T.rotate();
            assertEquals(T.getShape(), temp);
            System.out.println("ROTATION in NORMAL position, deleted cells: " + deletedShapeCell);
        }
        else{
            List<Integer> deletedShapeCell = T.rotate();
            assertEquals(deletedShapeCell, temp);
            System.out.println("NO ROTATION in NORMAL position, deleted cells: " + deletedShapeCell);
        }
        BOARD.initBoard();
        BOARD.addShape(T.getShape(), null);
        BOARD.displayTetrisBoard();
    }

    @Test
    public void testRotationInRightRotatedPosition(){
        List<Integer> temp = new ArrayList<>();
        int rowValue = (int) (Math.random() * 19);
        int colValue = (int) (Math.random() * 8);
        T.getShape().set(0, rowValue * GameBoard.BOARD_COL + colValue);
        T.getShape().set(1, rowValue * GameBoard.BOARD_COL + colValue + 1);
        T.getShape().set(2, rowValue * GameBoard.BOARD_COL + colValue + 2);
        T.getShape().set(3, rowValue * GameBoard.BOARD_COL + colValue + 1 + GameBoard.BOARD_COL);
        System.out.println("Shape's location: " + T.getShape().get(0));
        T.rotate();
        if( T.isValidRotation() ) {
            temp.add(T.getShape().get(1) - 1);
            temp.add(T.getShape().get(1));
            temp.add(T.getShape().get(1) + 1);
            temp.add(T.getShape().get(1) - GameBoard.BOARD_COL);
            List<Integer> deletedShapeCell = T.rotate();
            assertEquals(T.getShape(), temp);
            System.out.println("ROTATION in RIGHT_ROTATED position, deleted cells: " + deletedShapeCell);
        }
        else{
            List<Integer> deletedShapeCell = T.rotate();
            assertEquals(deletedShapeCell, temp);
            System.out.println("NO ROTATION in RIGHT_ROTATED position, deleted cells: " + deletedShapeCell);
        }
        BOARD.initBoard();
        BOARD.addShape(T.getShape(), null);
        BOARD.displayTetrisBoard();
    }

    @Test
    public void testRotationInUpsideDownRotatedPosition(){
        List<Integer> temp = new ArrayList<>();
        int rowValue = (int) (Math.random() * 19);
        int colValue = (int) (Math.random() * 8);
        T.getShape().set(0, rowValue * GameBoard.BOARD_COL + colValue);
        T.getShape().set(1, rowValue * GameBoard.BOARD_COL + colValue + 1);
        T.getShape().set(2, rowValue * GameBoard.BOARD_COL + colValue + 2);
        T.getShape().set(3, rowValue * GameBoard.BOARD_COL + colValue + 1 + GameBoard.BOARD_COL);
        System.out.println("Shape's location: " + T.getShape().get(0));
        T.rotate();
        T.rotate();
        if( T.isValidRotation() ) {
            temp.add(T.getShape().get(1) - GameBoard.BOARD_COL);
            temp.add(T.getShape().get(1));
            temp.add(T.getShape().get(1) + GameBoard.BOARD_COL);
            temp.add(T.getShape().get(1) + 1);
            List<Integer> deletedShapeCell = T.rotate();
            assertEquals(T.getShape(), temp);
            System.out.println("ROTATION in UPSIDE_DOWN position, deleted cells: " + deletedShapeCell);
        }
        else{
            List<Integer> deletedShapeCell = T.rotate();
            assertEquals(deletedShapeCell, temp);
            System.out.println("NO ROTATION in UPSIDE_DOWN position, deleted cells: " + deletedShapeCell);
        }
        BOARD.initBoard();
        BOARD.addShape(T.getShape(), null);
        BOARD.displayTetrisBoard();
    }
    @Test
    public void testRotationInLeftRotatedPosition(){
        List<Integer> temp = new ArrayList<>();
        int rowValue = (int) (Math.random() * 19);
        int colValue = (int) (Math.random() * 8);
        T.getShape().set(0, rowValue * GameBoard.BOARD_COL + colValue);
        T.getShape().set(1, rowValue * GameBoard.BOARD_COL + colValue + 1);
        T.getShape().set(2, rowValue * GameBoard.BOARD_COL + colValue + 2);
        T.getShape().set(3, rowValue * GameBoard.BOARD_COL + colValue + 1 + GameBoard.BOARD_COL);
        System.out.println("Shape's location: " + T.getShape().get(0));
        T.rotate();
        T.rotate();
        T.rotate();
        if( T.isValidRotation() ) {
            temp.add(T.getShape().get(1) - 1);
            temp.add(T.getShape().get(1));
            temp.add(T.getShape().get(1) + 1);
            temp.add(T.getShape().get(1) + GameBoard.BOARD_COL);
            List<Integer> deletedShapeCell = T.rotate();
            assertEquals(T.getShape(), temp);
            System.out.println("ROTATION in LEFT_ROTATED position, deleted cells: " + deletedShapeCell);
        }
        else{
            List<Integer> deletedShapeCell = T.rotate();
            assertEquals(deletedShapeCell, temp);
            System.out.println("NO ROTATION in LEFT_ROTATED position, deleted cells: " + deletedShapeCell);
        }
        BOARD.initBoard();
        BOARD.addShape(T.getShape(), null);
        BOARD.displayTetrisBoard();
    }

}
