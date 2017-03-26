<<<<<<< HEAD
package net.zdendukmonarezio.takuzu.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import net.zdendukmonarezio.takuzu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.view.NucleusActivity;

public class MainActivity extends NucleusActivity<MainPresenter> implements MainView {

    @BindView(R.id.game_board_layout)
    GameBoardLayout gameBoardLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        gameBoardLayout.setOnMoveListener((x, y) -> getPresenter().onMoveMade(x, y));
    }

    @Override
    public void showGameBoard(GameBoard gameBoard) {
        gameBoardLayout.setBoard(gameBoard);
    }

    @Override
    public void warn(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
=======
package net.zdendukmonarezio.takuzu.presentation;

public class MainActivity {

}
>>>>>>> ae0b4c94be9536fa7b876b352a173756ced6e3ea
