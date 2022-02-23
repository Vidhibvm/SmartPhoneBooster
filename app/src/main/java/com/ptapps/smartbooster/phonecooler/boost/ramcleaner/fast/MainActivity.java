package com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity implements View.OnClickListener {
    public static int finalRandomXForTotallSizeApi26;
    private static ArrayList<ListInstalledAppModel> listInstalled;
    private static String[] requestedPermissions;
    private RelativeLayout BatteryLay;
    private RelativeLayout BatteryLayInner;
    private RelativeLayout BoostLay;
    private RelativeLayout BoostLayInner;
    private RelativeLayout BoostRoundLay;
    private RelativeLayout CoolerLay;
    private RelativeLayout CoolerLayInner;
    private RelativeLayout JunkLay;
    private RelativeLayout JunkLayInner;
    private RelativeLayout MainCleanerLay;
    float MainCleanerLayX;
    private ProgressBar MainProgBar;
    private ImageView RippleImage;
    private TextView bodytxt;
    final Runnable coloredRunnable = new Runnable() {
        /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.MainActivity.AnonymousClass6 */
        private int gl;

        public void run() {
            while (MainActivity.this.wheelProgress2 < 361) {
                MainActivity.this.progressBar.incrementProgress();
                MainActivity.this.wheelProgress2++;
                double d = (double) MainActivity.this.wheelProgress2;
                Double.isNaN(d);
                this.gl = (int) Math.round(d / 3.6d);
                if (MainActivity.this.wheelProgress2 < 360) {
                    if (MainActivity.this.wheelProgress2 < 290) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else if (MainActivity.this.wheelProgress2 > 290) {
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
    private ImageView handImg;
    Handler handler = new Handler();
    private RelativeLayout junkTextLay;
    private TextView junkfound;
    AnimationDrawable loadAnimation;
    private TextView mainCanBeSaveValTxt;
    private ImageView mainScrenRoundImg;
    int maxX = 390;
    int minX = ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION;
    private TextView minimize;
    private RelativeLayout moreAppbtn;
    private TextView moreapps;
    private RelativeLayout notnowBtn;
    SharedPreferences pref;
    private ProgressWheel progressBar;
    private Animation pulse;
    Runnable r = new Runnable() {
        /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.MainActivity.AnonymousClass7 */

        public void run() {
            ActivityManager activityManager = (ActivityManager) MainActivity.this.getSystemService(Context.ACTIVITY_SERVICE);
            int i = 0;
            while (i < Utils.runningList.size() && i <= 30 && i < Utils.runningList.size()) {
                final RunningItem runningItem = new RunningItem();
                int finalI = i;
                MainActivity.this.runOnUiThread(new Runnable() {
                    /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.MainActivity.AnonymousClass7.AnonymousClass1 */

                    public void run() {
                        Drawable drawable = SplashActivity.icons.get(Utils.runningList.get(finalI).getPak());
                        if (drawable != null) {
                            runningItem.setIcon(drawable);
                        } else {
                            runningItem.setIcon(MainActivity.this.getResources().getDrawable(R.drawable.icon));
                        }
                        runningItem.setLabel(Utils.runningList.get(finalI).getLabel());
                        runningItem.setSize((int) Utils.runningList.get(finalI).getS());
                        runningItem.setPak(Utils.runningList.get(finalI).getPak());
                        runningItem.setChk(true);
                        System.currentTimeMillis();
                        MainActivity.this.pref.getLong("timeChk", 0);
                        TimeUnit.MINUTES.toMillis(2);
                        Utils.IgnorListforCheck = new IgnorList_DataBase(MainActivity.this.context).getPakgList();
                        if (Utils.IgnorListforCheck.size() == 0) {
                            MainActivity.this.funForAdddDataInList(runningItem);
                        } else if (Utils.contain(Utils.IgnorListforCheck, Utils.runningList.get(finalI).getPak()) != 1) {
                            MainActivity.this.funForAdddDataInList(runningItem);
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
            MainActivity.this.runOnUiThread(new Runnable() {
                /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.MainActivity.AnonymousClass7.AnonymousClass2 */

                public void run() {
                    MainActivity.this.afterLoadImageShowLayApi21();
                }
            });
        }
    };
    private ImageView rateStarImage;
    private ImageView rateStarImage1;
    private ImageView rateStarImage2;
    private ImageView rateStarImage3;
    private ImageView rateStarImage4;
    private ImageView rateStarImage5;
    private RelativeLayout ratelay;
    private Animation rotation;
    private RelativeLayout screenBase;
    private RelativeLayout settingLay;
    private RelativeLayout starLayMain;
    private TextView tit;
    private RelativeLayout toggleLayMainLay;
    float toggleLayMainLayX;
    int wheelProgress2 = 0;

    
    @SuppressLint("WrongConstant")
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Utils.secondVisit) {
            setContentView(R.layout.activity_main_new);
        } else {
            setContentView(R.layout.activity_main);
        }
        this.context = this;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.pref = defaultSharedPreferences;
        this.editor = defaultSharedPreferences.edit();

        this.mainScrenRoundImg = (ImageView) findViewById(R.id.BoostRoundImg);
        this.progressBar = (ProgressWheel) findViewById(R.id.progressBar);
        this.RippleImage = (ImageView) findViewById(R.id.rippleImg);
        this.BoostLay = (RelativeLayout) findViewById(R.id.boosterLay);
        this.BatteryLay = (RelativeLayout) findViewById(R.id.batteryLay);
        this.CoolerLay = (RelativeLayout) findViewById(R.id.coolerLay);
        this.JunkLay = (RelativeLayout) findViewById(R.id.junkLay);
        this.screenBase = (RelativeLayout) findViewById(R.id.screenBase);
        this.toggleLayMainLay = (RelativeLayout) findViewById(R.id.toggleLayMainLay);
        this.BoostLayInner = (RelativeLayout) findViewById(R.id.boosterLayInner);
        this.BatteryLayInner = (RelativeLayout) findViewById(R.id.batteryLayInner);
        this.CoolerLayInner = (RelativeLayout) findViewById(R.id.coolerLayInner);
        this.JunkLayInner = (RelativeLayout) findViewById(R.id.junkLayInner);
        this.MainCleanerLay = (RelativeLayout) findViewById(R.id.MainScreenTopCleanLay);
        this.settingLay = (RelativeLayout) findViewById(R.id.settingLay);
        this.junkTextLay = (RelativeLayout) findViewById(R.id.junkTextLay);
        this.junkfound = (TextView) findViewById(R.id.textView2);
        TextView textView = (TextView) findViewById(R.id.mainCanBeSaveVal);
        this.mainCanBeSaveValTxt = textView;
        textView.setTypeface(AppAnaylatics.RobotoBold);
        if (Utils.secondVisit) {
            this.delayForRotation = 500;
            this.progressBar.setVisibility(8);
            this.screenBase.setVisibility(0);
            this.toggleLayMainLay.setVisibility(0);
        } else {
            this.delayForRotation = 2300;
            Utils.secondVisit = true;
            this.handler.postDelayed(new Runnable() {
                /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.MainActivity.AnonymousClass1 */

                public void run() {
                    MainActivity.this.progressBar.setVisibility(View.VISIBLE);
                    MainActivity.this.progressBar.setRimColor(MainActivity.this.getResources().getColor(R.color.colorAccentdark));
                    new Thread(MainActivity.this.coloredRunnable).start();
                }
            }, 300);
            this.handler.postDelayed(new Runnable() {
                /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.MainActivity.AnonymousClass2 */

                public void run() {
                    MainActivity.this.progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity.this.context, R.anim.fade_out));
                    MainActivity.this.progressBar.setVisibility(8);
                }
            }, 1000);
            this.handler.postDelayed(new Runnable() {
                /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.MainActivity.AnonymousClass3 */

                public void run() {
                    MainActivity.this.screenBase.startAnimation(AnimationUtils.loadAnimation(MainActivity.this.context, R.anim.fade_in));
                    MainActivity.this.screenBase.setVisibility(0);
                }
            }, 1300);
            if (!getResources().getBoolean(R.bool.isTablet)) {
                this.MainCleanerLayX = -450.0f;
                this.toggleLayMainLayX = -900.0f;
            } else if (getResources().getBoolean(R.bool.isSmall)) {
                this.MainCleanerLayX = -250.0f;
                this.toggleLayMainLayX = -512.0f;
            } else {
                this.MainCleanerLayX = -250.0f;
                this.toggleLayMainLayX = -612.0f;
            }
            this.handler.postDelayed(new Runnable() {


                public void run() {
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(MainActivity.this.MainCleanerLay, "translationY", MainActivity.this.MainCleanerLayX);
                    ofFloat.setDuration(500L);
                    ofFloat.start();
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(MainActivity.this.toggleLayMainLay, "translationY", MainActivity.this.toggleLayMainLayX);
                    ofFloat2.setDuration(500L);
                    ofFloat2.start();
                }
            }, 1800);
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - TimeUnit.MINUTES.toMillis(2) >= this.pref.getLong(Utils.CheckStateOfAlreadyPhoneBoost, 0)) {
            this.handler.postDelayed(new Runnable() {
                /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.MainActivity.AnonymousClass5 */

                public void run() {
                    Animation loadAnimation = AnimationUtils.loadAnimation(MainActivity.this.context, R.anim.rotate);
                    loadAnimation.setDuration(8500);
                    MainActivity.this.mainScrenRoundImg.startAnimation(loadAnimation);
                    MainActivity.this.mainScrenRoundImg.setVisibility(8);
                }
            }, (long) this.delayForRotation);
        }
        this.BoostLay.setOnClickListener(this);
        this.BatteryLay.setOnClickListener(this);
        this.CoolerLay.setOnClickListener(this);
        this.JunkLay.setOnClickListener(this);
        this.MainCleanerLay.setOnClickListener(this);
        this.settingLay.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= 26) {
            beforeLoadInvisibleLayForApi21();
            new Thread(this.r).start();
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
                } else if (this.pref.getBoolean(this.context.getString(R.string.isAllowedToAccess), true)) {
                    Utils.permissionDialog(this.context, 1);
                    return;
                } else {
                    FunForBoost();
                    return;
                }
            case R.id.batteryLay:
                startActivity(new Intent(this, ScanningBattery.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
                return;
            case R.id.boosterLay:
                if (Build.VERSION.SDK_INT < 23) {
                    FunForBoost();
                    return;
                } else if (this.pref.getBoolean(this.context.getString(R.string.isAllowedToAccess), true)) {
                    Utils.permissionDialog(this.context, 1);
                    return;
                } else {
                    FunForBoost();
                    return;
                }
            case R.id.coolerLay:
                if (Build.VERSION.SDK_INT < 23) {
                    switchCoolerScanning();
                    return;
                } else if (this.pref.getBoolean(this.context.getString(R.string.isAllowedToAccess), true)) {
                    Utils.permissionDialog(this.context, 1);
                    return;
                } else {
                    switchCoolerScanning();
                    return;
                }
            case R.id.junkLay:
                if (Build.VERSION.SDK_INT < 23) {
                    funForWiFi();
                    return;
                } else if (Settings.System.canWrite(this.context)) {
                    funForWiFi();
                    return;
                } else {
                    Utils.permissionDialog(this.context, 0);
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
        startActivity(new Intent(this, CoolerScanning.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }

    public void funForWiFi() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - TimeUnit.MINUTES.toMillis(2) >= this.pref.getLong(Utils.CheckStateOfAlreadyWifiBoost, 0)) {
            startActivity(new Intent(this, WifiBoosterActivity.class));
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            finish();
            return;
        }
        Utils.CheckFromWichActivityComming = 8;
        switchActivity();
    }

    public void switchSettingActivity() {
        startActivity(new Intent(this.context, SettingActivity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }

    public void switchActivity() {
        startActivity(new Intent(this, OptimizeActivity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }

    @SuppressLint("WrongConstant")
    public void afterLoadImageShowLayApi21() {
        this.BoostLayInner.setVisibility(0);
        this.BatteryLayInner.setVisibility(0);
        this.CoolerLayInner.setVisibility(0);
        this.JunkLayInner.setVisibility(0);
        this.MainCleanerLay.setVisibility(0);
    }

    @SuppressLint("WrongConstant")
    public void beforeLoadInvisibleLayForApi21() {
        this.BoostLayInner.setVisibility(4);
        this.BatteryLayInner.setVisibility(4);
        this.CoolerLayInner.setVisibility(4);
        this.JunkLayInner.setVisibility(4);
        this.MainCleanerLay.setVisibility(4);
    }

    public void funForAdddDataInList(RunningItem runningItem) {
        try {
            Utils.mApps.add(runningItem);
            Utils.ListforDisplayIcons.add(runningItem);
            Utils.CoolerListmApps.add(runningItem);
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
        if (currentTimeMillis - TimeUnit.MINUTES.toMillis(2) >= this.pref.getLong(Utils.CheckStateOfAlreadyPhoneBoost, 0)) {
            startActivity(new Intent(this, BoostingList.class));
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            finish();
            return;
        }
        Utils.CheckFromWichActivityComming = 5;
        startActivity(new Intent(this, OptimizeActivity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }


    @SuppressLint("WrongConstant")
    public void onResume() {
        super.onResume();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - TimeUnit.MINUTES.toMillis(2) < this.pref.getLong(Utils.CheckStateOfAlreadyPhoneBoost, 0)) {
            this.junkfound.setVisibility(8);
            this.mainCanBeSaveValTxt.setText(getResources().getString(R.string.junkcleaned));
            this.junkTextLay.setVisibility(0);
        } else if (Build.VERSION.SDK_INT >= 26) {
            finalRandomXForTotallSizeApi26 = new Random().nextInt(this.maxX) + this.minX;
            TextView textView = this.mainCanBeSaveValTxt;
            textView.setText("" + finalRandomXForTotallSizeApi26 + " MB");
        } else {
            this.mainCanBeSaveValTxt.setText(Utils.formatSize(SplashActivity.ramUsedByApps));
        }
    }

    public void exitDialog() {
        final Dialog dialog = new Dialog(this.context);
        dialog.requestWindowFeature(1);
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.exit_dialog, (ViewGroup) null);
        this.notnowBtn = (RelativeLayout) inflate.findViewById(R.id.notnowlay);
        this.ratelay = (RelativeLayout) inflate.findViewById(R.id.ratelay);
        this.moreAppbtn = (RelativeLayout) inflate.findViewById(R.id.freeapplay);
        this.bodytxt = (TextView) inflate.findViewById(R.id.textView4);
        this.moreapps = (TextView) inflate.findViewById(R.id.textView5);
        this.minimize = (TextView) inflate.findViewById(R.id.textView7);
        this.exit = (TextView) inflate.findViewById(R.id.textView6);
        this.junkfound.setTypeface(AppAnaylatics.RobotoRegular);
        this.bodytxt.setTypeface(AppAnaylatics.RobotoRegular);
        this.moreapps.setTypeface(AppAnaylatics.RobotoBold);
        this.minimize.setTypeface(AppAnaylatics.RobotoBold);
        this.exit.setTypeface(AppAnaylatics.RobotoBold);
        this.ratelay.setOnClickListener(new View.OnClickListener() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.MainActivity.AnonymousClass8 */

            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast")));
                dialog.cancel();
            }
        });
        this.notnowBtn.setOnClickListener(new View.OnClickListener() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.MainActivity.AnonymousClass9 */

            public void onClick(View view) {
                Utils.secondVisit = false;
                MainActivity.this.finish();
                dialog.cancel();
            }
        });
        this.moreAppbtn.setOnClickListener(new View.OnClickListener() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.MainActivity.AnonymousClass10 */

            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(MainActivity.this.getResources().getString(R.string.moreAppsLink))));
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
            Utils.secondVisit = false;
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }
}
