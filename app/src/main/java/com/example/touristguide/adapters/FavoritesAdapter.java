package com.example.touristguide.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.touristguide.R;
import com.example.touristguide.components.Favorite;

import org.w3c.dom.Text;

import java.util.List;

public class FavoritesAdapter extends ArrayAdapter<Favorite> {

    private Context context;
    private List<Favorite> favoriteList;
    private TextView location, description, rating;

    public FavoritesAdapter(@NonNull Context context, @NonNull List<Favorite> objects) {
        super(context, R.layout.favorites_list_layout, objects);
        this.context = context;
        this.favoriteList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View listViewItem = inflater.inflate(R.layout.favorites_list_layout, null, true);

        location = listViewItem.findViewById(R.id.textViewLocation);
        description = listViewItem.findViewById(R.id.textViewDescription);
        rating = listViewItem.findViewById(R.id.textViewRating);

        Favorite favorite = favoriteList.get(position);

        location.setText(favorite.getLocation());
        description.setText(favorite.getDescription());
        rating.setText(favorite.getRating());

        return listViewItem;
    }
}
