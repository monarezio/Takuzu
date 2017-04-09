package net.zdendukmonarezio.takuzu.presentation.game;

import android.os.Bundle;

import net.zdendukmonarezio.takuzu.domain.game.Game;
import net.zdendukmonarezio.takuzu.domain.game.Takuzu;
import net.zdendukmonarezio.takuzu.domain.game.models.game.Board;
import net.zdendukmonarezio.takuzu.domain.game.models.hint.models.Hint;
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
        System.out.println(game.getGameBoard().getFields());
        if (!game.isGameOver()) {
            if (game.isMovePossible(x, y)) {
                if (game.isBoardFilled()) {
                    viewIfExists().subscribe(view -> {
                        showMistakes();
                    });
                }
                game = game.onMoveMade(x, y);
                Board newGameBoard = game.getGameBoard();
                viewIfExists().subscribe(view -> {
                    view.showGameBoard(newGameBoard, gameSize);
                    view.updatePercentStatus(game.getGameBoard().getProgress());
                    view.setNotification("");
                });
                if (game.isGameOver()) {
                    viewIfExists().subscribe(view -> {
                        view.goToResults(gameSize * gameSize);
                    });
                }
            } else {
                viewIfExists().subscribe(view -> {
                    List<Pair<Integer, Integer>> pairs = game.getGameBoard().getLockedFields();
                    view.highlightWrongFields(game.getGameBoard(), gameSize, pairs);
                    view.setNotification("These fields are locked");
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

    public void showMistakes() {
        Hint hint = game.getWrongFields();
        viewIfExists().subscribe(view -> {
            switch (hint.component2()) {
                case ROWS_EQUAL:
                    view.setNotification("There can't be any equal rows.");
                    break;
                case COLUMNS_EQUAL:
                    view.setNotification("There can't be any equal columns.");
                    break;
                case FIELDS_DO_NOT_EQUAL_IN_ROW:
                    view.setNotification("There is an equal number of red fields and blue fields in each row");
                    break;
                case FIELD_DO_NOT_EQUAL_IN_COLUMN:
                    view.setNotification("There is an equal number of red fields and blue fields in each column");
                    break;
            }
            view.highlightWrongFields(game.getGameBoard(), gameSize, hint.getCoords());
        });
    }
}
