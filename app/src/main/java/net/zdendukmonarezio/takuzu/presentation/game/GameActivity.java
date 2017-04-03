package net.zdendukmonarezio.takuzu.presentation.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.zdendukmonarezio.takuzu.R;
import net.zdendukmonarezio.takuzu.domain.game.models.game.Board;
import net.zdendukmonarezio.takuzu.presentation.results.ResultsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kotlin.Pair;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActivity;

@RequiresPresenter(GamePresenter.class)
public class GameActivity extends NucleusActivity<GamePresenter> implements GameView {

    @BindView(R.id.game_board_layout)
    GameBoardLayout gameBoardLayout;

    @BindView(R.id.percent_counter)
    TextView percentCounter;

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
    public void highlightWrongFields(List<Pair<Integer, Integer>> pairs) {
        // TODO
    }

    @Override
    public void updatePercentStatus(int percent) {
        percentCounter.setText(percent + "%");
    }

    @Override
    public void goToResults(int score) {
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
    }

    public void resetBoard(View view) {
        getPresenter().resetGame();
    }

}
