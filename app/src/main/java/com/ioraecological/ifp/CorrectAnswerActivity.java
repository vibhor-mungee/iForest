package com.ioraecological.ifp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class CorrectAnswerActivity extends AppCompatActivity {

    private TextView question_tv;
    private TextView explanation_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_answer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        question_tv = (TextView) findViewById(R.id.question_tv);
        question_tv.setText(QuizPage.QUESTION);

        explanation_tv = (TextView) findViewById(R.id.explanation_tv);
        explanation_tv.setText(QuizPage.EXPLANATION);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
