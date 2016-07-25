package com.iyad.sultan.charger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by salkhmis on 5/27/2016.
 */
public class start_3 extends AppCompatActivity {


    private  static  int company  ;
    /*
    * 1  stc
    * 2 Mobily
    * 3 Zain
    * */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_3);


        // get selected provider
        int selected = getIntent().getExtras().getInt("provider");

        if(selected == R.id.stcRadio)
           company = 1 ;
        else if(selected == R.id.mobilyRadio)
           company = 2;
        else if (selected == R.id.zainRadio)
           company = 3;



        //get NID Number

        final EditText ed = (EditText) findViewById(R.id.NID);


        final Button start = (Button) findViewById(R.id.btnStart_3);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bd  = new Bundle();
                bd.putInt("provider",company);
                bd.putString("nid",ed.getText().toString());
                startActivity(new Intent(start_3.this, start_4.class).putExtras(bd));


            }
        });
    }
}
