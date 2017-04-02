package net.zdendukmonarezio.takuzu.presentation.main;

import android.os.Bundle;

import net.zdendukmonarezio.takuzu.presentation.Presenter;

public class MainPresenter extends Presenter<MainView> {

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    public void setScoreText() {
        viewIfExists().subscribe(view -> {
            view.updateScore();
        });
    }
}
