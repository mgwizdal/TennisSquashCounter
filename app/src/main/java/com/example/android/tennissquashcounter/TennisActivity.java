package com.example.android.tennissquashcounter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.tennissquashcounter.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TennisActivity extends AppCompatActivity {
    private String[] points={"0","15","30","40"};
    private int iAPoint=0;
    private String displayerAPoint="0";
    private int iBPoint=0;
    private String displayerBPoint="0";
    private int scorePlayerAGame=5;
    private int scorePlayerBGame=5;
    private int scorePlayerASet=0;
    private int scorePlayerBSet=0;
    private int winningScore = 2;
    private int tiebreakPlayerAScore = 0;
    private int tiebreakPlayerBScore = 0;
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
    private boolean isGrandSlam = false;
    boolean begin = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tennis);
        ButterKnife.bind(this);

        namePlayerA = getIntent().getStringExtra(getString(R.string.playerA_name));
        namePlayerB = getIntent().getStringExtra(getString(R.string.playerB_name));
        isGrandSlam = getIntent().getBooleanExtra(getString(R.string.intent_grand_slam), isGrandSlam);

        if(isGrandSlam){
            winningScore = 3;
        }

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
        if(begin){
            Log.i("Tennis", "TieBREAAAAAAAK!!!" + tiebreakPlayerAScore +":"+tiebreakPlayerBScore);
            iAPoint = 0;
            iBPoint = 0;
        }else if(displayerBPoint.equals("AD")){
            displayerBPoint="40";
            displayForPlayerBPoint(displayerBPoint);
        }else if(displayerAPoint.equals("AD")){
            displayerAPoint="0";
            iAPoint=0;
            scorePlayerAGame=scorePlayerAGame+1;
            iBPoint=0;
            displayerBPoint=points[iBPoint];
            displayForPlayerBPoint(displayerBPoint);
        }else if(displayerAPoint.equals("40")&&displayerBPoint!="40"&&displayerBPoint!="AD"){
            displayerAPoint="0";
            iAPoint=0;
            scorePlayerAGame=scorePlayerAGame+1;
            iBPoint=0;
            displayerBPoint=points[iBPoint];
            displayForPlayerBPoint(displayerBPoint);
        }else if(displayerAPoint.equals("40")&&displayerBPoint.equals("40")) {
            displayerAPoint="AD";
        }   else{
            displayerAPoint=points[++iAPoint];
        }

        if(scorePlayerAGame>=6&& (scorePlayerAGame - scorePlayerBGame>=2)) {
            scorePlayerASet = scorePlayerASet + 1;
            scorePlayerAGame = 0;
            scorePlayerBGame = 0;
            displayerBPoint = "0";
            displayForPlayerBPoint(displayerBPoint);
            displayForPlayerBGame(scorePlayerBGame);
        }


        if(scorePlayerAGame==6&&scorePlayerBGame==6&&scorePlayerASet!=2&&scorePlayerBSet!=2&&!begin) {
            displayForPlayerAPoint(tiebreakPlayerAScore);
            displayForPlayerBPoint(tiebreakPlayerBScore);
            begin = true;
        }else if(scorePlayerAGame==6&&scorePlayerBGame==6&&scorePlayerASet!=2&&scorePlayerBSet!=2&&begin){
            tiebreakPlayerAScore += 1;
            displayForPlayerAPoint(tiebreakPlayerAScore);
            displayForPlayerBPoint(tiebreakPlayerBScore);
        }

        if(tiebreakPlayerAScore>=7&&(tiebreakPlayerAScore - tiebreakPlayerBScore>=2)){
            scorePlayerASet = scorePlayerASet+1;
            scorePlayerAGame=0;
            scorePlayerBGame=0;
            displayerBPoint="0";
            tiebreakPlayerAScore = 0;
            tiebreakPlayerBScore = 0;
            displayForPlayerBPoint(displayerBPoint);
            displayForPlayerBGame(scorePlayerBGame);
            begin = false;
        }
        if(scorePlayerASet==winningScore){
            if(namePlayerA.equals("")){
                namePlayerA=getString(R.string.default_player_a);
            }
            showWinnerDialog(namePlayerA, scorePlayerASet, scorePlayerBSet);

            scorePlayerAGame=0;
            scorePlayerBGame=0;
            displayerBPoint="0";
            scorePlayerASet=0;
            scorePlayerBSet=0;
            displayForPlayerBPoint(displayerBPoint);

            displayForPlayerBGame(scorePlayerBGame);

        }
        if(tiebreakPlayerAScore==0){
            displayForPlayerAPoint(displayerAPoint);
        }
        displayForPlayerAGame(scorePlayerAGame);
        displayForPlayerASet(scorePlayerASet);
    }
    public void displayForPlayerAPoint(String score) {
        TextView scoreView = (TextView) findViewById(R.id.player_a_score_point);
        scoreView.setText(String.valueOf(score));
    }
    public void displayForPlayerAPoint(Integer score) {
        TextView scoreView = (TextView) findViewById(R.id.player_a_score_point);
        scoreView.setText(String.valueOf(score));
    }
    public void displayForPlayerAGame(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_a_score_game);
        scoreView.setText(String.valueOf(score));
    }
    public void displayForPlayerASet(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_a_score_set);
        scoreView.setText(String.valueOf(score));
    }
    public void addBPoint() {
        if(tiebreakPlayerBScore!=0 || tiebreakPlayerAScore!=0){
            Log.i("Tennis", "TieBREAAAAAAAK!!!" + tiebreakPlayerAScore +":"+tiebreakPlayerBScore);

        }else if(displayerAPoint.equals("AD")){
            displayerAPoint="40";
            displayForPlayerAPoint(displayerAPoint);
        }else if(displayerBPoint.equals("AD")){
            displayerBPoint="0";
            iBPoint=0;
            scorePlayerBGame=scorePlayerBGame + 1;
            iAPoint=0;
            displayerAPoint=points[iAPoint];
            displayForPlayerAPoint(displayerAPoint);
        }else if(displayerBPoint.equals("40")&&displayerAPoint!="40"&&displayerAPoint!="AD"){
            displayerBPoint="0";
            iBPoint=0;
            scorePlayerBGame=scorePlayerBGame+1;
            iAPoint=0;
            displayerAPoint=points[iAPoint];
            displayForPlayerAPoint(displayerAPoint);
        }else if(displayerBPoint.equals("40")&&displayerAPoint.equals("40")) {
            displayerBPoint="AD";
        }   else{
            displayerBPoint=points[++iBPoint];
        }


        if(scorePlayerAGame==6&&scorePlayerBGame==6&&scorePlayerASet!=2&&scorePlayerBSet!=2) {
            tiebreakPlayerBScore += 1;
            displayForPlayerAPoint(tiebreakPlayerAScore);
            displayForPlayerBPoint(tiebreakPlayerBScore);
        }
        if(scorePlayerBGame>=6&& scorePlayerBGame - scorePlayerAGame>=2) {
            scorePlayerBSet = scorePlayerBSet + 1;
            scorePlayerAGame = 0;
            scorePlayerBGame = 0;
            displayerAPoint = "0";
            displayForPlayerAPoint(displayerBPoint);
            displayForPlayerAGame(scorePlayerBGame);
        }

        if(tiebreakPlayerBScore>=7&&(tiebreakPlayerBScore - tiebreakPlayerAScore>=2)){
            scorePlayerASet = scorePlayerASet+1;
            scorePlayerAGame=0;
            scorePlayerBGame=0;
            displayerBPoint="0";
            tiebreakPlayerAScore = 0;
            tiebreakPlayerBScore = 0;
            displayForPlayerBPoint(displayerBPoint);
            displayForPlayerBGame(scorePlayerBGame);
        }


        if(scorePlayerBSet==winningScore){
            if(namePlayerB.equals("")){
                namePlayerB=getString(R.string.default_player_b);
            }
            showWinnerDialog(namePlayerB, scorePlayerASet, scorePlayerBSet);

            scorePlayerAGame=0;
            scorePlayerBGame=0;
            displayerAPoint="0";
            scorePlayerASet=0;
            scorePlayerBSet=0;
            displayForPlayerAPoint(displayerAPoint);
            displayForPlayerAGame(scorePlayerBGame);
            displayForPlayerASet(scorePlayerBGame);

        }
        if(tiebreakPlayerBScore==0){
            displayForPlayerBPoint(displayerBPoint);
        }
        displayForPlayerBGame(scorePlayerBGame);
        displayForPlayerBSet(scorePlayerBSet);

    }
    public void displayForPlayerBPoint(String score) {
        TextView scoreView = (TextView) findViewById(R.id.player_b_score_point);
        scoreView.setText(String.valueOf(score));
    }
    public void displayForPlayerBPoint(Integer score) {
        TextView scoreView = (TextView) findViewById(R.id.player_b_score_point);
        scoreView.setText(String.valueOf(score));
    }
    public void displayForPlayerBGame(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_b_score_game);
        scoreView.setText(String.valueOf(score));
    }
    public void displayForPlayerBSet(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_b_score_set);
        scoreView.setText(String.valueOf(score));
    }
    public void resetA() {
        displayerAPoint="0";
        scorePlayerAGame=0;
        scorePlayerASet=0;
        iAPoint = 0;
        displayForPlayerAPoint(displayerAPoint);
        displayForPlayerAGame(scorePlayerAGame);
        displayForPlayerASet(scorePlayerASet);
    }
    public void resetB() {
        displayerBPoint="0";
        scorePlayerBGame = 0;
        scorePlayerBSet = 0;
        iBPoint = 0;
        displayForPlayerBPoint(displayerBPoint);
        displayForPlayerBGame(scorePlayerBGame);
        displayForPlayerBSet(scorePlayerBSet);
    }
    public void resetBoth() {
        displayerAPoint="0";
        scorePlayerAGame=0;
        scorePlayerASet=0;
        displayerBPoint="0";
        scorePlayerBGame=0;
        scorePlayerBSet=0;
        iAPoint = 0;
        iBPoint = 0;

        displayForPlayerAPoint(displayerAPoint);
        displayForPlayerAGame(scorePlayerAGame);
        displayForPlayerASet(scorePlayerASet);
        displayForPlayerBPoint(displayerBPoint);
        displayForPlayerBGame(scorePlayerBGame);
        displayForPlayerBSet(scorePlayerBSet);
    }
    private void showWinnerDialog(String namePlayer, int scorePlayerA, int scorePlayerB){
        AlertDialog alertDialog = new AlertDialog.Builder(TennisActivity.this).create();
        alertDialog.setTitle("WINNER");
        alertDialog.setMessage(namePlayer+" won the match!! Congratulations :) \nScore: "+scorePlayerA + ":"+scorePlayerB);
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
