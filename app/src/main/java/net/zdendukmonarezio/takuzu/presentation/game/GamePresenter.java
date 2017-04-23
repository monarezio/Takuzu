package net.zdendukmonarezio.takuzu.presentation.game;

import android.os.Bundle;
import android.os.Handler;

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
                presentMove(x, y);
                if (game.isGameOver()) {
                    viewIfExists().subscribe(view -> {
                        view.goToResults(gameSize * gameSize);
                    });
                }
            } else {
                viewIfExists().subscribe(view -> {
                    clickedOnLocked(view);
                });
            }
        }
    }

    private void clickedOnLocked(GameView view) {
        List<Pair<Integer, Integer>> pairs = game.getGameBoard().getLockedFields();
        view.highlightWrongFields(game.getGameBoard(), gameSize, pairs);
        view.setNotification("These fields are locked");
    }

    private void presentMove(int x, int y) {
        game = game.onMoveMade(x, y);
        Board newGameBoard = game.getGameBoard();
        viewIfExists().subscribe(view -> {
            view.showGameBoard(newGameBoard, gameSize);
            view.updatePercentStatus(game.getGameBoard().getProgress());
            view.setNotification("");
        });
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
            view.setNotification("");
        });
    }

    public void showMistakes() {
        Hint hint = game.getWrongFields();
        viewIfExists().subscribe(view -> {
            switch (hint.component2()) {
                /*
                 * ERRORS
                 */
                case ROWS_EQUAL:
                    view.setNotification("There can't be any equal rows");
                    break;
                case COLUMNS_EQUAL:
                    view.setNotification("There can't be any equal columns");
                    break;
                case FIELDS_DO_NOT_EQUAL_IN_ROW:
                    view.setNotification("There is not an equal number of red fields and blue fields in each row");
                    break;
                case FIELD_DO_NOT_EQUAL_IN_COLUMN:
                    view.setNotification("There is not an equal number of red fields and blue fields in each column");
                    break;
                /*
                 * HINTS
                 */
                case ROWS_HAS_EQUAL_NUMBER_OF_EACH_FIELD:
                    view.setNotification("There should be an equal number of red fields and blue fields in each row");
                    break;
                case COLUMNS_HAS_EQUAL_NUMBER_OF_EACH_FIELD:
                    view.setNotification("There should be an equal number of red fields and blue fields in each columns");
                    break;
                case THREE_TILES_HINT:
                    view.setNotification("There can be max 2 fields consecutively");
                    break;
                case ONLY_ONE_POSSIBLE_COMBINATION:
                    view.setNotification("There should be no equivalent rows or columns");
                    break;
                case NO_HINT_AVAILABLE:
                    view.setNotification("No hint is available for this situation");
                    break;
            }
            view.highlightWrongFields(game.getGameBoard(), gameSize, hint.getCoords());
        });
        hideMistakesDelay();
    }

    private void hideMistakesDelay() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            viewIfExists().subscribe(view -> {
                view.showGameBoard(game.getGameBoard(), gameSize);
                view.fadeOutNotification();
            });
        }, 3000);
    }
}
