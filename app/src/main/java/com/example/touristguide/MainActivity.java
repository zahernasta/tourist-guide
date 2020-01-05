package com.example.touristguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.touristguide.activitites.AddFavoritesActivity;
import com.example.touristguide.activitites.AddStaysActivity;
import com.example.touristguide.activitites.EditProfileActivity;
import com.example.touristguide.activitites.FavoritesActivity;
import com.example.touristguide.activitites.LoginActivity;
import com.example.touristguide.activitites.StaysActivity;
import com.example.touristguide.activitites.ViewProfileActivity;
import com.example.touristguide.database.models.Users;
import com.example.touristguide.fragments.FavoriteFragment;
import com.example.touristguide.fragments.HomeFragment;
import com.example.touristguide.fragments.InboxFragement;
import com.example.touristguide.fragments.TripsFragment;
import com.example.touristguide.fragments.UserFragment;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    
    private TabAdapter tabAdapter;
    private TabLayout tabLayout;
    private CardView cardView;
    private ViewPager viewPager;
    private int[] tabIcons = {
        R.drawable.ic_search_black_24dp,
//        R.drawable.ic_favorite_border_black_24dp,
//        R.drawable.ic_flight_black_24dp,
//        R.drawable.ic_inbox_black_24dp,
        R.drawable.ic_person_outline_black_24dp
    };

    Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (Users) getIntent().getParcelableExtra("User");
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        cardView = (CardView) findViewById(R.id.editProfileCard);

        tabAdapter = new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(new HomeFragment(), "Home");
//        tabAdapter.addFragment(new FavoriteFragment(), "Favorite");
//        tabAdapter.addFragment(new TripsFragment(), "Trips");
//        tabAdapter.addFragment(new InboxFragement(), "Inbox");
        tabAdapter.addFragment(new UserFragment(), "User");

        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

        for(int i = 0; i < tabIcons.length; i++) {
            tabLayout.getTabAt(i).setIcon(tabIcons[i]);
        }
    }

    public void openEditProfile(View view) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void openStaysCard(View view) {
        Intent intent = new Intent(this, StaysActivity.class);
        startActivity(intent);
    }

    public void openViewProfile(View view) {
        Intent intent = new Intent(this, ViewProfileActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void openAddStay(View view) {
        Intent intent = new Intent(this, AddStaysActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void openAddFavorite(View view) {
        Intent intent = new Intent (this, AddFavoritesActivity.class);
        startActivity(intent);
    }

    public void openFavoriteActivity(View view) {
        Intent intent = new Intent (this, FavoritesActivity.class);
        startActivity(intent);
    }

    public void onCLickLogout(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        MainActivity.this.finish();
    }
}
