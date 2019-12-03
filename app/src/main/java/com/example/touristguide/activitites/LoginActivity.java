package com.example.touristguide.activitites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.touristguide.MainActivity;
import com.example.touristguide.R;
import com.example.touristguide.utils.Globals;

import org.w3c.dom.Text;

import java.util.Iterator;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private EditText username;
    private EditText password;
    private Button loginButton;
    private TextView goToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = (EditText) findViewById(R.id.loginNameEditText);
        password = (EditText) findViewById(R.id.loginPasswordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        goToRegister = (TextView) findViewById(R.id.registerText);

        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(intent);

                LoginActivity.this.finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkDataEntered()) {
                    if(Globals.credentialsMap.containsKey(username.getText().toString())) {
                        if(Globals.credentialsMap.get(username.getText().toString()).equals(
                                password.getText().toString() )) {

                            Intent intent = new Intent(LoginActivity.this,
                                    MainActivity.class);

                            startActivity(intent);

                            LoginActivity.this.finish();

                        } else {
                            Toast toast = Toast.makeText(LoginActivity.this,
                                    "Wrong password", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    } else {
                        Toast toast = Toast.makeText(LoginActivity.this,
                                "Wrong username", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });

    }

    private boolean isEmpty(EditText text) {
        String string = text.getText().toString();
        return TextUtils.isEmpty(string);
    }

    private boolean checkDataEntered() {

        if(isEmpty(username)) {
            Toast toast =  Toast.makeText(this, "You must provide a username",
                    Toast.LENGTH_SHORT);
            toast.show();
            username.setError("Username is required");

            return false;
        }

        if(isEmpty(password)) {
            Toast toast =  Toast.makeText(this, "You must provide a password",
                    Toast.LENGTH_SHORT);
            toast.show();
            password.setError("Password is required");

            return false;
        }

        return true;
     }

}
