package com.example.touristguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.touristguide.activitites.EditProfileActivity;
import com.example.touristguide.activitites.StaysActivity;
import com.example.touristguide.fragments.FavoriteFragment;
import com.example.touristguide.fragments.HomeFragment;
import com.example.touristguide.fragments.InboxFragement;
import com.example.touristguide.fragments.TripsFragment;
import com.example.touristguide.fragments.UserFragment;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    private TabAdapter tabAdapter;
    private TabLayout tabLayout;
    private CardView cardView;
    private ViewPager viewPager;
    private int[] tabIcons = {
        R.drawable.ic_search_black_24dp,
        R.drawable.ic_favorite_border_black_24dp,
        R.drawable.ic_flight_black_24dp,
        R.drawable.ic_inbox_black_24dp,
        R.drawable.ic_person_outline_black_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        cardView = (CardView) findViewById(R.id.editProfileCard);

        tabAdapter = new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(new HomeFragment(), "Home");
        tabAdapter.addFragment(new FavoriteFragment(), "Favorite");
        tabAdapter.addFragment(new TripsFragment(), "Trips");
        tabAdapter.addFragment(new InboxFragement(), "Inbox");
        tabAdapter.addFragment(new UserFragment(), "User");

        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

        for(int i = 0; i < tabIcons.length; i++) {
            tabLayout.getTabAt(i).setIcon(tabIcons[i]);
        }
    }

    public void openEditProfile(View view) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }

    public void openStaysCard(View view) {
        Intent intent = new Intent(this, StaysActivity.class);
        startActivity(intent);
    }
}
