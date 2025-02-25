package com.david.giczi.tetris.app;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.david.giczi.tetris.app.logic.GameBoard;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GameBoardUnitTest {

    @BeforeClass
    public static void createGameBoard(){
        GameBoard BOARD = new GameBoard();
        BOARD.displayTetrisBoard();
    }

    @Test
    public void testSizeOfGameBoard(){
        assertEquals(GameBoard.BOARD_COL * GameBoard.BOARD_ROW, GameBoard.TETRIS_BOARD.size());
    }
    @Test
    public void testCreatedGameBoard(){
        for (Boolean boardCell : GameBoard.TETRIS_BOARD) {
            assertEquals(false, boardCell);
        }
    }

}