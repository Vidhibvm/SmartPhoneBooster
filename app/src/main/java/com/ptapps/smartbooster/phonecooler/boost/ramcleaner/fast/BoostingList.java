package com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class BoostingList extends Activity implements View.OnClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
    public static long availableMegs;
    public static long ramused;
    private FloatingActionButton BoostButton;
    private RelativeLayout ExpandBoostButton;
    private int INITIAL_DELAY_MILLIS = 300;
    private ListView ListRinning;
    private RelativeLayout SettingLay;
    int actualHeight;
    LinearLayout adContainer;
    private SwingBottomInAnimationAdapter animationAdapter;
    private RelativeLayout backlay;
    RelativeLayout bannerAdLay;
    private Animation bottomToTopAnim;
    private Animation bottomtoCenterAnim;
    private int checkBack;
    private int chkNoItmBoost;
    Context context;
    final Runnable downRamPercentage = new Runnable() {
        /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.BoostingList.AnonymousClass5 */

        public void run() {
            BoostingList boostingList = BoostingList.this;
            boostingList.progress = boostingList.ramDonutProgress.getProgress() - 1.0f;
            if (BoostingList.this.progress >= ((float) BoostingList.this.ramPercentage)) {
                BoostingList.this.ramDonutProgress.setProgress((int) BoostingList.this.progress);
                TextView textView = BoostingList.this.ramPersentage;
                textView.setText("" + ((int) BoostingList.this.progress));
                return;
            }
            BoostingList.this.timer.cancel();
        }
    };
    RelativeLayout dummyBannerContainer;
    SharedPreferences.Editor editor;
    private Animation fadInAnim;
    Handler handler = new Handler();
    int initialHeight;
    SharedPreferences pref;
    private float progress = 0.0f;
    Running_Adaptor rAdaptor;
    private DonutProgress ramDonutProgress;
    private TextView ramFree;
    private long ramPercentage;
    private TextView ramPersentage;
    private TextView ramUsed;
    private Runnable runner;
    private Timer timer;
    private int total_memory;
    private TextView totallAppsTxt;
    private TextView tvRamUsedByApps;
    final Runnable uperRamPercentage = new Runnable() {
        /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.BoostingList.AnonymousClass6 */

        public void run() {
            BoostingList boostingList = BoostingList.this;
            boostingList.progress = boostingList.ramDonutProgress.getProgress() + 1.0f;
            if (BoostingList.this.progress <= ((float) BoostingList.this.ramPercentage)) {
                BoostingList.this.ramDonutProgress.setProgress((int) BoostingList.this.progress);
                TextView textView = BoostingList.this.ramPersentage;
                textView.setText("" + ((int) BoostingList.this.progress));
                return;
            }
            BoostingList.this.timer.cancel();
        }
    };

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_boosting_list);
        this.context = this;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.pref = defaultSharedPreferences;
        this.editor = defaultSharedPreferences.edit();
        this.bannerAdLay = (RelativeLayout) findViewById(R.id.bannerAdLay);
        this.dummyBannerContainer = (RelativeLayout) findViewById(R.id.dummy_banner_container);
        this.adContainer = (LinearLayout) findViewById(R.id.banner_container);

        this.ramDonutProgress = (DonutProgress) findViewById(R.id.ram_donut_progress);
        this.ramUsed = (TextView) findViewById(R.id.ram_used);
        this.ramPersentage = (TextView) findViewById(R.id.ram_percentage);
        this.ramFree = (TextView) findViewById(R.id.ram_free);
        this.BoostButton = (FloatingActionButton) findViewById(R.id.btn_boost_now);
        this.ExpandBoostButton = (RelativeLayout) findViewById(R.id.animationlay2);
        this.bottomtoCenterAnim = AnimationUtils.loadAnimation(this, R.anim.bottomtocennter);
        this.fadInAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        this.ListRinning = (ListView) findViewById(R.id.lv_apps);
        this.backlay = (RelativeLayout) findViewById(R.id.backlay);
        this.SettingLay = (RelativeLayout) findViewById(R.id.settingLay);
        this.tvRamUsedByApps = (TextView) findViewById(R.id.ram_used_by_apps);
        this.totallAppsTxt = (TextView) findViewById(R.id.running_apps_count_label);
        this.bottomToTopAnim = AnimationUtils.loadAnimation(this.context, R.anim.bottomtotopfast);
        this.BoostButton.setOnClickListener(this);
        this.backlay.setOnClickListener(this);
        this.SettingLay.setOnClickListener(this);
        this.ListRinning.setOnItemLongClickListener(this);
        this.ListRinning.setOnItemClickListener(this);
        if (this.pref.getBoolean("CheckAppStateBoost", true)) {
            functionForShowIgnorListDialog();
            this.editor.putBoolean("CheckAppStateBoost", false);
            this.editor.commit();
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.actualHeight = displayMetrics.heightPixels;
        available();
        this.handler.postDelayed(new Runnable() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.BoostingList.AnonymousClass1 */

            public void run() {
                BoostingList.this.progressWheelAnimation();
            }
        }, 500);
        new Handler().postDelayed(new Runnable() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.BoostingList.AnonymousClass2 */

            public void run() {
                BoostingList.this.rAdaptor = new Running_Adaptor(BoostingList.this.context);
                BoostingList.this.animationAdapter = new SwingBottomInAnimationAdapter(BoostingList.this.rAdaptor);
                BoostingList.this.animationAdapter.setAbsListView(BoostingList.this.ListRinning);
                BoostingList.this.animationAdapter.getViewAnimator().setInitialDelayMillis(BoostingList.this.INITIAL_DELAY_MILLIS);
                BoostingList.this.ListRinning.setAdapter((ListAdapter) BoostingList.this.animationAdapter);
            }
        }, 700);
        this.handler.postDelayed(new Runnable() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.BoostingList.AnonymousClass3 */

            public void run() {
                BoostingList.this.BoostButton.startAnimation(BoostingList.this.bottomToTopAnim);
                BoostingList.this.BoostButton.setVisibility(View.VISIBLE);
            }
        }, 2500);
    }

    /* access modifiers changed from: package-private */
    public void progressWheelAnimation() {
        ramused = getTotalMemory() - available();
        TextView textView = this.ramUsed;
        textView.setText("" + ramused);
        this.ramPercentage = (ramused * 100) / ((long) this.total_memory);
        TextView textView2 = this.ramFree;
        textView2.setText("" + availableMegs);
        if (((float) this.ramPercentage) > this.ramDonutProgress.getProgress()) {
            this.runner = this.uperRamPercentage;
        } else {
            this.runner = this.downRamPercentage;
        }
        Timer timer2 = new Timer();
        this.timer = timer2;
        timer2.schedule(new TimerTask() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.BoostingList.AnonymousClass4 */

            public void run() {
                BoostingList boostingList = BoostingList.this;
                boostingList.runOnUiThread(boostingList.runner);
            }
        }, 100, 50);
        if (Build.VERSION.SDK_INT >= 26) {
            TextView textView3 = this.tvRamUsedByApps;
            textView3.setText("" + MainActivity.finalRandomXForTotallSizeApi26 + "MB " + getString(R.string.canbeSave));
        } else {
            TextView textView4 = this.tvRamUsedByApps;
            textView4.setText(Utils.formatSize(SplashActivity.ramUsedByApps) + " " + getString(R.string.canbeSave));
        }
        TextView textView5 = this.totallAppsTxt;
        textView5.setText(getString(R.string.running_apps_label) + ": " + Utils.mApps.size());
        this.chkNoItmBoost = Utils.mApps.size();
    }

    public long getTotalMemory() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            String[] split = bufferedReader.readLine().split("\\s+");
            for (String str : split) {
            }
            this.total_memory = Integer.valueOf(split[1]).intValue() / 1024;
            bufferedReader.close();
            return (long) this.total_memory;
        } catch (IOException unused) {
            return -1;
        }
    }

    public long available() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE)).getMemoryInfo(memoryInfo);
        long j = memoryInfo.availMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        availableMegs = j;
        return j;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.backlay) {
            backLay();
        } else if (id == R.id.btn_boost_now) {
            this.handler.postDelayed(new Runnable() {
                /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.BoostingList.AnonymousClass8 */

                public void run() {
                    BoostingList.this.ExpandBoostButton.startAnimation(BoostingList.this.fadInAnim);
                    BoostingList.this.ExpandBoostButton.setVisibility(View.VISIBLE);
                }
            }, 500);
            this.handler.postDelayed(new Runnable() {
                /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.BoostingList.AnonymousClass9 */

                public void run() {
                    ani ani = new ani();
                    ani.setDuration(1000);
                    BoostingList.this.ExpandBoostButton.startAnimation(ani);
                }
            }, 600);
            this.handler.postDelayed(new Runnable() {
                /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.BoostingList.AnonymousClass10 */

                public void run() {
                    BoostingList.this.startActivity(new Intent(BoostingList.this.context, BoostActivity.class));
                    BoostingList.this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    BoostingList.this.finish();
                }
            }, 1700);
        } else if (id == R.id.settingLay) {
            PopupMenu popupMenu = new PopupMenu(this, this.SettingLay);
            popupMenu.getMenuInflater().inflate(R.menu.cooling_ignorelist, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.BoostingList.AnonymousClass7 */

                @Override // androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (menuItem.getItemId() != R.id.one) {
                        return true;
                    }
                    BoostingList.this.startActivity(new Intent(BoostingList.this.context, BoosterIgnorList.class));
                    BoostingList.this.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    BoostingList.this.finish();
                    return true;
                }
            });
            popupMenu.show();
        }
    }

    @Override // android.widget.AdapterView.OnItemLongClickListener
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long j) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("IgnorList");
        builder.setCancelable(true).setPositiveButton(getString(R.string.addToIgnoreList), new DialogInterface.OnClickListener() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.BoostingList.AnonymousClass11 */

            public void onClick(DialogInterface dialogInterface, int i) {
                IgnorList_DataBase ignorList_DataBase = new IgnorList_DataBase(BoostingList.this.context);
                if (Utils.mApps.size() != 0) {
                    try {
                        ignorList_DataBase.insertPakBooster(Utils.mApps.get(i).getPak());
                        Utils.mApps.remove(i);
                        BoostingList.this.rAdaptor.notifyDataSetChanged();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IndexOutOfBoundsException e2) {
                        e2.printStackTrace();
                    } catch (NullPointerException e3) {
                        e3.printStackTrace();
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
            }
        });
        builder.create().show();
        return true;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        try {
            if (Utils.mApps.size() > 0) {
                if (Utils.mApps.get(i).isChk()) {
                    Utils.mApps.get(i).setChk(false);
                    this.chkNoItmBoost--;
                } else {
                    Utils.mApps.get(i).setChk(true);
                    this.chkNoItmBoost++;
                }
            }
            this.totallAppsTxt.setText(getString(R.string.running_apps_label) + ":" + this.chkNoItmBoost);
            this.rAdaptor.notifyDataSetChanged();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    class ani extends Animation {
        public boolean willChangeBounds() {
            return true;
        }

        public ani() {
        }

        
        public void applyTransformation(float f, Transformation transformation) {
            int i = (int) (((float) BoostingList.this.initialHeight) * f);
            BoostingList.this.ExpandBoostButton.removeAllViews();
            BoostingList.this.ExpandBoostButton.getLayoutParams().height = i;
            BoostingList.this.ExpandBoostButton.requestLayout();
        }

        public void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
            BoostingList boostingList = BoostingList.this;
            boostingList.initialHeight = boostingList.actualHeight;
        }
    }

    public void onBackPressed() {
        backLay();
    }

    public void functionForShowIgnorListDialog() {
        final Dialog dialog = new Dialog(this.context);
        dialog.requestWindowFeature(1);
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.cooling_activity_dialog, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.textView29)).setTypeface(AppAnaylatics.RobotoLight);
        ((TextView) inflate.findViewById(R.id.textView28)).setTypeface(AppAnaylatics.RobotoRegular);
        ((RelativeLayout) inflate.findViewById(R.id.dialogButton)).setOnClickListener(new View.OnClickListener() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.BoostingList.AnonymousClass12 */

            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.setContentView(inflate);
        dialog.show();
    }

    public void backLay() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        finish();
    }

    
    public void onResume() {
        super.onResume();
    }
}
