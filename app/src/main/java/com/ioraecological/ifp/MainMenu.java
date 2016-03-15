package com.ioraecological.ifp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    LinearLayout learnBtn;
    LinearLayout visitBtn;
    LinearLayout actBtn;
    LinearLayout playBtn;
    LinearLayout meetBtn;
    LinearLayout contributeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        learnBtn = (LinearLayout) findViewById(R.id.learn_menu_item);
        visitBtn = (LinearLayout) findViewById(R.id.visit_menu_item);
        actBtn = (LinearLayout) findViewById(R.id.act_menu_item);
        playBtn = (LinearLayout) findViewById(R.id.play_menu_item);
        meetBtn = (LinearLayout) findViewById(R.id.meet_menu_item);
        contributeBtn = (LinearLayout) findViewById(R.id.contribute_menu_item);


        learnBtn.setOnClickListener(this);
        visitBtn.setOnClickListener(this);
        actBtn.setOnClickListener(this);
        playBtn.setOnClickListener(this);
        meetBtn.setOnClickListener(this);
        contributeBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        SessionManager sm = new SessionManager(getBaseContext());
        switch (view.getId()) {
            case R.id.learn_menu_item:
                Intent i = new Intent(MainMenu.this, LearnActivity.class);
                startActivity(i);
                break;
            case R.id.visit_menu_item:
                Intent i2 = new Intent(MainMenu.this, VisitForestActivity.class);
                startActivity(i2);
                break;
            case R.id.act_menu_item:
                Intent i3 = new Intent(MainMenu.this, ActActivity.class);
                startActivity(i3);
                break;
            case R.id.play_menu_item:
                if (sm.isQuizTaken()) {
                    Intent i4 = new Intent(MainMenu.this, QuizTakenActivity.class);
                    startActivity(i4);
                } else {
                    Intent i4 = new Intent(MainMenu.this, PlayActivity.class);
                    startActivity(i4);
                }
                break;
            case R.id.meet_menu_item:
                break;
            case R.id.contribute_menu_item:
                break;
        }
    }
}
