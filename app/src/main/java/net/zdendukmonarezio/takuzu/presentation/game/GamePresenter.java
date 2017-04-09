package net.zdendukmonarezio.takuzu.presentation.game;

import android.os.Bundle;

import net.zdendukmonarezio.takuzu.domain.game.Game;
import net.zdendukmonarezio.takuzu.domain.game.Takuzu;
import net.zdendukmonarezio.takuzu.domain.game.models.game.Board;
import net.zdendukmonarezio.takuzu.domain.score.ScoreManager;
import net.zdendukmonarezio.takuzu.presentation.Presenter;

import java.util.List;

import kotlin.Pair;

public class GamePresenter extends Presenter<GameView> {

    private Takuzu game;
    private Takuzu newGameState;
    private int gameSize;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    public void onMoveMade(int x, int y) {
        if (!game.isGameOver()) {
            if (game.isMovePossible(x, y)) {
                if (game.isBoardFilled()) {
                    List<Pair<Integer, Integer>> pairs = game.getWrongFields().getCoords();
                    viewIfExists().subscribe(view -> {
                        view.highlightWrongFields(pairs);
                    });
                }
                game = game.onMoveMade(x, y);
                Board newGameBoard = game.getGameBoard();
                viewIfExists().subscribe(view -> {
                    view.showGameBoard(newGameBoard, gameSize);
                    view.updatePercentStatus(game.getGameBoard().getProgress());
                });
                if (game.isGameOver()) {
                    viewIfExists().subscribe(view -> {
                        view.goToResults(gameSize * gameSize);
                    });
                }
            } else {
                viewIfExists().subscribe(view -> {
                    List<Pair<Integer, Integer>> pairs = game.getGameBoard().getLockedFields();
                    view.clickedOnLocked(game.getGameBoard(), gameSize, pairs);
                    view.warn("These fields are locked");
                });
            }
        }
    }

    public void setGameSize(int gameSize) {
        this.gameSize = gameSize;
    }

    public void setupGame() {
        game = Game.createNew(gameSize, gameSize);
        newGameState = game;
        viewIfExists().subscribe(view -> {
            view.showGameBoard(game.getGameBoard(), gameSize);
        });
    }

    public void resetGame() {
        game = newGameState;
        viewIfExists().subscribe(view -> {
            view.showGameBoard(game.getGameBoard(), gameSize);
            view.updatePercentStatus(0);
        });
    }
}
