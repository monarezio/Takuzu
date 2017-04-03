package net.zdendukmonarezio.takuzu.presentation.game;

import net.zdendukmonarezio.takuzu.domain.models.game.Board;

import java.util.List;

import kotlin.Pair;

public interface GameView {
    /*
     *  shows Game Board with fields of appropriate size
     */
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

    /*
     *  updates TextView percent_counter with corresponding data
     */
    void updatePercentStatus(int percent);
}
