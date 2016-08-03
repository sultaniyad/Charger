package com.iyad.sultan.charger;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


/**
 * Created by salkhmis on 6/3/2016.
 */
public class charger extends AppCompatActivity {

    final static int REQUEST_PHONE_CALL = 123;

    SharedPreferences myPrefs;
    private String nid;
    private int provider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        boolean isRightToLeft = getResources().getBoolean(R.bool.is_right_to_left);
        //IF less then  API 16  chose charge_arabic
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN && isRightToLeft)
            setContentView(R.layout.charger_arabic);
        else
            setContentView(R.layout.charge);

        //adverting
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-2100187188382709~6532666677");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //end

        //get all stored information
        myPrefs = getSharedPreferences("start_4", MODE_PRIVATE);
        nid = myPrefs.getString("NID", null);
        provider = myPrefs.getInt("PROVIDER", 0);
        //to make sure there is  always NID and others
        if (nid == null && provider == 0) {
            startActivity(new Intent(charger.this, start_1.class));

        } else {
            TextView textNid = (TextView) findViewById(R.id.viewId2);
            ImageView logo = (ImageView) findViewById(R.id.imgLogo);

            switch (provider) {
                case 1:
                    logo.setImageResource(R.mipmap.rounded_corners_stc);
                    ;
                    break;
                case 2:
                    logo.setImageResource(R.mipmap.rounded_corners_mobily);
                    ;
                    break;
                case 3:
                    logo.setImageResource(R.mipmap.rounded_corners_zain);
                    ;
                    break;
                default:
                    Toast.makeText(charger.this, "no image logo Found!!!", Toast.LENGTH_SHORT).show();
            }
            textNid.setText("" + nid);

            //Toast.makeText(this, "new id is :" + nid, Toast.LENGTH_LONG).show();

            //ask for permissions (call ,internet) on app Start if not granted
          onAppStart("android.permission.CALL_PHONE");

        }
    }


    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.reset:
                startActivity(new Intent(charger.this, start_1.class));
                break;

            default:
                ;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Actions


    //view user balance
    public void requestBalance(View v) {

        if (checkPermission("android.permission.CALL_PHONE")) {
            Intent balance = new Intent(Intent.ACTION_CALL);


            switch (provider) {
                case 1:
                    balance.setData(Uri.parse("tel:" + Uri.encode("*166#")));
                    break;
                case 2:
                    balance.setData(Uri.parse("tel:" + Uri.encode("*1411#")));
                    break;
                case 3:
                    balance.setData(Uri.parse("tel:" + Uri.encode("*142#")));
                    break;
                default:
                    Toast.makeText(charger.this, "Error!!!", Toast.LENGTH_SHORT).show();
            }
            startActivity(balance);

        }
        //No Permission given
        else {
            Toast.makeText(charger.this, getResources().getString(R.string.requestPhonePermission), Toast.LENGTH_LONG).show();
            requestPermission("android.permission.CALL_PHONE", REQUEST_PHONE_CALL);
        }
    }

    //charger user balance

    public void reCharge(View v) {


        if (checkPermission("android.permission.CALL_PHONE")) {
            Intent reChargerBalance = new Intent(Intent.ACTION_CALL);
            EditText cardNum = (EditText) findViewById(R.id.cardNumber);


            switch (provider) {
                case 1:
                    reChargerBalance.setData(Uri.parse("tel:" + Uri.encode("*155*" + cardNum.getText().toString() + "*" + nid + "#")));
                    break;
                case 2:
                    reChargerBalance.setData(Uri.parse("tel:" + Uri.encode("*1400*" + cardNum.getText().toString() + "*" + nid + "#")));
                    break;
                case 3:
                    reChargerBalance.setData(Uri.parse("tel:" + Uri.encode("*141*" + nid + "*" + cardNum.getText().toString() + "#")));
                    break;
                default:
                    Toast.makeText(charger.this, "Error!!!", Toast.LENGTH_SHORT).show();
            }
            startActivity(reChargerBalance);

        }
        //No Permission given
        else {
            Toast.makeText(charger.this, getResources().getString(R.string.requestPhonePermission), Toast.LENGTH_LONG).show();
            requestPermission("android.permission.CALL_PHONE", REQUEST_PHONE_CALL);
        }

    }

    //check for Permission and i can pass array to check for all permissions (Important)
    public boolean checkPermission(String permission) {
        int isGrandPermission = ContextCompat.checkSelfPermission(charger.this, permission);
        return (isGrandPermission == PackageManager.PERMISSION_GRANTED);
    }

    //ask for one Permission here Or can add Array of Permissions
    public void requestPermission(String permission, int permissionCode) {
        ActivityCompat.requestPermissions(charger.this, new String[]{permission}, permissionCode);

    }

    //check for call per on start
    void onAppStart(String permession){
       boolean isGrand= checkPermission(permession);
        if(isGrand)
            ;//do nothing
        else
        requestPermission(permession,REQUEST_PHONE_CALL);

    }


}
