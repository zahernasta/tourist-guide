package com.example.touristguide.activitites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.touristguide.R;
import com.example.touristguide.adapters.StayListAdapter;
import com.example.touristguide.components.Stay;
import com.example.touristguide.network.JSONParser;
import com.example.touristguide.network.StayAsync;
import com.example.touristguide.network.StayResponse;

import java.util.ArrayList;


public class StaysActivity extends AppCompatActivity {

    private static final String TAG = "StaysActivity";
    private static final String URL = "https://api.myjson.com/bins/dcgxu";
    private StayResponse stayResponse;
    private ProgressBar spinner;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stays);
        Log.v(TAG, "onCreate Started");
        spinner = (ProgressBar) findViewById(R.id.progressBarSpinner);


        new StayAsync() {

            @Override
            protected void onPreExecute() {
                spinner.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {

                stayResponse = JSONParser.parseJSON(s);
                Log.v(TAG, stayResponse.toString());
                if(stayResponse != null) {
                    Toast.makeText(getApplicationContext(), stayResponse.toString(),
                            Toast.LENGTH_LONG).show();
                }
                spinner.setVisibility(View.INVISIBLE);
            }

        }.execute(URL);

        ListView mListView = (ListView) findViewById(R.id.staysListView);
        mListView.setDivider(null);

        Stay stay1 = new Stay("Bucharest, Romania",
                "Historic Apartment in the center of Bucharest", 4.05f,
                R.drawable.stay_bucahrest_historic);

        Stay stay2 = new Stay("Bucharest, Romania",
                "Modern Apartment in highest of class neighborhood in Bucharest", 4.88f,
                R.drawable.stay_bucharest_modern);

        Stay stay3 = new Stay("Constanta, Romania",
                "Pool House close to the beach ", 4.70f,
                R.drawable.stay_constanta_poolhouse);

        Stay stay4 = new Stay("Brasov, Romania",
                "Historic Apartment in the center of Brasov", 4.05f,
                R.drawable.stay_brasov_historic);

        ArrayList<Stay> staysList = new ArrayList<>();
        staysList.add(stay1);
        staysList.add(stay2);
        staysList.add(stay3);
        staysList.add(stay4);

        StayListAdapter adapter = new StayListAdapter(this, R.layout.stay_view_adapter, staysList);
        mListView.setAdapter(adapter);


    }
}
