package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView timerTextView;
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationofcorrectanswer;
    int Score = 0;
    int numberofquestion = 0;
    public void playAgain(View view){
        Score = 0;
        numberofquestion = 0;
        timerTextView.setText("30");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();
        new CountDownTimer(31000,1000){
            public void onTick(long millisecondsuntildone){
                timerTextView.setText(String.valueOf(millisecondsuntildone/1000));
            }
            public  void onFinish(){
                timerTextView.setText("0");
                resultTextView.setText("Your Score"+ Integer.toString(Score)+"/" + Integer.toString(numberofquestion));
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }
    public void generateQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a)+ " " + "+" + " " + Integer.toString(b));
        locationofcorrectanswer = rand.nextInt(4);
        answers.clear();
        int incorrectanswer;
        for(int i=0; i<4;i++ ){
            if(i==locationofcorrectanswer){
                answers.add(a+b);

            }
            else {
                incorrectanswer = rand.nextInt(41);
                while (incorrectanswer == a+b)
                {
                    incorrectanswer=rand.nextInt(41);
                }
                answers.add(incorrectanswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
    public void chooseAnswer(View view){
        if (view.getTag().toString().equals(Integer.toString(locationofcorrectanswer))){
            Score++;
            resultTextView.setText("Correct");

        }
        else {
            resultTextView.setText("Wrong!");
        }
        numberofquestion++;
        pointsTextView.setText(Integer.toString(Score)+"/" + Integer.toString(numberofquestion));
        generateQuestion();

    }
    public void start (View view){
           startButton = (Button) findViewById(R.id.startButton);
           startButton.setVisibility(View.INVISIBLE);

       }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        playAgain(findViewById(R.id.playAgainButton) );
        }




}