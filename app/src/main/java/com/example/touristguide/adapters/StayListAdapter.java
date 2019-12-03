package com.example.touristguide.adapters;

import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.touristguide.R;
import com.example.touristguide.components.Stay;

import java.util.ArrayList;
import java.util.List;

public class StayListAdapter extends ArrayAdapter<Stay> {

    private Context mContext;
    private int mResource;

    /**
     *
     * @param context
     * @param resource
     * @param objects
     */
    public StayListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Stay> objects) {
        super(context, resource, objects);

        mContext = context;
        mResource = resource;
    }


    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //getting stays information
        String location = getItem(position).getLocation();
        String description = getItem(position).getDescription();
        float rating = getItem(position).getRating();
        int picture = getItem(position).getPicture();

        //create the stay object with the information
        Stay stay = new Stay(location, description, rating, picture);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView textViewLocation = (TextView) convertView.findViewById(R.id.locationText);
        TextView textViewDescription = (TextView) convertView.findViewById(R.id.descriptionText);
        TextView textViewRating = (TextView) convertView.findViewById(R.id.ratingText);
        ImageView imageViewPicture = (ImageView) convertView.findViewById(R.id.stayImage);

        textViewLocation.setText(location);
        textViewDescription.setText(description);
        textViewRating.setText(" " + rating);
        imageViewPicture.setImageResource(picture);

        return convertView;

    }
}
