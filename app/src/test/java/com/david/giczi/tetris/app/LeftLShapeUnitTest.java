package com.david.giczi.tetris.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.david.giczi.tetris.app.logic.GameBoard;
import com.david.giczi.tetris.app.logic.Shape;
import com.david.giczi.tetris.app.logic.ShapeFactory;
import com.david.giczi.tetris.app.logic.ShapeType;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeftLShapeUnitTest {

    private static Shape leftL;
    private static GameBoard BOARD;

    @BeforeClass
    public static void createBoard(){
        BOARD = new GameBoard();
    }
    @Before
    public void initTest(){
        leftL = ShapeFactory.getShape(ShapeType.LEFT_L);
        BOARD.initBoard();
    }

    @Test
    public void testShapeColorCode(){
        int colorCode = leftL.getColorCode();
        System.out.println("The Shape's color code: " + colorCode);
        assertTrue( "The Shape's color code:",
                colorCode == 0 || colorCode == 1 || colorCode == 2 ||
                        colorCode == 3 || colorCode == 4 || colorCode == 5);
    }

    @Test
    public void testCreateShape() {
        BOARD.addShape(leftL.getShape(), null);
        System.out.println();
        System.out.println("Shape's location: " + leftL.getLocation());
        System.out.println();
        BOARD.displayTetrisBoard();
        switch (leftL.getLocation()) {
            case 1:
                assertEquals(leftL.getShape(), Arrays.asList(1, 2, 3, 13));
                break;
            case 2:
                assertEquals(leftL.getShape(), Arrays.asList(2, 3, 4, 14));
                break;
            case 3:
                assertEquals(leftL.getShape(), Arrays.asList(3, 4, 5, 15));
                break;
            case 4:
                assertEquals(leftL.getShape(), Arrays.asList(4, 5, 6, 16));
                break;
            case 5:
                assertEquals(leftL.getShape(), Arrays.asList(5, 6, 7, 17));
                break;
            case 6:
                assertEquals(leftL.getShape(), Arrays.asList(6, 7, 8, 18));
                break;
            case 7:
                assertEquals(leftL.getShape(), Arrays.asList(7, 8, 9, 19));
                break;
            default:
                assertEquals(leftL.getShape(), Arrays.asList(0, 1, 2, 12));
        }
    }

    @Test
    public void testIsValidStepLeft(){
        Shape leftL = ShapeFactory.getShape(ShapeType.LEFT_L);
        leftL.getShape().set(0, 0);
        assertFalse(leftL.isValidStepLeft());
        int shapeCellValue = (int) (Math.random() * 7 + 1);
        leftL.getShape().set(0, shapeCellValue);
        assertTrue(leftL.isValidStepLeft());
    }
    @Test
    public void testStepLeft(){
        List<Integer> temp = new ArrayList<>(leftL.getShape());
        System.out.println("Shape's location: " + leftL.getLocation());
        if( leftL.isValidStepLeft() ) {
            List<Integer> deletedShapeCell = leftL.stepLeft();
            temp.replaceAll(cellValue -> cellValue - 1);
            assertEquals(leftL.getShape(), temp);
            System.out.println("Step LEFT, deleted cell: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(leftL.getShape(), null);
            BOARD.displayTetrisBoard();
        }
        else {
            List<Integer> deletedShapeCell = leftL.stepLeft();
            assertEquals(leftL.getShape(), temp);
            System.out.println("NO step LEFT, Deleted cell: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(leftL.getShape(), null);
            BOARD.displayTetrisBoard();
        }
    }

    @Test
    public void testIsValidStepRight(){
        Shape leftL = ShapeFactory.getShape(ShapeType.LEFT_L);
        leftL.getShape().set(3, 19);
        assertFalse(leftL.isValidStepRight());
        int shapeCellValue = (int) (Math.random() * 7);
        leftL.getShape().set(3, shapeCellValue);
        assertTrue(leftL.isValidStepRight());
    }

    @Test
    public void testStepRight(){
        List<Integer> temp = new ArrayList<>(leftL.getShape());
        System.out.println("Shape's location: " + leftL.getLocation());
        if( leftL.isValidStepRight() ) {
            List<Integer> deletedShapeCell = leftL.stepRight();
            temp.replaceAll(cellValue -> cellValue + 1);
            assertEquals(leftL.getShape(), temp);
            System.out.println("Step RIGHT, deleted cell: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(leftL.getShape(), null);
            BOARD.displayTetrisBoard();
        }
        else {
            List<Integer> deletedShapeCell = leftL.stepRight();
            assertEquals(leftL.getShape(), temp);
            System.out.println("NO step RIGHT, deleted cell: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(leftL.getShape(), null);
            BOARD.displayTetrisBoard();
        }
    }

    @Test
    public void testIsValidStepDown(){
        Shape leftL = ShapeFactory.getShape(ShapeType.LEFT_L);
        int shapeCellValue = (int) (Math.random() *  8 + 192);
        leftL.getShape().set(3, shapeCellValue);
        assertFalse(leftL.isValidStepDown());
        shapeCellValue = (int) (Math.random() * 8 + 12);
        leftL.getShape().set(3, shapeCellValue);
        assertTrue(leftL.isValidStepDown());
    }

    @Test
    public void testStepDown(){
        List<Integer> temp = new ArrayList<>(leftL.getShape());
        System.out.println("Shape's location: " + leftL.getLocation());
        if( leftL.isValidStepDown() ) {
            List<Integer> deletedShapeCell = leftL.stepDown();
            temp.replaceAll(cellValue -> cellValue + GameBoard.BOARD_COL);
            assertEquals(leftL.getShape(), temp);
            System.out.println("Step DOWN, deleted cells: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(leftL.getShape(), null);
            BOARD.displayTetrisBoard();
        }
        else {
            List<Integer> deletedShapeCell = leftL.stepDown();
            assertEquals(leftL.getShape(), temp);
            System.out.println("NO step DOWN, deleted cells: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(leftL.getShape(), null);
            BOARD.displayTetrisBoard();
        }
    }

    @Test
    public void testIsValidRotation(){
        Shape leftL = ShapeFactory.getShape(ShapeType.LEFT_L);
        assertFalse(leftL.isValidRotation());
        int shapeCellValue = (int) (Math.random() * 7 + 10);
        leftL.getShape().set(0, shapeCellValue);
        leftL.getShape().set(1, shapeCellValue + 1);
        leftL.getShape().set(2, shapeCellValue + 2);
        leftL.getShape().set(3, shapeCellValue + 2 + GameBoard.BOARD_COL);
        assertTrue(leftL.isValidRotation());
    }

    @Test
    public void testRotationInNormalPosition(){
        List<Integer> temp = new ArrayList<>();
        leftL.getShape().replaceAll(cellValue -> cellValue + 10);
        System.out.println("Shape's location: " + (leftL.getLocation() + 10));
        if( leftL.isValidRotation() ) {
            temp.add(leftL.getShape().get(1) - GameBoard.BOARD_COL);
            temp.add(leftL.getShape().get(1));
            temp.add(leftL.getShape().get(1) + GameBoard.BOARD_COL);
            temp.add(leftL.getShape().get(1) + GameBoard.BOARD_COL - 1);
            List<Integer> deletedShapeCell = leftL.rotate();
            assertEquals(leftL.getShape(), temp);
            System.out.println("ROTATION in NORMAL position, deleted cells: " + deletedShapeCell);
        }
        else{
            List<Integer> deletedShapeCell = leftL.rotate();
            assertEquals(deletedShapeCell, temp);
            System.out.println("NO ROTATION in NORMAL position, deleted cells: " + deletedShapeCell);
        }
        BOARD.initBoard();
        BOARD.addShape(leftL.getShape(), null);
        BOARD.displayTetrisBoard();
    }

    @Test
    public void testRotationInRightRotatedPosition(){
        List<Integer> temp = new ArrayList<>();
        leftL.getShape().replaceAll(cellValue -> cellValue + 10);
        System.out.println("Shape's location: " + (leftL.getLocation() + 10));
        leftL.rotate();
        if( leftL.isValidRotation() ) {
            temp.add(leftL.getShape().get(1) + 1);
            temp.add(leftL.getShape().get(1));
            temp.add(leftL.getShape().get(1) - 1);
            temp.add(leftL.getShape().get(1) - GameBoard.BOARD_COL - 1);
            List<Integer> deletedShapeCell = leftL.rotate();
            assertEquals(leftL.getShape(), temp);
            System.out.println("ROTATION in RIGHT_ROTATED position, deleted cells: " + deletedShapeCell);
        }
        else{
            List<Integer> deletedShapeCell = leftL.rotate();
            assertEquals(deletedShapeCell, temp);
            System.out.println("NO ROTATION in RIGHT_ROTATED position, deleted cells: " + deletedShapeCell);
        }
        BOARD.initBoard();
        BOARD.addShape(leftL.getShape(), null);
        BOARD.displayTetrisBoard();
    }

    @Test
    public void testRotationInUpsideDownRotatedPosition(){
        List<Integer> temp = new ArrayList<>();
        leftL.getShape().replaceAll(cellValue -> cellValue + 10);
        System.out.println("Shape's location: " + (leftL.getLocation() + 10));
        leftL.rotate();
        leftL.rotate();
        if( leftL.isValidRotation() ) {
            temp.add(leftL.getShape().get(1) + GameBoard.BOARD_COL);
            temp.add(leftL.getShape().get(1));
            temp.add(leftL.getShape().get(1) - GameBoard.BOARD_COL);
            temp.add(leftL.getShape().get(1) - GameBoard.BOARD_COL + 1);
            List<Integer> deletedShapeCell = leftL.rotate();
            assertEquals(leftL.getShape(), temp);
            System.out.println("ROTATION in UPSIDE_DOWN position, deleted cells: " + deletedShapeCell);
        }
        else{
            List<Integer> deletedShapeCell = leftL.rotate();
            assertEquals(deletedShapeCell, temp);
            System.out.println("NO ROTATION in UPSIDE_DOWN position, deleted cells: " + deletedShapeCell);
        }
        BOARD.initBoard();
        BOARD.addShape(leftL.getShape(), null);
        BOARD.displayTetrisBoard();
    }

    @Test
    public void testRotationInLeftRotatedPosition(){
        List<Integer> temp = new ArrayList<>();
        leftL.getShape().replaceAll(cellValue -> cellValue + 10);
        System.out.println("Shape's location: " + (leftL.getLocation() + 10));
        leftL.rotate();
        leftL.rotate();
        leftL.rotate();
        if( leftL.isValidRotation() ) {
            temp.add(leftL.getShape().get(1) - 1);
            temp.add(leftL.getShape().get(1));
            temp.add(leftL.getShape().get(1) + 1);
            temp.add(leftL.getShape().get(1) + GameBoard.BOARD_COL + 1);
            List<Integer> deletedShapeCell = leftL.rotate();
            assertEquals(leftL.getShape(), temp);
            System.out.println("ROTATION in LEFT_ROTATED position, deleted cells: " + deletedShapeCell);
        }
        else{
            List<Integer> deletedShapeCell = leftL.rotate();
            assertEquals(deletedShapeCell, temp);
            System.out.println("NO ROTATION in LEFT_ROTATED position, deleted cells: " + deletedShapeCell);
        }
        BOARD.initBoard();
        BOARD.addShape(leftL.getShape(), null);
        BOARD.displayTetrisBoard();
    }
}
