package com.phonecleaner.fastbooster.safe;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.util.Random;

public class CleanerActivity extends Activity {
    Runnable RamSizeRunAble = new Runnable() {

        public void run() {
            while (CleanerActivity.this.wheelProgress > 0.0d) {
                CleanerActivity.this.wheelProgress -= 1.0d;
                CleanerActivity.this.runOnUiThread(new Runnable() {

                    public void run() {
                        String format = new DecimalFormat("##.##").format(CleanerActivity.this.wheelProgress);
                        if (CleanerActivity.this.wheelProgress > 10.0d) {
                            CleanerActivity.this.ramSizeTxt.setText(format);
                            return;
                        }
                        TextView textView = CleanerActivity.this.ramSizeTxt;
                        textView.setText("0" + format);
                    }
                });
                if (CleanerActivity.this.wheelProgress <= 1.0d) {
                    CleanerActivity.this.runOnUiThread(new Runnable() {

                        @SuppressLint("WrongConstant")
                        public void run() {
                            CleanerActivity.this.ramSizeTxt.setVisibility(4);
                            CleanerActivity.this.RamSizeUnitTxt.setVisibility(4);
                        }
                    });
                }
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private TextView RamSizeUnitTxt;
    private int ShowAdsOnce = 0;
    private ImageView appImage;
  //  private RelativeLayout appImageLay;

    private Animation bottomUpAnim;
    private ImageView cloudImage;
    Context context;
    private TextView coolsuccesstext;
    private RelativeLayout cpuLay;
    private RelativeLayout dummyLay;
    SharedPreferences.Editor editor;
    private Animation fadeOutAnim;
    private RelativeLayout fogImageLay;
    private Animation growFromMiddleAnim;
    Handler handler = new Handler();
    private TypedArray imgs;
    private RelativeLayout mainLay;
    private Animation moveLogo;
    private TextView optimizeText;
    private RelativeLayout optimizedLay;
    SharedPreferences pref;
    private RelativeLayout ramLay;
    private TextView ramSizeTxt;
    private Random rand;
    private LinearLayout rocketImageLay;
    private ImageView rocketImg;
    private RelativeLayout rocketLay;
    private Animation roketLayAnim;
    private Animation roketMoveUpFastAnim;
    private float scalingValue;
    private Animation shakeAnim;
    private Animation toptoFixposAnim;
    private Animation translateBottomAnim;
    private Animation updownAnim;
    double wheelProgress = 0.0d;

    public void onBackPressed() {
    }

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_boost);
        this.context = this;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.pref = defaultSharedPreferences;
        this.editor = defaultSharedPreferences.edit();
        util.CheckFromWichActivityComming = 4;


        this.editor.putLong(util.CheckStateOfAlreadyPhoneBoost, System.currentTimeMillis());
        this.editor.commit();
        this.imgs = getResources().obtainTypedArray(R.array.bubbles);
        this.rand = new Random();
        this.mainLay = (RelativeLayout) findViewById(R.id.mainLay);
        this.cpuLay = (RelativeLayout) findViewById(R.id.cpuLay);
        this.optimizedLay = (RelativeLayout) findViewById(R.id.optimizedLay);
        this.dummyLay = (RelativeLayout) findViewById(R.id.dummyLay);
        this.fogImageLay = (RelativeLayout) findViewById(R.id.fogImageLay);
        this.cloudImage = (ImageView) findViewById(R.id.cloudImage);
        this.rocketImg = (ImageView) findViewById(R.id.rocketImg);
        this.appImage = (ImageView) findViewById(R.id.appImage);
        this.coolsuccesstext = (TextView) findViewById(R.id.coolsuccesstext);
        this.optimizeText = (TextView) findViewById(R.id.optimizeText);
        this.ramSizeTxt = (TextView) findViewById(R.id.RamSize);
        this.RamSizeUnitTxt = (TextView) findViewById(R.id.RamSizeUnit);
        this.rocketImageLay = (LinearLayout) findViewById(R.id.rocketImageLay);
        this.ramLay = (RelativeLayout) findViewById(R.id.ramLay);
        this.optimizeText.setTypeface(MainApp.RobotoRegular);
        this.coolsuccesstext.setTypeface(MainApp.RobotoRegular);
        this.ramSizeTxt.setTypeface(MainApp.RobotoRegular);
        this.RamSizeUnitTxt.setTypeface(MainApp.RobotoRegular);
        this.updownAnim = AnimationUtils.loadAnimation(this.context, R.anim.roketup_down);
        this.shakeAnim = AnimationUtils.loadAnimation(this.context, R.anim.shake);
        this.growFromMiddleAnim = AnimationUtils.loadAnimation(this.context, R.anim.grow_from_middle);
        this.translateBottomAnim = AnimationUtils.loadAnimation(this.context, R.anim.translate_star_bottom);
        this.fadeOutAnim = AnimationUtils.loadAnimation(this.context, R.anim.fade_in);
        this.scalingValue = -120.0f;
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, util.convertDpToPixel(-120.0f, this.context));
        this.moveLogo = translateAnimation;
        translateAnimation.setDuration(1500);
        this.moveLogo.setFillAfter(true);
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                this.wheelProgress = (double) StartActivity.finalRandomXForTotallSizeApi26;
            } else {
                String[] split = util.formatSize(WelcomeActivity.ramUsedByApps).split(" ");
                String str = split[0];
                String str2 = split[1];
                this.wheelProgress = (double) Integer.parseInt(str);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        new Thread(this.RamSizeRunAble).start();
        //displayStarsWithAnim();
        this.rocketImg.startAnimation(this.shakeAnim);
        this.rocketImageLay.startAnimation(this.updownAnim);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                try {
                    new LongOperation().execute(new String[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 500);

    }



    public class LongOperation extends AsyncTask<String, Drawable, String> {
        private String TAG = "HomeScreen";
        private int size;
        private String top;

        public LongOperation() {
        }

        
        public void onPreExecute() {
            super.onPreExecute();
            try {
                Log.e("LOG", "" + util.mApps.size());
            } catch (Exception e) {
                Log.e("HomeScreen", "LongOperation onPreExecute() Exception: " + e.getMessage());
            }
        }

        
        public String doInBackground(String... strArr) {
            try {
                if (util.mApps.size() > 12) {
                    this.size = util.mApps.size() / 2;
                } else {
                    this.size = util.mApps.size();
                }
                for (int i = 0; i < this.size; i++) {
                    try {
                        Thread.sleep(800);
                        publishProgress(util.mApps.get(i).getIcon());
                        if (i == 10) {
                            break;
                        }
                    } catch (Exception e) {
                        Log.i("makemachine", e.getMessage());
                    }
                }
                try {
                    Thread.sleep(300);
                    return null;
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                    return null;
                }
            } catch (Exception e3) {
                Log.e("HomeScreen", "LongOperation doInBackground Exception: " + e3.getMessage());
                return null;
            }
        }

        
        @SuppressLint("WrongConstant")
        public void onProgressUpdate(Drawable... drawableArr) {
            super.onProgressUpdate(drawableArr);
            try {
                CleanerActivity.this.appImage.setVisibility(0);
                CleanerActivity.this.toptoFixposAnim = AnimationUtils.loadAnimation(CleanerActivity.this.context, R.anim.fade_out_slow);
                CleanerActivity.this.appImage.startAnimation(CleanerActivity.this.toptoFixposAnim);
                CleanerActivity.this.appImage.setBackgroundDrawable(drawableArr[0]);
                CleanerActivity.this.handler.postDelayed(new Runnable() {

                    @SuppressLint("WrongConstant")
                    public void run() {
                        CleanerActivity.this.appImage.setBackgroundDrawable(null);
                        CleanerActivity.this.appImage.setVisibility(8);
                    }
                }, 1500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        
        public void onPostExecute(String str) {
            super.onPostExecute( str);
            try {
                if (CleanerActivity.this.ShowAdsOnce == 0) {
                    CleanerActivity.this.afterBoostFun();
                    CleanerActivity.this.ShowAdsOnce = 1;
                }
            } catch (Exception e) {
                Log.e("HomeScreen", "LongOperation onPostExecute Exception: " + e.getMessage());
            }
        }
    }

    @SuppressLint("WrongConstant")
    public void afterBoostFun() {
        this.ramLay.setVisibility(View.GONE);
        this.dummyLay.setVisibility(View.GONE);
        this.rocketImg.clearAnimation();
        this.rocketImageLay.clearAnimation();
        this.translateBottomAnim.cancel();
        this.cloudImage.setAnimation(AnimationUtils.loadAnimation(this.context, R.anim.topanim));
        this.rocketImageLay.setAnimation(AnimationUtils.loadAnimation(this.context, R.anim.topbottom));
        this.handler.postDelayed(new Runnable() {

            @SuppressLint("WrongConstant")
            public void run() {
                CleanerActivity.this.fogImageLay.setAnimation(AnimationUtils.loadAnimation(CleanerActivity.this.context, R.anim.fade_out));
                CleanerActivity.this.fogImageLay.setVisibility(8);
            }
        }, 700);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                try {
                    CleanerActivity.this.appImage.clearAnimation();
                    CleanerActivity.this.appImage.setVisibility(8);
                    CleanerActivity.this.optimizedLay.setVisibility(0);
                    CleanerActivity.this.cpuLay.startAnimation(AnimationUtils.loadAnimation(CleanerActivity.this.context, R.anim.grow_from_middle));
                    CleanerActivity.this.cpuLay.setVisibility(0);
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                try {
                    Animation loadAnimation = AnimationUtils.loadAnimation(CleanerActivity.this.context, R.anim.postoup);
                    CleanerActivity.this.optimizeText.setVisibility(0);
                    CleanerActivity.this.optimizeText.startAnimation(loadAnimation);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }, 1500);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                try {
                    Animation loadAnimation = AnimationUtils.loadAnimation(CleanerActivity.this.context, R.anim.postoup);
                    CleanerActivity.this.coolsuccesstext.setVisibility(0);
                    CleanerActivity.this.coolsuccesstext.startAnimation(loadAnimation);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }, 2500);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                try {

                    CleanerActivity.this.startActivity(new Intent(CleanerActivity.this, FinalAllActivity.class));
                    CleanerActivity.this.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    CleanerActivity.this.finish();
                    return;
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }, 3500);
    }

    public void switchActivity() {
        startActivity(new Intent(this, FinalAllActivity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }

    
    public void onResume() {
        super.onResume();
    }
}
