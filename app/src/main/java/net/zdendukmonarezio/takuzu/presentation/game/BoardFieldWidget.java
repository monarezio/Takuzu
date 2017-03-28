package net.zdendukmonarezio.takuzu.presentation.game;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;

import net.zdendukmonarezio.takuzu.R;
import net.zdendukmonarezio.takuzu.domain.models.Field;

public class BoardFieldWidget extends View {

    private Paint gridPaint;
    private Field gameField;
    private int row;
    private int column;

    public BoardFieldWidget(Context context, Field gameField, int row, int column) {
        super(context);
        init(gameField, row, column);
    }

    private void init(Field gameField, int row, int column) {
        this.gameField = gameField;
        this.row = row;
        this.column = column;
        setClickable(true);

        gridPaint = new Paint();
        gridPaint.setColor(gameField == Field.ANON ? ContextCompat.getColor(getContext(), R.color.anonFieldColor) : gameField == Field.BLUE ?
                ContextCompat.getColor(getContext(), R.color.blueFieldColor): ContextCompat.getColor(getContext(), R.color.redFieldColor));

        int[] attrs = new int[]{R.attr.selectableItemBackground};
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs);
        int backgroundResource = typedArray.getResourceId(0, 0);
        setBackgroundResource(backgroundResource);
        typedArray.recycle();

        float fieldSize = getResources().getDimension(R.dimen.field_size);
        GridLayout.LayoutParams param = new GridLayout.LayoutParams();
        param.height = (int) fieldSize;
        param.width = (int) fieldSize;
        param.rightMargin = 4;
        param.topMargin = 4;
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

        canvas.drawRoundRect(new RectF(10, 10, width, height), 50, 50, gridPaint);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
