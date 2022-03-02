package com.phonecleaner.fastbooster.safe;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class SettingsActivity extends Activity implements View.OnClickListener {
    public static int a = 1;
 
    private RelativeLayout backLay;
    Context context;
    SharedPreferences.Editor editor;
    private TextView feedbackTxt;
    private RelativeLayout feedbacklay;
    private String manufacturer;
    int maxX = 39;
    int minX = 20;
    private String model;
    private TextView moreAppTxt;
    private RelativeLayout moreappsLay;
    public NotificationManager myNotificationManager;
    RelativeLayout nativeContainerLay;
    RelativeLayout nativeDummyContainer;
    int onStartCount = 0;
    SharedPreferences pref;
    private RelativeLayout rateLay;
    private TextView rateText;
    private RelativeLayout soundLay;
    private RelativeLayout soundOffLay;
    private TextView soundOffText;
    private RelativeLayout soundOnLay;
    private TextView soundOnText;
    private TextView soundText;
    private TextView title;
    private boolean val;

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_setting);
        this.context = this;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.pref = defaultSharedPreferences;
        this.editor = defaultSharedPreferences.edit();

        this.nativeContainerLay = (RelativeLayout) findViewById(R.id.nativeContainerLay);
        this.nativeDummyContainer = (RelativeLayout) findViewById(R.id.native_dummy_container);

        this.myNotificationManager = (NotificationManager) this.context.getSystemService(Context.NOTIFICATION_SERVICE);
        this.backLay = (RelativeLayout) findViewById(R.id.backlay);
        this.rateLay = (RelativeLayout) findViewById(R.id.RateUslay);

        this.soundLay = (RelativeLayout) findViewById(R.id.soundLay);
        this.soundOnLay = (RelativeLayout) findViewById(R.id.soundOnLay);
        this.soundOffLay = (RelativeLayout) findViewById(R.id.soundOffLay);
        this.title = (TextView) findViewById(R.id.textView8);
        this.rateText = (TextView) findViewById(R.id.rateText);

        this.soundText = (TextView) findViewById(R.id.soundTxt);
        this.soundOnText = (TextView) findViewById(R.id.soundOnTxt);
        this.soundOffText = (TextView) findViewById(R.id.soundOffTxt);
        boolean z = this.pref.getBoolean("soundchk", true);
        this.val = z;
        if (z) {
            soundOn();
        } else {
            soundOff();
        }
        this.title.setTypeface(MainApp.RobotoRegular);
        this.rateText.setTypeface(MainApp.RobotoRegular);
        this.moreAppTxt.setTypeface(MainApp.RobotoRegular);
        this.feedbackTxt.setTypeface(MainApp.RobotoRegular);
        this.soundText.setTypeface(MainApp.RobotoRegular);
        this.soundOnText.setTypeface(MainApp.RobotoRegular);
        this.soundOffText.setTypeface(MainApp.RobotoRegular);
        this.soundLay.setOnClickListener(this);
        this.backLay.setOnClickListener(this);
        this.rateLay.setOnClickListener(this);
        this.moreappsLay.setOnClickListener(this);
        this.feedbacklay.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.RateUslay:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast")));
                return;
            case R.id.backlay:
                back();
                return;
            case R.id.soundLay:
                if (this.pref.getBoolean("soundchk", false)) {
                    soundOff();
                    return;
                } else {
                    soundOn();
                    return;
                }
            default:
                return;
        }
    }

    public void back() {
        startActivity(new Intent(this.context, StartActivity.class));
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        finish();
    }

    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    public void soundOn() {
        this.soundOnLay.setBackgroundResource(R.drawable.enable_border);
        this.soundOnText.setTextColor(getResources().getColor(R.color.listitem));
        this.soundOffLay.setBackgroundResource(R.drawable.disable_border);
        this.soundOffText.setTextColor(getResources().getColor(R.color.blackcolor));
        this.editor.putBoolean("soundchk", true);
        this.editor.commit();
    }

    public void soundOff() {
        this.soundOffLay.setBackgroundResource(R.drawable.enable_border);
        this.soundOffText.setTextColor(getResources().getColor(R.color.listitem));
        this.soundOnLay.setBackgroundResource(R.drawable.disable_border);
        this.soundOnText.setTextColor(getResources().getColor(R.color.blackcolor));
        this.editor.putBoolean("soundchk", false);
        this.editor.commit();
    }

    
    public void onResume() {
        super.onResume();
    }
}
