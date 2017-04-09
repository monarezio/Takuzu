package net.zdendukmonarezio.takuzu.presentation.game;

import net.zdendukmonarezio.takuzu.domain.game.models.game.Board;
import net.zdendukmonarezio.takuzu.domain.game.models.hint.models.Hint;

import java.util.List;

import kotlin.Pair;

public interface GameView {
    /*
     *  shows Game Board with fields of appropriate size
     */
    void showGameBoard(Board gameBoard, int gameSize);

    /*
     *  goes to result screen and adds score to score manager
     */
    void goToResults(int score);

    /*
     *  makes a toast with message in parameter
     */
    void warn(String message);

    /*
     *  highlights wrong fields with white outline and writes corresponding message
     */

    void updatePercentStatus(int percent);

    /*
     *  renews Game Board with highlighted locked fields
     */
    void highlightWrongFields(Board gameBoard, int gameSize, List<Pair<Integer, Integer>> pairs);

    /*
     *  shows notification on notification TextView
     */
    void setNotification(String message);
}
