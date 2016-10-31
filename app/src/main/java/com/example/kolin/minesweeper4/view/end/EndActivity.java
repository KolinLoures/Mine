package com.example.kolin.minesweeper4.view.end;

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

import com.example.kolin.minesweeper4.R;
import com.example.kolin.minesweeper4.view.game.MainActivity;

import java.util.Set;

public class EndActivity extends AppCompatActivity {

    private EndAdapter adapter;
    private Button buttonEnd;
    private Button buttonRetry;
    private Button buttonNewGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_end_toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);

        TextView textView = (TextView) findViewById(R.id.activity_end_result);
        String result = getIntent().getStringExtra("result");
        if (result != null) {
            textView.setText(result);
        }

        adapter = new EndAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_end_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        buttonEnd = (Button) findViewById(R.id.activity_end_button_end);
        buttonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        buttonRetry = (Button) findViewById(R.id.activity_end_button_retry);
        buttonRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRetry();
            }
        });
        buttonNewGame = (Button) findViewById(R.id.activity_end_button_new_game);
        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNewGame();
            }
        });

        checkPreferences();
    }

    private void onClickNewGame() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("retry", 0);
        startActivity(intent);
        finish();
    }

    private void onClickRetry() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("retry", 1);
        startActivity(intent);
        finish();
    }

    private void checkPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.result_name), MODE_PRIVATE);
        Set<String> stringSet = sharedPreferences.getStringSet(getString(R.string.result_key), null);
        if (stringSet != null){
            adapter.addAll(stringSet);
        }
    }

    @Override
    protected void onDestroy() {
        buttonEnd.setOnClickListener(null);
        buttonRetry.setOnClickListener(null);
        buttonNewGame.setOnClickListener(null);
        super.onDestroy();
    }
}
