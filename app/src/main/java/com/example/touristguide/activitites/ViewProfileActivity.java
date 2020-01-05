package com.example.touristguide.activitites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.touristguide.R;
import com.example.touristguide.database.models.Users;

public class ViewProfileActivity extends AppCompatActivity {

    private Users user;
    private TextView textViewUsername;
    private TextView textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        user = (Users) getIntent().getParcelableExtra("User");
        Log.v("EditProfileActivity", user.toString());
        textViewUsername = findViewById(R.id.usernameId);
        textViewEmail = findViewById(R.id.emailId);

        if(user != null) {
            textViewUsername.setText(user.getUsername());
            textViewEmail.setText(user.getEmail());
        }
    }
}
