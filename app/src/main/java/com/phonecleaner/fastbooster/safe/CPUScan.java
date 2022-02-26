package com.phonecleaner.fastbooster.safe;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class CPUScan extends AppCompatActivity {
 
    LinearLayout adContainer;
    AnimationDrawable anim;
    private ImageView appImage;
    RelativeLayout bannerAdLay;
    private int checkVal = 0;
    Context context;
    private ImageView coolerLoadingImage;
    private LinearLayout coolerUpDownLay;
    private ImageView cooler_circuit1;
    RelativeLayout dummyBannerContainer;
    SharedPreferences.Editor editor;
    Handler handler = new Handler();
    private Drawable icon;
    private CharSequence labl;
    PackageManager mPackManager;
    ActivityManager manager;
    MediaPlayer mp;
    private ActivityManager.RunningServiceInfo pInfo2;
    private String pkgnames;
    SharedPreferences pref;
    private ImageView rippleImg;
    List<ActivityManager.RunningAppProcessInfo> running;
    private TextView scanningTxt;
    private TextView titleTxt;
    public boolean val;

    @Override // androidx.fragment.app.FragmentActivity
    public void onBackPressed() {
    }

    
    @Override // androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.context = this;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.pref = defaultSharedPreferences;
        this.editor = defaultSharedPreferences.edit();
        setContentView(R.layout.activity_cooler_scanning);
        this.bannerAdLay = (RelativeLayout) findViewById(R.id.bannerAdLay);
        this.dummyBannerContainer = (RelativeLayout) findViewById(R.id.dummy_banner_container);
        this.adContainer = (LinearLayout) findViewById(R.id.banner_container);
      
        this.coolerUpDownLay = (LinearLayout) findViewById(R.id.cooler_move_lay);
        this.scanningTxt = (TextView) findViewById(R.id.ScanningTxt);
        this.titleTxt = (TextView) findViewById(R.id.appTitleTxt);
        this.appImage = (ImageView) findViewById(R.id.appIconImg);
        this.rippleImg = (ImageView) findViewById(R.id.rippleImg);
        this.cooler_circuit1 = (ImageView) findViewById(R.id.cooler_circuit1);
        this.coolerLoadingImage = (ImageView) findViewById(R.id.coolerLoadingImage);
        this.mp = MediaPlayer.create(getApplicationContext(), (int) R.raw.scanning_sound);
        this.titleTxt.setTypeface(AppAnaylatics.RobotoRegular);
        this.scanningTxt.setTypeface(AppAnaylatics.RobotoRegular);
        AnimationDrawable animationDrawable = (AnimationDrawable) this.coolerLoadingImage.getBackground();
        this.anim = animationDrawable;
        animationDrawable.setVisible(false, true);
        this.anim.start();
        new Handler().postDelayed(new Runnable() {

            @SuppressLint("WrongConstant")
            public void run() {
                Animation loadAnimation = AnimationUtils.loadAnimation(CPUScan.this.context, R.anim.pulse_low);
                CPUScan.this.rippleImg.setVisibility(0);
                CPUScan.this.rippleImg.startAnimation(loadAnimation);
            }
        }, 500);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                if (Build.VERSION.SDK_INT >= 26) {
                    CPUScan.this.FunForNextActivity();
                    return;
                }
                if (CPUScan.this.checkVal == 1) {
                    CPUScan.this.FunForNextActivity();
                }
                CPUScan.this.checkVal = 2;
            }
        }, 8000);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                try {
                    CPUScan.this.mp = MediaPlayer.create(CPUScan.this.getApplicationContext(), (int) R.raw.scanning_sound);
                    CPUScan.this.val = CPUScan.this.pref.getBoolean("soundchk", true);
                    if (util.SoundModeStatus(CPUScan.this).getRingerMode() == 2 && CPUScan.this.val && CPUScan.this.mp != null) {
                        CPUScan.this.mp.start();
                    }
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }, 1000);
        if (util.CoolerListmApps.size() == 0) {
            FunToAddCoolerListItem();
        }
        if (Build.VERSION.SDK_INT >= 26) {
            moveAnim();
        } else {
            new Longoperation().execute(new String[0]);
        }
    }

    public void moveAnim() {
        this.coolerUpDownLay.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.cooler_top_down));
        this.handler.postDelayed(new Runnable() {

            public void run() {
                CPUScan.this.fadeView();
            }
        }, 50);
    }

    public void fadeView() {
        this.cooler_circuit1.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_slow));
        this.handler.postDelayed(new Runnable() {

            public void run() {
                CPUScan.this.fadeView();
            }
        }, 800);
    }

    public void FunToAddCoolerListItem() {
        for (int i = 0; i < util.CoolerBackupListmApps.size(); i++) {
            try {
                util.CoolerListmApps.add(i, util.CoolerBackupListmApps.get(i));
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            } catch (NullPointerException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public class Longoperation extends AsyncTask<String, RunningItem, String> {
        public Longoperation() {
        }

        
        public void onPreExecute() {
            CPUScan.this.moveAnim();
            super.onPreExecute();
        }

        
        public String doInBackground(String... strArr) {
            CPUScan.this.process_memory();
            return null;
        }

        
        public void onPostExecute(String str) {
            if (CPUScan.this.checkVal == 2) {
                CPUScan.this.FunForNextActivity();
            }
            CPUScan.this.checkVal = 1;
        }
    }

    public int process_memory() {
        try {
            ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            int[] iArr = new int[util.mApps.size()];
            for (int i = 0; i < util.mApps.size(); i++) {
                iArr[i] = util.mApps.get(i).getPid();
            }
            Debug.MemoryInfo[] processMemoryInfo = activityManager.getProcessMemoryInfo(iArr);
            for (int i2 = 0; i2 < processMemoryInfo.length; i2++) {
                util.CoolerListmApps.get(i2).setSize(processMemoryInfo[i2].getTotalPss());
                processMemoryInfo[i2].getTotalPss();
            }
        } catch (Throwable unused) {
        }
        return 0;
    }

    public void FunForNextActivity() {
        try {
            if (this.mp != null && this.mp.isPlaying()) {
                this.mp.stop();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - TimeUnit.MINUTES.toMillis(2) < this.pref.getLong(util.CheckStateOfAlreadyCooled, 0) || util.CoolerListmApps.size() == 0) {
            util.CheckFromWichActivityComming = 7;
            startActivity(new Intent(this, FinalAllActivity.class));
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            finish();
            return;
        }
        startActivity(new Intent(this, CPU_Boost_Activity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }

    
    @Override // androidx.fragment.app.FragmentActivity
    public void onResume() {
        super.onResume();
    }
}
