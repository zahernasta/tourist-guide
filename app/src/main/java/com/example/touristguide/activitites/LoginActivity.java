package com.example.touristguide.activitites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
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
import com.example.touristguide.database.DatabaseManager;
import com.example.touristguide.database.dao.UserDao;
import com.example.touristguide.database.models.Users;
import com.example.touristguide.utils.Globals;

import org.w3c.dom.Text;

import java.util.Iterator;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    public static final String MyPreferences = "myPrefs";
    public static final String Username = "usernameKey";
    public static final String Password = "passwordKey";


    private static final String TAG = "LoginActivity";
    private EditText username;
    private EditText password;
    private Button loginButton;
    private TextView goToRegister;
    private SharedPreferences sharedPreferences;

    private UserDao userDao;
    private Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = (EditText) findViewById(R.id.loginNameEditText);
        password = (EditText) findViewById(R.id.loginPasswordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        goToRegister = (TextView) findViewById(R.id.registerText);
        sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);

        userDao = Room.databaseBuilder(this, DatabaseManager.class, "db_android")
                    .allowMainThreadQueries()
                    .build()
                    .getUserDao();

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
//                    if(Globals.credentialsMap.containsKey(username.getText().toString())) {
//                        if(username.getText().toString()
//                                .equals(sharedPreferences.getString(Username, ""))) {
//                            if(password.getText().toString()
//                                    .equals(sharedPreferences.getString(Password, ""))) {
//                        if(Globals.credentialsMap.get(username.getText().toString()).equals(
//                                password.getText().toString() )) {

                    handler = new Handler();

                    final Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            Users user = userDao.fetchOneUser(username.getText().toString(),
                                    password.getText().toString());
                            if ( user != null) {
                                Log.v("LoginActivitiy", user.toString());
                                Intent intent = new Intent(LoginActivity.this,
                                        MainActivity.class);
                                intent.putExtra("User", user);

                                startActivity(intent);

                                LoginActivity.this.finish();

                            } else {
                                Toast toast = Toast.makeText(LoginActivity.this, "user doesn't exists"
                                        ,Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    };

                    handler.postDelayed(r, 1000);
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
