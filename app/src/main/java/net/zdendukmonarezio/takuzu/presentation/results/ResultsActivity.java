package net.zdendukmonarezio.takuzu.presentation.results;

import android.app.Activity;
import android.os.Bundle;

import net.zdendukmonarezio.takuzu.R;

import butterknife.ButterKnife;


public class ResultsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);
    }
}