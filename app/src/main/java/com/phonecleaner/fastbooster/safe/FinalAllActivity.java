package com.phonecleaner.fastbooster.safe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Random;

public class FinalAllActivity extends AppCompatActivity implements View.OnClickListener {
    public static boolean backToNoHome;
 
    RelativeLayout app_cardsLay0;
    RelativeLayout app_cardsLay1;
    RelativeLayout app_cardsLay2;
    RelativeLayout backlay;

    private TextView boostSubText;
    private TextView boostText;
    Context context;
    private double[] dumbTempArray = {1.0d, 1.3d, 1.5d, 1.6d, 1.9d, 2.0d, 2.3d, 2.5d, 2.7d, 3.0d, 3.2d, 3.5d, 4.0d, 4.2d, 4.3d};
    Animation fadeInAnim;
    Animation fadeOutAnim;
    private TextView gameDes0;
    private TextView gameDes1;
    private TextView gameDes2;
    private TextView gameName0;
    private TextView gameName1;
    private TextView gameName2;
    Handler handler = new Handler();
    Animation heartbeatAnim;
    Animation leftInAnim;
    private String manufacturer;
    private String model;
    RelativeLayout nativeContainerLay;
    RelativeLayout nativeDummyContainer;
    private Random random = new Random();
    private TextView rateus_text0;
    private TextView rateus_text1;
    private TextView rateus_text2;
    Animation rotateAnim;
    Animation shakeAnim;

  //  private ImageView starsImg;
    private TextView videoStatus;
    private ImageView waterImg;

    
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_optimize);
        this.context = this;


        this.nativeContainerLay = (RelativeLayout) findViewById(R.id.nativeContainerLay);
        this.nativeDummyContainer = (RelativeLayout) findViewById(R.id.native_dummy_container);

        this.backlay = (RelativeLayout) findViewById(R.id.backlay);
        this.waterImg = (ImageView) findViewById(R.id.waterImg);
      //  this.starsImg = (ImageView) findViewById(R.id.starsImg);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                FinalAllActivity optimizeActivity = FinalAllActivity.this;
                optimizeActivity.leftInAnim = AnimationUtils.loadAnimation(optimizeActivity, R.anim.push_left_in);
           //     OptimizeActivity.this.starsImg.startAnimation(OptimizeActivity.this.leftInAnim);
             //   OptimizeActivity.this.starsImg.setVisibility(View.VISIBLE);
            }
        }, 500);

        this.boostText = (TextView) findViewById(R.id.cooledText);
        this.boostSubText = (TextView) findViewById(R.id.droppedText);
        this.boostText.setTypeface(MainApp.RobotoRegular);
        this.boostSubText.setTypeface(MainApp.RobotoRegular);
        this.backlay.setOnClickListener(this);
        if (util.CheckFromWichActivityComming == 1) {
            this.boostText.setText(getResources().getString(R.string.wifiBoost));
            this.boostSubText.setText(getResources().getString(R.string.wifiBoostSubTxt));
            wiFiBoostLayAnim();
        } else if (util.CheckFromWichActivityComming == 2) {
            this.boostText.setText(getResources().getString(R.string.batteryBoosted));
            this.boostSubText.setText(getResources().getString(R.string.enjoyBattery));
            batteryLayAnim();
        } else if (util.CheckFromWichActivityComming == 3) {
            this.boostText.setText(getResources().getString(R.string.cooler));
            double d = this.dumbTempArray[this.random.nextInt(15)];
            TextView textView = this.boostSubText;
            textView.setText(getResources().getString(R.string.dropped) + " : " + d);
            snowLayAnim();
        } else if (util.CheckFromWichActivityComming == 4) {
            this.boostText.setText(getResources().getString(R.string.booster));
            this.boostSubText.setText(getResources().getString(R.string.boostedSub));
            tickLayAnim();
        } else if (util.CheckFromWichActivityComming == 5) {
            this.boostText.setText(getResources().getString(R.string.alreadyBooster));
            this.boostSubText.setText(getResources().getString(R.string.boostedSub));
            tickLayAnim();
        } else if (util.CheckFromWichActivityComming == 6) {
            this.boostText.setText(getResources().getString(R.string.AlreadybatteryBoosted));
            this.boostSubText.setText(getResources().getString(R.string.enjoyBattery));
            batteryLayAnim();
        } else if (util.CheckFromWichActivityComming == 7) {
            this.boostText.setText(getResources().getString(R.string.AlreadyCooler));
            this.boostSubText.setText(getResources().getString(R.string.coolerSub_des));
            snowLayAnim();
        } else if (util.CheckFromWichActivityComming == 8) {
            this.boostText.setText(getResources().getString(R.string.AlreadywifiBoost));
            this.boostSubText.setText(getResources().getString(R.string.wifiBoostSubTxt));
            wiFiBoostLayAnim();
        }
    }

    @SuppressLint("WrongConstant")
    public void batteryLayAnim() {
        this.waterImg.setVisibility(8);

    }

    @SuppressLint("WrongConstant")
    public void tickLayAnim() {
        this.waterImg.setVisibility(8);

    }

    @SuppressLint("WrongConstant")
    public void snowLayAnim() {
        this.waterImg.setVisibility(8);

        this.handler.postDelayed(new Runnable() {

            public void run() {
                FinalAllActivity optimizeActivity = FinalAllActivity.this;
                optimizeActivity.fadeInAnim = AnimationUtils.loadAnimation(optimizeActivity.context, R.anim.fade_in);
                FinalAllActivity.this.waterImg.setVisibility(0);
                FinalAllActivity.this.waterImg.startAnimation(FinalAllActivity.this.fadeInAnim);
                FinalAllActivity.this.handler.postDelayed(new Runnable() {

                    public void run() {
                        FinalAllActivity.this.fadeOutAnim = AnimationUtils.loadAnimation(FinalAllActivity.this.context, R.anim.fade_out);
                        FinalAllActivity.this.waterImg.setVisibility(8);
                        FinalAllActivity.this.waterImg.startAnimation(FinalAllActivity.this.fadeOutAnim);
                    }
                }, 1500);
            }
        }, 1000);
    }

    @SuppressLint("WrongConstant")
    public void wiFiBoostLayAnim() {
        this.waterImg.setVisibility(8);

    }

    public void RateUs(View view) {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast")));
    }

    public void FeedBack(View view) {
        try {
            this.manufacturer = Build.VERSION.RELEASE;
            this.model = Build.MODEL;
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("message/rfc822");
            intent.putExtra("android.intent.extra.EMAIL", new String[]{"protrustedapps@gmail.com"});
            intent.putExtra("android.intent.extra.SUBJECT", getString(R.string.feedbackTitle) + "[" + this.model + "-" + this.manufacturer + "]");
            intent.putExtra("android.intent.extra.TEXT", "");
            startActivity(intent);
        } catch (Exception unused) {
            Toast.makeText(this.context, "Please Install app for email", Toast.LENGTH_LONG).show();
        }
    }

    public void back() {
        if (backToNoHome) {
            backToNoHome = false;
            startActivity(new Intent(this.context, BatterySavingActivity.class));
            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            finish();
            return;
        }
        startActivity(new Intent(this.context, StartActivity.class));
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onBackPressed() {
        back();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.backlay) {
            back();
        }
    }

    
    @Override // androidx.fragment.app.FragmentActivity
    public void onResume() {
        super.onResume();
    }
}
