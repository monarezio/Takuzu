package net.zdendukmonarezio.takuzu.presentation.game;

import android.view.View;

import net.zdendukmonarezio.takuzu.domain.models.Board;

public interface GameView {
    void showGameBoard(Board gameBoard, int gameSize);

    void goToResults();

    void warn(String message);
}
