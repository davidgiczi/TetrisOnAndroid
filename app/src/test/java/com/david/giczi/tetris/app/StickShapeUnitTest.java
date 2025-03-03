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

public class StickShapeUnitTest {

    private static Shape STICK;
    private static GameBoard BOARD;

    @BeforeClass
    public static void createBoard(){
        BOARD = new GameBoard();
    }
    @Before
    public void createShape(){
        STICK = ShapeFactory.getShape(ShapeType.STICK);
        BOARD.initBoard();
    }
    @Test
    public void testShapeColorCode(){
        int colorCode = STICK.getColorCode();
        System.out.println("The Shape's color code: " + colorCode);
        assertTrue( "The Shape's color code:",
                colorCode == 0 || colorCode == 1 || colorCode == 2 ||
                        colorCode == 3 || colorCode == 4 || colorCode == 5);
    }

    @Test
    public void testCreateStickShape() {
        BOARD.addShape(STICK.getShape(), null);
        System.out.println();
        System.out.println("Shape's location: " + STICK.getLocation());
        System.out.println();
        BOARD.displayTetrisBoard();
        switch (STICK.getLocation()) {
            case 1:
                assertEquals(STICK.getShape(), Arrays.asList(1, 2, 3, 4));
                break;
            case 2:
                assertEquals(STICK.getShape(), Arrays.asList(2, 3, 4, 5));
                break;
            case 3:
                assertEquals(STICK.getShape(), Arrays.asList(3, 4, 5, 6));
                break;
            case 4:
                assertEquals(STICK.getShape(), Arrays.asList(4, 5, 6, 7));
                break;
            case 5:
                assertEquals(STICK.getShape(), Arrays.asList(5, 6, 7, 8));
                break;
            case 6:
                assertEquals(STICK.getShape(), Arrays.asList(6, 7, 8, 9));
                break;
            default:
                assertEquals(STICK.getShape(), Arrays.asList(0, 1, 2, 3));
        }
    }

    @Test
    public void testIsValidStepLeft(){
        Shape stick = ShapeFactory.getShape(ShapeType.STICK);
        stick.getShape().set(0, 0);
        assertFalse(stick.isValidStepLeft());
        int shapeCellValue = (int) (Math.random() * 6 + 1);
        stick.getShape().set(0, shapeCellValue);
        assertTrue(stick.isValidStepLeft());
    }

    @Test
    public void testStepLeft(){
        List<Integer> temp = new ArrayList<>(STICK.getShape());
        System.out.println("Shape's location: " + STICK.getLocation());
        if( STICK.isValidStepLeft() ) {
            List<Integer> deletedShapeCell = STICK.stepLeft();
            temp.replaceAll(cellValue -> cellValue - 1);
            assertEquals(STICK.getShape(), temp);
            System.out.println("Step LEFT, deleted cell: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(STICK.getShape(), null);
            BOARD.displayTetrisBoard();
        }
        else {
            List<Integer> deletedShapeCell = STICK.stepLeft();
            assertEquals(STICK.getShape(), temp);
            System.out.println("NO step LEFT, Deleted cell: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(STICK.getShape(), null);
            BOARD.displayTetrisBoard();
        }
    }

    @Test
    public void testIsValidStepRight(){
        Shape stick = ShapeFactory.getShape(ShapeType.STICK);
        stick.getShape().set(3, 9);
        assertFalse(stick.isValidStepRight());
        int shapeCellValue = (int) (Math.random() * 6);
        stick.getShape().set(3, shapeCellValue);
        assertTrue(stick.isValidStepRight());
    }

    @Test
    public void testStepRight(){
        List<Integer> temp = new ArrayList<>(STICK.getShape());
        System.out.println("Shape's location: " + STICK.getLocation());
        if( STICK.isValidStepRight() ) {
            List<Integer> deletedShapeCell = STICK.stepRight();
            temp.replaceAll(cellValue -> cellValue + 1);
            assertEquals(STICK.getShape(), temp);
            System.out.println("Step RIGHT, deleted cell: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(STICK.getShape(), null);
            BOARD.displayTetrisBoard();
        }
        else {
            List<Integer> deletedShapeCell = STICK.stepRight();
            assertEquals(STICK.getShape(), temp);
            System.out.println("NO step RIGHT, deleted cell: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(STICK.getShape(), null);
            BOARD.displayTetrisBoard();
        }
    }
    @Test
    public void testIsValidStepDown(){
        Shape stick = ShapeFactory.getShape(ShapeType.STICK);
        int shapeCellValue = (int) (Math.random() *  7 + 190);
        stick.getShape().set(0, shapeCellValue);
        stick.getShape().set(1, shapeCellValue + 1);
        stick.getShape().set(2, shapeCellValue + 2);
        stick.getShape().set(3, shapeCellValue + 3);
        assertFalse(stick.isValidStepDown());
        shapeCellValue = (int) (Math.random() * 19);
        stick.getShape().set(0, shapeCellValue * GameBoard.BOARD_COL + 1);
        stick.getShape().set(1, shapeCellValue * GameBoard.BOARD_COL + 2);
        stick.getShape().set(2, shapeCellValue * GameBoard.BOARD_COL + 3);
        stick.getShape().set(3, shapeCellValue * GameBoard.BOARD_COL + 4);
        assertTrue(stick.isValidStepDown());
    }

    @Test
    public void testStepDown(){
        List<Integer> temp = new ArrayList<>(STICK.getShape());
        System.out.println("Shape's location: " + STICK.getLocation());
        if( STICK.isValidStepDown() ) {
            List<Integer> deletedShapeCell = STICK.stepDown();
            temp.replaceAll(cellValue -> cellValue + GameBoard.BOARD_COL);
            assertEquals(STICK.getShape(), temp);
            System.out.println("Step DOWN, deleted cells: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(STICK.getShape(), null);
            BOARD.displayTetrisBoard();
        }
        else {
            List<Integer> deletedShapeCell = STICK.stepDown();
            assertEquals(STICK.getShape(), temp);
            System.out.println("NO step DOWN, deleted cells: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(STICK.getShape(), null);
            BOARD.displayTetrisBoard();
        }
    }

    @Test
    public void testIsValidRotation(){
        Shape stick = ShapeFactory.getShape(ShapeType.STICK);
        int shapeCellValue = (int) (Math.random() * 7 + 190);
        stick.getShape().set(0, shapeCellValue);
        stick.getShape().set(1, shapeCellValue + 1);
        stick.getShape().set(2, shapeCellValue + 2);
        stick.getShape().set(3, shapeCellValue + 3);
        assertFalse(stick.isValidRotation());
        shapeCellValue = (int) (Math.random() * 16);
        stick.getShape().set(0, shapeCellValue * GameBoard.BOARD_COL + 1);
        stick.getShape().set(1, shapeCellValue * GameBoard.BOARD_COL + 2);
        stick.getShape().set(2, shapeCellValue * GameBoard.BOARD_COL + 3);
        stick.getShape().set(3, shapeCellValue * GameBoard.BOARD_COL + 4);
        assertTrue(stick.isValidRotation());
    }

    @Test
    public void testRotationInNormalPosition(){
        List<Integer> temp = new ArrayList<>();
        System.out.println("Shape's location: " + STICK.getLocation());
        if( STICK.isValidRotation() ) {
            if( STICK.getPosition() == ShapePosition.NORMAL ) {
                temp.add(STICK.getShape().get(0));
                temp.add(STICK.getShape().get(0) + GameBoard.BOARD_COL);
                temp.add(STICK.getShape().get(0) + 2 * GameBoard.BOARD_COL);
                temp.add(STICK.getShape().get(0) + 3 * GameBoard.BOARD_COL);
            }
            List<Integer> deletedShapeCell = STICK.rotate();
            assertEquals(STICK.getShape(), temp);
            System.out.println("ROTATION in NORMAL position, deleted cells: " + deletedShapeCell);
        }
        else {
            List<Integer> deletedShapeCell = STICK.rotate();
            assertEquals(STICK.getShape(), temp);
            System.out.println("NO ROTATION in NORMAL position, deleted cells: " + deletedShapeCell);
        }
        BOARD.initBoard();
        BOARD.addShape(STICK.getShape(), null);
        BOARD.displayTetrisBoard();
    }

    @Test
    public void testRotationInLeftRotatePosition(){
        List<Integer> temp = new ArrayList<>();
        System.out.println("Shape's location: " + STICK.getLocation());
        STICK.rotate();
        if( STICK.isValidRotation() ) {
            if( STICK.getPosition() == ShapePosition.LEFT_ROTATE ) {
                temp.add(STICK.getShape().get(0));
                temp.add(STICK.getShape().get(0) + 1);
                temp.add(STICK.getShape().get(0) + 2);
                temp.add(STICK.getShape().get(0) + 3);
            }
            List<Integer> deletedShapeCell = STICK.rotate();
            assertEquals(STICK.getShape(), temp);
            System.out.println("ROTATION in LEFT position, deleted cells: " + deletedShapeCell);
        }
        else {
            List<Integer> deletedShapeCell = STICK.rotate();
            assertEquals(STICK.getShape(), temp);
            System.out.println("NO ROTATION in LEFT position, deleted cells: " + deletedShapeCell);
        }
        BOARD.initBoard();
        BOARD.addShape(STICK.getShape(), null);
        BOARD.displayTetrisBoard();
    }
}