package net.zdendukmonarezio.takuzu.presentation;

import android.os.Bundle;

import nucleus.presenter.Presenter;
import nucleus.presenter.RxPresenter;

public class MainPresenter extends RxPresenter<MainView> {

    private Game game;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        game = GameImpl.createNew(6, 4);
        view().subscribe(view -> {
            if (view != null) {
                view.showGameBoard(game.getBoard());
            }
        });
    }

    public void onMoveMade(int x, int y) {
        if (!game.isGameOver()) {
            if (game.isMovePossible(x, y)) {
                GameBoard newGameBoard = game.onMoveMade(x, y);
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
