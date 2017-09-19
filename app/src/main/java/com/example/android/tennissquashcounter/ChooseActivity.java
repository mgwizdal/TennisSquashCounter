package com.example.android.tennissquashcounter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.tennissquashcounter.R.id.squash_button;
import static com.example.android.tennissquashcounter.R.id.tennis_button;

public class ChooseActivity extends AppCompatActivity {

    String playerA_name, playerB_name;
    private boolean grandSlamChecker = false;
    @BindView(R.id.edit_text_name_player_a) EditText playerANameEditText;
    @BindView(R.id.edit_text_name_player_b) EditText playerBNameEditText;
    @BindView(R.id.tennis_button) Button tennisButton;
    @BindView(R.id.squash_button) Button squashButton;
    @BindView(R.id.simpleSwitch) Switch switchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        ButterKnife.bind(this);

        switchButton.setChecked(grandSlamChecker);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!grandSlamChecker) {
                    grandSlamChecker = true;
                    Log.i("Choose", "grandSlamChecker: "+grandSlamChecker);
                }else {
                    grandSlamChecker = false;
                    Log.i("Choose", "grandSlamChecker: "+grandSlamChecker);
                }
            }
        });

        tennisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),TennisActivity.class);
                playerA_name = playerANameEditText.getText().toString();
                playerB_name = playerBNameEditText.getText().toString();
                if(grandSlamChecker){
                    i.putExtra(getString(R.string.intent_grand_slam), grandSlamChecker);
                    Log.i("Choose", "grandSlamChecker: " + grandSlamChecker);
                }
                i.putExtra(getString(R.string.playerA_name),playerA_name);//to wywal
                i.putExtra(getString(R.string.playerB_name),playerB_name);//to wywal
                startActivity(i);

            }
        });
        squashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SquashActivity.class);
                playerA_name = playerANameEditText.getText().toString();
                playerB_name = playerBNameEditText.getText().toString();
                i.putExtra(getString(R.string.playerA_name),playerA_name);//to wywal
                i.putExtra(getString(R.string.playerB_name),playerB_name);//to wywal
                startActivity(i);
            }
        });
    }
}

