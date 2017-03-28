package net.zdendukmonarezio.takuzu.presentation.game;

import android.content.Intent;
import android.os.Bundle;

import net.zdendukmonarezio.takuzu.domain.Game;
import net.zdendukmonarezio.takuzu.domain.Takuzu;
import net.zdendukmonarezio.takuzu.domain.models.Board;

import nucleus.presenter.RxPresenter;

public class GamePresenter extends RxPresenter<GameView> {

    private Takuzu game;

    private int gameSize;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }


    public void onMoveMade(int x, int y) {
        if (!game.isGameOver()) {
            if (game.isMovePossible(x, y)) {
                game = game.onMoveMade(x, y);
                Board newGameBoard = game.getGameBoard();
                view().subscribe(view -> {
                    if (view != null) {
                        view.showGameBoard(newGameBoard, gameSize);
                    }
                });
                if (game.isGameOver()) {
                    view().subscribe(view -> {
                        if (view != null) {
                            /* do something */
                        }
                    });
                }
            } else {
                view().subscribe(view -> {
                    if (view != null) {
                        view.warn("Unable to make move");
                    }
                });
            }
        }
    }

    public void setGameSize(int gameSize) {
        this.gameSize = gameSize;
    }

    public void setupGame() {
        game = Game.createNew(gameSize, gameSize);
        view().subscribe(view -> {
            if (view != null) {
                view.showGameBoard(game.getGameBoard(), gameSize);
            }
        });
    }
}
