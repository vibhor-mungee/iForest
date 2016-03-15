package com.ioraecological.ifp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    private static int DELAYED_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SessionManager sm = new SessionManager(getBaseContext());
                Intent i;
                if (sm.isUserLogin()) {
                    i = new Intent(Splash.this, MainMenu.class);
                } else {
                    i = new Intent(Splash.this, SignInActivity.class);
                }
                startActivity(i);
                finish();
            }
        }, DELAYED_TIME);


    }
}
