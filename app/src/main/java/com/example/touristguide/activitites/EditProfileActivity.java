package com.example.touristguide.activitites;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.touristguide.fragments.UserFragment;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editName, editEmail, editNumber;
    private String name, email, number;
    private Button buttonSave, buttonDelete;

    private Handler handler;
    private UserDao userDao;
    private Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        user = (Users) getIntent().getParcelableExtra("User");

        editName = (EditText) findViewById(R.id.nameEditText);
        editEmail = (EditText) findViewById(R.id.emailEditText);
        buttonSave = (Button) findViewById(R.id.saveEditChanges);
        buttonDelete = (Button) findViewById(R.id.deleteProfile);

        userDao = Room.databaseBuilder(this, DatabaseManager.class, "db_android")
                .allowMainThreadQueries()
                .build()
                .getUserDao();

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);

                builder.setTitle(R.string.alertDialogDelete);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userDao.deleteUserById(user.getId());
                        Intent intent = new Intent(EditProfileActivity.this,
                                LoginActivity.class);
                        startActivity(intent);
                        EditProfileActivity.this.finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),
                                "User refused to delete", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkDataEntered()) {
                    Log.v("Username", editName.getText().toString());
                    Log.v("Email", editEmail.getText().toString());
                    userDao.updateUserNameEmail(editName.getText().toString()
                            , editEmail.getText().toString(), user.getId());
                    Users NewUser = userDao.fetchOneUserById(user.getId());
                    Intent intent = new Intent(EditProfileActivity.this,
                            MainActivity.class);
                    intent.putExtra( "User", NewUser);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private boolean isEmpty(EditText text) {
        String string = text.getText().toString();
        return TextUtils.isEmpty(string);
    }

    private boolean checkDataEntered() {

        if(isEmpty(editName)) {
            Toast toast =  Toast.makeText(this, getString(R.string.provideUsername),
                    Toast.LENGTH_SHORT);
            toast.show();
            editName.setError(getString(R.string.requireUsername));

            return false;
        }

        if(isEmpty(editEmail)) {
            Toast toast =  Toast.makeText(this, getString(R.string.provideEmail),
                    Toast.LENGTH_SHORT);
            toast.show();
            editEmail.setError(getString(R.string.requireEmail));

            return false;
        }

        return true;
    }
}
