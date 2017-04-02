package net.zdendukmonarezio.takuzu.presentation.game;

import android.view.View;

import net.zdendukmonarezio.takuzu.domain.models.Board;

import java.util.List;

import kotlin.Pair;

public interface GameView {
    void showGameBoard(Board gameBoard, int gameSize);

    /*
     *  goes to result screen and puts score as an extra
     *  score = game size^2
     */
    void goToResults(int score);

    /*
     *  makes a toast with message in parameter
     */
    void warn(String message);

    /*
     *  highlights wrong fields with white outline
     */
    void highlightWrongFields(List<Pair<Integer, Integer>> pairs);

    void updatePercentStatus(int percent);
}
