package net.zdendukmonarezio.takuzu.presentation.game;

import net.zdendukmonarezio.takuzu.domain.models.Board;

public interface GameView {
    void showGameBoard(Board gameBoard);

    void warn(String message);
}
