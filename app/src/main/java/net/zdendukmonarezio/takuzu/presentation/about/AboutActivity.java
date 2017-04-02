package net.zdendukmonarezio.takuzu.presentation.about;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import net.zdendukmonarezio.takuzu.R;
import net.zdendukmonarezio.takuzu.presentation.main.MainActivity;

import butterknife.ButterKnife;

public class AboutActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
    }

    public void backToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
