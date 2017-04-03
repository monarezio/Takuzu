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
        start(4);
    }

    public void startGame6(View view) {
        start(6);
    }

    public void startGame8(View view) {
        start(8);
    }

    public void startGame12(View view) {
        start(12);
    }

    public void startGame16(View view) {
        start(16);
    }

    private void start(int gameSize) {
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

        Intent intent = getIntent();
        if (intent != null) {
            SharedPreferences preferences = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
            int score = preferences.getInt("highscore", 0);

            int newScore = intent.getIntExtra("score", 0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("highscore", newScore + score);
            editor.commit();
            scoreTextView.setText("Score " + (newScore + score));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        finishAndRemoveTask();
    }
}
