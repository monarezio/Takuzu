package net.zdendukmonarezio.takuzu.presentation.about;

import android.app.Activity;
import android.os.Bundle;

import net.zdendukmonarezio.takuzu.R;

import butterknife.ButterKnife;

public class AboutActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
    }
}
