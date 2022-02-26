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
import android.net.Uri;
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
import com.phonecleaner.fastbooster.safe.R;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.regex.Pattern;

public class CoolingActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private static final int INITIAL_DELAY_MILLIS = 400;
    private RelativeLayout AppLay;
    private RelativeLayout ExpandBoostLay;
    private RelativeLayout FirstLayout;

    private RelativeLayout SettingLay;
    int TempWheelArcProg = 0;
    int TempWheelProgress = 0;
    int actualHeight;
    LinearLayout adContainer;
    private ActivityManager am;
    private SwingLeftInAnimationAdapter animationAdapter;
    RelativeLayout bannerAdLay;
    public BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            if (CoolingActivity.this.valforReceView == 0) {
                CoolingActivity.this.temperature = (float) intent.getIntExtra("temperature", -1);
                CoolingActivity coolingActivity = CoolingActivity.this;
                coolingActivity.t = coolingActivity.temperature;
                CoolingActivity coolingActivity2 = CoolingActivity.this;
                coolingActivity2.t = coolingActivity2.temperature / 10.0f;
                CoolingActivity.this.editor.putFloat(Utils.TempAfterCoolVal, CoolingActivity.this.t);
                CoolingActivity.this.editor.commit();
                CoolingActivity coolingActivity3 = CoolingActivity.this;
                coolingActivity3.tempUnit = coolingActivity3.pref.getInt("tempunit", 0);
                if (CoolingActivity.this.t != 0.0f) {
                    CoolingActivity coolingActivity4 = CoolingActivity.this;
                    coolingActivity4.tempforn = ((coolingActivity4.t * 9.0f) / 5.0f) + 32.0f;
                    if (CoolingActivity.this.tempUnit == 2) {
                        CoolingActivity.this.tempunt.setText("°F");
                        CoolingActivity coolingActivity5 = CoolingActivity.this;
                        coolingActivity5.t = ((coolingActivity5.t * 9.0f) / 5.0f) + 32.0f;
                        CoolingActivity coolingActivity6 = CoolingActivity.this;
                        coolingActivity6.size = coolingActivity6.mFormatPercent.format((double) CoolingActivity.this.t);
                        CoolingActivity.this.temptxt.setText("0");
                    } else {
                        CoolingActivity.this.tempunt.setText("°C");
                        CoolingActivity coolingActivity7 = CoolingActivity.this;
                        coolingActivity7.size = coolingActivity7.mFormatPercent.format((double) CoolingActivity.this.t);
                        CoolingActivity.this.temptxt.setText("0");
                    }
                    if (CoolingActivity.this.size.contains(".")) {
                        String[] split = CoolingActivity.this.size.split(Pattern.quote("."));
                        CoolingActivity.this.tempSize = split[0];
                        String str = split[1];
                        CoolingActivity.this.tempSizedecimal = Integer.parseInt(str);
                        CoolingActivity coolingActivity8 = CoolingActivity.this;
                        coolingActivity8.tempAnimVal = Integer.parseInt(coolingActivity8.tempSize);
                    } else {
                        CoolingActivity coolingActivity9 = CoolingActivity.this;
                        coolingActivity9.tempSize = coolingActivity9.size;
                        CoolingActivity coolingActivity10 = CoolingActivity.this;
                        coolingActivity10.tempAnimVal = Integer.parseInt(coolingActivity10.tempSize);
                        CoolingActivity.this.tempSizedecimal = 0;
                    }
                }
                CoolingActivity coolingActivity11 = CoolingActivity.this;
                coolingActivity11.tempUnit = coolingActivity11.pref.getInt("tempunit", 0);
                if (CoolingActivity.this.tempUnit == 1) {
                    CoolingActivity.this.tempunt.setText("°C");
                } else if (CoolingActivity.this.tempUnit == 2) {
                    CoolingActivity.this.tempunt.setText("°F");
                } else {
                    CoolingActivity.this.tempunt.setText("°C");
                }
                CoolingActivity.this.valforReceView = 1;
            }
        }
    };
    public int chkNoItm = 0;
    Context context;
    private FloatingActionButton coolBtn;
    RelativeLayout dummyBannerContainer;
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
            CoolingActivity.this.wheelRunning = true;
            while (CoolingActivity.this.TempWheelProgress < 140) {
                CoolingActivity.this.TempWheelProgress++;
                CoolingActivity.this.runOnUiThread(new Runnable() {

                    public void run() {
                        TextView textView = CoolingActivity.this.temptxt;
                        textView.setText(CoolingActivity.this.TempWheelProgress + "." + CoolingActivity.this.tempSizedecimal);
                    }
                });
                if (CoolingActivity.this.TempWheelProgress < CoolingActivity.this.tempAnimVal) {
                    try {
                        Thread.sleep(36);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    return;
                }
            }
            CoolingActivity.this.wheelRunning = false;
        }
    };
    Cooling_Running_Adaptor rAdaptor;
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
        this.bannerAdLay = (RelativeLayout) findViewById(R.id.bannerAdLay);
        this.dummyBannerContainer = (RelativeLayout) findViewById(R.id.dummy_banner_container);
        this.adContainer = (LinearLayout) findViewById(R.id.banner_container);

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
        this.temptxt.setTypeface(AppAnaylatics.RobotoRegular);
        this.tempunt.setTypeface(AppAnaylatics.RobotoRegular);
        this.titleTxt.setTypeface(AppAnaylatics.RobotoRegular);
        this.context.registerReceiver(this.batteryInfoReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        this.runningRecycler.setOnItemClickListener(this);
        this.runningRecycler.setOnItemLongClickListener(this);
        this.chkNoItm = Utils.CoolerListmApps.size();
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
            textView.setText("" + Utils.mApps.size());
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        new Handler().postDelayed(new Runnable() {

            public void run() {
                new Thread(CoolingActivity.this.r2).start();
            }
        }, 500);
        new Handler().postDelayed(new Runnable() {

            public void run() {
                Animation loadAnimation = AnimationUtils.loadAnimation(CoolingActivity.this.context, R.anim.bottomtop);
                CoolingActivity.this.coolBtn.setVisibility(View.VISIBLE);
                CoolingActivity.this.coolBtn.startAnimation(loadAnimation);
            }
        }, 1000);
        this.FirstLayout = (RelativeLayout) findViewById(R.id.firstlay);
        this.fadein = AnimationUtils.loadAnimation(this.context, R.anim.fade_in);
        this.coolBtn.setOnClickListener(this);
        this.SettingLay.setOnClickListener(this);
        this.am = (ActivityManager) this.context.getSystemService(Context.ACTIVITY_SERVICE);
        this.rAdaptor = new Cooling_Running_Adaptor(this.context);
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
         /*   case R.id.causeOverHeatSeeMoreLay:
                forCallHelp();
                return;
            case R.id.damageOverHeatSeeMoreLay:
                forCallHelp();
                return;*/
            case R.id.coolbtn:
             /*   if (this.isShowingHotLayout) {
                    startActivity(new Intent(this.context, HomeScreen.class));
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    finish();
                    return;
                }*/

                startActivity(new Intent(this.context, CoolerActivity.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
                return;
            /*case R.id.phoneTempSeeMoreLay:
                forCallHelp();
                return;
            case R.id.powerBtn:
                powerSaverMode();
                return;*/
            case R.id.settingLay:
                PopupMenu popupMenu = new PopupMenu(this, this.SettingLay);
                popupMenu.getMenuInflater().inflate(R.menu.cooling_ignorelist, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    /* class com.protrustedapps.coolmaster.supercooling.phone.cooler.CoolingTips.AnonymousClass2 */

                    @Override // androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {

                            case R.id.one:
                               startActivity(new Intent(context, SaverModeActivity.class));
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
            int i = (int) (((float) CoolingActivity.this.initialHeight) * f);
            CoolingActivity.this.ExpandBoostLay.removeAllViews();
            CoolingActivity.this.ExpandBoostLay.getLayoutParams().height = i;
            CoolingActivity.this.ExpandBoostLay.requestLayout();
        }

        public void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
            CoolingActivity coolingActivity = CoolingActivity.this;
            coolingActivity.initialHeight = coolingActivity.actualHeight;
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        try {
            if (Utils.CoolerListmApps.size() > 0) {
                if (Utils.CoolerListmApps.get(i).isChk()) {
                    Utils.CoolerListmApps.get(i).setChk(false);
                    this.chkNoItm--;
                } else {
                    Utils.CoolerListmApps.get(i).setChk(true);
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

    @Override // android.widget.AdapterView.OnItemLongClickListener
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long j) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("IgnorList");
        builder.setCancelable(true).setPositiveButton(getString(R.string.addToIgnoreList), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int i) {
                IgnorList_DataBase ignorList_DataBase = new IgnorList_DataBase(CoolingActivity.this.context);
                if (Utils.CoolerListmApps.size() != 0) {
                    try {
                        ignorList_DataBase.insertPak(Utils.CoolerListmApps.get(i).getPak());
                        Utils.CoolerListmApps.remove(i);
                        CoolingActivity.this.rAdaptor.notifyDataSetChanged();
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
        startActivity(new Intent(this.context, MainActivity.class));
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
        ((TextView) inflate.findViewById(R.id.textView29)).setTypeface(AppAnaylatics.RobotoLight);
        ((TextView) inflate.findViewById(R.id.textView28)).setTypeface(AppAnaylatics.RobotoRegular);
        ((RelativeLayout) inflate.findViewById(R.id.dialogButton)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.setContentView(inflate);
        dialog.show();
    }
}
