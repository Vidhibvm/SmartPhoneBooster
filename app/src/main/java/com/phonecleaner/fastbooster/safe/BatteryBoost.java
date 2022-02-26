package com.phonecleaner.fastbooster.safe;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.phonecleaner.fastbooster.safe.R;


import java.util.Random;

public class BatteryBoost extends Activity {
    private TextView BatteryExtendTxt;
    private TextView BatteryExtendTxtUnit;
    private TextView BatteryExtendTxtVal;
    Runnable BatteryTimeRunAble = new Runnable() {

        public void run() {
            while (BatteryBoost.this.wheelProgress < BatteryBoost.this.finalX) {
                BatteryBoost.this.wheelProgress++;
                BatteryBoost.this.runOnUiThread(new Runnable() {

                    public void run() {
                        TextView textView = BatteryBoost.this.BatteryExtendTxtVal;
                        textView.setText("" + BatteryBoost.this.wheelProgress);
                    }
                });
                if (BatteryBoost.this.wheelProgress == BatteryBoost.this.finalX) {
                    BatteryBoost.this.runOnUiThread(new Runnable() {

                        @SuppressLint("WrongConstant")
                        public void run() {
                            BatteryBoost.this.BatteryExtendTxtVal.clearAnimation();
                            BatteryBoost.this.BatteryExtendTxtVal.setVisibility(4);
                            BatteryBoost.this.BatteryExtendTxtUnit.setVisibility(4);
                            BatteryBoost.this.BatteryExtendTxt.setVisibility(4);
                            BatteryBoost.this.batteryGlowImage.clearAnimation();
                            BatteryBoost.this.batteryGlowImage0.clearAnimation();
                            BatteryBoost.this.batteryGlowImage.setVisibility(8);
                            BatteryBoost.this.batteryGlowImage0.setVisibility(8);
                            BatteryBoost.this.sparkImageLay.setVisibility(0);
                            BatteryBoost.this.pulseAnim = AnimationUtils.loadAnimation(BatteryBoost.this.getApplicationContext(), R.anim.heartbeat);
                            BatteryBoost.this.sparkImage.startAnimation(BatteryBoost.this.pulseAnim);
                            BatteryBoost.this.handler.postDelayed(new Runnable() {

                                public void run() {
                                    OptimizeActivity.backToNoHome = true;
                                    BatteryBoost.this.startActivity(new Intent(BatteryBoost.this, OptimizeActivity.class));
                                    BatteryBoost.this.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                                    BatteryBoost.this.finish();
                                    return;
                                }
                            }, 1200);
                        }
                    });
                }
                try {
                    Thread.sleep(180);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    LinearLayout adContainer;
    RelativeLayout bannerAdLay;
    private ImageView batteryGlowImage;
    private ImageView batteryGlowImage0;
    private Animation blinkTxtAnim;
    Context context;
    RelativeLayout dummyBannerContainer;
    SharedPreferences.Editor editor;
    private Animation fadeOut;
    int finalX;
    Handler handler = new Handler();
    int maxX = 39;
    int minX = 20;
    private Animation moveUpFadeOut;
    private Animation moveUpFadeOut2;
    private Animation moveUpFadeOut3;
    private Animation moveUpFadeOut4;
    private ImageView plus1;
    private ImageView plus2;
    private ImageView plus3;
    private ImageView plus4;
    private ImageView plus5;
    private ImageView plus6;
    private ImageView plus7;
    private ImageView plus8;
    private ImageView plus9;
    SharedPreferences pref;
    private Animation pulseAnim;
    private ImageView sparkImage;
    private RelativeLayout sparkImageLay;
    private RelativeLayout sparkLay;
    private ImageView waterRipple;
    int wheelProgress = 0;

    public void onBackPressed() {
    }

    
    @SuppressLint("WrongConstant")
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_battery_boost);
        this.context = this;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.pref = defaultSharedPreferences;
        this.editor = defaultSharedPreferences.edit();
        Utils.CheckFromWichActivityComming = 2;
        this.bannerAdLay = (RelativeLayout) findViewById(R.id.bannerAdLay);
        this.dummyBannerContainer = (RelativeLayout) findViewById(R.id.dummy_banner_container);
        this.adContainer = (LinearLayout) findViewById(R.id.banner_container);

        this.editor.putLong(Utils.CheckStateOfAlreadyBatteryBoost, System.currentTimeMillis());
        this.editor.commit();
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.sparkImageLay);
        this.sparkImageLay = relativeLayout;
        relativeLayout.setVisibility(8);
        this.BatteryExtendTxtVal = (TextView) findViewById(R.id.BatteryExtendTxt);
        this.BatteryExtendTxtUnit = (TextView) findViewById(R.id.BatteryExtendTxtUnit);
        this.BatteryExtendTxt = (TextView) findViewById(R.id.extendTxt);
        this.waterRipple = (ImageView) findViewById(R.id.waterRipple);
        ImageView imageView = (ImageView) findViewById(R.id.batteryGlowImage0);
        this.batteryGlowImage0 = imageView;
        imageView.setVisibility(0);
        ImageView imageView2 = (ImageView) findViewById(R.id.batteryGlowImage);
        this.batteryGlowImage = imageView2;
        imageView2.setVisibility(0);
        this.sparkImage = (ImageView) findViewById(R.id.sparkImage);
        this.plus1 = (ImageView) findViewById(R.id.plus1);
        this.plus2 = (ImageView) findViewById(R.id.plus2);
        this.plus3 = (ImageView) findViewById(R.id.plus3);
        this.plus4 = (ImageView) findViewById(R.id.plus4);
        this.plus5 = (ImageView) findViewById(R.id.plus5);
        this.plus6 = (ImageView) findViewById(R.id.plus6);
        this.plus7 = (ImageView) findViewById(R.id.plus7);
        this.plus8 = (ImageView) findViewById(R.id.plus8);
        this.plus9 = (ImageView) findViewById(R.id.plus9);
        this.sparkLay = (RelativeLayout) findViewById(R.id.sparkLay);
        this.finalX = new Random().nextInt(this.maxX) + this.minX;
        this.blinkTxtAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.grow_for_time);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                BatteryBoost batteryBoost = BatteryBoost.this;
                batteryBoost.moveUpFadeOut = AnimationUtils.loadAnimation(batteryBoost.getApplicationContext(), R.anim.translate_plus_bottom_up);
                BatteryBoost.this.plus1.startAnimation(BatteryBoost.this.moveUpFadeOut);
            }
        }, 1200);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                BatteryBoost batteryBoost = BatteryBoost.this;
                batteryBoost.moveUpFadeOut2 = AnimationUtils.loadAnimation(batteryBoost.getApplicationContext(), R.anim.translate_plus_bottom_up2);
                BatteryBoost.this.plus2.startAnimation(BatteryBoost.this.moveUpFadeOut2);
            }
        }, 2200);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                BatteryBoost batteryBoost = BatteryBoost.this;
                batteryBoost.moveUpFadeOut3 = AnimationUtils.loadAnimation(batteryBoost.getApplicationContext(), R.anim.translate_plus_bottom_up3);
                BatteryBoost.this.plus3.startAnimation(BatteryBoost.this.moveUpFadeOut3);
            }
        }, 3250);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                BatteryBoost batteryBoost = BatteryBoost.this;
                batteryBoost.moveUpFadeOut4 = AnimationUtils.loadAnimation(batteryBoost.getApplicationContext(), R.anim.translate_plus_bottom_up4);
                BatteryBoost.this.plus4.startAnimation(BatteryBoost.this.moveUpFadeOut4);
            }
        }, 4200);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                BatteryBoost batteryBoost = BatteryBoost.this;
                batteryBoost.moveUpFadeOut = AnimationUtils.loadAnimation(batteryBoost.getApplicationContext(), R.anim.translate_plus_bottom_up);
                BatteryBoost.this.plus9.startAnimation(BatteryBoost.this.moveUpFadeOut);
            }
        }, 3200);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                BatteryBoost batteryBoost = BatteryBoost.this;
                batteryBoost.moveUpFadeOut2 = AnimationUtils.loadAnimation(batteryBoost.getApplicationContext(), R.anim.translate_plus_bottom_up2);
                BatteryBoost.this.plus8.startAnimation(BatteryBoost.this.moveUpFadeOut2);
            }
        }, 4400);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                BatteryBoost batteryBoost = BatteryBoost.this;
                batteryBoost.moveUpFadeOut3 = AnimationUtils.loadAnimation(batteryBoost.getApplicationContext(), R.anim.translate_plus_bottom_up3);
                BatteryBoost.this.plus7.startAnimation(BatteryBoost.this.moveUpFadeOut3);
            }
        }, 5650);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                BatteryBoost batteryBoost = BatteryBoost.this;
                batteryBoost.moveUpFadeOut4 = AnimationUtils.loadAnimation(batteryBoost.getApplicationContext(), R.anim.translate_plus_bottom_up4);
                BatteryBoost.this.plus6.startAnimation(BatteryBoost.this.moveUpFadeOut4);
            }
        }, 6500);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                BatteryBoost batteryBoost = BatteryBoost.this;
                batteryBoost.moveUpFadeOut4 = AnimationUtils.loadAnimation(batteryBoost.getApplicationContext(), R.anim.translate_plus_bottom_up);
                BatteryBoost.this.plus5.startAnimation(BatteryBoost.this.moveUpFadeOut4);
            }
        }, 8000);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                AnimationDrawable animationDrawable = (AnimationDrawable) BatteryBoost.this.batteryGlowImage.getBackground();
                animationDrawable.setVisible(false, true);
                animationDrawable.start();
                AnimationDrawable animationDrawable2 = (AnimationDrawable) BatteryBoost.this.batteryGlowImage0.getBackground();
                animationDrawable2.setVisible(false, true);
                animationDrawable2.start();
                BatteryBoost.this.BatteryExtendTxtVal.setVisibility(0);
                BatteryBoost.this.BatteryExtendTxt.setVisibility(0);
                BatteryBoost.this.BatteryExtendTxtUnit.setVisibility(0);
                new Thread(BatteryBoost.this.BatteryTimeRunAble).start();
                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        Animation loadAnimation = AnimationUtils.loadAnimation(BatteryBoost.this.context, R.anim.pulse_low);
                        BatteryBoost.this.waterRipple.setVisibility(0);
                        BatteryBoost.this.waterRipple.startAnimation(loadAnimation);
                    }
                }, 500);
            }
        }, 900);
    }

    
    public void onResume() {
        super.onResume();
    }
}
