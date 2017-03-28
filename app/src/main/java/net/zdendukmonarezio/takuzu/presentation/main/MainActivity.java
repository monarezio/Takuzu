package net.zdendukmonarezio.takuzu.presentation.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import net.zdendukmonarezio.takuzu.R;
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
    public void startGame16(View view) {
        start(16);
    }
    public void startGame32(View view) {
        start(32);
    }
    public void startGame64(View view) {
        start(64);
    }

    private void start(int gameType) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("Type", gameType);
        startActivity(intent);
    }
}
