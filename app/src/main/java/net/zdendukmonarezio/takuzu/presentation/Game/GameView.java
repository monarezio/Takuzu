package net.zdendukmonarezio.takuzu.presentation.Game;

import net.zdendukmonarezio.takuzu.domain.models.Board;

public interface GameView {
    void showGameBoard(Board gameBoard);

    void warn(String message);
}
