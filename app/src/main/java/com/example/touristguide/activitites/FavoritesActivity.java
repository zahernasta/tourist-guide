package com.example.touristguide.activitites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.touristguide.R;
import com.example.touristguide.adapters.FavoritesAdapter;
import com.example.touristguide.components.Favorite;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    ListView listViewFavorites;
    DatabaseReference databaseFavorites;

    List<Favorite> favList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        listViewFavorites = (ListView) findViewById(R.id.favoritesListView);
        databaseFavorites = FirebaseDatabase.getInstance().getReference("favorites");
        favList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseFavorites.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                favList.clear();

                for(DataSnapshot favoritesSnapshot: dataSnapshot.getChildren()) {
                    Favorite favorite = favoritesSnapshot.getValue(Favorite.class);

                    favList.add(favorite);
                }

                ArrayAdapter adapter = new FavoritesAdapter(FavoritesActivity.this, favList);
                listViewFavorites.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
