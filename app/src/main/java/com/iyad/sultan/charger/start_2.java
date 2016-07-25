package com.iyad.sultan.charger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class start_2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_2);

        final Button start =(Button) findViewById(R.id.btnStart_2);


        final RadioGroup rg =(RadioGroup) findViewById(R.id.providerRadioGroup) ;

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int selected = rg.getCheckedRadioButtonId();

                if(selected !=-1) {
                    Bundle bund = new Bundle();
                    bund.putInt("provider",selected );
                    startActivity(new Intent(start_2.this, start_3.class).putExtras(bund));
                }
                else Toast.makeText(start_2.this,getResources().getString(R.string.no_Item_selected),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
