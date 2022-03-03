package com.phonecleaner.fastbooster.safe;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class BatteryFirstActivity extends AppCompatActivity {
 
    private RelativeLayout ScanLay;
    private TextView analyziningTxt;

    private Animation blinkTxtAnim;
    Context context;
    Handler handler;
    ImageView loadingImage;
    private Animation sgAnimation;

    @Override 
    public void onBackPressed() {
    }

    
    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_scanning_battery);
        this.context = this;


        this.handler = new Handler();
        this.analyziningTxt = (TextView) findViewById(R.id.analyzingTxt);
        this.loadingImage = (ImageView) findViewById(R.id.loadingImage);
        this.analyziningTxt.setTypeface(MainApp.RobotoRegular);
        this.ScanLay = (RelativeLayout) findViewById(R.id.scanLay);
        Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottomtofix_position);
        this.sgAnimation = loadAnimation;
        this.ScanLay.startAnimation(loadAnimation);
        AnimationDrawable animationDrawable = (AnimationDrawable) this.loadingImage.getBackground();
        animationDrawable.setVisible(false, true);
        animationDrawable.start();
        this.handler.postDelayed(new Runnable() {

            public void run() {
                BatteryFirstActivity.this.startActivity(new Intent(BatteryFirstActivity.this, BatterySavingActivity.class));
                BatteryFirstActivity.this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                BatteryFirstActivity.this.finish();
            }
        }, 7000);
    }

    
    @Override 
    public void onResume() {
        super.onResume();
    }
}
