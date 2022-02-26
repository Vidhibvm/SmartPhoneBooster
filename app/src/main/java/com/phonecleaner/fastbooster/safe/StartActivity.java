package com.phonecleaner.fastbooster.safe;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.ItemTouchHelper;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class StartActivity extends Activity implements View.OnClickListener {
    public static int finalRandomXForTotallSizeApi26;
    private static ArrayList<ListInstalledAppModel> listInstalled;
    private static String[] requestedPermissions;
    private RelativeLayout BatteryLay;
    private RelativeLayout BatteryLayInner;
    private RelativeLayout BoostLay;
    private RelativeLayout BoostLayInner;
    private RelativeLayout CoolerLay;
    private RelativeLayout CoolerLayInner;
    private RelativeLayout JunkLay;
    private RelativeLayout JunkLayInner;
    private RelativeLayout MainCleanerLay;
    float MainCleanerLayX;
    private TextView bodytxt;
    final Runnable coloredRunnable = new Runnable() {
        private int gl;

        public void run() {
            while (wheelProgress2 < 361) {
             //   progressBar.incrementProgress();
                wheelProgress2++;
                double d = (double) wheelProgress2;
                Double.isNaN(d);
                gl = (int) Math.round(d / 3.6d);
                if (wheelProgress2 < 360) {
                    if (wheelProgress2 < 290) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else if (wheelProgress2 > 290) {
                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    } else {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e3) {
                            e3.printStackTrace();
                        }
                    }
                } else {
                    return;
                }
            }
        }
    };
    Context context;
    int delayForRotation;
    SharedPreferences.Editor editor;
    private TextView exit;
    Handler handler = new Handler();
    private RelativeLayout junkTextLay;
    private TextView junkfound;
    private TextView mainCanBeSaveValTxt;
//    private ImageView mainScrenRoundImg;
    int maxX = 390;
    int minX = ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION;
    private TextView minimize;
    private RelativeLayout moreAppbtn;
    private TextView moreapps;
    private RelativeLayout notnowBtn;
    SharedPreferences pref;
   // private ProgressWheel progressBar;
    Runnable r = new Runnable() {

        public void run() {
            ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            int i = 0;
            while (i < util.runningList.size() && i <= 30 && i < util.runningList.size()) {
                final RunningItem runningItem = new RunningItem();
                int finalI = i;
                runOnUiThread(new Runnable() {

                    public void run() {
                        Drawable drawable = WelcomeActivity.icons.get(util.runningList.get(finalI).getPak());
                        if (drawable != null) {
                            runningItem.setIcon(drawable);
                        } else {
                            runningItem.setIcon(getResources().getDrawable(R.drawable.icon));
                        }
                        runningItem.setLabel(util.runningList.get(finalI).getLabel());
                        runningItem.setSize((int) util.runningList.get(finalI).getS());
                        runningItem.setPak(util.runningList.get(finalI).getPak());
                        runningItem.setChk(true);
                        System.currentTimeMillis();
                        pref.getLong("timeChk", 0);
                        TimeUnit.MINUTES.toMillis(2);
                        util.IgnorListforCheck = new IgnorAppList_DataBase(context).getPakgList();
                        if (util.IgnorListforCheck.size() == 0) {
                            funForAdddDataInList(runningItem);
                        } else if (util.contain(util.IgnorListforCheck, util.runningList.get(finalI).getPak()) != 1) {
                            funForAdddDataInList(runningItem);
                        }
                    }
                });
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
            runOnUiThread(new Runnable() {

                public void run() {
                    afterLoadImageShowLayApi21();
                }
            });
        }
    };

    private RelativeLayout ratelay;
    private RelativeLayout screenBase;
    private RelativeLayout settingLay;
    private RelativeLayout toggleLayMainLay;
    float toggleLayMainLayX;
    int wheelProgress2 = 0;


    @SuppressLint("WrongConstant")
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (util.secondVisit) {
            setContentView(R.layout.activity_main_new);
        } else {
            setContentView(R.layout.activity_main);
        }
        context = this;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        pref = defaultSharedPreferences;
        editor = defaultSharedPreferences.edit();

//        mainScrenRoundImg = (ImageView) findViewById(R.id.BoostRoundImg);
       // progressBar = (ProgressWheel) findViewById(R.id.progressBar);

        BoostLay = (RelativeLayout) findViewById(R.id.boosterLay);
        BatteryLay = (RelativeLayout) findViewById(R.id.batteryLay);
        CoolerLay = (RelativeLayout) findViewById(R.id.coolerLay);
        JunkLay = (RelativeLayout) findViewById(R.id.junkLay);
        screenBase = (RelativeLayout) findViewById(R.id.screenBase);
        toggleLayMainLay = (RelativeLayout) findViewById(R.id.toggleLayMainLay);
        BoostLayInner = (RelativeLayout) findViewById(R.id.boosterLayInner);
        BatteryLayInner = (RelativeLayout) findViewById(R.id.batteryLayInner);
        CoolerLayInner = (RelativeLayout) findViewById(R.id.coolerLayInner);
        JunkLayInner = (RelativeLayout) findViewById(R.id.junkLayInner);
        MainCleanerLay = (RelativeLayout) findViewById(R.id.MainScreenTopCleanLay);
        settingLay = (RelativeLayout) findViewById(R.id.settingLay);
        junkTextLay = (RelativeLayout) findViewById(R.id.junkTextLay);
        junkfound = (TextView) findViewById(R.id.textView2);
        TextView textView = (TextView) findViewById(R.id.mainCanBeSaveVal);
        mainCanBeSaveValTxt = textView;
        textView.setTypeface(AppAnaylatics.RobotoBold);
        if (util.secondVisit) {
            delayForRotation = 500;
           // progressBar.setVisibility(8);
            screenBase.setVisibility(0);
            toggleLayMainLay.setVisibility(0);
        } else {
            delayForRotation = 2300;
            util.secondVisit = true;
           /* handler.postDelayed(new Runnable() {

                public void run() {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setRimColor(getResources().getColor(R.color.colorAccentdark));
                    new Thread(coloredRunnable).start();
                }
            }, 300);*/
         /*   handler.postDelayed(new Runnable() {

                public void run() {
                    progressBar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_out));
                    progressBar.setVisibility(8);
                }
            }, 1000);*/
            handler.postDelayed(new Runnable() {

                public void run() {
                    screenBase.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));
                    screenBase.setVisibility(0);
                }
            }, 1300);
            if (!getResources().getBoolean(R.bool.isTablet)) {
                MainCleanerLayX = -450.0f;
                toggleLayMainLayX = -900.0f;
            } else if (getResources().getBoolean(R.bool.isSmall)) {
                MainCleanerLayX = -250.0f;
                toggleLayMainLayX = -512.0f;
            } else {
                MainCleanerLayX = -250.0f;
                toggleLayMainLayX = -612.0f;
            }
            handler.postDelayed(new Runnable() {


                public void run() {
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(MainCleanerLay, "translationY", MainCleanerLayX);
                    ofFloat.setDuration(500L);
                    ofFloat.start();
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(toggleLayMainLay, "translationY", toggleLayMainLayX);
                    ofFloat2.setDuration(500L);
                    ofFloat2.start();
                }
            }, 1800);
        }
//        long currentTimeMillis = System.currentTimeMillis();
//        if (currentTimeMillis - TimeUnit.MINUTES.toMillis(2) >= pref.getLong(Utils.CheckStateOfAlreadyPhoneBoost, 0)) {
//            handler.postDelayed(new Runnable() {
//
//                public void run() {
//                    Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate);
//                    loadAnimation.setDuration(8500);
//                    mainScrenRoundImg.startAnimation(loadAnimation);
//                    mainScrenRoundImg.setVisibility(8);
//                }
//            }, (long) delayForRotation);
//        }
        BoostLay.setOnClickListener(this);
        BatteryLay.setOnClickListener(this);
        CoolerLay.setOnClickListener(this);
        JunkLay.setOnClickListener(this);
        MainCleanerLay.setOnClickListener(this);
        settingLay.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= 26) {
            beforeLoadInvisibleLayForApi21();
            new Thread(r).start();
            return;
        }
        afterLoadImageShowLayApi21();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.MainScreenTopCleanLay:
                if (Build.VERSION.SDK_INT < 23) {
                    FunForBoost();
                    return;
                } else if (pref.getBoolean(context.getString(R.string.isAllowedToAccess), true)) {
                    util.permissionDialog(context, 1);
                    return;
                } else {
                    FunForBoost();
                    return;
                }
            case R.id.batteryLay:
                startActivity(new Intent(this, BatteryFirstActivity.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
                return;
            case R.id.boosterLay:
                if (Build.VERSION.SDK_INT < 23) {
                    FunForBoost();
                    return;
                } else if (pref.getBoolean(context.getString(R.string.isAllowedToAccess), true)) {
                    util.permissionDialog(context, 1);
                    return;
                } else {
                    FunForBoost();
                    return;
                }
            case R.id.coolerLay:
                if (Build.VERSION.SDK_INT < 23) {
                    switchCoolerScanning();
                    return;
                } else if (pref.getBoolean(context.getString(R.string.isAllowedToAccess), true)) {
                    util.permissionDialog(context, 1);
                    return;
                } else {
                    switchCoolerScanning();
                    return;
                }
            case R.id.junkLay:
                if (Build.VERSION.SDK_INT < 23) {
                    funForWiFi();
                    return;
                } else if (Settings.System.canWrite(context)) {
                    funForWiFi();
                    return;
                } else {
                    util.permissionDialog(context, 0);
                    return;
                }
            case R.id.settingLay:
                switchSettingActivity();
                return;
            default:
                return;
        }
    }

    public void switchCoolerScanning() {
        startActivity(new Intent(this, CPUScan.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }

    public void funForWiFi() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - TimeUnit.MINUTES.toMillis(2) >= pref.getLong(util.CheckStateOfAlreadyWifiBoost, 0)) {
            startActivity(new Intent(this, WifiCleanerActivity.class));
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            finish();
            return;
        }
        util.CheckFromWichActivityComming = 8;
        switchActivity();
    }

    public void switchSettingActivity() {
        startActivity(new Intent(context, SettingsActivity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }

    public void switchActivity() {
        startActivity(new Intent(this, FinalAllActivity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }

    @SuppressLint("WrongConstant")
    public void afterLoadImageShowLayApi21() {
        BoostLayInner.setVisibility(0);
        BatteryLayInner.setVisibility(0);
        CoolerLayInner.setVisibility(0);
        JunkLayInner.setVisibility(0);
        MainCleanerLay.setVisibility(0);
    }

    @SuppressLint("WrongConstant")
    public void beforeLoadInvisibleLayForApi21() {
        BoostLayInner.setVisibility(4);
        BatteryLayInner.setVisibility(4);
        CoolerLayInner.setVisibility(4);
        JunkLayInner.setVisibility(4);
        MainCleanerLay.setVisibility(4);
    }

    public void funForAdddDataInList(RunningItem runningItem) {
        try {
            util.mApps.add(runningItem);
            util.ListforDisplayIcons.add(runningItem);
            util.CoolerListmApps.add(runningItem);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        } catch (IndexOutOfBoundsException e3) {
            e3.printStackTrace();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    public void FunForBoost() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - TimeUnit.MINUTES.toMillis(2) >= pref.getLong(util.CheckStateOfAlreadyPhoneBoost, 0)) {
            startActivity(new Intent(this, CleanerList.class));
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            finish();
            return;
        }
        util.CheckFromWichActivityComming = 5;
        startActivity(new Intent(this, FinalAllActivity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }


    @SuppressLint("WrongConstant")
    public void onResume() {
        super.onResume();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - TimeUnit.MINUTES.toMillis(2) < pref.getLong(util.CheckStateOfAlreadyPhoneBoost, 0)) {
            junkfound.setVisibility(8);
            mainCanBeSaveValTxt.setText(getResources().getString(R.string.junkcleaned));
            junkTextLay.setVisibility(0);
        } else if (Build.VERSION.SDK_INT >= 26) {
            finalRandomXForTotallSizeApi26 = new Random().nextInt(maxX) + minX;
            TextView textView = mainCanBeSaveValTxt;
            textView.setText("" + finalRandomXForTotallSizeApi26 + " MB");
        } else {
            mainCanBeSaveValTxt.setText(util.formatSize(WelcomeActivity.ramUsedByApps));
        }
    }

    public void exitDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(1);
        View inflate = LayoutInflater.from(context).inflate(R.layout.exit_dialog, (ViewGroup) null);
        notnowBtn = (RelativeLayout) inflate.findViewById(R.id.notnowlay);
        ratelay = (RelativeLayout) inflate.findViewById(R.id.ratelay);
        moreAppbtn = (RelativeLayout) inflate.findViewById(R.id.freeapplay);
        bodytxt = (TextView) inflate.findViewById(R.id.textView4);
        moreapps = (TextView) inflate.findViewById(R.id.textView5);
        minimize = (TextView) inflate.findViewById(R.id.textView7);
        exit = (TextView) inflate.findViewById(R.id.textView6);
        junkfound.setTypeface(AppAnaylatics.RobotoRegular);
        bodytxt.setTypeface(AppAnaylatics.RobotoRegular);
        moreapps.setTypeface(AppAnaylatics.RobotoBold);
        minimize.setTypeface(AppAnaylatics.RobotoBold);
        exit.setTypeface(AppAnaylatics.RobotoBold);
        ratelay.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast")));
                dialog.cancel();
            }
        });
        notnowBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                util.secondVisit = false;
                finish();
                dialog.cancel();
            }
        });
        moreAppbtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getResources().getString(R.string.moreAppsLink))));
            }
        });
        dialog.setContentView(inflate);
        dialog.show();
    }

    public void onBackPressed() {
        try {
            if (!isFinishing()) {
                exitDialog();
            } else {
                finish();
            }
            util.secondVisit = false;
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }
}
