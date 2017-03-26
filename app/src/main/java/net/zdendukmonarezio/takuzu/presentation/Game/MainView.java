package net.zdendukmonarezio.takuzu.presentation.Game;

import net.zdendukmonarezio.takuzu.domain.models.Board;
import net.zdendukmonarezio.takuzu.domain.models.GameBoard;

public interface MainView {
    void showGameBoard(Board gameBoard);

    void warn(String message);
}
