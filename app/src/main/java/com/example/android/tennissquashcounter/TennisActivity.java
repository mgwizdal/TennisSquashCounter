package com.example.android.tennissquashcounter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.tennissquashcounter.R;

public class TennisActivity extends AppCompatActivity {
    private String[] points={"0","15","30","40"};
    private int iAPoint=0;
    private String displayerAPoint="0";
    private int iBPoint=0;
    private String displayerBPoint="0";
    private int scorePlayerAGame=0;
    private int scorePlayerBGame=0;
    private int scorePlayerASet=0;
    private int scorePlayerBSet=0;

    String namePlayerA="Max";
    String namePlayerB="Sabina";
    TextView playerANameTextView;
    TextView playerBNameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tennis);
        playerANameTextView = (TextView) findViewById(R.id.player_a_name_text_view);
        playerBNameTextView = (TextView) findViewById(R.id.player_b_name_text_view);
        namePlayerA = getIntent().getStringExtra("playerA_name");
        namePlayerB = getIntent().getStringExtra("playerB_name");

        playerANameTextView.setText(namePlayerA);
        playerBNameTextView.setText(namePlayerB);
    }
    public void displayForPlayerAPoint(String score) {
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
    public void addAPoint(View view) {
        if(displayerBPoint.equals("AD")){
            displayerBPoint="40";
            displayForPlayerBPoint(displayerBPoint);
        }else if(displayerAPoint.equals("AD")){
            displayerAPoint="0";
            iAPoint=0;
            scorePlayerAGame=scorePlayerAGame+1;
            iBPoint=0;
            displayerBPoint=points[iBPoint];
            displayForPlayerBPoint(displayerBPoint);//dodaj ze jezeli a zdobedzie punkt kiedy b jest na "AD" to B musi wrocic na 40
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
        if(scorePlayerAGame==6&&scorePlayerBGame<5) {
            scorePlayerASet = scorePlayerASet + 1;
            scorePlayerAGame = 0;
            scorePlayerBGame = 0;
            displayerBPoint = "0";
            displayForPlayerBPoint(displayerBPoint);
            displayForPlayerBGame(scorePlayerBGame);
        }else if(scorePlayerAGame==6&&scorePlayerBGame==6){
            scorePlayerAGame = 6;
            displayerBPoint = "0";
            displayForPlayerBPoint(displayerBPoint);
            displayForPlayerBGame(scorePlayerBGame);
        }else if(scorePlayerAGame==7&&scorePlayerBGame==5){
            scorePlayerASet = scorePlayerASet+1;
            scorePlayerAGame=0;
            scorePlayerBGame=0;
            displayerBPoint="0";
            displayForPlayerBPoint(displayerBPoint);
            displayForPlayerBGame(scorePlayerBGame);
        }
        if(scorePlayerASet==2){
            scorePlayerAGame=0;
            scorePlayerBGame=0;
            displayerBPoint="0";
            scorePlayerASet=0;
            scorePlayerBSet=0;
            displayForPlayerBPoint(displayerBPoint);
            displayForPlayerBGame(scorePlayerBGame);

            AlertDialog alertDialog = new AlertDialog.Builder(TennisActivity.this).create();
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

        displayForPlayerAPoint(displayerAPoint);
        displayForPlayerAGame(scorePlayerAGame);
        displayForPlayerASet(scorePlayerASet);
    }
    public void resetA(View view) {
        displayerAPoint="0";
        scorePlayerAGame=0;
        scorePlayerASet=0;
        displayForPlayerAPoint(displayerAPoint);
        displayForPlayerAGame(scorePlayerAGame);
        displayForPlayerASet(scorePlayerASet);
    }
    public void displayForPlayerBPoint(String score) {
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
    public void addBPoint(View view) {
        if(displayerAPoint.equals("AD")){
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
        if(scorePlayerBGame==6&&scorePlayerAGame<5) {
            scorePlayerBSet = scorePlayerBSet + 1;
            scorePlayerBGame = 0;
            scorePlayerAGame = 0;
            displayerAPoint = "0";
            displayForPlayerAPoint(displayerAPoint);
            displayForPlayerAGame(scorePlayerAGame);
        }else if(scorePlayerBGame == 6 && scorePlayerAGame==6){
            scorePlayerBGame = 6;
            displayerAPoint = "0";
            displayForPlayerAPoint(displayerAPoint);
            displayForPlayerBGame(scorePlayerBGame);
        }else if(scorePlayerBGame==7&&scorePlayerAGame==5){
            scorePlayerBSet = scorePlayerBSet+1;
            scorePlayerBGame=0;
            scorePlayerAGame=0;
            displayerAPoint="0";
            displayForPlayerAPoint(displayerAPoint);
            displayForPlayerAGame(scorePlayerAGame);
        }

        if(scorePlayerBSet==2){
            AlertDialog alertDialog = new AlertDialog.Builder(TennisActivity.this).create();
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
        displayForPlayerBPoint(displayerBPoint);
        displayForPlayerBGame(scorePlayerBGame);
        displayForPlayerBSet(scorePlayerBSet);

    }

    public void resetB(View view) {
        displayerBPoint="0";
        scorePlayerBGame = 0;
        scorePlayerBSet = 0;
        displayForPlayerBPoint(displayerBPoint);
        displayForPlayerBGame(scorePlayerBGame);
        displayForPlayerBSet(scorePlayerBSet);
    }
    public void resetBoth(View view) {
        displayerAPoint="0";
        scorePlayerAGame=0;
        scorePlayerASet=0;
        displayerBPoint="0";
        scorePlayerBGame=0;
        scorePlayerBSet=0;

        displayForPlayerAPoint(displayerAPoint);
        displayForPlayerAGame(scorePlayerAGame);
        displayForPlayerASet(scorePlayerASet);
        displayForPlayerBPoint(displayerBPoint);
        displayForPlayerBGame(scorePlayerBGame);
        displayForPlayerBSet(scorePlayerBSet);
    }
}
