package com.example.danny_brbar.galgeleg.fragmenter;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.danny_brbar.galgeleg.MainActivity;
import com.example.danny_brbar.galgeleg.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_EndGame extends Fragment implements View.OnClickListener {

    private TextView twEndCondition;
    private TextView twWord01;
    private TextView twWord02;
    private TextView twWord03;

    private Button bReturn;

    // Bundle argument keywords
    private final String bundleWord = "word";
    private final String bundleCondition = "endCondition";
    private final String bundleGuesses = "incorrectGuesses";

    // return to main menu
    Fragment frag_Menu;

    // Shared preferences - Local data
    SharedPreferences prefs;
    SharedPreferences.Editor prefsEditor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__end_game, container, false);

        twEndCondition = (TextView) view.findViewById(R.id.twEndCondition); // Win or lose text view
        twWord01 = (TextView) view.findViewById(R.id.twWord01);             // Correct word was
        twWord02 = (TextView) view.findViewById(R.id.twWord02);             // Word
        twWord03 = (TextView) view.findViewById(R.id.twWord03);             // Incorrect amount of guesses

        // Buttons to return to main menu after the game has ended
        bReturn = (Button) view.findViewById(R.id.bReturn);
        bReturn.setOnClickListener(this);

        boolean endCondition = false; // Lose = false, Win = true
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            endCondition = bundle.getBoolean(bundleCondition);
            if (endCondition == false) {
                // lose
                System.out.println("lose");
                twEndCondition.setText("You've lost the game!");
                twEndCondition.setTextColor(Color.RED);
                twWord01.setText("The correct word was");
                twWord02.setText(bundle.getString(bundleWord));

            } else {
                // win
                System.out.println("Win");
                twEndCondition.setText("You've won the game");
                twEndCondition.setTextColor(Color.GREEN);
                twWord01.setText("You've guessed the correct word");
                twWord02.setText(bundle.getString(bundleWord));
            }
            twWord03.setText("Incorrect Guesses: " + bundle.getInt(bundleGuesses));
        }


        // Access shared preferences data saved under Highscore.
        prefs = this.getActivity().getSharedPreferences("Highscore", Context.MODE_PRIVATE);
        prefsEditor = prefs.edit();

        int winStreak = prefs.getInt("winStreak", 0);   // Get the winStreak if there is any. otherwise set it to 0
        if (endCondition) { // if Win
            winStreak++;
            prefsEditor.putInt("winStreak", winStreak); // Count up the winstreak
            prefsEditor.commit();
        } else {
            // If it's a loss
            reOrderHighScore(winStreak);    // Recursive function moving down the score on a top5 highscore board
            prefsEditor.putInt("winStreak", 0); // Put winstreak back to 0
            prefsEditor.commit();
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        // Return to main menu
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
    }


    private void reOrderHighScore(int newScore) {
        if (newScore == 0) return;

        for (int i = 1; i <= 5; i++) {
            String tmp = "highscore" + i;
            int score = prefs.getInt(tmp, 0);
            if (newScore > score) {

                prefsEditor.putInt(tmp, newScore);
                prefsEditor.commit();
                reOrderHighScore(score);
                break;
            }
        }
    }
}
