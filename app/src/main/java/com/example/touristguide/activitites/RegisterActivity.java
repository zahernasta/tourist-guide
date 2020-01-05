package com.example.touristguide.activitites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.touristguide.MainActivity;
import com.example.touristguide.R;
import com.example.touristguide.database.DatabaseManager;
import com.example.touristguide.database.dao.UserDao;
import com.example.touristguide.database.models.Users;
import com.example.touristguide.utils.Globals;

public class RegisterActivity extends AppCompatActivity{

    public static final String MyPreferences = "myPrefs";
    public static final String Username = "usernameKey";
    public static final String Password = "passwordKey";

    private EditText username;
    private EditText email;
    private EditText password;
    private Button registerButton;
    private TextView goToLogin;
    private SharedPreferences sharedPreferences;
    private UserDao userDao;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.registerNameEditText);
        email = (EditText) findViewById(R.id.registerEmailEditText);
        password = (EditText) findViewById(R.id.registerPasswordEditText);
        registerButton = (Button) findViewById(R.id.registerButton);
        goToLogin = (TextView) findViewById(R.id.loginText);
        sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);

        userDao = Room.databaseBuilder(this, DatabaseManager.class, "db_android")
                .allowMainThreadQueries()
                .build()
                .getUserDao();

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
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Username, username.getText().toString());
                    editor.putString(Password, password.getText().toString());
                    editor.commit();

                    Toast.makeText(RegisterActivity.this, "SharedPreferences Done"
                            , Toast.LENGTH_LONG).show();

                    handler = new Handler();

                    final Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            Globals.credentialsMap.put(username.getText().toString(),
                                    password.getText().toString());

                            Users user = new Users(username.getText().toString(), email.getText().toString()
                                    , password.getText().toString());

                            userDao.insertOneUser(user);

                        }
                    };

                    handler.postDelayed(r, 1000);


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
