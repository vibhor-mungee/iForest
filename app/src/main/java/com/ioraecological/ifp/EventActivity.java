package com.ioraecological.ifp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout planEvent;
    private LinearLayout inviteFriends;
    private LinearLayout joinEvent;
    private LinearLayout myEvents;
    private LinearLayout connectToForester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        planEvent = (LinearLayout) findViewById(R.id.create_event);
        inviteFriends = (LinearLayout) findViewById(R.id.invite_to_event);
        joinEvent = (LinearLayout) findViewById(R.id.join_event);
        myEvents = (LinearLayout) findViewById(R.id.my_events);
        connectToForester = (LinearLayout) findViewById(R.id.connect_to_forester);

        planEvent.setOnClickListener(this);
        inviteFriends.setOnClickListener(this);
        joinEvent.setOnClickListener(this);
        myEvents.setOnClickListener(this);
        connectToForester.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.create_event):
                Intent i = new Intent(EventActivity.this, PlanActivity.class);
                startActivity(i);
                break;
            case (R.id.invite_to_event):
                Intent i2 = new Intent(EventActivity.this, InviteFriendActivity.class);
                startActivity(i2);
                break;
            case (R.id.join_event):
                Intent i3 = new Intent(EventActivity.this, JoinActivity.class);
                startActivity(i3);
                break;
            case (R.id.my_events):
                break;
            case (R.id.connect_to_forester):
                break;
        }

    }
}
