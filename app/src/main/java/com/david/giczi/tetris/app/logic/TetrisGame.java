package com.david.giczi.tetris.app.logic;

import android.graphics.Color;
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
    private List<TextView> nextCells;

    public TetrisGame(FragmentGameBinding display) {
        this.board = new GameBoard();
        this.display = display;
        initBoard();
    }

    public void setPlayer(Gamer player) {
        this.player = player;
    }

    public void playGame(){
        actualShape = ShapeFactory.getShape(getShapeType());
        nextShape = ShapeFactory.getShape(getShapeType());
        displayShape(null);
        displayNextShape();
    }

    private ShapeType getShapeType(){

        switch ((int) (Math.random() * 5)){
            case 1:
                return ShapeType.STICK;
            case 2:
                return ShapeType.T;
            case 3:
                return ShapeType.RIGHT_L;
            case 4:
                return ShapeType.LEFT_L;
            case 5:
                return ShapeType.RIGHT_WORM;
            case 6:
                return ShapeType.LEFT_WORM;
            default:
                return ShapeType.SQUARE;
        }
    }
    private void displayShape(List<Integer> deletedCells){
        for (Integer shapeCellId : actualShape.getShape()) {
            boardCells.get(shapeCellId).setBackground(getShapeColor(actualShape.getColorCode()));
        }
        if( deletedCells == null ){
            return;
        }
        for (Integer deletedCellId : deletedCells) {
            boardCells.get(deletedCellId).setBackgroundColor(Color.BLACK);
        }
    }

    private void displayNextShape() {
        for (TextView nextCell : nextCells) {
            nextCell.setBackgroundColor(ContextCompat.getColor(display.getRoot().getContext(), R.color.dark_gray));
        }
        if ( nextShape instanceof Stick ) {
            nextCells.get(4).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(5).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(6).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(7).setBackground(getShapeColor(nextShape.getColorCode()));
        }
        else if( nextShape instanceof T) {
            nextCells.get(2).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(5).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(6).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(10).setBackground(getShapeColor(nextShape.getColorCode()));
            }
        else if ( nextShape instanceof Right_L) {
            nextCells.get(1).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(5).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(9).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(10).setBackground(getShapeColor(nextShape.getColorCode()));
        }
        else if ( nextShape instanceof Left_L) {
            nextCells.get(2).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(6).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(10).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(9).setBackground(getShapeColor(nextShape.getColorCode()));
        }
        else if ( nextShape instanceof RightWorm) {
            nextCells.get(1).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(5).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(6).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(10).setBackground(getShapeColor(nextShape.getColorCode()));
        }
        else if ( nextShape instanceof LeftWorm) {
            nextCells.get(2).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(6).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(5).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(9).setBackground(getShapeColor(nextShape.getColorCode()));
        }
        else {
            nextCells.get(5).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(6).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(9).setBackground(getShapeColor(nextShape.getColorCode()));
            nextCells.get(10).setBackground(getShapeColor(nextShape.getColorCode()));
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


    public void stepLeft(){
        displayShape(actualShape.stepLeft());
    }
    public void stepRight(){
        displayShape(actualShape.stepRight());
    }

    public void stepDown(){
        displayShape(actualShape.stepDown());
    }

    public void rotation(){
        displayShape(actualShape.rotate());
    }

    private void initBoard(){
        this.boardCells = new ArrayList<>();
        this.nextCells = new ArrayList<>();
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
        for(int i = 0; i < display.nextFirstRow.getChildCount(); i++){
            nextCells.add((TextView) display.nextFirstRow.getChildAt(i));
        }
        for(int i = 0; i < display.nextSecondRow.getChildCount(); i++){
            nextCells.add((TextView) display.nextSecondRow.getChildAt(i));
        }
        for(int i = 0; i < display.nextThirdRow.getChildCount(); i++){
            nextCells.add((TextView) display.nextThirdRow.getChildAt(i));
        }
    }
}
