package com.ioraecological.ifp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class LearnActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner stateSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        stateSpinner = (Spinner) findViewById(R.id.state_spinner);
        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(this, R.array.state_list, android.R.layout.simple_spinner_item);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);
        stateSpinner.setOnItemSelectedListener(this);

        IndiaMapFragment imf = new IndiaMapFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.map_fragment, imf);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String state = adapterView.getItemAtPosition(i).toString();
        switch (state) {
            case "Delhi":

            case "Gujrat":

                break;
            case "Himachal Pradesh":

                break;
            case "Karnataka":

                break;
            case "Madhya Pradesh":

                break;
            case "Sikkim":
                Intent sikkimIntent = new Intent(LearnActivity.this,Learn_Gujrat_Activity.class );
                startActivity(sikkimIntent);
                break;

            default:
                throw new IllegalArgumentException("No map found for: " + state);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
