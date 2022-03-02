package com.phonecleaner.fastbooster.safe;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.stevenyang.snowfalling.SnowFlakesLayout;

public class CPUActivity extends Activity {

    private Animation RoateAnim;
    AnimationDrawable anim;
    private Animation antiRoateAnim;

    Context context;
    private RelativeLayout coolerLay;
    private TextView coolingDownTxt;
    private TextView coolsuccesstext;
    private RelativeLayout cpuLay;
    private Animation downAnim;
    SharedPreferences.Editor editor;
    private Animation growAnim;
    Handler handler = new Handler();
    private Animation heartAnim;
    private ImageView hollowSnow;
    private ImageView hollowSnowBorder;
    private ImageView loadingImage;
    private MediaPlayer mediaplay;
    private TextView optimizeText;
    private RelativeLayout optimizedLay;
    SharedPreferences pref;
    SnowFlakesLayout snowFlakesLayout;
    private RelativeLayout snowLay;
    private RelativeLayout textLay;
    private boolean val;

    public void onBackPressed() {
    }

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_cooler);
        this.context = this;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.pref = defaultSharedPreferences;
        this.editor = defaultSharedPreferences.edit();


        this.textLay = (RelativeLayout) findViewById(R.id.textLay);
        this.snowLay = (RelativeLayout) findViewById(R.id.snowLay);
        this.optimizedLay = (RelativeLayout) findViewById(R.id.optimizedLay);
        this.cpuLay = (RelativeLayout) findViewById(R.id.cpuLay);
        this.optimizeText = (TextView) findViewById(R.id.optimizeText);
        this.coolsuccesstext = (TextView) findViewById(R.id.coolsuccesstext);
        this.coolerLay = (RelativeLayout) findViewById(R.id.coolerLay);
        this.snowFlakesLayout = (SnowFlakesLayout) findViewById(R.id.snowflakelayout);
        this.hollowSnow = (ImageView) findViewById(R.id.hollowSnow);
        this.loadingImage = (ImageView) findViewById(R.id.loadingImage);
        this.coolingDownTxt = (TextView) findViewById(R.id.coolingDownTxt);
        this.optimizeText.setTypeface(MainApp.RobotoRegular);
        this.coolsuccesstext.setTypeface(MainApp.RobotoRegular);
        util.CheckFromWichActivityComming = 3;
        this.editor.putLong(util.CheckStateOfAlreadyCooled, System.currentTimeMillis());
        this.editor.commit();
        this.handler.postDelayed(new Runnable() {

            public void run() {
                CPUActivity.this.snowFlakesLayout.init();
                CPUActivity.this.snowFlakesLayout.setWholeAnimateTiming(3000000);
                CPUActivity.this.snowFlakesLayout.setAnimateDuration(10000);
                CPUActivity.this.snowFlakesLayout.setGenerateSnowTiming(300);
                CPUActivity.this.snowFlakesLayout.setRandomSnowSizeRange(80, 1);
                CPUActivity.this.snowFlakesLayout.setImageResourceID(R.drawable.snow);
                CPUActivity.this.snowFlakesLayout.setEnableRandomCurving(true);
                CPUActivity.this.snowFlakesLayout.setEnableAlphaFade(true);
                CPUActivity.this.snowFlakesLayout.startSnowing();
                try {
                    CPUActivity.this.mediaplay = MediaPlayer.create(CPUActivity.this.context, (int) R.raw.fan_sound);
                    CPUActivity.this.val = CPUActivity.this.pref.getBoolean("soundchk", true);
                    if (util.SoundModeStatus(CPUActivity.this).getRingerMode() == 2 && CPUActivity.this.val && CPUActivity.this.mediaplay != null) {
                        CPUActivity.this.mediaplay.start();
                    }
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                CPUActivity coolerActivity = CPUActivity.this;
                coolerActivity.RoateAnim = AnimationUtils.loadAnimation(coolerActivity.context, R.anim.rotate);
                CPUActivity.this.RoateAnim.setDuration(500);
                CPUActivity coolerActivity2 = CPUActivity.this;
                coolerActivity2.antiRoateAnim = AnimationUtils.loadAnimation(coolerActivity2.context, R.anim.rotate_anti_clock);
                CPUActivity.this.hollowSnow.startAnimation(CPUActivity.this.RoateAnim);
                CPUActivity coolerActivity3 = CPUActivity.this;
                coolerActivity3.anim = (AnimationDrawable) coolerActivity3.loadingImage.getBackground();
                CPUActivity.this.anim.setVisible(false, true);
                CPUActivity.this.anim.start();
                TransitionDrawable transitionDrawable = new TransitionDrawable(new ColorDrawable[]{new ColorDrawable(CPUActivity.this.getResources().getColor(R.color.coolcolor)), new ColorDrawable(CPUActivity.this.getResources().getColor(R.color.endcolor))});
                CPUActivity.this.coolerLay.setBackgroundDrawable(transitionDrawable);
                transitionDrawable.startTransition(11500);
            }
        }, 500);
        this.handler.postDelayed(new Runnable() {

            @SuppressLint("WrongConstant")
            public void run() {
                try {
                    if (CPUActivity.this.mediaplay != null && CPUActivity.this.mediaplay.isPlaying()) {
                        CPUActivity.this.mediaplay.stop();
                    }
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                CPUActivity.this.anim.stop();
                CPUActivity.this.RoateAnim.cancel();
                CPUActivity.this.hollowSnow.clearAnimation();
                CPUActivity.this.optimizedLay.setVisibility(0);
                CPUActivity coolerActivity = CPUActivity.this;
                coolerActivity.downAnim = AnimationUtils.loadAnimation(coolerActivity.context, R.anim.down_from_middle);
                CPUActivity.this.snowLay.startAnimation(CPUActivity.this.downAnim);
                CPUActivity.this.snowLay.setVisibility(8);
                CPUActivity.this.textLay.setVisibility(8);
                CPUActivity.this.snowFlakesLayout.stopSnowing();
            }
        }, 11700);
        this.handler.postDelayed(new Runnable() {

            @SuppressLint("WrongConstant")
            public void run() {
                try {
                    CPUActivity.this.coolerLay.setBackgroundDrawable(CPUActivity.this.getResources().getDrawable(R.drawable.app_bg));
                    CPUActivity.this.optimizedLay.setVisibility(0);
                    CPUActivity.this.growAnim = AnimationUtils.loadAnimation(CPUActivity.this.context, R.anim.grow_from_middle);
                    CPUActivity.this.cpuLay.startAnimation(CPUActivity.this.growAnim);
                    CPUActivity.this.cpuLay.setVisibility(0);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }, 12500);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                try {
                    Animation loadAnimation = AnimationUtils.loadAnimation(CPUActivity.this.context, R.anim.postoup);
                    CPUActivity.this.optimizeText.setVisibility(View.VISIBLE);
                    CPUActivity.this.optimizeText.startAnimation(loadAnimation);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }, 12800);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                try {
                    Animation loadAnimation = AnimationUtils.loadAnimation(CPUActivity.this.context, R.anim.postoup);
                    CPUActivity.this.coolsuccesstext.setVisibility(View.VISIBLE);
                    CPUActivity.this.coolsuccesstext.startAnimation(loadAnimation);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }, 13200);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                try {
                  /*  if (Utils.interstitialAd == null || !Utils.interstitialAd.isAdLoaded()) {
                        CoolerActivity.this.startActivity(new Intent(CoolerActivity.this, OptimizeActivity.class));
                        CoolerActivity.this.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                        CoolerActivity.this.finish();
                        return;
                    }
                    Intent intent = new Intent(CoolerActivity.this.context, InterstitialAdsActivity.class);
                    intent.putExtra(Utils.SaveStateOfReturnActivity, 0);
                    CoolerActivity.this.startActivity(intent);
                    CoolerActivity.this.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    CoolerActivity.this.finish();*/
                    CPUActivity.this.startActivity(new Intent(CPUActivity.this, FinalAllActivity.class));
                    CPUActivity.this.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    CPUActivity.this.finish();
                    return;
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }, 14500);
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
