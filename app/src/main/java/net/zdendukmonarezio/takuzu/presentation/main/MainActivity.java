package net.zdendukmonarezio.takuzu.presentation.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import net.zdendukmonarezio.takuzu.R;
import net.zdendukmonarezio.takuzu.presentation.about.AboutActivity;
import net.zdendukmonarezio.takuzu.presentation.game.GameActivity;

import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
        startActivity(intent);
    }

    public void showAbout(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

}
