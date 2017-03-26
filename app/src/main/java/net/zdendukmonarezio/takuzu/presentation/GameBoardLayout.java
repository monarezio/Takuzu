package net.zdendukmonarezio.takuzu.presentation;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.GridLayout;

import net.zdendukmonarezio.takuzu.domain.models.Board;
import net.zdendukmonarezio.takuzu.domain.models.GameBoard;

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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GameBoardLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setBoard(Board gameBoard) {
        removeAllViews();
        setRowCount(gameBoard.rows());
        setColumnCount(gameBoard.columns());

        for (int r = 0; r < gameBoard.rows(); r++) {
            for (int c = 0; c < gameBoard.columns(); c++) {
                System.out.println(gameBoard.getFields().get(r).get(c));
                BoardFieldWidget field = new BoardFieldWidget(getContext(), gameBoard.getFields().get(r).get(c), r, c);
                addView(field);
            }
        }

        if (listener != null) {
            setupFieldListeners(listener);
        }
    }

    public void setOnMoveListener(OnMoveListener listener) {
        this.listener = listener;
        setupFieldListeners(listener);
    }

    public void setupFieldListeners(OnMoveListener listener) {
        for (int i = 0; i < getChildCount(); i++) {
            BoardFieldWidget child = (BoardFieldWidget) getChildAt(i);
            GridLayout.LayoutParams lp = (LayoutParams) child.getLayoutParams();
            child.setOnClickListener(v -> listener.onMoveMade(child.getRow(), child.getColumn()));
        }
    }

    public interface OnMoveListener {
        void onMoveMade(int x, int y);
    }
}


