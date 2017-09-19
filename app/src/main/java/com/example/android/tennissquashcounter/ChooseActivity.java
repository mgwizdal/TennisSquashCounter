package com.example.android.tennissquashcounter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChooseActivity extends AppCompatActivity {
    private Button tennis_button;
    private Button squash_button;
    private EditText playerA_name_edit_text;
    private EditText playerB_name_edit_text;
    String playerA_name, playerB_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        tennis_button = (Button) findViewById(R.id.tennis_button);
        squash_button = (Button) findViewById(R.id.squash_button);
        playerA_name_edit_text = (EditText) findViewById(R.id.edit_text_name_player_a);
        playerB_name_edit_text = (EditText) findViewById(R.id.edit_text_name_player_b);
        tennis_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),TennisActivity.class);
                playerA_name = playerA_name_edit_text.getText().toString();
                playerB_name = playerB_name_edit_text.getText().toString();
                i.putExtra("playerA_name",playerA_name);//to wywal
                i.putExtra("playerB_name",playerB_name);//to wywal
                startActivity(i);

            }
        });
        squash_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SquashActivity.class);
                playerA_name = playerA_name_edit_text.getText().toString();
                playerB_name = playerB_name_edit_text.getText().toString();
                i.putExtra("playerA_name",playerA_name);//to wywal
                i.putExtra("playerB_name",playerB_name);//to wywal
                startActivity(i);
            }
        });
    }
}

