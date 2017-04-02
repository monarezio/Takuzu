package net.zdendukmonarezio.takuzu.presentation.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import net.zdendukmonarezio.takuzu.R;
import net.zdendukmonarezio.takuzu.domain.models.Board;
import net.zdendukmonarezio.takuzu.presentation.results.ResultsActivity;

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
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        int gameSize = getIntent().getIntExtra("gameSize", 4);

        GamePresenter presenter = getPresenter();
        presenter.setGameSize(gameSize);
        presenter.setupGame();
        gameBoardLayout.setOnMoveListener((x, y) -> presenter.onMoveMade(x, y));
    }

    @Override
    public void showGameBoard(Board gameBoard, int gameSize) {
        gameBoardLayout.setBoard(gameBoard, gameSize);
    }

    @Override
    public void warn(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void goToResults() {
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivity(intent);
    }
}
