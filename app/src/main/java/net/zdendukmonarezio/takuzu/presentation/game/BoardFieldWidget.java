package net.zdendukmonarezio.takuzu.presentation.game;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;

import net.zdendukmonarezio.takuzu.R;
import net.zdendukmonarezio.takuzu.domain.game.models.game.Field;

import java.util.List;

import kotlin.Pair;

public class BoardFieldWidget extends View {

    private Paint gridPaint;
    private Paint borderPaint;
    private Field gameField;
    private int row;
    private int column;
    private int fieldWidth;
    private boolean locked;


    public BoardFieldWidget(Context context, Field gameField, int row, int column, int fieldWidth) {
        super(context);
        init(gameField, row, column, fieldWidth);
        locked = false;
    }

    public BoardFieldWidget(Context context, Field gameField, int row, int column, int fieldWidth, List<Pair<Integer, Integer>> pairs) {
        super(context);
        init(gameField, row, column, fieldWidth);

        for (Pair<Integer, Integer> pair : pairs) {
            if (pair.component1() == row && pair.component2() == column) {
                locked = true;
            }
        }
        borderPaint = new Paint();
        borderPaint.setColor(ContextCompat.getColor(getContext(), R.color.white));
    }

    private void init(Field gameField, int row, int column, int fieldWidth) {
        this.gameField = gameField;
        this.row = row;
        this.column = column;
        setClickable(true);

        gridPaint = new Paint();
        gridPaint.setColor(gameField == Field.ANON ? ContextCompat.getColor(getContext(), R.color.anonFieldColor) : gameField == Field.BLUE ?
                ContextCompat.getColor(getContext(), R.color.blueFieldColor) : ContextCompat.getColor(getContext(), R.color.redFieldColor));

        /*
                            ____
                          .'* *.'
                       __/_*_*(_
                      / _______ \
                     _\_)/___\(_/_
                    / _((\- -/))_ \
                    \ \())(-)(()/ /
                     ' \(((()))/ '
                    / ' \)).))/ ' \     THIS WIZARD MAKES ALL THE COOL STUFF HAPPEN.
                   / _ \ - | - /_  \    NO ONE KNOWS WHY IT WORKS BUT IT WORKS.
                  (   ( .;''';. .'  )   DO NOT TOUCH.
                  _\"__ /    )\ __"/_
                    \/  \   ' /  \/
                     .'  '...' ' )
                      / /  |  \ \
                     / .   .   . \
                    /   .     .   \
                   /   /   |   \   \
                 .'   /    b    '.  '.
             _.-'    /     Bb     '-. '-._
         _.-'       |      BBb       '-.  '-.
        (________mrf\____.dBBBb.________)____)

      */

        int[] attrs = new int[]{R.attr.selectableItemBackground};
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs);
        int backgroundResource = typedArray.getResourceId(0, 0);
        setBackgroundResource(backgroundResource);
        typedArray.recycle();

        float fieldSize = fieldWidth;
        GridLayout.LayoutParams param = new GridLayout.LayoutParams();
        param.height = (int) fieldSize;
        param.width = (int) fieldSize;
        param.rightMargin = (int) getResources().getDimension(R.dimen.field_margin);
        param.topMargin = (int) getResources().getDimension(R.dimen.field_margin);
        param.setGravity(Gravity.CENTER);
        param.rowSpec = GridLayout.spec(row);
        param.columnSpec = GridLayout.spec(column);
        setLayoutParams(param);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        if (locked) {
            canvas.drawRoundRect(new RectF(10, 10, width, height), 50, 50, borderPaint);
            canvas.drawRoundRect(new RectF(20, 20, width - 10, height - 10), 50, 50, gridPaint);
        } else {
            canvas.drawRoundRect(new RectF(10, 10, width, height), 50, 50, gridPaint);
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
