package com.ioraecological.ifp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizPage extends AppCompatActivity implements View.OnClickListener {

    public static String QUESTION = "Which of these grasses are used for making cooling screens?";
    public static String[] ANSWERS = {
            "Elephant grass",
            "Bamboo",
            "Khus grass",
            "Rescue grass"
    };
    public static int CORRECT_ANSWER;
    public static String EXPLANATION = "The mats made from khus khus plant are hung in the house, to cool rooms during summer. Moreover, they even add a pleasant aroma in the house, when sprinkled with water occasionally.";
    private TextView question;
    private TextView option_a;
    private TextView option_b;
    private TextView option_c;
    private TextView option_d;
    private Button submitQuiz;
    private int selectedOption = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        question = (TextView) findViewById(R.id.question_tv);
        option_a = (TextView) findViewById(R.id.option_a);
        option_b = (TextView) findViewById(R.id.option_b);
        option_c = (TextView) findViewById(R.id.option_c);
        option_d = (TextView) findViewById(R.id.option_d);

        option_a.setOnClickListener(this);
        option_b.setOnClickListener(this);
        option_c.setOnClickListener(this);
        option_d.setOnClickListener(this);

        submitQuiz = (Button) findViewById(R.id.submit_quiz);
        submitQuiz.setOnClickListener(this);

        setQnA(QUESTION, ANSWERS, 3);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setQnA(String ques, String[] answers, int correctAnswer) {

        question.setText(ques);

        option_a.setText("a) " + answers[0]);
        option_b.setText("b) " + answers[1]);
        option_c.setText("c) " + answers[2]);
        option_d.setText("d) " + answers[3]);

        CORRECT_ANSWER = correctAnswer;
    }

    @Override
    public void onClick(View view) {
        SessionManager sm = new SessionManager(getBaseContext());
        int currentScore = sm.getScore();
        switch (view.getId()) {
            case R.id.option_a:
                option_a.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.colorSelectedAnswer));
                option_b.setBackgroundColor(0x00000000);
                option_c.setBackgroundColor(0x00000000);
                option_d.setBackgroundColor(0x00000000);
                selectedOption = 1;
                break;
            case R.id.option_b:
                option_a.setBackgroundColor(0x00000000);
                option_b.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.colorSelectedAnswer));
                option_c.setBackgroundColor(0x00000000);
                option_d.setBackgroundColor(0x00000000);
                selectedOption = 2;
                break;
            case R.id.option_c:
                option_a.setBackgroundColor(0x00000000);
                option_b.setBackgroundColor(0x00000000);
                option_c.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.colorSelectedAnswer));
                option_d.setBackgroundColor(0x00000000);
                selectedOption = 3;
                break;
            case R.id.option_d:
                option_a.setBackgroundColor(0x00000000);
                option_b.setBackgroundColor(0x00000000);
                option_c.setBackgroundColor(0x00000000);
                option_d.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.colorSelectedAnswer));
                selectedOption = 4;
                break;
            case R.id.submit_quiz:
                if (selectedOption > 0) {
                    sm.setQuizTaken(true);
                    if (checkAnswer(selectedOption)) {
                        Intent correctAnswerIntent = new Intent(QuizPage.this, CorrectAnswerActivity.class);
                        sm.setScore(currentScore + 5);
                        startActivity(correctAnswerIntent);
                        finish();
                    } else {
                        Intent wrongAnswerIntent = new Intent(QuizPage.this, WrongAnswerActivity.class);
                        sm.setScore(currentScore);
                        startActivity(wrongAnswerIntent);
                        finish();
                    }
                } else {
                    Snackbar.make(view, "Please select an answer first.", Snackbar.LENGTH_SHORT).show();
                }
                break;
        }

    }

    private boolean checkAnswer(int optionSelected) {
        if (optionSelected == CORRECT_ANSWER) {
            return true;
        }
        return false;
    }
}
