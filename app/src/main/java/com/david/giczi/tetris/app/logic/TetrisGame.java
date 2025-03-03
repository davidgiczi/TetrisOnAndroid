package com.david.giczi.tetris.app.logic;

import android.graphics.drawable.Drawable;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.david.giczi.tetris.app.R;
import com.david.giczi.tetris.app.databinding.FragmentGameBinding;
import com.david.giczi.tetris.app.db.Gamer;

import java.util.ArrayList;
import java.util.List;

public class TetrisGame {

    private FragmentGameBinding display;
    private GameBoard board;
    private Gamer player;
    private Shape actualShape;
    private Shape nextShape;
    private List<TextView> boardCells;

    public TetrisGame(FragmentGameBinding display) {
        this.board = new GameBoard();
        this.display = display;
        initBoard();
    }

    public void setPlayer(Gamer player) {
        this.player = player;
    }

    public void playGame(){
        actualShape = ShapeFactory.getShape(ShapeType.STICK);
        for (Integer cellId : actualShape.getShape()) {
            boardCells.get(cellId).setBackground(getShapeColor(actualShape.getColorCode()));
        }
    }

    private Drawable getShapeColor(int colorCode){
        switch (colorCode){
            case 1:
                return ContextCompat.getDrawable(display.getRoot().getContext(), R.drawable.blue_style);
            case 2:
                return ContextCompat.getDrawable(display.getRoot().getContext(), R.drawable.red_style);
            case 3:
                return ContextCompat.getDrawable(display.getRoot().getContext(), R.drawable.yellow_style);
            case 4:
                return ContextCompat.getDrawable(display.getRoot().getContext(), R.drawable.purple_style);
            case 5:
                return ContextCompat.getDrawable(display.getRoot().getContext(), R.drawable.orange_style);
            default:
                return ContextCompat.getDrawable(display.getRoot().getContext(), R.drawable.green_style);
        }
    }

    private void initBoard(){
        this.boardCells = new ArrayList<>();
        for (int i = 0; i < display.firstRow.getChildCount(); i++) {
            boardCells.add((TextView) display.firstRow.getChildAt(i));
        }
        for (int i = 0; i < display.secondRow.getChildCount(); i++) {
            boardCells.add((TextView) display.secondRow.getChildAt(i));
        }
        for (int i = 0; i < display.thirdRow.getChildCount(); i++) {
            boardCells.add((TextView) display.thirdRow.getChildAt(i));
        }
        for (int i = 0; i < display.forthRow.getChildCount(); i++) {
            boardCells.add((TextView) display.forthRow.getChildAt(i));
        }
        for (int i = 0; i < display.fifthRow.getChildCount(); i++) {
            boardCells.add((TextView) display.fifthRow.getChildAt(i));
        }
        for (int i = 0; i < display.sixthRow.getChildCount(); i++) {
            boardCells.add((TextView) display.sixthRow.getChildAt(i));
        }
        for (int i = 0; i < display.seventhRow.getChildCount(); i++) {
            boardCells.add((TextView) display.seventhRow.getChildAt(i));
        }
        for (int i = 0; i < display.eightRow.getChildCount(); i++) {
            boardCells.add((TextView) display.eightRow.getChildAt(i));
        }
        for (int i = 0; i < display.ninthRow.getChildCount(); i++) {
            boardCells.add((TextView) display.ninthRow.getChildAt(i));
        }
        for (int i = 0; i < display.tenthRow.getChildCount(); i++) {
            boardCells.add((TextView) display.tenthRow.getChildAt(i));
        }
        for (int i = 0; i < display.eleventhRow.getChildCount(); i++) {
            boardCells.add((TextView) display.eleventhRow.getChildAt(i));
        }
        for (int i = 0; i < display.twelfthRow.getChildCount(); i++) {
            boardCells.add((TextView) display.twelfthRow.getChildAt(i));
        }
        for (int i = 0; i < display.thirteenthRow.getChildCount(); i++) {
            boardCells.add((TextView) display.thirteenthRow.getChildAt(i));
        }
        for (int i = 0; i < display.fourteenthRow.getChildCount(); i++) {
            boardCells.add((TextView) display.fourteenthRow.getChildAt(i));
        }
        for (int i = 0; i < display.fifteenthRow.getChildCount(); i++) {
            boardCells.add((TextView) display.fifteenthRow.getChildAt(i));
        }
        for (int i = 0; i < display.sixteenthRow.getChildCount(); i++) {
            boardCells.add((TextView) display.sixteenthRow.getChildAt(i));
        }
        for (int i = 0; i < display.seventeenthRow.getChildCount(); i++) {
            boardCells.add((TextView) display.seventeenthRow.getChildAt(i));
        }
        for (int i = 0; i < display.eighteenthRow.getChildCount(); i++) {
            boardCells.add((TextView) display.eighteenthRow.getChildAt(i));
        }
        for (int i = 0; i < display.nineteenthRow.getChildCount(); i++) {
            boardCells.add((TextView) display.nineteenthRow.getChildAt(i));
        }
        for (int i = 0; i < display.twentiethRow.getChildCount(); i++) {
            boardCells.add((TextView) display.twentiethRow.getChildAt(i));
        }
    }
}
