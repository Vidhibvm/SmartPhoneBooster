package com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast;

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

public class CoolerActivity extends Activity {

    private Animation RoateAnim;
    LinearLayout adContainer;
    AnimationDrawable anim;
    private Animation antiRoateAnim;
    RelativeLayout bannerAdLay;
    Context context;
    private RelativeLayout coolerLay;
    private TextView coolingDownTxt;
    private TextView coolsuccesstext;
    private RelativeLayout cpuLay;
    private Animation downAnim;
    RelativeLayout dummyBannerContainer;
    SharedPreferences.Editor editor;
    private Animation growAnim;
    Handler handler = new Handler();
    private Animation heartAnim;
    private ImageView hollowSnow;
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
        this.bannerAdLay = (RelativeLayout) findViewById(R.id.bannerAdLay);
        this.dummyBannerContainer = (RelativeLayout) findViewById(R.id.dummy_banner_container);
        this.adContainer = (LinearLayout) findViewById(R.id.banner_container);
      
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
        this.optimizeText.setTypeface(AppAnaylatics.RobotoRegular);
        this.coolsuccesstext.setTypeface(AppAnaylatics.RobotoRegular);
        Utils.CheckFromWichActivityComming = 3;
        this.editor.putLong(Utils.CheckStateOfAlreadyCooled, System.currentTimeMillis());
        this.editor.commit();
        this.handler.postDelayed(new Runnable() {

            public void run() {
                CoolerActivity.this.snowFlakesLayout.init();
                CoolerActivity.this.snowFlakesLayout.setWholeAnimateTiming(3000000);
                CoolerActivity.this.snowFlakesLayout.setAnimateDuration(10000);
                CoolerActivity.this.snowFlakesLayout.setGenerateSnowTiming(300);
                CoolerActivity.this.snowFlakesLayout.setRandomSnowSizeRange(80, 1);
                CoolerActivity.this.snowFlakesLayout.setImageResourceID(R.drawable.snow);
                CoolerActivity.this.snowFlakesLayout.setEnableRandomCurving(true);
                CoolerActivity.this.snowFlakesLayout.setEnableAlphaFade(true);
                CoolerActivity.this.snowFlakesLayout.startSnowing();
                try {
                    CoolerActivity.this.mediaplay = MediaPlayer.create(CoolerActivity.this.context, (int) R.raw.fan_sound);
                    CoolerActivity.this.val = CoolerActivity.this.pref.getBoolean("soundchk", true);
                    if (Utils.SoundModeStatus(CoolerActivity.this).getRingerMode() == 2 && CoolerActivity.this.val && CoolerActivity.this.mediaplay != null) {
                        CoolerActivity.this.mediaplay.start();
                    }
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                CoolerActivity coolerActivity = CoolerActivity.this;
                coolerActivity.RoateAnim = AnimationUtils.loadAnimation(coolerActivity.context, R.anim.rotate);
                CoolerActivity.this.RoateAnim.setDuration(500);
                CoolerActivity coolerActivity2 = CoolerActivity.this;
                coolerActivity2.antiRoateAnim = AnimationUtils.loadAnimation(coolerActivity2.context, R.anim.rotate_anti_clock);
                CoolerActivity.this.hollowSnow.startAnimation(CoolerActivity.this.RoateAnim);
                CoolerActivity coolerActivity3 = CoolerActivity.this;
                coolerActivity3.anim = (AnimationDrawable) coolerActivity3.loadingImage.getBackground();
                CoolerActivity.this.anim.setVisible(false, true);
                CoolerActivity.this.anim.start();
                TransitionDrawable transitionDrawable = new TransitionDrawable(new ColorDrawable[]{new ColorDrawable(CoolerActivity.this.getResources().getColor(R.color.lightredcolor)), new ColorDrawable(CoolerActivity.this.getResources().getColor(R.color.colorPrimary))});
                CoolerActivity.this.coolerLay.setBackgroundDrawable(transitionDrawable);
                transitionDrawable.startTransition(11500);
            }
        }, 500);
        this.handler.postDelayed(new Runnable() {

            @SuppressLint("WrongConstant")
            public void run() {
                try {
                    if (CoolerActivity.this.mediaplay != null && CoolerActivity.this.mediaplay.isPlaying()) {
                        CoolerActivity.this.mediaplay.stop();
                    }
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                CoolerActivity.this.anim.stop();
                CoolerActivity.this.RoateAnim.cancel();
                CoolerActivity.this.hollowSnow.clearAnimation();
                CoolerActivity.this.optimizedLay.setVisibility(0);
                CoolerActivity coolerActivity = CoolerActivity.this;
                coolerActivity.downAnim = AnimationUtils.loadAnimation(coolerActivity.context, R.anim.down_from_middle);
                CoolerActivity.this.snowLay.startAnimation(CoolerActivity.this.downAnim);
                CoolerActivity.this.snowLay.setVisibility(8);
                CoolerActivity.this.textLay.setVisibility(8);
                CoolerActivity.this.snowFlakesLayout.stopSnowing();
            }
        }, 11700);
        this.handler.postDelayed(new Runnable() {

            @SuppressLint("WrongConstant")
            public void run() {
                try {
                    CoolerActivity.this.coolerLay.setBackgroundDrawable(CoolerActivity.this.getResources().getDrawable(R.drawable.lm));
                    CoolerActivity.this.optimizedLay.setVisibility(0);
                    CoolerActivity.this.growAnim = AnimationUtils.loadAnimation(CoolerActivity.this.context, R.anim.grow_from_middle);
                    CoolerActivity.this.cpuLay.startAnimation(CoolerActivity.this.growAnim);
                    CoolerActivity.this.cpuLay.setVisibility(0);
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
                    Animation loadAnimation = AnimationUtils.loadAnimation(CoolerActivity.this.context, R.anim.postoup);
                    CoolerActivity.this.optimizeText.setVisibility(View.VISIBLE);
                    CoolerActivity.this.optimizeText.startAnimation(loadAnimation);
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
                    Animation loadAnimation = AnimationUtils.loadAnimation(CoolerActivity.this.context, R.anim.postoup);
                    CoolerActivity.this.coolsuccesstext.setVisibility(View.VISIBLE);
                    CoolerActivity.this.coolsuccesstext.startAnimation(loadAnimation);
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
                    CoolerActivity.this.startActivity(new Intent(CoolerActivity.this, OptimizeActivity.class));
                    CoolerActivity.this.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    CoolerActivity.this.finish();
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
        startActivity(new Intent(this, OptimizeActivity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }

    
    public void onResume() {
        super.onResume();
    }
}
