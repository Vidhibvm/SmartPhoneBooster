package com.phonecleaner.fastbooster.safe;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.regex.Pattern;

public class CPU_Boost_Activity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private static final int INITIAL_DELAY_MILLIS = 400;
    private RelativeLayout AppLay;
    private RelativeLayout ExpandBoostLay;
    private RelativeLayout FirstLayout;

    private RelativeLayout SettingLay;
    int TempWheelArcProg = 0;
    int TempWheelProgress = 0;
    int actualHeight;
    private ActivityManager am;
    private SwingLeftInAnimationAdapter animationAdapter;

    public BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            if (CPU_Boost_Activity.this.valforReceView == 0) {
                CPU_Boost_Activity.this.temperature = (float) intent.getIntExtra("temperature", -1);
                CPU_Boost_Activity coolingActivity = CPU_Boost_Activity.this;
                coolingActivity.t = coolingActivity.temperature;
                CPU_Boost_Activity coolingActivity2 = CPU_Boost_Activity.this;
                coolingActivity2.t = coolingActivity2.temperature / 10.0f;
                CPU_Boost_Activity.this.editor.putFloat(util.TempAfterCoolVal, CPU_Boost_Activity.this.t);
                CPU_Boost_Activity.this.editor.commit();
                CPU_Boost_Activity coolingActivity3 = CPU_Boost_Activity.this;
                coolingActivity3.tempUnit = coolingActivity3.pref.getInt("tempunit", 0);
                if (CPU_Boost_Activity.this.t != 0.0f) {
                    CPU_Boost_Activity coolingActivity4 = CPU_Boost_Activity.this;
                    coolingActivity4.tempforn = ((coolingActivity4.t * 9.0f) / 5.0f) + 32.0f;
                    if (CPU_Boost_Activity.this.tempUnit == 2) {
                        CPU_Boost_Activity.this.tempunt.setText("°F");
                        CPU_Boost_Activity coolingActivity5 = CPU_Boost_Activity.this;
                        coolingActivity5.t = ((coolingActivity5.t * 9.0f) / 5.0f) + 32.0f;
                        CPU_Boost_Activity coolingActivity6 = CPU_Boost_Activity.this;
                        coolingActivity6.size = coolingActivity6.mFormatPercent.format((double) CPU_Boost_Activity.this.t);
                        CPU_Boost_Activity.this.temptxt.setText("0");
                    } else {
                        CPU_Boost_Activity.this.tempunt.setText("°C");
                        CPU_Boost_Activity coolingActivity7 = CPU_Boost_Activity.this;
                        coolingActivity7.size = coolingActivity7.mFormatPercent.format((double) CPU_Boost_Activity.this.t);
                        CPU_Boost_Activity.this.temptxt.setText("0");
                    }
                    if (CPU_Boost_Activity.this.size.contains(".")) {
                        String[] split = CPU_Boost_Activity.this.size.split(Pattern.quote("."));
                        CPU_Boost_Activity.this.tempSize = split[0];
                        String str = split[1];
                        CPU_Boost_Activity.this.tempSizedecimal = Integer.parseInt(str);
                        CPU_Boost_Activity coolingActivity8 = CPU_Boost_Activity.this;
                        coolingActivity8.tempAnimVal = Integer.parseInt(coolingActivity8.tempSize);
                    } else {
                        CPU_Boost_Activity coolingActivity9 = CPU_Boost_Activity.this;
                        coolingActivity9.tempSize = coolingActivity9.size;
                        CPU_Boost_Activity coolingActivity10 = CPU_Boost_Activity.this;
                        coolingActivity10.tempAnimVal = Integer.parseInt(coolingActivity10.tempSize);
                        CPU_Boost_Activity.this.tempSizedecimal = 0;
                    }
                }
                CPU_Boost_Activity coolingActivity11 = CPU_Boost_Activity.this;
                coolingActivity11.tempUnit = coolingActivity11.pref.getInt("tempunit", 0);
                if (CPU_Boost_Activity.this.tempUnit == 1) {
                    CPU_Boost_Activity.this.tempunt.setText("°C");
                } else if (CPU_Boost_Activity.this.tempUnit == 2) {
                    CPU_Boost_Activity.this.tempunt.setText("°F");
                } else {
                    CPU_Boost_Activity.this.tempunt.setText("°C");
                }
                CPU_Boost_Activity.this.valforReceView = 1;
            }
        }
    };
    public int chkNoItm = 0;
    Context context;
    private FloatingActionButton coolBtn;
    SharedPreferences.Editor editor;
    private Animation fadInAnim;
    private Animation fadeOutAnim;
    private Animation fadein;
    private RelativeLayout firstheadlay;
    private Animation growFromMiddleAnim;
    Handler handler = new Handler();
    private RelativeLayout headlay;
    int initialHeight;
    boolean isBackOfCardShowing = true;
    DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.US);
   // private DecimalFormat mFormat = new DecimalFormat("##,###,##0", this.symbols);
    private DecimalFormat mFormatPercent = new DecimalFormat("##0.0", this.symbols);
   // private DecimalFormat mFormatTime = new DecimalFormat("0.#", this.symbols);
    private TextView noOffApps;
    private float percentagevalue;
    SharedPreferences pref;
    final Runnable r2 = new Runnable() {
        private int gl;

        public void run() {
            CPU_Boost_Activity.this.wheelRunning = true;
            while (CPU_Boost_Activity.this.TempWheelProgress < 140) {
                CPU_Boost_Activity.this.TempWheelProgress++;
                CPU_Boost_Activity.this.runOnUiThread(new Runnable() {

                    public void run() {
                        TextView textView = CPU_Boost_Activity.this.temptxt;
                        textView.setText(CPU_Boost_Activity.this.TempWheelProgress + "." + CPU_Boost_Activity.this.tempSizedecimal);
                    }
                });
                if (CPU_Boost_Activity.this.TempWheelProgress < CPU_Boost_Activity.this.tempAnimVal) {
                    try {
                        Thread.sleep(36);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    return;
                }
            }
            CPU_Boost_Activity.this.wheelRunning = false;
        }
    };
    CPU_Adapter rAdaptor;
    private float ramUsed;
    private ListView runningRecycler;
    private ImageView shadowImg;
    private String size;

    private float t;
    private int tempAnimVal;
    private String tempSize;
    private int tempSizedecimal;
    private int tempUnit;
    public float tempValue = 0.0f;
    private float temperature;
    private float tempforn;
    private TextView temptxt;
    private TextView tempunt;
    private RelativeLayout tipLayheader;
    private TextView titleTxt;
    private Animation topBottom;
    private Animation topToBottomFadeInnAnim;
    private Animation topanim;
    private float total_memory;
    private boolean val;
    public int valforReceView = 0;
    int wheelProgress = 0;
    boolean wheelRunning;

    public interface ClickListener {
        void onClick(View view, int i);

        void onLongClick(View view, int i);
    }

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LoadActivityOnCreate();
    }

    public void LoadActivityOnCreate() {
        this.context = this;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.pref = defaultSharedPreferences;
        this.editor = defaultSharedPreferences.edit();
        this.pref.getLong("time", 0);
        System.currentTimeMillis();
        setContentView(R.layout.activity_cooling);


        colorSelection(getResources().getColor(R.color.appcolor));
        this.shadowImg = (ImageView) findViewById(R.id.shadowimg);
        this.runningRecycler = (ListView) findViewById(R.id.mainlist);
        this.fadInAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        this.ExpandBoostLay = (RelativeLayout) findViewById(R.id.animationlay);
        this.coolBtn = (FloatingActionButton) findViewById(R.id.coolbtn);
        this.temptxt = (TextView) findViewById(R.id.TempTxt);
        this.tempunt = (TextView) findViewById(R.id.textView2);
        this.noOffApps = (TextView) findViewById(R.id.noOffApps);
        this.headlay = (RelativeLayout) findViewById(R.id.subHeaderLay);
        this.firstheadlay = (RelativeLayout) findViewById(R.id.headerlay);
        this.SettingLay = (RelativeLayout) findViewById(R.id.settingLay);
        this.titleTxt = (TextView) findViewById(R.id.textView);
        this.growFromMiddleAnim = AnimationUtils.loadAnimation(this.context, R.anim.grow_from_middle);
        this.temptxt.setTypeface(MainApp.RobotoRegular);
        this.tempunt.setTypeface(MainApp.RobotoRegular);
        this.titleTxt.setTypeface(MainApp.RobotoRegular);
        this.context.registerReceiver(this.batteryInfoReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        this.runningRecycler.setOnItemClickListener(this);
        this.runningRecycler.setOnItemLongClickListener(this);
        this.chkNoItm = util.CoolerListmApps.size();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.actualHeight = displayMetrics.heightPixels;
        if (this.pref.getBoolean("CheckAppState", true)) {
            functionForShowIgnorListDialog();
            this.editor.putBoolean("CheckAppState", false);
            this.editor.commit();
        }
        try {
            TextView textView = this.noOffApps;
            textView.setText("" + util.mApps.size());
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        new Handler().postDelayed(new Runnable() {

            public void run() {
                new Thread(CPU_Boost_Activity.this.r2).start();
            }
        }, 500);
        new Handler().postDelayed(new Runnable() {

            public void run() {
                Animation loadAnimation = AnimationUtils.loadAnimation(CPU_Boost_Activity.this.context, R.anim.bottomtop);
                CPU_Boost_Activity.this.coolBtn.setVisibility(View.VISIBLE);
                CPU_Boost_Activity.this.coolBtn.startAnimation(loadAnimation);
            }
        }, 1000);
        this.FirstLayout = (RelativeLayout) findViewById(R.id.firstlay);
        this.fadein = AnimationUtils.loadAnimation(this.context, R.anim.fade_in);
        this.coolBtn.setOnClickListener(this);
        this.SettingLay.setOnClickListener(this);
        this.am = (ActivityManager) this.context.getSystemService(Context.ACTIVITY_SERVICE);
        this.rAdaptor = new CPU_Adapter(this.context);
        try {
            SwingLeftInAnimationAdapter swingLeftInAnimationAdapter = new SwingLeftInAnimationAdapter(this.rAdaptor);
            this.animationAdapter = swingLeftInAnimationAdapter;
            swingLeftInAnimationAdapter.setAbsListView(this.runningRecycler);
            this.animationAdapter.getViewAnimator().setInitialDelayMillis(INITIAL_DELAY_MILLIS);
            this.runningRecycler.setAdapter((ListAdapter) this.animationAdapter);
        } catch (IllegalStateException e3) {
            e3.printStackTrace();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.headlay.setElevation(10.0f);
        } else {
            this.shadowImg.setVisibility(View.VISIBLE);
        }
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.coolbtn:
            
                startActivity(new Intent(this.context, CPUActivity.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
                return;
            case R.id.settingLay:
                PopupMenu popupMenu = new PopupMenu(this, this.SettingLay);
                popupMenu.getMenuInflater().inflate(R.menu.cooling_ignorelist, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {

                            case R.id.one:
                               startActivity(new Intent(context, BatterySavingActivity.class));
                               overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                                finish();
                                return true;
                            default:
                                return true;
                        }
                    }
                });
                popupMenu.show();
                return;
           /* case R.id.smartDeviceWorksSeeMoreLay:
                forCallHelp();
                return;*/
            default:
                return;
        }
    }

    class animClass extends Animation {
        public boolean willChangeBounds() {
            return true;
        }

        public animClass() {
        }

        
        public void applyTransformation(float f, Transformation transformation) {
            int i = (int) (((float) CPU_Boost_Activity.this.initialHeight) * f);
            CPU_Boost_Activity.this.ExpandBoostLay.removeAllViews();
            CPU_Boost_Activity.this.ExpandBoostLay.getLayoutParams().height = i;
            CPU_Boost_Activity.this.ExpandBoostLay.requestLayout();
        }

        public void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
            CPU_Boost_Activity coolingActivity = CPU_Boost_Activity.this;
            coolingActivity.initialHeight = coolingActivity.actualHeight;
        }
    }

    @Override 
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        try {
            if (util.CoolerListmApps.size() > 0) {
                if (util.CoolerListmApps.get(i).isChk()) {
                    util.CoolerListmApps.get(i).setChk(false);
                    this.chkNoItm--;
                } else {
                    util.CoolerListmApps.get(i).setChk(true);
                    this.chkNoItm++;
                }
            }
            this.rAdaptor.notifyDataSetChanged();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    @Override 
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long j) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("IgnorList");
        builder.setCancelable(true).setPositiveButton(getString(R.string.addToIgnoreList), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int i) {
                IgnorAppList_DataBase ignorList_DataBase = new IgnorAppList_DataBase(CPU_Boost_Activity.this.context);
                if (util.CoolerListmApps.size() != 0) {
                    try {
                        ignorList_DataBase.insertPak(util.CoolerListmApps.get(i).getPak());
                        util.CoolerListmApps.remove(i);
                        CPU_Boost_Activity.this.rAdaptor.notifyDataSetChanged();
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

    public void onBackPressed() {
        startActivity(new Intent(this.context, StartActivity.class));
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        finish();
    }

    public float getTotalMemory() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            String[] split = bufferedReader.readLine().split("\\s+");
            for (String str : split) {
            }
            this.total_memory = (float) (Integer.valueOf(split[1]).intValue() / 1024);
            bufferedReader.close();
            return this.total_memory;
        } catch (IOException unused) {
            return -1.0f;
        }
    }

    public long available() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE)).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
    }

    
    public void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.batteryInfoReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void onResume() {
        super.onResume();
    }

    
    public void onStop() {
        super.onStop();
    }


    public void colorSelection(int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(i);
            window.setNavigationBarColor(i);
        }
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
}
