package com.example.danny_brbar.galgeleg.fragmenter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.danny_brbar.galgeleg.R;

public class Fragment_Menu extends Fragment implements View.OnClickListener {

    private Button button_ContinueGame;
    private Button button_NewGame;
    private Button button_Stats;
    private Button button_Settings;

    private Fragment_Game frag_Game;
    private Fragment_Stats frag_Stats;
    private Fragment_Settings frag_Settings;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_galgeleg__menu, container, false);

        button_ContinueGame = (Button) view.findViewById(R.id.ContinueGame);
        button_NewGame = (Button) view.findViewById(R.id.NewGame);
        button_Stats = (Button) view.findViewById(R.id.Stats);
        button_Settings = (Button) view.findViewById(R.id.Settings);

        button_ContinueGame.setOnClickListener(this);
        button_NewGame.setOnClickListener(this);
        button_Stats.setOnClickListener(this);
        button_Settings.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {

        boolean newPage = false;

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        switch(v.getId()) {
            case R.id.ContinueGame:     // Continue Game currently has no effect but to start a new game
                frag_Game = new Fragment_Game();
                transaction.replace(R.id.fragment_container, frag_Game);
                newPage = true;
                break;
            case R.id.NewGame:
                frag_Game = new Fragment_Game();
                transaction.replace(R.id.fragment_container, frag_Game);
                newPage = true;
                break;
            case R.id.Stats:            // Show some high scores using SharedPreferences
                frag_Stats = new Fragment_Stats();
                transaction.replace(R.id.fragment_container, frag_Stats);
                newPage = true;
                break;
            case R.id.Settings:         // No settings yet - but are to be implemented
                frag_Settings = new Fragment_Settings();
                transaction.replace(R.id.fragment_container, frag_Settings);
                newPage = true;
                break;
            default:
                newPage = false;
        }

        if (newPage == true) {
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
