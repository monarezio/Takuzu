package net.zdendukmonarezio.takuzu.presentation.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;

import net.zdendukmonarezio.takuzu.R;
import net.zdendukmonarezio.takuzu.domain.score.ScoreManager;
import net.zdendukmonarezio.takuzu.presentation.about.AboutActivity;
import net.zdendukmonarezio.takuzu.presentation.game.GameActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActivity;


@RequiresPresenter(MainPresenter.class)
public class MainActivity extends NucleusActivity<MainPresenter> implements MainView {

    @BindView(R.id.score)
    TextView scoreTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getPresenter().setScoreText();
    }

    public void startGame4(View view) {
        getPresenter().onButtonClick(4);
    }

    public void startGame6(View view) {
        getPresenter().onButtonClick(6);
    }

    public void startGame8(View view) {
        getPresenter().onButtonClick(8);
    }

    public void startGame12(View view) {
        getPresenter().onButtonClick(12);
    }

    public void startGame16(View view) {
        getPresenter().onButtonClick(16);
    }

    public void start(int gameSize) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("gameSize", gameSize);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void showAbout(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public void updateScore() {
        ScoreManager manager = ScoreManager.createScoreManager(this);
        manager.getScore().subscribe(integer -> scoreTextView.setText(integer.toString()));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        finishAndRemoveTask();
    }
}
