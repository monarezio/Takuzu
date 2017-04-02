package net.zdendukmonarezio.takuzu.presentation.game;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;
import android.widget.GridLayout;

import net.zdendukmonarezio.takuzu.R;
import net.zdendukmonarezio.takuzu.domain.models.Board;

public class GameBoardLayout extends GridLayout {

    private OnMoveListener listener;

    public GameBoardLayout(Context context) {
        super(context);
    }

    public GameBoardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameBoardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public GameBoardLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setBoard(Board gameBoard, int gameSize) {
        removeAllViews();
        setRowCount(gameBoard.rows());
        setColumnCount(gameBoard.columns());
        int fieldWidth = getFieldWidth(gameSize);

        for (int r = 0; r < gameBoard.rows(); r++) {
            for (int c = 0; c < gameBoard.columns(); c++) {
                BoardFieldWidget field = new BoardFieldWidget(getContext(), gameBoard.getFields().get(r).get(c), r, c, fieldWidth);
                addView(field);
            }
        }

        if (listener != null) {
            setupFieldListeners(listener);
        }
    }

    private int getFieldWidth(int gameSize) {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int wWidth = size.x;
        System.out.println(wWidth);
        return ((wWidth - getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin) * 2) - (gameSize - 1) * getResources().getDimensionPixelSize(R.dimen.field_margin)) / gameSize;
    }

    public void setOnMoveListener(OnMoveListener listener) {
        this.listener = listener;
        setupFieldListeners(listener);
    }

    private void setupFieldListeners(OnMoveListener listener) {
        for (int i = 0; i < getChildCount(); i++) {
            BoardFieldWidget child = (BoardFieldWidget) getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            child.setOnClickListener(v -> listener.onMoveMade(child.getRow(), child.getColumn()));
        }
    }

    interface OnMoveListener {
        void onMoveMade(int x, int y);
    }
}
