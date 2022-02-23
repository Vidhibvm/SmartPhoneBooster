package com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast;

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

public class OptimizeActivity extends AppCompatActivity implements View.OnClickListener {
    public static boolean backToNoHome;
 
    LinearLayout adContainer;
    RelativeLayout app_cardsLay0;
    RelativeLayout app_cardsLay1;
    RelativeLayout app_cardsLay2;
    RelativeLayout backlay;
    RelativeLayout bannerAdLay;
    private TextView boostSubText;
    private TextView boostText;
    Context context;
    private double[] dumbTempArray = {1.0d, 1.3d, 1.5d, 1.6d, 1.9d, 2.0d, 2.3d, 2.5d, 2.7d, 3.0d, 3.2d, 3.5d, 4.0d, 4.2d, 4.3d};
    RelativeLayout dummyBannerContainer;
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
    private ImageView snowImgBg;
    private ImageView starsImg;
    private TextView textView10;
    private TextView textView8;
    private TextView textView9;
    private TextView videoStatus;
    private ImageView waterImg;

    
    @Override // androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_optimize);
        this.context = this;
        this.bannerAdLay = (RelativeLayout) findViewById(R.id.bannerAdLay);
        this.dummyBannerContainer = (RelativeLayout) findViewById(R.id.dummy_banner_container);
        this.adContainer = (LinearLayout) findViewById(R.id.banner_container);
      
        this.nativeContainerLay = (RelativeLayout) findViewById(R.id.nativeContainerLay);
        this.nativeDummyContainer = (RelativeLayout) findViewById(R.id.native_dummy_container);

        this.backlay = (RelativeLayout) findViewById(R.id.backlay);
        this.snowImgBg = (ImageView) findViewById(R.id.snowImgBg);
        this.waterImg = (ImageView) findViewById(R.id.waterImg);
        this.starsImg = (ImageView) findViewById(R.id.starsImg);
        this.handler.postDelayed(new Runnable() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.OptimizeActivity.AnonymousClass1 */

            public void run() {
                OptimizeActivity optimizeActivity = OptimizeActivity.this;
                optimizeActivity.leftInAnim = AnimationUtils.loadAnimation(optimizeActivity, R.anim.push_left_in);
                OptimizeActivity.this.starsImg.startAnimation(OptimizeActivity.this.leftInAnim);
                OptimizeActivity.this.starsImg.setVisibility(View.VISIBLE);
            }
        }, 500);
        this.textView9 = (TextView) findViewById(R.id.textView9);
        this.textView10 = (TextView) findViewById(R.id.textView10);
        this.textView8 = (TextView) findViewById(R.id.textView8);
        this.boostText = (TextView) findViewById(R.id.cooledText);
        this.boostSubText = (TextView) findViewById(R.id.droppedText);
        this.textView9.setTypeface(AppAnaylatics.RobotoRegular);
        this.textView10.setTypeface(AppAnaylatics.RobotoRegular);
        this.textView8.setTypeface(AppAnaylatics.RobotoRegular);
        this.boostText.setTypeface(AppAnaylatics.RobotoRegular);
        this.boostSubText.setTypeface(AppAnaylatics.RobotoRegular);
        this.backlay.setOnClickListener(this);
        if (Utils.CheckFromWichActivityComming == 1) {
            this.boostText.setText(getResources().getString(R.string.wifiBoost));
            this.boostSubText.setText(getResources().getString(R.string.wifiBoostSubTxt));
            wiFiBoostLayAnim();
        } else if (Utils.CheckFromWichActivityComming == 2) {
            this.boostText.setText(getResources().getString(R.string.batteryBoosted));
            this.boostSubText.setText(getResources().getString(R.string.enjoyBattery));
            batteryLayAnim();
        } else if (Utils.CheckFromWichActivityComming == 3) {
            this.boostText.setText(getResources().getString(R.string.cooler));
            double d = this.dumbTempArray[this.random.nextInt(15)];
            TextView textView = this.boostSubText;
            textView.setText(getResources().getString(R.string.dropped) + " : " + d);
            snowLayAnim();
        } else if (Utils.CheckFromWichActivityComming == 4) {
            this.boostText.setText(getResources().getString(R.string.booster));
            this.boostSubText.setText(getResources().getString(R.string.boostedSub));
            tickLayAnim();
        } else if (Utils.CheckFromWichActivityComming == 5) {
            this.boostText.setText(getResources().getString(R.string.alreadyBooster));
            this.boostSubText.setText(getResources().getString(R.string.boostedSub));
            tickLayAnim();
        } else if (Utils.CheckFromWichActivityComming == 6) {
            this.boostText.setText(getResources().getString(R.string.AlreadybatteryBoosted));
            this.boostSubText.setText(getResources().getString(R.string.enjoyBattery));
            batteryLayAnim();
        } else if (Utils.CheckFromWichActivityComming == 7) {
            this.boostText.setText(getResources().getString(R.string.AlreadyCooler));
            this.boostSubText.setText(getResources().getString(R.string.coolerSub_des));
            snowLayAnim();
        } else if (Utils.CheckFromWichActivityComming == 8) {
            this.boostText.setText(getResources().getString(R.string.AlreadywifiBoost));
            this.boostSubText.setText(getResources().getString(R.string.wifiBoostSubTxt));
            wiFiBoostLayAnim();
        }
    }

    @SuppressLint("WrongConstant")
    public void batteryLayAnim() {
        this.waterImg.setVisibility(8);
        this.snowImgBg.setVisibility(0);
        this.snowImgBg.setImageDrawable(getResources().getDrawable(R.drawable.battery_opt));
    }

    @SuppressLint("WrongConstant")
    public void tickLayAnim() {
        this.waterImg.setVisibility(8);
        this.snowImgBg.setVisibility(0);
        this.snowImgBg.setImageDrawable(getResources().getDrawable(R.drawable.boost_opt));
    }

    @SuppressLint("WrongConstant")
    public void snowLayAnim() {
        this.waterImg.setVisibility(8);
        this.snowImgBg.setVisibility(0);
        this.snowImgBg.setImageDrawable(getResources().getDrawable(R.drawable.cooler_opt));
        this.handler.postDelayed(new Runnable() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.OptimizeActivity.AnonymousClass2 */

            public void run() {
                OptimizeActivity optimizeActivity = OptimizeActivity.this;
                optimizeActivity.fadeInAnim = AnimationUtils.loadAnimation(optimizeActivity.context, R.anim.fade_in);
                OptimizeActivity.this.waterImg.setVisibility(0);
                OptimizeActivity.this.waterImg.startAnimation(OptimizeActivity.this.fadeInAnim);
                OptimizeActivity.this.handler.postDelayed(new Runnable() {
                    /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.OptimizeActivity.AnonymousClass2.AnonymousClass1 */

                    public void run() {
                        OptimizeActivity.this.fadeOutAnim = AnimationUtils.loadAnimation(OptimizeActivity.this.context, R.anim.fade_out);
                        OptimizeActivity.this.waterImg.setVisibility(8);
                        OptimizeActivity.this.waterImg.startAnimation(OptimizeActivity.this.fadeOutAnim);
                    }
                }, 1500);
            }
        }, 1000);
    }

    @SuppressLint("WrongConstant")
    public void wiFiBoostLayAnim() {
        this.waterImg.setVisibility(8);
        this.snowImgBg.setVisibility(0);
        this.snowImgBg.setImageDrawable(getResources().getDrawable(R.drawable.wifi_opt));
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
            startActivity(new Intent(this.context, SaverModeActivity.class));
            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            finish();
            return;
        }
        startActivity(new Intent(this.context, MainActivity.class));
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
