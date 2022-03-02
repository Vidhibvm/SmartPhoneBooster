package com.phonecleaner.fastbooster.safe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.Random;

public class WifiCleanerReadyActivity extends Activity implements View.OnClickListener {
 
    RelativeLayout app_cardsLay0;
    RelativeLayout app_cardsLay1;
    RelativeLayout app_cardsLay2;
    RelativeLayout backlay;
    TextView boostStatus;
    TextView boostsuccess;
    Context context;
    private int[] dumbTempArray = {4, 6, 8, 10, 12, 15, 20};
    SharedPreferences.Editor editor;
    TextView gameDes;
    TextView gameDes0;
    TextView gameDes1;
    TextView gameDes2;
    TextView gameName;
    TextView gameName0;
    TextView gameName1;
    TextView gameName2;
    Handler handler = new Handler();
    AnimationDrawable loadAnimation;
    RelativeLayout nativeContainerLay;
    RelativeLayout nativeDummyContainer;
    TextView netboostedPerValue;
    SharedPreferences pref;
    private Random random = new Random();
    TextView rateus_text;
    TextView rateus_text0;
    TextView rateus_text1;
    TextView rateus_text2;
    TextView title;

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.context = this;
        try {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            this.pref = defaultSharedPreferences;
            this.editor = defaultSharedPreferences.edit();
            setContentView(R.layout.activity_wifiboostcomplete);
            this.context = this;

            this.nativeContainerLay = (RelativeLayout) findViewById(R.id.nativeContainerLay);
            this.nativeDummyContainer = (RelativeLayout) findViewById(R.id.native_dummy_container);

            this.backlay = (RelativeLayout) findViewById(R.id.backlay);

            this.title = (TextView) findViewById(R.id.title);
            this.boostStatus = (TextView) findViewById(R.id.boostStatus);
            this.boostsuccess = (TextView) findViewById(R.id.boostsuccess);

            TextView textView = (TextView) findViewById(R.id.netboostedPerValue);
            this.netboostedPerValue = textView;
            textView.setTypeface(MainApp.RobotoRegular);
            this.title.setTypeface(MainApp.RobotoRegular);
            this.boostStatus.setTypeface(MainApp.RobotoRegular);
            this.boostsuccess.setTypeface(MainApp.RobotoRegular);
            this.gameName.setTypeface(MainApp.RobotoRegular);
            this.gameDes.setTypeface(MainApp.RobotoRegular);
            this.rateus_text.setTypeface(MainApp.RobotoRegular);
            this.backlay.setOnClickListener(this);
            int i = this.dumbTempArray[this.random.nextInt(7)];
            TextView textView2 = this.netboostedPerValue;
            textView2.setText("" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
       if (id == R.id.backlay) {
            back();
        }
    }

    public void back() {
        try {
            startActivity(new Intent(this.context, StartActivity.class));
            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    
    public void onResume() {
        super.onResume();
    }
}
