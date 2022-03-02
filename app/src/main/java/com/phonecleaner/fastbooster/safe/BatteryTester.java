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


import java.util.Random;

public class BatteryTester extends Activity {
    private TextView BatteryExtendTxt;
    private TextView BatteryExtendTxtUnit;
    private TextView BatteryExtendTxtVal;
    Runnable BatteryTimeRunAble = new Runnable() {

        public void run() {
            while (BatteryTester.this.wheelProgress < BatteryTester.this.finalX) {
                BatteryTester.this.wheelProgress++;
                BatteryTester.this.runOnUiThread(new Runnable() {

                    public void run() {
                        TextView textView = BatteryTester.this.BatteryExtendTxtVal;
                        textView.setText("" + BatteryTester.this.wheelProgress);
                    }
                });
                if (BatteryTester.this.wheelProgress == BatteryTester.this.finalX) {
                    BatteryTester.this.runOnUiThread(new Runnable() {

                        @SuppressLint("WrongConstant")
                        public void run() {
                            BatteryTester.this.BatteryExtendTxtVal.clearAnimation();
                            BatteryTester.this.BatteryExtendTxtVal.setVisibility(4);
                            BatteryTester.this.BatteryExtendTxtUnit.setVisibility(4);
                            BatteryTester.this.BatteryExtendTxt.setVisibility(4);
                            BatteryTester.this.batteryGlowImage.clearAnimation();
                            BatteryTester.this.batteryGlowImage0.clearAnimation();
                            BatteryTester.this.batteryGlowImage.setVisibility(8);
                            BatteryTester.this.batteryGlowImage0.setVisibility(8);
                            BatteryTester.this.sparkImageLay.setVisibility(0);
                            BatteryTester.this.pulseAnim = AnimationUtils.loadAnimation(BatteryTester.this.getApplicationContext(), R.anim.heartbeat);
                            BatteryTester.this.sparkImage.startAnimation(BatteryTester.this.pulseAnim);
                            BatteryTester.this.handler.postDelayed(new Runnable() {

                                public void run() {
                                    FinalAllActivity.backToNoHome = true;
                                    BatteryTester.this.startActivity(new Intent(BatteryTester.this, FinalAllActivity.class));
                                    BatteryTester.this.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                                    BatteryTester.this.finish();
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

    private ImageView batteryGlowImage;
    private ImageView batteryGlowImage0;
    private Animation blinkTxtAnim;
    Context context;
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
        util.CheckFromWichActivityComming = 2;

        this.editor.putLong(util.CheckStateOfAlreadyBatteryBoost, System.currentTimeMillis());
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
                BatteryTester batteryBoost = BatteryTester.this;
                batteryBoost.moveUpFadeOut = AnimationUtils.loadAnimation(batteryBoost.getApplicationContext(), R.anim.translate_plus_bottom_up);
                BatteryTester.this.plus1.startAnimation(BatteryTester.this.moveUpFadeOut);
            }
        }, 1200);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                BatteryTester batteryBoost = BatteryTester.this;
                batteryBoost.moveUpFadeOut2 = AnimationUtils.loadAnimation(batteryBoost.getApplicationContext(), R.anim.translate_plus_bottom_up2);
                BatteryTester.this.plus2.startAnimation(BatteryTester.this.moveUpFadeOut2);
            }
        }, 2200);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                BatteryTester batteryBoost = BatteryTester.this;
                batteryBoost.moveUpFadeOut3 = AnimationUtils.loadAnimation(batteryBoost.getApplicationContext(), R.anim.translate_plus_bottom_up3);
                BatteryTester.this.plus3.startAnimation(BatteryTester.this.moveUpFadeOut3);
            }
        }, 3250);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                BatteryTester batteryBoost = BatteryTester.this;
                batteryBoost.moveUpFadeOut4 = AnimationUtils.loadAnimation(batteryBoost.getApplicationContext(), R.anim.translate_plus_bottom_up4);
                BatteryTester.this.plus4.startAnimation(BatteryTester.this.moveUpFadeOut4);
            }
        }, 4200);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                BatteryTester batteryBoost = BatteryTester.this;
                batteryBoost.moveUpFadeOut = AnimationUtils.loadAnimation(batteryBoost.getApplicationContext(), R.anim.translate_plus_bottom_up);
                BatteryTester.this.plus9.startAnimation(BatteryTester.this.moveUpFadeOut);
            }
        }, 3200);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                BatteryTester batteryBoost = BatteryTester.this;
                batteryBoost.moveUpFadeOut2 = AnimationUtils.loadAnimation(batteryBoost.getApplicationContext(), R.anim.translate_plus_bottom_up2);
                BatteryTester.this.plus8.startAnimation(BatteryTester.this.moveUpFadeOut2);
            }
        }, 4400);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                BatteryTester batteryBoost = BatteryTester.this;
                batteryBoost.moveUpFadeOut3 = AnimationUtils.loadAnimation(batteryBoost.getApplicationContext(), R.anim.translate_plus_bottom_up3);
                BatteryTester.this.plus7.startAnimation(BatteryTester.this.moveUpFadeOut3);
            }
        }, 5650);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                BatteryTester batteryBoost = BatteryTester.this;
                batteryBoost.moveUpFadeOut4 = AnimationUtils.loadAnimation(batteryBoost.getApplicationContext(), R.anim.translate_plus_bottom_up4);
                BatteryTester.this.plus6.startAnimation(BatteryTester.this.moveUpFadeOut4);
            }
        }, 6500);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                BatteryTester batteryBoost = BatteryTester.this;
                batteryBoost.moveUpFadeOut4 = AnimationUtils.loadAnimation(batteryBoost.getApplicationContext(), R.anim.translate_plus_bottom_up);
                BatteryTester.this.plus5.startAnimation(BatteryTester.this.moveUpFadeOut4);
            }
        }, 8000);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                AnimationDrawable animationDrawable = (AnimationDrawable) BatteryTester.this.batteryGlowImage.getBackground();
                animationDrawable.setVisible(false, true);
                animationDrawable.start();
                AnimationDrawable animationDrawable2 = (AnimationDrawable) BatteryTester.this.batteryGlowImage0.getBackground();
                animationDrawable2.setVisible(false, true);
                animationDrawable2.start();
                BatteryTester.this.BatteryExtendTxtVal.setVisibility(0);
                BatteryTester.this.BatteryExtendTxt.setVisibility(0);
                BatteryTester.this.BatteryExtendTxtUnit.setVisibility(0);
                new Thread(BatteryTester.this.BatteryTimeRunAble).start();
                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        Animation loadAnimation = AnimationUtils.loadAnimation(BatteryTester.this.context, R.anim.pulse_low);
                        BatteryTester.this.waterRipple.setVisibility(0);
                        BatteryTester.this.waterRipple.startAnimation(loadAnimation);
                    }
                }, 500);
            }
        }, 900);
    }

    
    public void onResume() {
        super.onResume();
    }
}
