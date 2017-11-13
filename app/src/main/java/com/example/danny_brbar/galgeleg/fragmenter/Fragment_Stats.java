package com.example.danny_brbar.galgeleg.fragmenter;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.danny_brbar.galgeleg.R;

import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Stats extends Fragment {


    // Listview
    ListView listView;

    // Shared preferences - Local data
    SharedPreferences prefs;
    SharedPreferences.Editor prefsEditor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__stats, container, false);

        // Access shared preferences data saved under Highscore.
        prefs = this.getActivity().getSharedPreferences("Highscore", Context.MODE_PRIVATE);

        String[] scoreboard = {"Highscore 1", "Highscore 2", "Highscore 3", "Highscore 4", "Highscore 5"};

        // Add the highscore to the list
        for (int i = 1; i <= 5; i++) {
            String tmp = "highscore" + i;
            int score = prefs.getInt(tmp, 0);
            scoreboard[i-1] = scoreboard[i-1] + " : " + score;
        }

        listView = (ListView) view.findViewById(R.id.Scoreboard);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                scoreboard
        );

        listView.setAdapter(listViewAdapter);

        return view;

    }


}
