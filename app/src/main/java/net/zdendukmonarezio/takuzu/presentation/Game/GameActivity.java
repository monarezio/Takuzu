package net.zdendukmonarezio.takuzu.presentation.game;

import android.os.Bundle;
import android.widget.Toast;

import net.zdendukmonarezio.takuzu.R;
import net.zdendukmonarezio.takuzu.domain.models.Board;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActivity;

@RequiresPresenter(GamePresenter.class)
public class GameActivity extends NucleusActivity<GamePresenter> implements GameView {

    @BindView(R.id.game_board_layout)
    GameBoardLayout gameBoardLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        gameBoardLayout.setOnMoveListener((x, y) -> getPresenter().onMoveMade(x, y));
    }

    @Override
    public void showGameBoard(Board gameBoard) {
        gameBoardLayout.setBoard(gameBoard);
    }

    @Override
    public void warn(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
