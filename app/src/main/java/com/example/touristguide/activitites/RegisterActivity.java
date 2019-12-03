package com.example.touristguide.activitites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.touristguide.R;
import com.example.touristguide.utils.Globals;

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText email;
    EditText password;
    Button registerButton;
    TextView goToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.registerNameEditText);
        email = (EditText) findViewById(R.id.registerEmailEditText);
        password = (EditText) findViewById(R.id.registerPasswordEditText);
        registerButton = (Button) findViewById(R.id.registerButton);
        goToLogin = (TextView) findViewById(R.id.loginText);

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,
                        LoginActivity.class);
                startActivity(intent);

                RegisterActivity.this.finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkDataEntered()) {
                    Globals.credentialsMap.put(username.getText().toString(),
                            password.getText().toString());

                    Intent intent = new Intent(RegisterActivity.this,
                            LoginActivity.class);
                    startActivity(intent);

                    RegisterActivity.this.finish();
                }
            }
        });
    }

    private boolean isEmpty(EditText text) {
        String string = text.getText().toString();
        return TextUtils.isEmpty(string);
    }

    private boolean isEmail(EditText text) {
        String string = text.getText().toString();
        return (!TextUtils.isEmpty(string) && Patterns.EMAIL_ADDRESS.matcher(string).matches());
    }

    private boolean checkDataEntered() {
        if(isEmpty(username)) {
            Toast toast =  Toast.makeText(this, "You must provide a user name",
                    Toast.LENGTH_SHORT);
            toast.show();
            username.setError("Name is required");

            return false;
        }

        if(!isEmail(email)) {
            Toast toast =  Toast.makeText(this, "You must provide a valid email",
                    Toast.LENGTH_SHORT);
            toast.show();
            email.setError("Email is required");

            return false;
        }

        if(isEmpty(password)) {
            Toast toast =  Toast.makeText(this, "You must provide a user password",
                    Toast.LENGTH_SHORT);
            toast.show();
            password.setError("Password is required");

            return false;
        }


        return true;
    }
}
