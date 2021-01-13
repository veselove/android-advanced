package com.veselove.xmlparser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import org.xmlpull.v1.XmlPullParser;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    TextView tv;
    Boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textview1);

        sp = PreferenceManager.getDefaultSharedPreferences(this);

        XmlPullParser xpp = getResources().getXml(R.xml.smartphones);
        SmartphoneResourceParser parser = new SmartphoneResourceParser();
        if(parser.parse(xpp))
        {
            for(Smartphone prod: parser.getSmartphones()){
                Log.d("XML", prod.toString());
            }
        }
    }


    @Override
    protected void onResume() {
        Boolean enlargeText = sp.getBoolean("enlargeText", false);
        Boolean doubleBackPressToExit = sp.getBoolean("doubleBackPressToExit", false);
        if (enlargeText) {
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
        }
        else {
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        }
        if (doubleBackPressToExit) {
            doubleBackToExitPressedOnce = false;
        }
        else {
            doubleBackToExitPressedOnce = true;
        }
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settingsButton:
                Intent intent = new Intent(this, PrefActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}