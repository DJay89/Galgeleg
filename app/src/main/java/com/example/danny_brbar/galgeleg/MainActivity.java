package com.example.danny_brbar.galgeleg;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.danny_brbar.galgeleg.fragmenter.Fragment_Menu;

public class MainActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menu fragment to be placed in activity
        Fragment_Menu startMenu = new Fragment_Menu();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, startMenu).commit();
    }


}
