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
        rightL.getShape().set(0, 10);
        assertFalse(rightL.isValidStepLeft());
        int shapeCellValue = (int) (Math.random() * 7 + 11);
        rightL.getShape().set(0, shapeCellValue);
        assertTrue(rightL.isValidStepLeft());
    }

    @Test
    public void testStepLeft(){
        List<Integer> temp = new ArrayList<>(rightL.getShape());
        System.out.println("Shape's location: " + rightL.getLocation());
        if( rightL.isValidStepLeft() ) {
            List<Integer> deletedShapeCell = rightL.stepLeft();
            temp.replaceAll(cellValue -> cellValue - 1);
            assertEquals(rightL.getShape(), temp);
            System.out.println("Step LEFT, deleted cell: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(rightL.getShape(), null);
            BOARD.displayTetrisBoard();
        }
        else {
            List<Integer> deletedShapeCell = rightL.stepLeft();
            assertEquals(rightL.getShape(), temp);
            System.out.println("NO step LEFT, Deleted cell: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(rightL.getShape(), null);
            BOARD.displayTetrisBoard();
        }
    }

    @Test
    public void testIsValidStepRight(){
        Shape shapeT = ShapeFactory.getShape(ShapeType.RIGHT_L);
        shapeT.getShape().set(3, 9);
        assertFalse(shapeT.isValidStepRight());
        int shapeCellValue = (int) (Math.random() * 6 + 2);
        shapeT.getShape().set(3, shapeCellValue);
        assertTrue(shapeT.isValidStepRight());
    }

    @Test
    public void testStepRight(){
        List<Integer> temp = new ArrayList<>(rightL.getShape());
        System.out.println("Shape's location: " + rightL.getLocation());
        if( rightL.isValidStepRight() ) {
            List<Integer> deletedShapeCell = rightL.stepRight();
            temp.replaceAll(cellValue -> cellValue + 1);
            assertEquals(rightL.getShape(), temp);
            System.out.println("Step RIGHT, deleted cell: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(rightL.getShape(), null);
            BOARD.displayTetrisBoard();
        }
        else {
            List<Integer> deletedShapeCell = rightL.stepRight();
            assertEquals(rightL.getShape(), temp);
            System.out.println("NO step RIGHT, deleted cell: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(rightL.getShape(), null);
            BOARD.displayTetrisBoard();
        }
    }

    @Test
    public void testIsValidStepDown(){
        Shape shapeT = ShapeFactory.getShape(ShapeType.RIGHT_L);
        int shapeCellValue = (int) (Math.random() *  8 + 190);
        shapeT.getShape().set(0, shapeCellValue);
        assertFalse(shapeT.isValidStepDown());
        shapeCellValue = (int) (Math.random() * 8 + 10);
        shapeT.getShape().set(0, shapeCellValue);
        assertTrue(shapeT.isValidStepDown());
    }

    @Test
    public void testStepDown(){
        List<Integer> temp = new ArrayList<>(rightL.getShape());
        System.out.println("Shape's location: " + rightL.getLocation());
        if( rightL.isValidStepDown() ) {
            List<Integer> deletedShapeCell = rightL.stepDown();
            temp.replaceAll(cellValue -> cellValue + GameBoard.BOARD_COL);
            assertEquals(rightL.getShape(), temp);
            System.out.println("Step DOWN, deleted cells: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(rightL.getShape(), null);
            BOARD.displayTetrisBoard();
        }
        else {
            List<Integer> deletedShapeCell = rightL.stepDown();
            assertEquals(rightL.getShape(), temp);
            System.out.println("NO step DOWN, deleted cells: " + deletedShapeCell);
            BOARD.initBoard();
            BOARD.addShape(rightL.getShape(), null);
            BOARD.displayTetrisBoard();
        }
    }


    @Test
    public void testIsValidRotation(){
        Shape rightL = ShapeFactory.getShape(ShapeType.RIGHT_L);
        assertTrue(rightL.isValidRotation());
        int shapeCellValue = (int) (Math.random() * 8);
        rightL.getShape().set(0, shapeCellValue);
        rightL.getShape().set(1, shapeCellValue + 1);
        rightL.getShape().set(2, shapeCellValue + 2);
        rightL.getShape().set(3, shapeCellValue + 2 - GameBoard.BOARD_COL);
        assertFalse(rightL.isValidRotation());
    }

    @Test
    public void testRotationInNormalPosition(){
        List<Integer> temp = new ArrayList<>();
        System.out.println("Shape's location: " + rightL.getLocation());
        if( rightL.isValidRotation() ) {
            temp.add(rightL.getShape().get(1) - GameBoard.BOARD_COL);
            temp.add(rightL.getShape().get(1));
            temp.add(rightL.getShape().get(1) + GameBoard.BOARD_COL);
            temp.add(rightL.getShape().get(1) + GameBoard.BOARD_COL + 1);
            List<Integer> deletedShapeCell = rightL.rotate();
            assertEquals(rightL.getShape(), temp);
            System.out.println("ROTATION in NORMAL position, deleted cells: " + deletedShapeCell);
        }
        else{
            List<Integer> deletedShapeCell = rightL.rotate();
            assertEquals(deletedShapeCell, temp);
            System.out.println("NO ROTATION in NORMAL position, deleted cells: " + deletedShapeCell);
        }
        BOARD.initBoard();
        BOARD.addShape(rightL.getShape(), null);
        BOARD.displayTetrisBoard();
    }

    @Test
    public void testRotationInRightRotatedPosition(){
        List<Integer> temp = new ArrayList<>();
        System.out.println("Shape's location: " + rightL.getLocation());
        rightL.rotate();
        if( rightL.isValidRotation() ) {
            temp.add(rightL.getShape().get(1) + 1);
            temp.add(rightL.getShape().get(1));
            temp.add(rightL.getShape().get(1) - 1);
            temp.add(rightL.getShape().get(1) + GameBoard.BOARD_COL - 1);
            List<Integer> deletedShapeCell = rightL.rotate();
            assertEquals(rightL.getShape(), temp);
            System.out.println("ROTATION in RIGHT_ROTATED position, deleted cells: " + deletedShapeCell);
        }
        else{
            List<Integer> deletedShapeCell = rightL.rotate();
            assertEquals(deletedShapeCell, temp);
            System.out.println("NO ROTATION in RIGHT_ROTATED position, deleted cells: " + deletedShapeCell);
        }
        BOARD.initBoard();
        BOARD.addShape(rightL.getShape(), null);
        BOARD.displayTetrisBoard();
    }

    @Test
    public void testRotationInUpsideDownRotatedPosition(){
        List<Integer> temp = new ArrayList<>();
        System.out.println("Shape's location: " + rightL.getLocation());
        rightL.rotate();
        rightL.rotate();
        if( rightL.isValidRotation() ) {
            temp.add(rightL.getShape().get(1) + GameBoard.BOARD_COL);
            temp.add(rightL.getShape().get(1));
            temp.add(rightL.getShape().get(1) - GameBoard.BOARD_COL);
            temp.add(rightL.getShape().get(1) - GameBoard.BOARD_COL - 1);
            List<Integer> deletedShapeCell = rightL.rotate();
            assertEquals(rightL.getShape(), temp);
            System.out.println("ROTATION in UPSIDE_DOWN position, deleted cells: " + deletedShapeCell);
        }
        else{
            List<Integer> deletedShapeCell = rightL.rotate();
            assertEquals(deletedShapeCell, temp);
            System.out.println("NO ROTATION in UPSIDE_DOWN position, deleted cells: " + deletedShapeCell);
        }
        BOARD.initBoard();
        BOARD.addShape(rightL.getShape(), null);
        BOARD.displayTetrisBoard();
    }

    @Test
    public void testRotationInLeftRotatedPosition(){
        List<Integer> temp = new ArrayList<>();
        System.out.println("Shape's location: " + rightL.getLocation());
        rightL.rotate();
        rightL.rotate();
        rightL.rotate();
        if( rightL.isValidRotation() ) {
            temp.add(rightL.getShape().get(1) - 1);
            temp.add(rightL.getShape().get(1));
            temp.add(rightL.getShape().get(1) + 1);
            temp.add(rightL.getShape().get(1) - GameBoard.BOARD_COL + 1);
            List<Integer> deletedShapeCell = rightL.rotate();
            assertEquals(rightL.getShape(), temp);
            System.out.println("ROTATION in LEFT_ROTATED position, deleted cells: " + deletedShapeCell);
        }
        else{
            List<Integer> deletedShapeCell = rightL.rotate();
            assertEquals(deletedShapeCell, temp);
            System.out.println("NO ROTATION in LEFT_ROTATED position, deleted cells: " + deletedShapeCell);
        }
        BOARD.initBoard();
        BOARD.addShape(rightL.getShape(), null);
        BOARD.displayTetrisBoard();
    }

}
