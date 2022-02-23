package com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;

public class WifiBoosterActivity extends Activity implements View.OnClickListener {
    LinearLayout adContainer;
    RelativeLayout backlay;
    RelativeLayout bannerAdLay;
    RelativeLayout boostStatusLay;
    TextView boostingPerText;
    Context context;
    TextView coolsuccesstext;
    RelativeLayout cpuLay;
    RelativeLayout dummyBannerContainer;
    SharedPreferences.Editor editor;
    private Animation fadeInAnim;
    private Animation fadeOutAnim;
    private Animation growDownAnim;
    private Animation growFromMiddleAnim;
    Handler handler = new Handler();
    TextView heading_des;
    TextView heading_des1;
    RelativeLayout heading_desLay;
    AnimationDrawable loadAnimation;
    RelativeLayout mainLay;
    TextView networkBoostingText;
    TextView optimizeText;
    RelativeLayout optimizedLay;
    final Runnable percentageRunnable = new Runnable() {
        /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.WifiBoosterActivity.AnonymousClass7 */

        public void run() {
            while (WifiBoosterActivity.this.wheelProgress <= 100) {
                try {
                    WifiBoosterActivity.this.runOnUiThread(new Runnable() {
                        /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.WifiBoosterActivity.AnonymousClass7.AnonymousClass1 */

                        public void run() {
                            TextView textView = WifiBoosterActivity.this.boostingPerText;
                            textView.setText("" + WifiBoosterActivity.this.wheelProgress);
                            WifiBoosterActivity wifiBoosterActivity = WifiBoosterActivity.this;
                            wifiBoosterActivity.wheelProgress = wifiBoosterActivity.wheelProgress + 1;
                        }
                    });
                    if (WifiBoosterActivity.this.wheelProgress < 100) {
                        if (WifiBoosterActivity.this.wheelProgress < 40) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else if (WifiBoosterActivity.this.wheelProgress > 40 && WifiBoosterActivity.this.wheelProgress < 70) {
                            try {
                                Thread.sleep(150);
                            } catch (InterruptedException e2) {
                                e2.printStackTrace();
                            }
                        } else if (WifiBoosterActivity.this.wheelProgress <= 70 || WifiBoosterActivity.this.wheelProgress >= 90) {
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e3) {
                                e3.printStackTrace();
                            }
                        } else {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e4) {
                                e4.printStackTrace();
                            }
                        }
                    } else {
                        return;
                    }
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
        }
    };
    SharedPreferences pref;
    private Animation pulse;
    private Animation rotateAnim;
    ImageView rotatingImg;
    private Animation shakeAnim;
    ImageView tickImage;
    TextView title;
    public int valCheck = 0;
    ImageView waterButton;
    int wheelProgress = 0;
    RelativeLayout wifiBoostLay;
    RelativeLayout wifiBoostingLay;
    ImageView wifi_tower;

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.context = this;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.pref = defaultSharedPreferences;
        this.editor = defaultSharedPreferences.edit();
        Utils.CheckFromWichActivityComming = 1;
        try {
            setContentView(R.layout.activity_wifibooster);
            this.context = this;
            this.bannerAdLay = (RelativeLayout) findViewById(R.id.bannerAdLay);
            this.dummyBannerContainer = (RelativeLayout) findViewById(R.id.dummy_banner_container);
            this.adContainer = (LinearLayout) findViewById(R.id.banner_container);
            this.heading_desLay = (RelativeLayout) findViewById(R.id.heading_desLay);
            this.boostStatusLay = (RelativeLayout) findViewById(R.id.boostStatusLay);
            this.backlay = (RelativeLayout) findViewById(R.id.backlay);
            this.wifiBoostLay = (RelativeLayout) findViewById(R.id.wifiBoostLay);
            this.mainLay = (RelativeLayout) findViewById(R.id.mainLay);
            this.optimizedLay = (RelativeLayout) findViewById(R.id.optimizedLay);
            this.cpuLay = (RelativeLayout) findViewById(R.id.cpuLay);
            this.wifiBoostingLay = (RelativeLayout) findViewById(R.id.wifiBoostingLay);
            this.waterButton = (ImageView) findViewById(R.id.waterButton);
            this.wifi_tower = (ImageView) findViewById(R.id.wifi_tower);
            this.rotatingImg = (ImageView) findViewById(R.id.rotatingImg);
            this.heading_des = (TextView) findViewById(R.id.heading_des);
            this.heading_des1 = (TextView) findViewById(R.id.heading_des1);
            this.boostingPerText = (TextView) findViewById(R.id.boostingPerText);
            this.networkBoostingText = (TextView) findViewById(R.id.networkBoostingText);
            this.optimizeText = (TextView) findViewById(R.id.optimizeText);
            this.coolsuccesstext = (TextView) findViewById(R.id.coolsuccesstext);
            this.title = (TextView) findViewById(R.id.textView8);
            this.optimizeText.setTypeface(AppAnaylatics.RobotoRegular);
            this.coolsuccesstext.setTypeface(AppAnaylatics.RobotoRegular);
            this.title.setTypeface(AppAnaylatics.RobotoRegular);
            this.heading_des.setTypeface(AppAnaylatics.RobotoRegular);
            this.heading_des1.setTypeface(AppAnaylatics.RobotoRegular);
            this.boostingPerText.setTypeface(AppAnaylatics.RobotoRegular);
            this.networkBoostingText.setTypeface(AppAnaylatics.RobotoRegular);
            Animation loadAnimation2 = AnimationUtils.loadAnimation(this, R.anim.pulse);
            this.pulse = loadAnimation2;
            this.waterButton.startAnimation(loadAnimation2);
            this.wifiBoostLay.setOnClickListener(this);
            this.backlay.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.backlay) {
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            finish();
            finish();
        } else if (id == R.id.wifiBoostLay) {
            try {
                if (!isNetworkAvailable(this.context)) {
                    Toast.makeText(this.context, getResources().getString(R.string.network_unavailable), Toast.LENGTH_LONG).show();
                    return;
                }
                this.wifiBoostLay.setEnabled(false);
                this.editor.putLong(Utils.CheckStateOfAlreadyWifiBoost, System.currentTimeMillis());
                this.editor.commit();
                BoostingFun();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void otherAnimation() {
        runOnUiThread(new Runnable() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.WifiBoosterActivity.AnonymousClass1 */

            public void run() {
                try {
                    WifiBoosterActivity.this.waterButton.clearAnimation();
                    WifiBoosterActivity.this.pulse.cancel();
                    WifiBoosterActivity.this.growDownAnim = AnimationUtils.loadAnimation(WifiBoosterActivity.this, R.anim.down_from_middle);
                    WifiBoosterActivity.this.wifiBoostLay.startAnimation(WifiBoosterActivity.this.growDownAnim);
                    WifiBoosterActivity.this.wifiBoostLay.setVisibility(View.GONE);
                    WifiBoosterActivity.this.fadeOutAnim = AnimationUtils.loadAnimation(WifiBoosterActivity.this, R.anim.fade_out);
                    WifiBoosterActivity.this.heading_desLay.startAnimation(WifiBoosterActivity.this.fadeOutAnim);
                    WifiBoosterActivity.this.heading_desLay.setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.handler.postDelayed(new Runnable() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.WifiBoosterActivity.AnonymousClass2 */

            public void run() {
                WifiBoosterActivity wifiBoosterActivity = WifiBoosterActivity.this;
                wifiBoosterActivity.fadeInAnim = AnimationUtils.loadAnimation(wifiBoosterActivity, R.anim.fade_in);
                WifiBoosterActivity.this.wifiBoostingLay.startAnimation(WifiBoosterActivity.this.fadeInAnim);
                WifiBoosterActivity.this.wifiBoostingLay.setVisibility(View.VISIBLE);
                WifiBoosterActivity.this.handler.postDelayed(new Runnable() {
                    /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.WifiBoosterActivity.AnonymousClass2.AnonymousClass1 */

                    public void run() {
                        try {
                            WifiBoosterActivity.this.loadAnimation = (AnimationDrawable) WifiBoosterActivity.this.wifi_tower.getBackground();
                            WifiBoosterActivity.this.loadAnimation.start();
                            WifiBoosterActivity.this.rotateAnim = AnimationUtils.loadAnimation(WifiBoosterActivity.this, R.anim.rotate);
                            WifiBoosterActivity.this.rotatingImg.startAnimation(WifiBoosterActivity.this.rotateAnim);
                            WifiBoosterActivity.this.fadeInAnim = AnimationUtils.loadAnimation(WifiBoosterActivity.this, R.anim.fade_in);
                            WifiBoosterActivity.this.boostStatusLay.startAnimation(WifiBoosterActivity.this.fadeInAnim);
                            WifiBoosterActivity.this.boostStatusLay.setVisibility(View.VISIBLE);
                            new Thread(WifiBoosterActivity.this.percentageRunnable).start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 500);
            }
        }, 500);
        this.handler.postDelayed(new Runnable() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.WifiBoosterActivity.AnonymousClass3 */

            public void run() {
                try {
                    WifiBoosterActivity.this.rotateAnim.cancel();
                    WifiBoosterActivity.this.rotatingImg.clearAnimation();
                    WifiBoosterActivity.this.loadAnimation.stop();
                    WifiBoosterActivity.this.fadeOutAnim = AnimationUtils.loadAnimation(WifiBoosterActivity.this, R.anim.fade_out);
                    WifiBoosterActivity.this.wifiBoostingLay.startAnimation(WifiBoosterActivity.this.fadeOutAnim);
                    WifiBoosterActivity.this.wifiBoostingLay.setVisibility(View.GONE);
                    WifiBoosterActivity.this.handler.postDelayed(new Runnable() {
                        /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.WifiBoosterActivity.AnonymousClass3.AnonymousClass1 */

                        public void run() {
                            WifiBoosterActivity.this.boostStatusLay.setVisibility(View.GONE);
                            WifiBoosterActivity.this.mainLay.setBackgroundDrawable(WifiBoosterActivity.this.getResources().getDrawable(R.drawable.lm));
                            WifiBoosterActivity.this.optimizedLay.setVisibility(View.VISIBLE);
                            WifiBoosterActivity.this.cpuLay.startAnimation(AnimationUtils.loadAnimation(WifiBoosterActivity.this.context, R.anim.grow_from_middle));
                            WifiBoosterActivity.this.cpuLay.setVisibility(View.VISIBLE);
                        }
                    }, 500);
                    WifiBoosterActivity.this.handler.postDelayed(new Runnable() {
                        /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.WifiBoosterActivity.AnonymousClass3.AnonymousClass2 */

                        public void run() {
                            try {
                                Animation loadAnimation = AnimationUtils.loadAnimation(WifiBoosterActivity.this.context, R.anim.postoup);
                                WifiBoosterActivity.this.optimizeText.setVisibility(View.VISIBLE);
                                WifiBoosterActivity.this.optimizeText.startAnimation(loadAnimation);
                            } catch (IllegalStateException e) {
                                e.printStackTrace();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    }, 1000);
                    WifiBoosterActivity.this.handler.postDelayed(new Runnable() {
                        /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.WifiBoosterActivity.AnonymousClass3.AnonymousClass3 */

                        public void run() {
                            try {
                                Animation loadAnimation = AnimationUtils.loadAnimation(WifiBoosterActivity.this.context, R.anim.postoup);
                                WifiBoosterActivity.this.coolsuccesstext.setVisibility(View.VISIBLE);
                                WifiBoosterActivity.this.coolsuccesstext.startAnimation(loadAnimation);
                            } catch (IllegalStateException e) {
                                e.printStackTrace();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    }, 1800);
                    WifiBoosterActivity.this.handler.postDelayed(new Runnable() {
                        /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.WifiBoosterActivity.AnonymousClass3.AnonymousClass4 */

                        public void run() {
                            try {
                              /*  if (Utils.interstitialAd == null || !Utils.interstitialAd.isAdLoaded()) {
                                    WifiBoosterActivity.this.startActivity(new Intent(WifiBoosterActivity.this, WifiBoostCompleteActivity.class));
                                    WifiBoosterActivity.this.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                                    WifiBoosterActivity.this.finish();
                                    return;
                                }
                                Intent intent = new Intent(WifiBoosterActivity.this.context, InterstitialAdsActivity.class);
                                intent.putExtra(Utils.SaveStateOfReturnActivity, 1);
                                WifiBoosterActivity.this.startActivity(intent);
                                WifiBoosterActivity.this.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                                WifiBoosterActivity.this.finish();*/
                                WifiBoosterActivity.this.startActivity(new Intent(WifiBoosterActivity.this, WifiBoostCompleteActivity.class));
                                WifiBoosterActivity.this.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                                WifiBoosterActivity.this.finish();
                                return;
                            } catch (IllegalStateException e) {
                                e.printStackTrace();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    }, 4000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 11000);
    }

    public void BoostingFun() {
        if (Build.VERSION.SDK_INT < 23) {
            Utils.wifiOn(this.context, false);
            this.valCheck = 1;
            otherAnimation();
        } else if (Settings.System.canWrite(this.context)) {
            Utils.wifiOn(this.context, false);
            this.valCheck = 1;
            otherAnimation();
        } else {
            dialogPermissionFun();
        }
        this.handler.postDelayed(new Runnable() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.WifiBoosterActivity.AnonymousClass4 */

            public void run() {
                if (WifiBoosterActivity.this.valCheck != 1) {
                    return;
                }
                if (Build.VERSION.SDK_INT < 23) {
                    Utils.wifiOn(WifiBoosterActivity.this.context, true);
                    WifiBoosterActivity.this.valCheck = 0;
                } else if (Settings.System.canWrite(WifiBoosterActivity.this.context)) {
                    Utils.wifiOn(WifiBoosterActivity.this.context, true);
                    WifiBoosterActivity.this.valCheck = 0;
                } else {
                    WifiBoosterActivity.this.dialogPermissionFun();
                }
            }
        }, 5000);
    }

    public void dialogPermissionFun() {
        new AlertDialog.Builder(new ContextThemeWrapper(this.context, (int) R.style.CustomDialog)).setTitle(getString(R.string.settingPermission)).setMessage(getString(R.string.settingPermissionTxt)).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.WifiBoosterActivity.AnonymousClass6 */

            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
                intent.setData(Uri.parse("package:" + WifiBoosterActivity.this.context.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                WifiBoosterActivity.this.context.startActivity(intent);
            }
        }).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.WifiBoosterActivity.AnonymousClass5 */

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).show();
    }

    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        finish();
    }

    public boolean isNetworkAvailable(Context context2) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context2.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }
        try {
            NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
            if (allNetworkInfo != null) {
                for (NetworkInfo networkInfo : allNetworkInfo) {
                    if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public void onResume() {
        super.onResume();
    }
}
