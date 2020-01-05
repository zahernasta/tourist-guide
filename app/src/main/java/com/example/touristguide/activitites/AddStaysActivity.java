package com.example.touristguide.activitites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.touristguide.MainActivity;
import com.example.touristguide.R;
import com.example.touristguide.database.DatabaseManager;
import com.example.touristguide.database.dao.StayDao;
import com.example.touristguide.database.models.Stays;
import com.example.touristguide.database.models.Users;

public class AddStaysActivity extends AppCompatActivity {

    private String name, description, picture;

    private EditText stayName, stayDescription, stayPicture;
    private Button addStayButton;
    private Users user;
    private StayDao stayDao;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stays);

        stayName = (EditText) findViewById(R.id.stayNameEditTextt);
        stayDescription = (EditText) findViewById(R.id.stayDescriptionEditText);
        stayPicture = (EditText) findViewById(R.id.stayPictureEditText);
        addStayButton = (Button) findViewById(R.id.addStayButton);
        user =  getIntent().getParcelableExtra("User");

        stayDao = Room.databaseBuilder(this, DatabaseManager.class, "db_android")
                .allowMainThreadQueries()
                .build()
                .getStayDao();

        addStayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("name", stayName.getText().toString());
                Log.v("description", stayDescription.getText().toString());
                Log.v("picture", stayPicture.getText().toString());
                Log.v("user", user.toString());

                name = stayName.getText().toString();
                description = stayDescription.getText().toString();
                picture = stayPicture.getText().toString();

                if(checkDataEntered()) {
                    handler = new Handler();

                    final Runnable r = new Runnable() {
                        @Override
                        public void run() {

                            Stays stay = new Stays(name, description, picture, user.getId());

                            stayDao.insertOneStay(stay);

                        }
                    };

                    handler.postDelayed(r, 1000);
                    Toast toast = Toast.makeText(AddStaysActivity.this, getString(R.string.success_add_stay),
                            Toast.LENGTH_SHORT);
                    toast.show();

                    Intent intent = new Intent(AddStaysActivity.this, MainActivity.class);
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

        if(isEmpty(stayName)) {
            Toast toast =  Toast.makeText(this, getString(R.string.add_stay_name),
                    Toast.LENGTH_SHORT);
            toast.show();
            stayName.setError(getString(R.string.stay_name_required));

            return false;
        }

        if(isEmpty(stayDescription)) {
            Toast toast =  Toast.makeText(this, getString(R.string.add_stay_description),
                    Toast.LENGTH_SHORT);
            toast.show();
            stayDescription.setError(getString(R.string.stay_description_required));

            return false;
        }

        if(isEmpty(stayPicture)) {
            Toast toast = Toast.makeText(this, R.string.add_stay_picture,
                    Toast.LENGTH_SHORT);
            toast.show();
            stayPicture.setError(getString(R.string.stay_picture_required));

            return false;
        }

        return true;
    }
}
