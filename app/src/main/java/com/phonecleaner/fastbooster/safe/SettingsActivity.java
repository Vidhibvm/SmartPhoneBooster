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
 
    LinearLayout adContainer;
    private RelativeLayout backLay;
    RelativeLayout bannerAdLay;
    Context context;
    RelativeLayout dummyBannerContainer;
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
        this.bannerAdLay = (RelativeLayout) findViewById(R.id.bannerAdLay);
        this.dummyBannerContainer = (RelativeLayout) findViewById(R.id.dummy_banner_container);
        this.adContainer = (LinearLayout) findViewById(R.id.banner_container);
      
        this.nativeContainerLay = (RelativeLayout) findViewById(R.id.nativeContainerLay);
        this.nativeDummyContainer = (RelativeLayout) findViewById(R.id.native_dummy_container);

        this.myNotificationManager = (NotificationManager) this.context.getSystemService(Context.NOTIFICATION_SERVICE);
        this.backLay = (RelativeLayout) findViewById(R.id.backlay);
        this.rateLay = (RelativeLayout) findViewById(R.id.RateUslay);
        this.moreappsLay = (RelativeLayout) findViewById(R.id.moreappsLay);
        this.feedbacklay = (RelativeLayout) findViewById(R.id.feedbackLay);
        this.soundLay = (RelativeLayout) findViewById(R.id.soundLay);
        this.soundOnLay = (RelativeLayout) findViewById(R.id.soundOnLay);
        this.soundOffLay = (RelativeLayout) findViewById(R.id.soundOffLay);
        this.title = (TextView) findViewById(R.id.textView8);
        this.rateText = (TextView) findViewById(R.id.rateText);
        this.moreAppTxt = (TextView) findViewById(R.id.moreAppTxt);
        this.feedbackTxt = (TextView) findViewById(R.id.feedbackTxt);
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
        this.title.setTypeface(AppAnaylatics.RobotoRegular);
        this.rateText.setTypeface(AppAnaylatics.RobotoRegular);
        this.moreAppTxt.setTypeface(AppAnaylatics.RobotoRegular);
        this.feedbackTxt.setTypeface(AppAnaylatics.RobotoRegular);
        this.soundText.setTypeface(AppAnaylatics.RobotoRegular);
        this.soundOnText.setTypeface(AppAnaylatics.RobotoRegular);
        this.soundOffText.setTypeface(AppAnaylatics.RobotoRegular);
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
            case R.id.feedbackLay:
                try {
                    this.manufacturer = Build.VERSION.RELEASE;
                    this.model = Build.MODEL;
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("message/rfc822");
                    intent.putExtra("android.intent.extra.EMAIL", new String[]{"protrustedapps@gmail.com"});
                    intent.putExtra("android.intent.extra.SUBJECT", getString(R.string.feedbackTitle) + "[" + this.model + "-" + this.manufacturer + "]");
                    intent.putExtra("android.intent.extra.TEXT", "");
                    startActivity(intent);
                    return;
                } catch (Exception unused) {
                    Toast.makeText(this.context, "Please Install app for email", Toast.LENGTH_LONG).show();
                    return;
                }
            case R.id.moreappsLay:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.moreAppsLink))));
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
