package com.iyad.sultan.charger;

/**
 * Created by salkhmis on 5/27/2016.
 */
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class start_4 extends AppCompatActivity {

    private final String NAME="NID";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_4);

        /*
        * 1 stc
        * 2 mobily
        * 3 zain*/
        final int provider  = getIntent().getExtras().getInt("provider");
        final String nid =getIntent().getExtras().getString("nid");

        TextView  viewNid = (TextView) findViewById(R.id.viewId);
        viewNid.setText(" "+nid);


        ImageView imgProvder = (ImageView) findViewById(R.id.imgProvider);
        if(provider == 1)
         imgProvder.setImageResource(R.drawable.stc_logo);
        else if(provider == 2)
           imgProvder.setImageResource(R.drawable.mobily_logo);
        else if (provider == 3)
            imgProvder.setImageResource(R.drawable.zain_logo);

        //Toast.makeText(start_4.this,provider +nid ,Toast.LENGTH_SHORT).show();

        final Button start =(Button) findViewById(R.id.btnStart_4);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //send Data To DataBase
              // Toast.makeText(start_4.this," comapny: " +provider +" id :"+ nid  ,Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor ed = getPreferences(MODE_PRIVATE).edit();
                ed.putInt("PROVIDER",provider);
                ed.putString("NID",nid);
                ed.commit();
                startActivity(new Intent(start_4.this,charger.class));



            }
        });

    }

}
