package net.zdendukmonarezio.takuzu.presentation;

import net.zdendukmonarezio.takuzu.domain.models.Board;

public interface MainView {
    void showGameBoard(Board gameBoard);

    void warn(String message);
}
