package com.example.touristguide.activitites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.touristguide.R;
import com.example.touristguide.fragments.UserFragment;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editName, editEmail, editNumber;
    private String name, email, number;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editName = (EditText)findViewById(R.id.nameEditText);
        editEmail = (EditText)findViewById(R.id.emailEditText);
        editNumber = (EditText)findViewById(R.id.numberEditText);

        buttonSave = (Button)findViewById(R.id.saveEditChanges);



        /*
            Data Transfer from Profile Edit Activity to User Fragment
         */
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("name", editName.getText().toString());
                bundle.putString("email", editEmail.getText().toString());
                bundle.putString("number", editNumber.getText().toString());

                UserFragment userFragment = new UserFragment();
                userFragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerRelative, userFragment).commit();


            }
        });
    }
}
