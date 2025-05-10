package com.david.giczi.tetris.app;

import static org.junit.Assert.*;

import com.david.giczi.tetris.app.config.Configuration;
import com.david.giczi.tetris.app.config.ShapeColor;
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
import java.util.stream.Collectors;

public class TShapeUnitTest {

    private static GameBoard BOARD;
    private Shape tShape;  // Instance variable for the shape, for better test isolation
    private final int BOARD_COL = Configuration.BOARD_COL;


    @BeforeClass
    public static void setUpClass() {
        BOARD = new GameBoard(); // Initialize the board only once.  Good practice.
    }

    @Before
    public void setUp() {
        // Initialize a fresh T-shape and a clean board *before each test*.  CRITICAL for test isolation.
        tShape = ShapeFactory.getShape(ShapeType.T);
        BOARD.initBoard();
    }

    @Test
    public void testShapeColorCode() {
        int colorCode = tShape.getColorCode();
        System.out.println("The Shape's color code: " + colorCode);
        // More robust check using ShapeColor enum:
        assertTrue("The Shape's color code should be a valid color.",
                Arrays.stream(ShapeColor.values()).anyMatch(color -> color.getCode() == colorCode));
    }

    @Test
    public void testCreateShape_ValidLocations() {
        for (int location = 0; location <= 7; location++) { // More readable loop
            tShape = ShapeFactory.getShape(ShapeType.T, location); // Get a fresh shape for each location
            BOARD.initBoard(); // Ensure a clean board for each test
            BOARD.addShape(tShape.getShape(), null);
            System.out.println("\nTesting location: " + location);
            System.out.println("Shape's location: " + tShape.getLocation());
            BOARD.displayTetrisBoard();

            List<Integer> expectedShape = switch (tShape.getLocation()) {
                case 1 -> Arrays.asList(1, 2, 3, 12);
                case 2 -> Arrays.asList(2, 3, 4, 13);
                case 3 -> Arrays.asList(3, 4, 5, 14);
                case 4 -> Arrays.asList(4, 5, 6, 15);
                case 5 -> Arrays.asList(5, 6, 7, 16);
                case 6 -> Arrays.asList(6, 7, 8, 17);
                case 7 -> Arrays.asList(7, 8, 9, 18);
                default -> Arrays.asList(0, 1, 2, 11);
            };
            assertEquals("Shape creation at location " + location, expectedShape, tShape.getShape());
        }
    }

    @Test
    public void testIsValidStepLeft_EdgeCase() {
        tShape.getShape().set(0, 0); // Directly set the leftmost position to the edge
        assertFalse("Shape at leftmost edge should not be able to step left.", tShape.isValidStepLeft());
    }

    @Test
    public void testIsValidStepLeft_ValidCase() {
        tShape.getShape().set(0, 5); // Set to a position where left movement is possible
        assertTrue("Shape should be able to step left.", tShape.isValidStepLeft());
    }

    @Test
    public void testStepLeft_ValidStep() {
        // Set initial position to allow left movement
        tShape = ShapeFactory.getShape(ShapeType.T, 3); // Start at a known, valid location
        BOARD.addShape(tShape.getShape(), null);
        List<Integer> originalShape = new ArrayList<>(tShape.getShape()); // Create a copy for comparison
        List<Integer> expectedShape = originalShape.stream().map(cell -> cell - 1).collect(Collectors.toList());

        List<Integer> deletedShapeCell = tShape.stepLeft();  // Perform the step

        assertEquals("Shape after valid left step.", expectedShape, tShape.getShape());
        assertFalse("No cells should be deleted.", deletedShapeCell.isEmpty());
        BOARD.initBoard();
        BOARD.addShape(tShape.getShape(),null);
        BOARD.displayTetrisBoard();
    }

    @Test
    public void testStepLeft_InvalidStep() {
        // Force an invalid left step (already at the edge)
        tShape.getShape().set(0, 0);
        List<Integer> originalShape = new ArrayList<>(tShape.getShape());

        List<Integer> deletedShapeCell = tShape.stepLeft();

        assertEquals("Shape should not move on invalid left step.", originalShape, tShape.getShape());
        assertTrue("The cells should be deleted", !deletedShapeCell.isEmpty());
        BOARD.initBoard();
        BOARD.addShape(tShape.getShape(), null);
        BOARD.displayTetrisBoard();
    }

    @Test
    public void testIsValidStepRight_EdgeCase() {
        tShape.getShape().set(2, 9); // Set the rightmost position to the edge
        assertFalse("Shape at rightmost edge should not be able to step right.", tShape.isValidStepRight());
    }

    @Test
    public void testIsValidStepRight_ValidCase() {
        tShape.getShape().set(2, 5); // Set to a position where right movement is possible
        assertTrue("Shape should be able to step right.", tShape.isValidStepRight());
    }

    @Test
    public void testStepRight_ValidStep() {
        tShape = ShapeFactory.getShape(ShapeType.T, 3); // Start at a known, valid location
        BOARD.addShape(tShape.getShape(), null);
        List<Integer> originalShape = new ArrayList<>(tShape.getShape());
        List<Integer> expectedShape = originalShape.stream().map(cell -> cell + 1).collect(Collectors.toList());

        List<Integer> deletedShapeCell = tShape.stepRight();

        assertEquals("Shape after valid right step.", expectedShape, tShape.getShape());
        assertFalse("No cells should be deleted", deletedShapeCell.isEmpty());
         BOARD.initBoard();
        BOARD.addShape(tShape.getShape(),null);
        BOARD.displayTetrisBoard();
    }

    @Test
    public void testStepRight_InvalidStep() {
        tShape.getShape().set(2, 9); // Force invalid right step (already at edge)
        List<Integer> originalShape = new ArrayList<>(tShape.getShape());

        List<Integer> deletedShapeCell = tShape.stepRight();

        assertEquals("Shape should not move on invalid right step.", originalShape, tShape.getShape());
        assertTrue("The cells should be deleted.", !deletedShapeCell.isEmpty());
          BOARD.initBoard();
        BOARD.addShape(tShape.getShape(),null);
        BOARD.displayTetrisBoard();
    }

    @Test
    public void testIsValidStepDown_BottomEdgeCase() {
        tShape.getShape().set(3, 193); // Set to the bottom edge
        assertFalse("Shape at bottom edge should not be able to step down.", tShape.isValidStepDown());
    }

    @Test
    public void testIsValidStepDown_ValidCase() {
        tShape.getShape().set(3, 5); // Set to a position where down movement is possible
        assertTrue("Shape should be able to step down.", tShape.isValidStepDown());
    }
    @Test
    public void testStepDown_ValidStep() {
        tShape = ShapeFactory.getShape(ShapeType.T, 3); // Start at a known, valid location
        BOARD.addShape(tShape.getShape(), null);
        List<Integer> originalShape = new ArrayList<>(tShape.getShape());
        List<Integer> expectedShape = originalShape.stream().map(cell -> cell + BOARD_COL).collect(Collectors.toList());

        List<Integer> deletedShapeCell = tShape.stepDown();

        assertEquals("Shape after valid down step.", expectedShape, tShape.getShape());
        assertFalse("No cells should be deleted.", deletedShapeCell.isEmpty());
        BOARD.initBoard();
        BOARD.addShape(tShape.getShape(),null);
        BOARD.displayTetrisBoard();
    }

    @Test
    public void testStepDown_InvalidStep() {
        tShape.getShape().set(3, 193); // Force invalid down step (already at bottom)
        List<Integer> originalShape = new ArrayList<>(tShape.getShape());

        List<Integer> deletedShapeCell = tShape.stepDown();

        assertEquals("Shape should not move on invalid down step.", originalShape, tShape.getShape());
        assertTrue("The cells should be deleted.", !deletedShapeCell.isEmpty());
           BOARD.initBoard();
        BOARD.addShape(tShape.getShape(),null);
        BOARD.displayTetrisBoard();
    }

    @Test
    public void testIsValidRotation_InvalidCase_NearBottom() {
        // Example: Positioned such that rotation would go out of bounds at the bottom
        tShape.getShape().set(0, 180);
        tShape.getShape().set(1, 181);
        tShape.getShape().set(2, 182);
        tShape.getShape().set(3, 191);
        assertFalse("Shape should not be rotatable if it goes out of bounds (bottom).", tShape.isValidRotation());
    }
    @Test
    public void testIsValidRotation_InvalidCase_NearRightEdge() {
        // Example: Positioned such that rotation would go out of bounds on the right
        tShape.getShape().set(0, 8);
        tShape.getShape().set(1, 9);
        tShape.getShape().set(2, 10);
        tShape.getShape().set(3, 19);
        assertFalse("Shape should not be rotatable if it goes out of bounds (right).", tShape.isValidRotation());

    }
      @Test
    public void testIsValidRotation_InvalidCase_NearLeftEdge() {
        // Example: Positioned such that rotation would go out of bounds on the left
        tShape.getShape().set(0, 1);
        tShape.getShape().set(1, 2);
        tShape.getShape().set(2, 3);
        tShape.getShape().set(3, 11);
        tShape.rotate();
        assertFalse("Shape should not be rotatable if it goes out of bounds (right).", tShape.isValidRotation());

    }

    @Test
    public void testIsValidRotation_ValidCase() {
        // Positioned safely within bounds
        tShape.getShape().set(0, 22);
        tShape.getShape().set(1, 23);
        tShape.getShape().set(2, 24);
        tShape.getShape().set(3, 33);
        assertTrue("Shape should be rotatable within bounds.", tShape.isValidRotation());
    }

    private void setShapePosition(int row, int col) {
        tShape.getShape().set(0, row * BOARD_COL + col);
        tShape.getShape().set(1, row * BOARD_COL + col + 1);
        tShape.getShape().set(2, row * BOARD_COL + col + 2);
        tShape.getShape().set(3, (row + 1) * BOARD_COL + col + 1);
    }
    @Test
    public void testRotation_NormalToRight() {
        setShapePosition(5, 4); // Example safe position
        List<Integer> originalShape = new ArrayList<>(tShape.getShape());
        List<Integer> expectedShape = new ArrayList<>();
        expectedShape.add(originalShape.get(1) - BOARD_COL);
        expectedShape.add(originalShape.get(1));
        expectedShape.add(originalShape.get(1) + BOARD_COL);
        expectedShape.add(originalShape.get(1) -1);
        List<Integer> deletedCells = tShape.rotate();
        assertEquals("Rotation from normal to right.", expectedShape, tShape.getShape());
        assertFalse("No cells should be deleted.", deletedCells.isEmpty());
          BOARD.initBoard();
        BOARD.addShape(tShape.getShape(),null);
        BOARD.displayTetrisBoard();
    }

    @Test
    public void testRotation_RightToUpsideDown() {
        setShapePosition(5, 4);
        tShape.rotate(); // Rotate to right-rotated position first
        List<Integer> originalShape = new ArrayList<>(tShape.getShape());
         List<Integer> expectedShape = new ArrayList<>();
        expectedShape.add(originalShape.get(1) - 1);
        expectedShape.add(originalShape.get(1));
        expectedShape.add(originalShape.get(1) + 1);
        expectedShape.add(originalShape.get(1) - BOARD_COL);
        List<Integer> deletedCells = tShape.rotate(); // Then rotate to upside-down
        assertEquals("Rotation from right to upside-down.", expectedShape, tShape.getShape());
        assertFalse("No cells should be deleted.", deletedCells.isEmpty());
          BOARD.initBoard();
        BOARD.addShape(tShape.getShape(),null);
        BOARD.displayTetrisBoard();
    }

    @Test
    public void testRotation_UpsideDownToLeft() {
        setShapePosition(5, 4);
        tShape.rotate();
        tShape.rotate(); // Rotate to upside-down position first
        List<Integer> originalShape = new ArrayList<>(tShape.getShape());
        List<Integer> expectedShape = new ArrayList<>();
        expectedShape.add(originalShape.get(1) + BOARD_COL);
        expectedShape.add(originalShape.get(1));
        expectedShape.add(originalShape.get(1) - BOARD_COL);
        expectedShape.add(originalShape.get(1) + 1);

        List<Integer> deletedCells = tShape.rotate();
        assertEquals("Rotation from upside-down to left.", expectedShape, tShape.getShape());
         assertFalse("No cells should be deleted.", deletedCells.isEmpty());
           BOARD.initBoard();
        BOARD.addShape(tShape.getShape(),null);
        BOARD.displayTetrisBoard();
    }

    @Test
    public void testRotation_LeftToNormal() {
        setShapePosition(5, 4);
        tShape.rotate();
        tShape.rotate();
        tShape.rotate();// Rotate to left-rotated position first
        List<Integer> originalShape = new ArrayList<>(tShape.getShape());
         List<Integer> expectedShape = new ArrayList<>();
        expectedShape.add(originalShape.get(1) - 1);
        expectedShape.add(originalShape.get(1));
        expectedShape.add(originalShape.get(1) + 1);
        expectedShape.add(originalShape.get(1) + BOARD_COL);

        List<Integer> deletedCells = tShape.rotate();  // Rotate back to normal
        assertEquals("Rotation from left to normal.", expectedShape, tShape.getShape());
         assertFalse("No cells should be deleted.", deletedCells.isEmpty());
           BOARD.initBoard();
        BOARD.addShape(tShape.getShape(),null);
        BOARD.displayTetrisBoard();
    }


    @Test
    public void testRotation_InvalidRotation() {
        setShapePosition(18, 8); // Set a position where rotation will be invalid
        List<Integer> originalShape = new ArrayList<>(tShape.getShape());
        List<Integer> deletedCells = tShape.rotate();
        assertEquals("Shape should not change after invalid rotation.", originalShape, tShape.getShape());
        assertTrue("The cells should be deleted.", !deletedCells.isEmpty());
        BOARD.initBoard();
        BOARD.addShape(tShape.getShape(),null);
        BOARD.displayTetrisBoard();
    }

    // Additional helper methods and tests can be added as needed:
    // - Test collision detection with other shapes (more advanced)
}
