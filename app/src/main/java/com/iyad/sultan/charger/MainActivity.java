package com.iyad.sultan.charger;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //check data base if there contant start Main class
        //1-startActivity(Main app)
        //  SharedPreferences shared = getPreferences(MODE_PRIVATE);
        SharedPreferences myPrefs = getSharedPreferences("start_4", MODE_PRIVATE);
        String nid = myPrefs.getString("NID", null);
        int provider = myPrefs.getInt("PROVIDER", 0);

        //show what in sharedprefrence
        Toast.makeText(MainActivity.this, nid + "  +  " + provider, Toast.LENGTH_LONG).show();

//no data start initialiize process
        if (nid == null && provider == 0)
            startActivity(new Intent(MainActivity.this, start_1.class));

        else
            startActivity(new Intent(MainActivity.this, charger.class));
    }
}
