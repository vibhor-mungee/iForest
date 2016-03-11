package com.ioraecological.ifp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.SeekBar;
import android.widget.TextView;

public class SearchNearbyForest extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    int seekBarProgress = 0;
    private SeekBar distanceSeekbar;
    private TextView distanceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_nearby_forest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        distanceSeekbar = (SeekBar) findViewById(R.id.distance_seekbar);
        distanceSeekbar.setOnSeekBarChangeListener(this);

        distanceText = (TextView) findViewById(R.id.distance);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        seekBarProgress = i;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        distanceText.setText(seekBarProgress + " km");
    }
}
