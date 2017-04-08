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
        ScoreManager manager = ScoreManager.createScoreManager(this);
        manager.getScore().subscribe(integer -> scoreAnnouncer.setText(integer));
    }

    public void backToMainMenu(View view) {
        finish();
    }
}