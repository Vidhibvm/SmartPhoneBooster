package com.phonecleaner.fastbooster.safe;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class CleanerList extends Activity implements View.OnClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
    public static long availableMegs;
    public static long ramused;
    private FloatingActionButton BoostButton;
    private RelativeLayout ExpandBoostButton;
    private int INITIAL_DELAY_MILLIS = 300;
    private ListView ListRinning;
    private RelativeLayout SettingLay;
    int actualHeight;
    private SwingBottomInAnimationAdapter animationAdapter;
    private RelativeLayout backlay;

    private Animation bottomToTopAnim;
    private Animation bottomtoCenterAnim;
    private int checkBack;
    private int chkNoItmBoost;
    Context context;
    final Runnable downRamPercentage = new Runnable() {

        public void run() {
            CleanerList boostingList = CleanerList.this;
            boostingList.progress = boostingList.ramDonutProgress.getProgress() - 1.0f;
            if (CleanerList.this.progress >= ((float) CleanerList.this.ramPercentage)) {
                CleanerList.this.ramDonutProgress.setProgress((int) CleanerList.this.progress);
                TextView textView = CleanerList.this.ramPersentage;
                textView.setText("" + ((int) CleanerList.this.progress));
                return;
            }
            CleanerList.this.timer.cancel();
        }
    };
    SharedPreferences.Editor editor;
    private Animation fadInAnim;
    Handler handler = new Handler();
    int initialHeight;
    SharedPreferences pref;
    private float progress = 0.0f;
    Running_Adapterr rAdaptor;
    private RoundCornerProgressBar ramDonutProgress;
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

        public void run() {
            CleanerList boostingList = CleanerList.this;
            boostingList.progress = boostingList.ramDonutProgress.getProgress() + 1.0f;
            if (CleanerList.this.progress <= ((float) CleanerList.this.ramPercentage)) {
                CleanerList.this.ramDonutProgress.setProgress((int) CleanerList.this.progress);
                TextView textView = CleanerList.this.ramPersentage;
                textView.setText("" + ((int) CleanerList.this.progress));
                return;
            }
            CleanerList.this.timer.cancel();
        }
    };

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_boosting_list);
        this.context = this;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.pref = defaultSharedPreferences;
        this.editor = defaultSharedPreferences.edit();


        this.ramDonutProgress = (RoundCornerProgressBar) findViewById(R.id.ram_donut_progress);

        this.ramDonutProgress.setProgressColor(Color.parseColor("#ffffff"));
        this.ramDonutProgress.setProgressBackgroundColor(Color.parseColor("#4F3B73"));

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

            public void run() {
                CleanerList.this.progressWheelAnimation();
            }
        }, 500);
        new Handler().postDelayed(new Runnable() {

            public void run() {
                CleanerList.this.rAdaptor = new Running_Adapterr(CleanerList.this.context);
                CleanerList.this.animationAdapter = new SwingBottomInAnimationAdapter(CleanerList.this.rAdaptor);
                CleanerList.this.animationAdapter.setAbsListView(CleanerList.this.ListRinning);
                CleanerList.this.animationAdapter.getViewAnimator().setInitialDelayMillis(CleanerList.this.INITIAL_DELAY_MILLIS);
                CleanerList.this.ListRinning.setAdapter((ListAdapter) CleanerList.this.animationAdapter);
            }
        }, 700);
        this.handler.postDelayed(new Runnable() {

            public void run() {
                CleanerList.this.BoostButton.startAnimation(CleanerList.this.bottomToTopAnim);
                CleanerList.this.BoostButton.setVisibility(View.VISIBLE);
            }
        }, 2500);
    }

   
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

            public void run() {
                CleanerList boostingList = CleanerList.this;
                boostingList.runOnUiThread(boostingList.runner);
            }
        }, 100, 50);
        if (Build.VERSION.SDK_INT >= 26) {
            TextView textView3 = this.tvRamUsedByApps;
            textView3.setText("" + StartActivity.finalRandomXForTotallSizeApi26 + "MB " + getString(R.string.canbeSave));
        } else {
            TextView textView4 = this.tvRamUsedByApps;
            textView4.setText(util.formatSize(WelcomeActivity.ramUsedByApps) + " " + getString(R.string.canbeSave));
        }
        TextView textView5 = this.totallAppsTxt;
        textView5.setText(getString(R.string.running_apps_label) + ": " + util.mApps.size());
        this.chkNoItmBoost = util.mApps.size();
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

                public void run() {
                    CleanerList.this.ExpandBoostButton.startAnimation(CleanerList.this.fadInAnim);
                    CleanerList.this.ExpandBoostButton.setVisibility(View.VISIBLE);
                }
            }, 500);
            this.handler.postDelayed(new Runnable() {

                public void run() {
                    ani ani = new ani();
                    ani.setDuration(1000);
                    CleanerList.this.ExpandBoostButton.startAnimation(ani);
                }
            }, 600);
            this.handler.postDelayed(new Runnable() {

                public void run() {
                    CleanerList.this.startActivity(new Intent(CleanerList.this.context, CleanerActivity.class));
                    CleanerList.this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    CleanerList.this.finish();
                }
            }, 1700);
        } else if (id == R.id.settingLay) {
            PopupMenu popupMenu = new PopupMenu(this, this.SettingLay);
            popupMenu.getMenuInflater().inflate(R.menu.cooling_ignorelist, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (menuItem.getItemId() != R.id.one) {
                        return true;
                    }
                    CleanerList.this.startActivity(new Intent(CleanerList.this.context, appIgnorList.class));
                    CleanerList.this.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    CleanerList.this.finish();
                    return true;
                }
            });
            popupMenu.show();
        }
    }

    @Override 
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long j) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("IgnorList");
        builder.setCancelable(true).setPositiveButton(getString(R.string.addToIgnoreList), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int i) {
                IgnorAppList_DataBase ignorList_DataBase = new IgnorAppList_DataBase(CleanerList.this.context);
                if (util.mApps.size() != 0) {
                    try {
                        ignorList_DataBase.insertPakBooster(util.mApps.get(i).getPak());
                        util.mApps.remove(i);
                        CleanerList.this.rAdaptor.notifyDataSetChanged();
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

    @Override 
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        try {
            if (util.mApps.size() > 0) {
                if (util.mApps.get(i).isChk()) {
                    util.mApps.get(i).setChk(false);
                    this.chkNoItmBoost--;
                } else {
                    util.mApps.get(i).setChk(true);
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
            int i = (int) (((float) CleanerList.this.initialHeight) * f);
            CleanerList.this.ExpandBoostButton.removeAllViews();
            CleanerList.this.ExpandBoostButton.getLayoutParams().height = i;
            CleanerList.this.ExpandBoostButton.requestLayout();
        }

        public void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
            CleanerList boostingList = CleanerList.this;
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
        ((TextView) inflate.findViewById(R.id.textView29)).setTypeface(MainApp.RobotoLight);
        ((TextView) inflate.findViewById(R.id.textView28)).setTypeface(MainApp.RobotoRegular);
        ((RelativeLayout) inflate.findViewById(R.id.dialogButton)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.setContentView(inflate);
        dialog.show();
    }

    public void backLay() {
        startActivity(new Intent(this, StartActivity.class));
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        finish();
    }

    
    public void onResume() {
        super.onResume();
    }
}
