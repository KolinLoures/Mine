package com.example.kolin.minesweeper4.view.start;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kolin.minesweeper4.R;
import com.example.kolin.minesweeper4.view.game.MainActivity;

public class StartActivity extends AppCompatActivity {

    private TextView textViewError;
    private EditText editTextName;
    private FloatingActionButton fab;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_start_toolbar);
        editTextName = (EditText) findViewById(R.id.start_activity_edit_text);
        textViewError = (TextView) findViewById(R.id.start_activity_error_name);
        fab = (FloatingActionButton) findViewById(R.id.activity_start_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickFab();
            }
        });

        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);

        setTextWatcher();
        checkPreferences();
    }

    private void checkPreferences() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        String string = sharedPreferences.getString(getString(R.string.key_name), null);
        if (string != null){
            editTextName.setText(string);
        }
    }

    private void setTextWatcher() {
        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    textViewError.setVisibility(View.VISIBLE);
                } else {
                    textViewError.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        editTextName.addTextChangedListener(null);
        super.onDestroy();
    }

    private void onClickFab(){
        String text = editTextName.getText().toString();
        if (text.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Check your name!", Toast.LENGTH_LONG).show();
        } else {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(getString(R.string.key_name), text);
            edit.apply();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
