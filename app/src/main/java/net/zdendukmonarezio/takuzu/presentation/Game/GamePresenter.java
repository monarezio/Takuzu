package net.zdendukmonarezio.takuzu.presentation.Game;

import android.os.Bundle;

import net.zdendukmonarezio.takuzu.domain.Game;
import net.zdendukmonarezio.takuzu.domain.models.Board;

import nucleus.presenter.RxPresenter;

public class GamePresenter extends RxPresenter<GameView> {

    private Game game;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        game = Game.createNew(4,4);

        view().subscribe(view -> {
            if (view != null) {
                view.showGameBoard(game.getGameBoard());
            }
        });
    }

    public void onMoveMade(int x, int y) {
        if (!game.isGameOver()) {
            if (game.isMovePossible(x, y)) {
                Board newGameBoard = game.onMoveMade(x, y).getGameBoard();
                view().subscribe(view -> {
                    if (view != null) {
                        view.showGameBoard(newGameBoard);
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
}
