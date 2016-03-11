package com.ioraecological.ifp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class VisitForestActivity extends AppCompatActivity implements View.OnClickListener {

    private Button searchNearby;
    private Button locateOnMap;
    private Button forestEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_forest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        searchNearby = (Button) findViewById(R.id.search_nearby_forest);
        searchNearby.setOnClickListener(this);

        locateOnMap = (Button) findViewById(R.id.locate_on_map);
        locateOnMap.setOnClickListener(this);

        forestEvents = (Button) findViewById(R.id.forest_events);
        forestEvents.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_nearby_forest:
                Intent i = new Intent(VisitForestActivity.this, SearchNearbyForest.class);
                startActivity(i);
                break;
            case R.id.locate_on_map:
                break;
            case R.id.forest_events:
                Intent i3 = new Intent(VisitForestActivity.this, EventActivity.class);
                startActivity(i3);
                break;
        }

    }
}
