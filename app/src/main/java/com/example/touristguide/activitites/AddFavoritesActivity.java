package com.example.touristguide.activitites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.touristguide.MainActivity;
import com.example.touristguide.R;
import com.example.touristguide.components.Favorite;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFavoritesActivity extends AppCompatActivity {

    private EditText locationEditText, descriptionEditText, ratingEditText;
    private Button addButton;

    DatabaseReference databaseFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_favorites);

        locationEditText = findViewById(R.id.favoriteLocationEditText);
        descriptionEditText = findViewById(R.id.favoriteDescriptionEditText);
        ratingEditText = findViewById(R.id.favoriteRatingEditText);

        databaseFavorite = FirebaseDatabase.getInstance().getReference("favorites");

        addButton = findViewById(R.id.addFavoriteButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = locationEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                String rating = ratingEditText.getText().toString();

                if(checkDataEntered()) {
                    String id = databaseFavorite.push().getKey();

                    Favorite favorite = new Favorite(id, location, description, rating);

                    databaseFavorite.child(id).setValue(favorite);

                    Toast toast = Toast.makeText(AddFavoritesActivity.this, getString(R.string.successful_data_insert)
                            , Toast.LENGTH_SHORT);
                    toast.show();

                    Intent intent = new Intent(AddFavoritesActivity.this, MainActivity.class);
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

        if(isEmpty(locationEditText)) {
            Toast toast =  Toast.makeText(this, getString(R.string.fill_location),
                    Toast.LENGTH_SHORT);
            toast.show();
            locationEditText.setError(getString(R.string.fill_location));

            return false;
        }

        if(isEmpty(descriptionEditText)) {
            Toast toast =  Toast.makeText(this, getString(R.string.add_description_favorite),
                    Toast.LENGTH_SHORT);
            toast.show();
            descriptionEditText.setError(getString(R.string.add_description_favorite));

            return false;
        }

        if(isEmpty(ratingEditText)) {
            Toast toast = Toast.makeText(this, R.string.add_rating,
                    Toast.LENGTH_SHORT);
            toast.show();
            ratingEditText.setError(getString(R.string.add_rating));

            return false;
        }

        return true;
    }
}
