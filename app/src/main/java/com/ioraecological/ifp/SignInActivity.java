package com.ioraecological.ifp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username_et;
    private EditText password_et;
    private Button login_btn;
    private SessionManager sm;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        username_et = (EditText) findViewById(R.id.username_et);
        password_et = (EditText) findViewById(R.id.password_et);
        login_btn = (Button) findViewById(R.id.sign_in);
        register = (TextView) findViewById(R.id.register_tv);

        login_btn.setOnClickListener(this);
        register.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in:
                validate(view);
                break;
            case R.id.register_tv:
                Intent i = new Intent(SignInActivity.this, RegisterActivity.class);
                startActivity(i);
                break;
        }
    }

    private void validate(View view) {
        String username = username_et.getText().toString();
        String password = password_et.getText().toString();
        sm = new SessionManager();

        if (username.trim().equalsIgnoreCase("")) {
            username_et.setError("Please enter Username");
        } else if (password.trim().equalsIgnoreCase("")) {
            password_et.setError("Please enter Password");
        } else if (username.trim().equalsIgnoreCase("admin") && password.trim().equalsIgnoreCase("admin")) {
            sm.setPreferences(SignInActivity.this, "status", "1");
            Intent i = new Intent(SignInActivity.this, MainMenu.class);
            startActivity(i);
        } else {
            Snackbar.make(view, "Invalid username or password", Snackbar.LENGTH_SHORT).show();
        }
    }
}
