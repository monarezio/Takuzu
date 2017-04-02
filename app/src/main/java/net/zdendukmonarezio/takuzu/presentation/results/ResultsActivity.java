package net.zdendukmonarezio.takuzu.presentation.results;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import net.zdendukmonarezio.takuzu.R;
import net.zdendukmonarezio.takuzu.presentation.main.MainActivity;

import butterknife.ButterKnife;

public class ResultsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);
    }

    public void backToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("score", getIntent().getIntExtra("score", 0));
        startActivity(intent);
    }
}