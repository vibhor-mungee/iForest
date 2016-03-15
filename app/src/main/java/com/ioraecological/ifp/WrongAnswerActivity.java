package com.ioraecological.ifp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class WrongAnswerActivity extends AppCompatActivity {

    private TextView question_tv;
    private TextView correctAnswer;
    private TextView explanation_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_answer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        question_tv = (TextView) findViewById(R.id.question_tv);
        question_tv.setText(QuizPage.QUESTION);

        correctAnswer = (TextView) findViewById(R.id.correct_answer_tv);
        correctAnswer.setText(QuizPage.ANSWERS[QuizPage.CORRECT_ANSWER - 1]);

        explanation_tv = (TextView) findViewById(R.id.explanation_tv);
        explanation_tv.setText(QuizPage.EXPLANATION);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
