package com.example.kolin.minesweeper4.view.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kolin.minesweeper4.R;
import com.example.kolin.minesweeper4.model.Cell;
import com.example.kolin.minesweeper4.presenter.GamePresenter;
import com.example.kolin.minesweeper4.presenter.common.GridSpacingItemDecoration;
import com.example.kolin.minesweeper4.view.end.EndActivity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements GameView {

    private GamePresenter gamePresenter;
    private CellAdapter adapter;
    private TextView textView;
    private Button button;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);

        adapter = new CellAdapter();
        gamePresenter = new GamePresenter();
        gamePresenter.attacheView(this);
        if (getIntent().getIntExtra("retry", 0) == 0) {
            gamePresenter.createMatrix(true);
        } else {
            gamePresenter.createMatrix(false);
        }

        recyclerView = (RecyclerView) findViewById(R.id.main_activity_rv);
        textView = (TextView) findViewById(R.id.main_activity_score);
        button = (Button) findViewById(R.id.activity_main_show_results);
        button.setVisibility(View.INVISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickShowResults();
            }
        });


        recyclerView.setAdapter(adapter);
        GridLayoutManager layout = new GridLayoutManager(getApplicationContext(), gamePresenter.getCurrentSize());
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(gamePresenter.getCurrentSize(), 2, true, 0));
        recyclerView.setLayoutManager(layout);

        adapter.setCellAdapterListener(new CellAdapter.CellAdapterListener() {
            @Override
            public void onClickItemView(int xPos, int yPos) {
                gamePresenter.onClickItemView(xPos, yPos);
            }
        });
    }

    private void onClickShowResults() {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.result_name), MODE_PRIVATE);
        Set<String> stringSet = sharedPreferences.getStringSet(getString(R.string.result_key), null);
        if (stringSet != null) {
            stringSet.add(textView.getText().toString());
        } else {
            stringSet = new HashSet<>();
            stringSet.add(textView.getText().toString());
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putStringSet(getString(R.string.result_key), stringSet);
        edit.commit();

        Intent intent = new Intent(getApplicationContext(), EndActivity.class);
        intent.putExtra("result", textView.getText());
        startActivity(intent);
        finish();
    }

    @Override
    public void showGrid(List<Cell> cells) {
        adapter.addAll(cells);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setTextScore(String text) {
        textView.setText(text);
    }

    @Override
    public void endGame() {
        button.setVisibility(View.VISIBLE);
        recyclerView.setEnabled(false);
    }

    @Override
    protected void onDestroy() {
        gamePresenter.detacheView();
        adapter.setCellAdapterListener(null);
        super.onDestroy();
    }
}
