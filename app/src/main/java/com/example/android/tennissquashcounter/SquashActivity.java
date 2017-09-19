package com.example.android.tennissquashcounter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.tennissquashcounter.R;

public class SquashActivity extends AppCompatActivity {
    private int scorePlayerAPoint=0;
    private int scorePlayerBPoint=0;
    private int scorePlayerASet=0;
    private int scorePlayerBSet=0;

    String namePlayerA="Max";
    String namePlayerB="Sabina";
    TextView playerANameTextView;
    TextView playerBNameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squash);
        playerANameTextView = (TextView) findViewById(R.id.player_a_name_text_view);
        playerBNameTextView = (TextView) findViewById(R.id.player_b_name_text_view);
        namePlayerA = getIntent().getStringExtra("playerA_name");
        namePlayerB = getIntent().getStringExtra("playerB_name");

        playerANameTextView.setText(namePlayerA);
        playerBNameTextView.setText(namePlayerB);

    }
    public void displayForPlayerAPoint(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_a_score_point);
        scoreView.setText(String.valueOf(score));
    }
    public void displayForPlayerASet(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_a_score_set);
        scoreView.setText(String.valueOf(score));
    }
    public void addAPoint(View view) {
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
            scorePlayerAPoint=0;
            scorePlayerBPoint=0;
            scorePlayerASet=0;
            scorePlayerBSet=0;
            displayForPlayerBPoint(scorePlayerBPoint);

            AlertDialog alertDialog = new AlertDialog.Builder(SquashActivity.this).create();
            alertDialog.setTitle("WINNER");
            alertDialog.setMessage(namePlayerA+" won the match!! Congratulations :)");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        displayForPlayerAPoint(scorePlayerAPoint);
        displayForPlayerASet(scorePlayerASet);

    }
    public void resetA(View view) {
        scorePlayerASet=0;
        scorePlayerAPoint=0;
        displayForPlayerAPoint(scorePlayerAPoint);
        displayForPlayerASet(scorePlayerASet);
    }
    public void displayForPlayerBPoint(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_b_score_point);
        scoreView.setText(String.valueOf(score));
    }
    public void displayForPlayerBSet(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_b_score_set);
        scoreView.setText(String.valueOf(score));
    }
    public void addBPoint(View view) {
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
            scorePlayerBPoint=0;
            scorePlayerAPoint=0;
            scorePlayerBSet=0;
            scorePlayerASet=0;
            displayForPlayerAPoint(scorePlayerAPoint);

            AlertDialog alertDialog = new AlertDialog.Builder(SquashActivity.this).create();
            alertDialog.setTitle("WINNER");
            alertDialog.setMessage(namePlayerB+" won the match!! Congratulations :)");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        displayForPlayerBPoint(scorePlayerBPoint);
        displayForPlayerBSet(scorePlayerBSet);
    }

    public void resetB(View view) {
        scorePlayerBSet=0;
        scorePlayerBPoint=0;
        displayForPlayerBPoint(scorePlayerBPoint);
        displayForPlayerBSet(scorePlayerBSet);
    }
    public void resetBoth(View view) {
        scorePlayerASet=0;
        scorePlayerBSet=0;
        scorePlayerAPoint=0;
        scorePlayerBPoint=0;

        displayForPlayerAPoint(scorePlayerAPoint);
        displayForPlayerASet(scorePlayerASet);
        displayForPlayerBPoint(scorePlayerBPoint);
        displayForPlayerBSet(scorePlayerBSet);
    }
}
