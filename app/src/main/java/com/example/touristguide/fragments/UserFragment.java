package com.example.touristguide.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.touristguide.R;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    TextView userName, userEmail, userNumber;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_user, container, false);
        userName = (TextView) rootView.findViewById(R.id.userNameText);
        userEmail = (TextView) rootView.findViewById(R.id.emailText);
        userNumber = (TextView) rootView.findViewById(R.id.numberText);

        Bundle bundle = getArguments();


        if (bundle != null) {
            Log.v("IT_WORKS", bundle.toString());
            userName.setText(bundle.getString("name"));
            userEmail.setText(bundle.getString("email"));
            userNumber.setText(bundle.getString("number"));

        } else {
            Log.v("IT_NO_WORK", "It Doesn't work");
        }




        // Inflate the layout for this fragment
        return rootView;
    }

}
