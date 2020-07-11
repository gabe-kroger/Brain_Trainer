package com.example.brain_trainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    Button buttonText;
    Button button;
    Button button2;
    Button button3;
    Button button4;
    Button playAgainButton;
    TextView timerText;
    TextView resultTextView;
    TextView questionText;
    TextView scoreText;
    RelativeLayout relativeLayout;
    ArrayList<Integer> answers = new ArrayList<>();
    Random rand = new Random();
    int correctLocation;
    int correctAnswer;
    int incorrectAnswer;
    int a, b;
    int totalScore;
    int totalTries;
    CountDownTimer countDownTimer;
    boolean activeGame;



    public void nextQuestion(){
        correctLocation = rand.nextInt(4);
        a = rand.nextInt(51);
        b = rand.nextInt(51);
        correctAnswer = a + b;
        for (int i = 0; i < 4; i++) {
            if(i == correctLocation){
                answers.add(correctAnswer);
            }else{
                incorrectAnswer = rand.nextInt(101);
                while(incorrectAnswer == correctAnswer){
                    incorrectAnswer = rand.nextInt(101);
                }
                answers.add(incorrectAnswer);
            }
        }

        button.setText(answers.get(0).toString());
        button2.setText(answers.get(1).toString());
        button3.setText(answers.get(2).toString());
        button4.setText(answers.get(3).toString());
        scoreText.setText(Integer.toString(totalScore) + " / " + Integer.toString(totalTries));
        questionText.setText(Integer.toString(a) + " + " + Integer.toString(b));

    }


    public void goButton(View view){
        goButton.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(View.VISIBLE);
        timerText.setText("30");
        activeGame = true;

        nextQuestion();
        resetTimer();
    }









    public void buttonClick(View view){
        buttonText = (Button) view;
        answers.clear();

        if(activeGame) {
            if (buttonText.getText().toString() == Integer.toString(correctAnswer)) {
                totalScore++;
                totalTries++;
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            } else {
                totalTries++;
                Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
            }
            nextQuestion();
        }

    }

    public void resetTimer() {
        countDownTimer = new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                activeGame = false;
                timerText.setText("0");
                resultTextView.setVisibility(View.VISIBLE);
                resultTextView.setText("Your score is: " + Integer.toString(totalScore) + " / " + Integer.toString(totalTries));
            }
        }.start();
    }


    public void playAgain(View view){
        activeGame = true;
        answers.clear();
        countDownTimer.cancel();
        timerText.setText("30");
        totalTries = 0;
        totalScore = 0;
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        nextQuestion();
        resetTimer();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        goButton = (Button)findViewById(R.id.goButton);
        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        timerText = (TextView)findViewById(R.id.timerText);
        questionText = (TextView)findViewById(R.id.questionText);
        scoreText = (TextView)findViewById(R.id.scoreText);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        playAgainButton = (Button)findViewById(R.id.playAgain);

    }
}