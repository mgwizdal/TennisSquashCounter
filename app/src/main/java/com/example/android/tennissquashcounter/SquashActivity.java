package com.example.android.tennissquashcounter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.tennissquashcounter.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SquashActivity extends AppCompatActivity {
    private int scorePlayerAPoint=0;
    private int scorePlayerBPoint=0;
    private int scorePlayerASet=0;
    private int scorePlayerBSet=0;

    String namePlayerA;
    String namePlayerB;

    @BindView(R.id.player_a_name_text_view) TextView playerANameTextView;
    @BindView(R.id.player_b_name_text_view) TextView playerBNameTextView;
    @BindView(R.id.addApoint_button) Button playerAPointButton;
    @BindView(R.id.addBpoint_button) Button playerBPointButton;
    @BindView(R.id.resetA_button) Button resetAPointButton;
    @BindView(R.id.resetB_button) Button resetBPointButton;
    @BindView(R.id.resetBoth_button) Button resetBothPointButton;

    private boolean hasChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squash);
        ButterKnife.bind(this);

        namePlayerA = getIntent().getStringExtra("playerA_name");
        namePlayerB = getIntent().getStringExtra("playerB_name");

        playerANameTextView.setText(namePlayerA);
        playerBNameTextView.setText(namePlayerB);

        playerAPointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAPoint();
                hasChanged=true;
            }
        });
        playerBPointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBPoint();
                hasChanged=true;
            }
        });
        resetAPointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetA();
                hasChanged=true;
            }
        });
        resetBPointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetB();
                hasChanged=true;
            }
        });
        resetBothPointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoth();
                hasChanged=true;
            }
        });

    }
    public void addAPoint() {
        scorePlayerAPoint=scorePlayerAPoint+1;
        if(scorePlayerAPoint==11&&scorePlayerBPoint<10) {
            scorePlayerASet = scorePlayerASet + 1;
            scorePlayerAPoint = 0;
            scorePlayerBPoint = 0;
            displayForPlayerBPoint(scorePlayerBPoint);
        }else if(scorePlayerAPoint>=11&&scorePlayerBPoint>9&&scorePlayerAPoint-scorePlayerBPoint==2){
            scorePlayerASet = scorePlayerASet+1;
            scorePlayerAPoint = 0;
            scorePlayerBPoint = 0;
            displayForPlayerBPoint(scorePlayerBPoint);
            displayForPlayerAPoint(scorePlayerAPoint);

        }
        if(scorePlayerASet==3){
            if(namePlayerA.equals("")){
                namePlayerA=getString(R.string.default_player_a);
            }
            showWinnerDialog(namePlayerA, scorePlayerASet, scorePlayerBSet);
            scorePlayerASet=0;
            scorePlayerBSet=0;
            displayForPlayerBSet(scorePlayerBSet);

        }
        displayForPlayerAPoint(scorePlayerAPoint);
        displayForPlayerASet(scorePlayerASet);

    }
    public void displayForPlayerAPoint(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_a_score_point);
        scoreView.setText(String.valueOf(score));
    }
    public void displayForPlayerASet(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_a_score_set);
        scoreView.setText(String.valueOf(score));
    }
    public void addBPoint() {
        scorePlayerBPoint=scorePlayerBPoint+1;
        if(scorePlayerBPoint==11&&scorePlayerAPoint<10) {
            scorePlayerBSet = scorePlayerBSet + 1;
            scorePlayerBPoint = 0;
            scorePlayerAPoint = 0;
            displayForPlayerAPoint(scorePlayerAPoint);
        }else if(scorePlayerBPoint>=11&&scorePlayerAPoint>9&&scorePlayerBPoint-scorePlayerAPoint==2){
            scorePlayerBSet = scorePlayerBSet+1;
            scorePlayerBPoint = 0;
            scorePlayerAPoint = 0;
            displayForPlayerAPoint(scorePlayerAPoint);
            displayForPlayerBPoint(scorePlayerBPoint);
        }
        if(scorePlayerBSet==3){
            if(namePlayerB.equals("")){
                namePlayerB=getString(R.string.default_player_b);
            }
            showWinnerDialog(namePlayerB, scorePlayerASet, scorePlayerBSet);
            scorePlayerASet=0;
            scorePlayerBSet=0;
            displayForPlayerASet(scorePlayerASet);
        }
        displayForPlayerBPoint(scorePlayerBPoint);
        displayForPlayerBSet(scorePlayerBSet);
    }

    public void displayForPlayerBPoint(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_b_score_point);
        scoreView.setText(String.valueOf(score));
    }
    public void displayForPlayerBSet(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_b_score_set);
        scoreView.setText(String.valueOf(score));
    }

    public void resetA() {
        scorePlayerASet=0;
        scorePlayerAPoint=0;
        displayForPlayerAPoint(scorePlayerAPoint);
        displayForPlayerASet(scorePlayerASet);
    }

    public void resetB() {
        scorePlayerBSet=0;
        scorePlayerBPoint=0;
        displayForPlayerBPoint(scorePlayerBPoint);
        displayForPlayerBSet(scorePlayerBSet);
    }
    public void resetBoth() {
        scorePlayerASet=0;
        scorePlayerBSet=0;
        scorePlayerAPoint=0;
        scorePlayerBPoint=0;

        displayForPlayerAPoint(scorePlayerAPoint);
        displayForPlayerASet(scorePlayerASet);
        displayForPlayerBPoint(scorePlayerBPoint);
        displayForPlayerBSet(scorePlayerBSet);
    }
    private void showWinnerDialog(String namePlayer, int scorePlayerA, int scorePlayerB){
        AlertDialog alertDialog = new AlertDialog.Builder(SquashActivity.this).create();
        alertDialog.setTitle("WINNER");
        alertDialog.setMessage(namePlayer+" won the match!! Congratulations :)\nScore: " + scorePlayerA + ":" + scorePlayerB);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        if (!hasChanged) {
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.exit_msg);
        builder.setPositiveButton(R.string.yes_msg, discardButtonClickListener);
        builder.setNegativeButton(R.string.stay_msg, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
