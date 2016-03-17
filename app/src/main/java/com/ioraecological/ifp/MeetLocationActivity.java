package com.ioraecological.ifp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MeetLocationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner communitySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        communitySpinner = (Spinner) findViewById(R.id.state_spinner);
//        ArrayAdapter<CharSequence> communityAdapter = ArrayAdapter.createFromResource(this, R.array.community_list, android.R.layout.simple_spinner_item);
//        communityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        communitySpinner.setAdapter(communityAdapter);
//        communitySpinner.setOnItemSelectedListener(this);

        communitySpinner = (Spinner) findViewById(R.id.community_spinner);
        List<String> list = new ArrayList<String>();
        list.add("NGOs");
        list.add("Youth Groups");
        list.add("Nature Clubs");
        list.add("Photographers");
        list.add("Adventure Groups");
        list.add("Environmentalists");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(R.layout.location_resource_file);
        communitySpinner.setAdapter(dataAdapter);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
