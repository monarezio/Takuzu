package net.zdendukmonarezio.takuzu.presentation.results;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.zdendukmonarezio.takuzu.R;
import net.zdendukmonarezio.takuzu.domain.score.ScoreManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultsActivity extends Activity {

    @BindView(R.id.scoreAnnouncer)
    TextView scoreAnnouncer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);
        setScoreAnnouncerText();
    }

    private void setScoreAnnouncerText() {
        int score = getIntent().getIntExtra("score", 0);
        scoreAnnouncer.setText("Score +" + score);
    }

    public void backToMainMenu(View view) {
        finish();
    }
}