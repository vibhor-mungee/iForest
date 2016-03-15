package com.ioraecological.ifp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ActActivity extends AppCompatActivity implements View.OnClickListener {

    private Button i_will;
    private Button we_will;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        i_will = (Button) findViewById(R.id.i_will_btn);
        we_will = (Button) findViewById(R.id.we_will_btn);

        i_will.setOnClickListener(this);
        we_will.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(ActActivity.this, IWillActivity.class);
        switch (view.getId()) {
            case R.id.i_will_btn:
                startActivity(i);
                finish();
                break;
            case R.id.we_will_btn:
                startActivity(i);
                finish();
                break;
        }
    }
}
