package com.phonecleaner.fastbooster.safe;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class BatterySavingActivity extends Activity implements View.OnClickListener {
    public static final int MY_PERMISSIONS_REQUEST_WRITE_CALENDAR = 123;
    private TextView BatteryFullTxt;
    public int CheckRadioModeSelected = 0;

    private RadioButton MaxpowerSaverRadio;
    private RelativeLayout PowerSaverMainLay;
    private TextView RemainingHours;
    private TextView RemainingHours2;
    private TextView RemainingMintues;
    private TextView RemainingMintues2;
    private RelativeLayout SkipDoneLay;
    public Runnable appearChannelLay = new Runnable() {

        public void run() {
            BatterySavingActivity.this.channelLay.clearAnimation();
            BatterySavingActivity.this.slideLeftAnim = null;
            BatterySavingActivity saverModeActivity = BatterySavingActivity.this;
            saverModeActivity.slideLeftAnim = AnimationUtils.loadAnimation(saverModeActivity, R.anim.slide_left);
            BatterySavingActivity.this.channelLay.setAnimation(BatterySavingActivity.this.slideLeftAnim);
            BatterySavingActivity.this.channelLay.setVisibility(View.VISIBLE);
            BatterySavingActivity.this.handler.postDelayed(BatterySavingActivity.this.disappearChannelLay, 1500);
        }
    };
    private RelativeLayout backlay;
    double bat;
    public BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {

        private int n;
        private int u;


        @SuppressLint("ResourceType")
        public void onReceive(Context var1_1, Intent var2_2) {
            String var26_50;
            String var30_51;
            int var31_60;
            Throwable var33_64;
            block42:
            {
                String var29_55 = null;
                block45:
                {
                    Throwable var37_54 = null;
                    block41:
                    {
                        String[] var25_49;
                        block40:
                        {
                            block44:
                            {
                                block43:
                                {
                                    BatterySavingActivity.access$702(BatterySavingActivity.this, var2_2.getIntExtra("temperature", 0));
                                    BatterySavingActivity var4_3 = BatterySavingActivity.this;
                                    BatterySavingActivity.access$802(var4_3, BatterySavingActivity.access$700(var4_3) / 10);
                                    BatterySavingActivity.access$902(BatterySavingActivity.this, var2_2.getStringExtra("technology"));
                                    BatterySavingActivity.access$1002(BatterySavingActivity.this, var2_2.getIntExtra("voltage", 0));
                                    int var8_4 = var2_2.getIntExtra("level", 0);
                                    Locale var9_5 = Locale.US;
                                    Object[] var10_6 = new Object[]{var8_4};
                                    String var11_7 = String.format((Locale) var9_5, (String) "%s", (Object[]) var10_6);
                                    BatterySavingActivity.access$1102(BatterySavingActivity.this, Integer.parseInt((String) var11_7));
                                    TextView var13_8 = BatterySavingActivity.access$1200(BatterySavingActivity.this);
                                    StringBuilder var14_9 = new StringBuilder();
                                    var14_9.append(BatterySavingActivity.this.getString(R.string.batteryLife));
                                    var14_9.append(" ");
                                    var14_9.append(var8_4);
                                    var14_9.append("%");
                                    var13_8.setText((CharSequence) var14_9.toString());
                                    if (var8_4 < 21) {
                                        BatterySavingActivity.access$1300(BatterySavingActivity.this).setBackgroundResource(R.drawable.battery1);
                                    } else if (var8_4 > 20 && var8_4 < 51) {
                                        BatterySavingActivity.access$1300(BatterySavingActivity.this).setBackgroundResource(R.drawable.battery2);
                                    } else if (var8_4 > 50 && var8_4 < 81) {
                                        BatterySavingActivity.access$1300(BatterySavingActivity.this).setBackgroundResource(R.drawable.battery3);
                                    } else {
                                        BatterySavingActivity.access$1300(BatterySavingActivity.this).setBackgroundResource(R.drawable.battery4);
                                    }
                                    int var19_10 = var2_2.getIntExtra("status", 0);
                                    boolean var20_11 = var19_10 == 2 || var19_10 == 5;
                                    if (!var20_11) {
                                        if (var20_11 != false) return;
                                        BatterySavingActivity.access$1900(BatterySavingActivity.this).setVisibility(4);
                                        BatterySavingActivity.access$1400(BatterySavingActivity.this).setVisibility(0);
                                        BatterySavingActivity.access$1400(BatterySavingActivity.this).setText(R.string.remaining);
                                        BatterySavingActivity.this.l = 60.0;
                                        BatterySavingActivity.this.bat = 14 * BatterySavingActivity.access$1100(BatterySavingActivity.this);
                                        if (BatterySavingActivity.access$1100(BatterySavingActivity.this) > 15 || BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 10)
                                            break block43;
                                        BatterySavingActivity.this.bat = 7 * BatterySavingActivity.access$1100(BatterySavingActivity.this);
                                        return;
                                    }
                                    BatterySavingActivity.access$1400(BatterySavingActivity.this).setText(R.string.chargeTime);
                                    if (BatterySavingActivity.access$1100(BatterySavingActivity.this) > 99) {
                                        BatterySavingActivity.access$1900(BatterySavingActivity.this).setVisibility(0);
                                        BatterySavingActivity.access$1900(BatterySavingActivity.this).setText(R.string.batteryfull);
                                        BatterySavingActivity.access$1500(BatterySavingActivity.this).setVisibility(4);
                                        BatterySavingActivity.access$1600(BatterySavingActivity.this).setVisibility(4);
                                        BatterySavingActivity.access$1800(BatterySavingActivity.this).setVisibility(4);
                                        BatterySavingActivity.access$1700(BatterySavingActivity.this).setVisibility(4);
                                        BatterySavingActivity.access$1400(BatterySavingActivity.this).setVisibility(4);
                                        return;
                                    }
                                    BatterySavingActivity.this.btime = 1.5;
                                    BatterySavingActivity.this.timecharg = 100 - BatterySavingActivity.access$1100(BatterySavingActivity.this);
                                    double var92_12 = BatterySavingActivity.this.timecharg * BatterySavingActivity.this.btime;
                                    Locale var94_13 = Locale.US;
                                    Object[] var95_14 = new Object[]{var92_12};
                                    int var96_15 = Integer.parseInt((String) String.format((Locale) var94_13, (String) "%.0f", (Object[]) var95_14));
                                    if (var96_15 < 60) {
                                        BatterySavingActivity.this.concateZero(BatterySavingActivity.access$1600(BatterySavingActivity.this), var96_15);
                                        BatterySavingActivity.access$1500(BatterySavingActivity.this).setText((CharSequence) "0");
                                        BatterySavingActivity.access$1500(BatterySavingActivity.this).setVisibility(0);
                                        BatterySavingActivity.access$1800(BatterySavingActivity.this).setVisibility(0);
                                        return;
                                    }
                                    int var97_16 = var96_15 / 60;
                                    BatterySavingActivity.this.concateZero(BatterySavingActivity.access$1500(BatterySavingActivity.this), var97_16);
                                    {

                                    }
                                    int var98_17 = var96_15 % 60;
                                    if (var98_17 <= 0) {
                                        BatterySavingActivity.access$1600(BatterySavingActivity.this).setVisibility(4);
                                        BatterySavingActivity.access$1700(BatterySavingActivity.this).setVisibility(4);
                                        return;
                                    }
                                    BatterySavingActivity.this.concateZero(BatterySavingActivity.access$1600(BatterySavingActivity.this), var98_17);
                                    return;


                                }
                                if (BatterySavingActivity.access$1100(BatterySavingActivity.this) > 20 || BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 15) {
                                    if (BatterySavingActivity.access$1100(BatterySavingActivity.this) > 10 || BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 5) {
                                        if (BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 5) {
                                            BatterySavingActivity.this.bat = 3 * BatterySavingActivity.access$1100(BatterySavingActivity.this);
                                        }
                                    }
                                    BatterySavingActivity var78_22 = BatterySavingActivity.this;
                                    int var79_23 = BatterySavingActivity.access$1100(BatterySavingActivity.this);
                                    int var80_24 = var79_23;
                                    Double.isNaN((double) var80_24);
                                    double var83_25 = var80_24 * 3.8;
                                    var78_22.bat = var83_25;
                                    break block44;
                                }
                                BatterySavingActivity var85_18 = BatterySavingActivity.this;
                                int var86_19 = BatterySavingActivity.access$1100(BatterySavingActivity.this);
                                int var87_20 = var86_19;
                                Double.isNaN((double) var87_20);
                                double var90_21 = var87_20 * 6.6;
                                var85_18.bat = var90_21;
                                //   ** GOTO lbl95
                               /* lbl84: // 1 sources:
                                if (SaverModeActivity.access$1100(SaverModeActivity.this) > 10 || SaverModeActivity.access$1100(SaverModeActivity.this) <= 5){
                                    if (SaverModeActivity.access$1100(SaverModeActivity.this) <= 5) {
                                        SaverModeActivity.this.bat = 3 * SaverModeActivity.access$1100(SaverModeActivity.this);
                                    }
                                }
                                SaverModeActivity var78_22 = SaverModeActivity.this;
                                int var79_23 = SaverModeActivity.access$1100(SaverModeActivity.this);
                                int var80_24 = var79_23;
                                Double.isNaN((double)var80_24);
                                double var83_25 = var80_24 * 3.8;
                                var78_22.bat = var83_25;
                                break block44;*/

                            }
                            if (util.AutoBrightnessCheck(var1_1) == 1) {
                                BatterySavingActivity.this.bat = BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 10 && BatterySavingActivity.access$1100(BatterySavingActivity.this) > 5 ? 2.5 + BatterySavingActivity.this.bat : (BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 5 ? 10.0 + BatterySavingActivity.this.bat : (BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 20 && BatterySavingActivity.access$1100(BatterySavingActivity.this) > 10 ? 50.0 + BatterySavingActivity.this.bat : 90.0 + BatterySavingActivity.this.bat));
                            }
                            if (util.WifiStatus(var1_1).isConnected()) {
                                BatterySavingActivity.this.bat = BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 10 && BatterySavingActivity.access$1100(BatterySavingActivity.this) > 5 ? (BatterySavingActivity.this.bat -= 3.5) : (BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 5 ? (BatterySavingActivity.this.bat -= 0.5) : (BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 20 && BatterySavingActivity.access$1100(BatterySavingActivity.this) > 10 ? (BatterySavingActivity.this.bat -= 4.0) : (BatterySavingActivity.this.bat -= 50.0)));
                            }
                            if (util.BluetoothStatus().isEnabled()) {
                                BatterySavingActivity.this.bat = BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 10 && BatterySavingActivity.access$1100(BatterySavingActivity.this) > 5 ? (BatterySavingActivity.this.bat -= 2.5) : (BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 5 ? (BatterySavingActivity.this.bat -= 0.5) : (BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 20 && BatterySavingActivity.access$1100(BatterySavingActivity.this) > 10 ? (BatterySavingActivity.this.bat -= 10.0) : (BatterySavingActivity.this.bat -= 30.0)));
                            }
                            if (util.GPSStatus(var1_1).isProviderEnabled("gps")) {
                                BatterySavingActivity.this.bat = BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 10 && BatterySavingActivity.access$1100(BatterySavingActivity.this) > 5 ? (BatterySavingActivity.this.bat -= 3.5) : (BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 5 ? (BatterySavingActivity.this.bat -= 0.5) : (BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 20 && BatterySavingActivity.access$1100(BatterySavingActivity.this) > 10 ? (BatterySavingActivity.this.bat -= 22.0) : (BatterySavingActivity.this.bat -= 107.0)));
                            }
                            if (util.isMobileDataEnable(var1_1)) {
                                if (BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 10 && BatterySavingActivity.access$1100(BatterySavingActivity.this) > 5) {
                                    BatterySavingActivity.this.bat -= 2.5;
                                } else if (BatterySavingActivity.access$1100(BatterySavingActivity.this) > 5) {
                                    BatterySavingActivity.this.bat = BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 20 && BatterySavingActivity.access$1100(BatterySavingActivity.this) > 10 ? (BatterySavingActivity.this.bat -= 32.0) : (BatterySavingActivity.this.bat -= 90.0);
                                }
                            }
                            if (util.AirPlaneModeStatus(var1_1)) {
                                BatterySavingActivity.this.bat = BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 10 && BatterySavingActivity.access$1100(BatterySavingActivity.this) > 5 ? (BatterySavingActivity.this.bat -= 2.5) : (BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 5 ? (BatterySavingActivity.this.bat -= 0.5) : (BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 20 && BatterySavingActivity.access$1100(BatterySavingActivity.this) > 10 ? (BatterySavingActivity.this.bat -= 4.0) : 150.0 + BatterySavingActivity.this.bat));
                            }
                            if (util.SoundModeStatus(var1_1).getRingerMode() == 2) {
                                BatterySavingActivity.this.bat = BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 10 && BatterySavingActivity.access$1100(BatterySavingActivity.this) > 5 ? (BatterySavingActivity.this.bat -= 2.0) : (BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 5 ? (BatterySavingActivity.this.bat -= 0.5) : (BatterySavingActivity.access$1100(BatterySavingActivity.this) <= 20 && BatterySavingActivity.access$1100(BatterySavingActivity.this) > 10 ? (BatterySavingActivity.this.bat -= 12.0) : (BatterySavingActivity.this.bat -= 37.0)));
                            }
                            double var21_46;
                            if (BatterySavingActivity.access$1100(BatterySavingActivity.this) < 20) {
                                if (BatterySavingActivity.access$1100(BatterySavingActivity.this) < 10 || BatterySavingActivity.access$1100(BatterySavingActivity.this) >= 20) {
                                    var21_46 = BatterySavingActivity.this.bat / BatterySavingActivity.this.l;
                                    Locale var23_47 = Locale.US;
                                    Object[] var24_48 = new Object[]{var21_46};
                                    var25_49 = util.size(String.format((Locale) var23_47, (String) "%.2f", (Object[]) var24_48));
                                    var26_50 = null;
                                    if (var25_49.length <= 1) break block40;
                                    var30_51 = var25_49[0];
                                    var26_50 = var25_49[1];
                                    this.n = Integer.parseInt((String) var26_50);
                                    if ((var31_60 = Integer.parseInt((String) var30_51)) <= 0)
                                        break block42;
                                    BatterySavingActivity.access$1500(BatterySavingActivity.this).setVisibility(0);
                                    BatterySavingActivity.access$1800(BatterySavingActivity.this).setVisibility(0);
                                }
                                int var42_36 = 2 * util.counter;
                                BatterySavingActivity var43_37 = BatterySavingActivity.this;
                                double var44_38 = BatterySavingActivity.this.bat;
                                int var46_39 = var42_36;
                                Double.isNaN((double) var46_39);
                                double var49_40 = var44_38 - var46_39;
                                var43_37.bat = var49_40;
                                int var51_41 = 2 * util.heavyappcounter;
                                BatterySavingActivity var52_42 = BatterySavingActivity.this;
                                double var53_43 = BatterySavingActivity.this.bat;
                                int var55_44 = var51_41;
                                Double.isNaN((double) var55_44);
                                double var58_45 = var53_43 - var55_44;
                                var52_42.bat = var58_45;
                            }
                            BatterySavingActivity var60_26 = BatterySavingActivity.this;
                            double var61_27 = BatterySavingActivity.this.bat;
                            int var63_28 = util.timedrain;
                            int var64_29 = var63_28;
                            Double.isNaN((double) var64_29);
                            double var67_30 = var61_27 - var64_29;
                            var60_26.bat = var67_30;
                            BatterySavingActivity var69_31 = BatterySavingActivity.this;
                            double var70_32 = BatterySavingActivity.this.bat;
                            int var72_33 = util.heavyapp;
                            int var73_34 = var72_33;
                            Double.isNaN((double) var73_34);
                            double var76_35 = var70_32 - var73_34;
                            var69_31.bat = var76_35;
                            lbl148:
                            // 2 sources:
                            var21_46 = BatterySavingActivity.this.bat / BatterySavingActivity.this.l;
                            Locale var23_47 = Locale.US;
                            Object[] var24_48 = new Object[]{var21_46};
                            var25_49 = util.size(String.format((Locale) var23_47, (String) "%.2f", (Object[]) var24_48));
                            var26_50 = null;
                            if (var25_49.length <= 1) break block40;
                            var30_51 = var25_49[0];
                            var26_50 = var25_49[1];
                            this.n = Integer.parseInt((String) var26_50);
                            if ((var31_60 = Integer.parseInt((String) var30_51)) <= 0)
                                break block42;
                            BatterySavingActivity.access$1500(BatterySavingActivity.this).setVisibility(0);
                            BatterySavingActivity.access$1800(BatterySavingActivity.this).setVisibility(0);
                        }
                        var30_51 = var25_49[0];
                        var26_50 = "00";
                        if ((var31_60 = Integer.parseInt((String) var30_51)) <= 0) break block42;
                        BatterySavingActivity.access$1500(BatterySavingActivity.this).setVisibility(0);
                        BatterySavingActivity.access$1800(BatterySavingActivity.this).setVisibility(0);


                    }
                    var37_54.printStackTrace();
                    break block45;


                }
                var30_51 = var26_50;
                var26_50 = var29_55;

            }
            try {
                if (this.n >= 60 && this.n != 0) {
                    int var34_61 = this.n / 60;
                    this.u = this.n % 60;
                    int var35_62 = var31_60 + var34_61;
                    BatterySavingActivity.this.concateZero(BatterySavingActivity.access$1500(BatterySavingActivity.this), var35_62);
                    if (this.u > 0) {
                        BatterySavingActivity.access$1600(BatterySavingActivity.this).setVisibility(0);
                        BatterySavingActivity.access$1700(BatterySavingActivity.this).setVisibility(0);
                    }
                    BatterySavingActivity.this.concateZero(BatterySavingActivity.access$1600(BatterySavingActivity.this), this.u);
                    return;
                }
                BatterySavingActivity.this.concateZero(BatterySavingActivity.access$1500(BatterySavingActivity.this), Integer.valueOf((String) var30_51));
                if (this.n != 0) {
                    BatterySavingActivity.access$1600(BatterySavingActivity.this).setVisibility(0);
                    BatterySavingActivity.access$1700(BatterySavingActivity.this).setVisibility(0);
                }
                BatterySavingActivity.this.concateZero(BatterySavingActivity.access$1600(BatterySavingActivity.this), Integer.parseInt((String) var26_50));
                return;
            } catch (Exception var32_63) {
                var32_63.printStackTrace();
                return;
            }
        }
    };
    private ImageView batteryInnerImg;
    private TextView batteryLifeTxt;
    public int blu;
    private ImageView blueImg;
    private RelativeLayout blueToothLay;
    private BluetoothAdapter bluetoothStatus;
    public int brig;
    private ImageView brightnessImg;
    private RelativeLayout brightnessLay;
    double btime;
    private RelativeLayout channelLay;
    private ImageView channelLogo;
    public Context context;
    private TextView controlText;
    private TextView controlTextValue;
    public Runnable disappearChannelLay = new Runnable() {

        public void run() {
            BatterySavingActivity.this.channelLogo.setBackgroundResource(0);
            BatterySavingActivity.this.channelLay.clearAnimation();
            BatterySavingActivity.this.controlText.setText("");
            BatterySavingActivity.this.controlTextValue.setText("");
            BatterySavingActivity.this.slideRightAnim = null;
            BatterySavingActivity saverModeActivity = BatterySavingActivity.this;
            saverModeActivity.slideRightAnim = AnimationUtils.loadAnimation(saverModeActivity, R.anim.slide_right);
            BatterySavingActivity.this.channelLay.setAnimation(BatterySavingActivity.this.slideRightAnim);
            BatterySavingActivity.this.channelLay.setVisibility(View.GONE);
        }
    };
    SharedPreferences.Editor editor;
    private int finalTemp;
    Handler handler = new Handler();
    private RelativeLayout headerLay;
    double l;
    private int level;
    private RelativeLayout nativeContainerLay;
    private RelativeLayout nativeDummyContainer;
    private Button nextBtn;
    double normal;
    private RadioButton powerSaverRadio;
    SharedPreferences pref;
    public int rotat = 0;
    private Button skipBtn;
    Animation slideLeftAnim;
    Animation slideRightAnim;
    public int sond;
    private ImageView soundImg;
    private RelativeLayout soundLay;
    private String technology;
    private int temperature;
    public int tim;
    private TextView time;
    private ImageView timeImg;
    private RelativeLayout timeOutLay;
    private int timeStatus;
    double timecharg;
    private float voltage;
    public int wi;
    private ImageView wifiImg;
    private RelativeLayout wifiLay;

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.context = this;
        LayForAppThemeOnCreate();
    }

    public void LayForAppThemeOnCreate() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        this.pref = defaultSharedPreferences;
        this.editor = defaultSharedPreferences.edit();
        setContentView(R.layout.activity_saver_mode);
        this.nativeContainerLay = (RelativeLayout) findViewById(R.id.nativeContainerLay);
        this.nativeDummyContainer = (RelativeLayout) findViewById(R.id.native_dummy_container);

        this.slideLeftAnim = AnimationUtils.loadAnimation(this, R.anim.slide_left);
        this.slideRightAnim = AnimationUtils.loadAnimation(this, R.anim.slide_right);
        this.controlText = (TextView) findViewById(R.id.controlText);
        this.controlTextValue = (TextView) findViewById(R.id.controlTextValue);
        this.channelLogo = (ImageView) findViewById(R.id.channelLogo);
        this.powerSaverRadio = (RadioButton) findViewById(R.id.PowerRadioBtn);
        this.MaxpowerSaverRadio = (RadioButton) findViewById(R.id.MaxPowerRadioBtn);
        this.PowerSaverMainLay = (RelativeLayout) findViewById(R.id.saverModeMainLay);
        this.headerLay = (RelativeLayout) findViewById(R.id.headerLaySaver);
        this.SkipDoneLay = (RelativeLayout) findViewById(R.id.skipDoneLay);
        this.channelLay = (RelativeLayout) findViewById(R.id.channelLay);
        this.backlay = (RelativeLayout) findViewById(R.id.backlay);
        this.nextBtn = (Button) findViewById(R.id.nextBtn);
        this.skipBtn = (Button) findViewById(R.id.skipBtn);
        this.wifiImg = (ImageView) findViewById(R.id.wifiImg);
        this.blueImg = (ImageView) findViewById(R.id.bluetoothImg);
        this.brightnessImg = (ImageView) findViewById(R.id.brightnessImg);
        this.timeImg = (ImageView) findViewById(R.id.timeOutImg);
        this.soundImg = (ImageView) findViewById(R.id.soundImg);
        this.batteryInnerImg = (ImageView) findViewById(R.id.batteryInner);
        this.RemainingHours = (TextView) findViewById(R.id.tvremaininghour);
        this.RemainingMintues = (TextView) findViewById(R.id.remainMinuts);
        this.RemainingHours2 = (TextView) findViewById(R.id.remainingHour2);
        this.RemainingMintues2 = (TextView) findViewById(R.id.remainMinuts2);
        this.time = (TextView) findViewById(R.id.timeleft);
        this.BatteryFullTxt = (TextView) findViewById(R.id.mainBatteryFullTxt);
        this.batteryLifeTxt = (TextView) findViewById(R.id.batteryLifeTxt);
        this.wifiLay = (RelativeLayout) findViewById(R.id.WifiLay);
        this.blueToothLay = (RelativeLayout) findViewById(R.id.bluetoothLay);
        this.brightnessLay = (RelativeLayout) findViewById(R.id.brightnessLay);
        this.timeOutLay = (RelativeLayout) findViewById(R.id.timeOutLay);
        this.soundLay = (RelativeLayout) findViewById(R.id.soundLay);
        this.powerSaverRadio.setTypeface(MainApp.RobotoRegular);
        this.MaxpowerSaverRadio.setTypeface(MainApp.RobotoRegular);
        this.nextBtn.setTypeface(MainApp.RobotoRegular);
        this.RemainingHours.setTypeface(MainApp.RobotoRegular);
        this.RemainingMintues.setTypeface(MainApp.RobotoRegular);
        this.RemainingHours2.setTypeface(MainApp.RobotoRegular);
        this.RemainingMintues2.setTypeface(MainApp.RobotoRegular);
        this.time.setTypeface(MainApp.RobotoRegular);
        this.controlText.setTypeface(MainApp.RobotoRegular);
        this.controlTextValue.setTypeface(MainApp.RobotoRegular);
        this.BatteryFullTxt.setTypeface(MainApp.RobotoRegular);
        this.batteryLifeTxt.setTypeface(MainApp.RobotoRegular);
        if (this.pref.getBoolean("CheckAppStateTip", true)) {
            this.editor.putBoolean("CheckAppStateTip", false);
            this.editor.commit();
        }
        this.wifiLay.setOnClickListener(this);
        this.blueToothLay.setOnClickListener(this);
        this.brightnessLay.setOnClickListener(this);
        this.timeOutLay.setOnClickListener(this);
        this.soundLay.setOnClickListener(this);
        this.backlay.setOnClickListener(this);
        this.skipBtn.setOnClickListener(this);
        this.nextBtn.setOnClickListener(this);
        int i = this.pref.getInt("RadioChk", 0);
        if (i == 1) {
            this.powerSaverRadio.setChecked(true);
            this.CheckRadioModeSelected = 1;
        } else if (i == 2) {
            this.MaxpowerSaverRadio.setChecked(true);
            this.CheckRadioModeSelected = 2;
        }
        new Handler().postDelayed(new Runnable() {

            public void run() {
                BatterySavingActivity.this.SkipDoneLay.setAnimation(AnimationUtils.loadAnimation(BatterySavingActivity.this.context, R.anim.bottomtopslow));
                BatterySavingActivity.this.SkipDoneLay.setVisibility(View.VISIBLE);
            }
        }, 200);
        this.powerSaverRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    BatterySavingActivity.this.PowerSaverRadioFunction();
                    Toast.makeText(BatterySavingActivity.this.context, BatterySavingActivity.this.getString(R.string.modeApplied), Toast.LENGTH_LONG).show();
                    BatterySavingActivity.this.MaxpowerSaverRadio.setChecked(false);
                }
            }
        });
        this.MaxpowerSaverRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    Toast.makeText(BatterySavingActivity.this.context, BatterySavingActivity.this.getString(R.string.modeApplied), Toast.LENGTH_LONG).show();
                    BatterySavingActivity.this.MaxpowerSaverRadioFunction();
                    BatterySavingActivity.this.powerSaverRadio.setChecked(false);
                }
            }
        });
    }

    public void colorSelection(int i, int i2) {
        this.PowerSaverMainLay.setBackgroundColor(i);
        this.headerLay.setBackgroundColor(i);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(i2);
            window.setNavigationBarColor(i2);
        }
    }

    public void onBackPressed() {
        back();
    }

    public void back() {
        startActivity(new Intent(this.context, StartActivity.class));
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        finish();
    }

    
    public void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.batteryInfoReceiver);
        } catch (Exception e) {
            Log.e("Exception", "SaverModeActivity.java onDestroy: " + e.getMessage());
        }
    }

    
    public void onResume() {
        super.onResume();
        this.context.registerReceiver(this.batteryInfoReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (util.WifiStatus(this.context).isConnected()) {
            this.wi = 0;
            this.wifiImg.setImageResource(R.drawable.wifi_on);
        } else {
            this.wifiImg.setImageResource(R.drawable.wifi_off);
            this.wi = 1;
        }
        BluetoothAdapter BluetoothStatus = util.BluetoothStatus();
        this.bluetoothStatus = BluetoothStatus;
        if (BluetoothStatus == null) {
            Toast.makeText(this.context, "Bluetooth is not exist", Toast.LENGTH_LONG).show();
        } else if (BluetoothStatus.isEnabled()) {
            this.blu = 0;
            this.blueImg.setImageResource(R.drawable.bluetooth_on);
        } else {
            this.blu = 1;
            this.blueImg.setImageResource(R.drawable.bluetooth_off);
        }
        if (util.AutoBrightnessCheck(this.context) == 1) {
            this.brightnessImg.setImageResource(R.drawable.bright_auto);
            this.brig = 10;
        } else {
            int brightneStatus = util.brightneStatus(this.context);
            if (brightneStatus <= 35) {
                this.brightnessImg.setImageResource(R.drawable.bright_low);
                this.brig = 20;
            } else if (brightneStatus > 35 && brightneStatus <= 133) {
                this.brightnessImg.setImageResource(R.drawable.bright_medium);
                this.brig = 30;
            } else if (brightneStatus > 133) {
                this.brightnessImg.setImageResource(R.drawable.bright_full);
                this.brig = 90;
            }
        }
        int timeOutvalue = util.timeOutvalue(this.context) / 1000;
        this.timeStatus = timeOutvalue;
        if (timeOutvalue < 60) {
            if (timeOutvalue == 10) {
                this.timeImg.setImageResource(R.drawable.timeout_10s);
                this.tim = 2;
            } else if (timeOutvalue == 15) {
                this.timeImg.setImageResource(R.drawable.timeout_15s);
                this.tim = 3;
            } else if (timeOutvalue == 30) {
                this.timeImg.setImageResource(R.drawable.timeout_30s);
                this.tim = 4;
            }
        } else if (timeOutvalue >= 60) {
            int i = timeOutvalue / 60;
            if (i == 1) {
                this.timeImg.setImageResource(R.drawable.timeout_1m);
                this.tim = 5;
            } else if (i == 2) {
                this.timeImg.setImageResource(R.drawable.timeout_2m);
                this.tim = 6;
            } else if (i == 3) {
                this.timeImg.setImageResource(R.drawable.timeout_3m);
                this.tim = 7;
            } else if (i == 5) {
                this.timeImg.setImageResource(R.drawable.timeout_5m);
                this.tim = 8;
            } else if (i >= 10) {
                this.timeImg.setImageResource(R.drawable.timeout_10m);
                this.tim = 1;
            }
        }
        AudioManager SoundModeStatus = util.SoundModeStatus(this.context);
        if (SoundModeStatus.getRingerMode() == 0) {
            this.soundImg.setImageResource(R.drawable.sound_off);
            this.sond = 1;
        } else if (SoundModeStatus.getRingerMode() == 2) {
            this.soundImg.setImageResource(R.drawable.sound_on);
            this.sond = 2;
        } else {
            this.soundImg.setImageResource(R.drawable.vibration);
            this.sond = 0;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.WifiLay:
                if (Build.VERSION.SDK_INT < 23) {
                    WifiSet();
                    return;
                } else if (Settings.System.canWrite(this.context)) {
                    WifiSet();
                    return;
                } else {
                    util.permissionDialog(this.context, 0);
                    return;
                }
            case R.id.backlay:
                back();
                return;
            case R.id.bluetoothLay:
                if (Build.VERSION.SDK_INT < 23) {
                    bluetoothSet();
                    return;
                } else if (Settings.System.canWrite(this.context)) {
                    bluetoothSet();
                    return;
                } else {
                    util.permissionDialog(this.context, 0);
                    return;
                }
            case R.id.brightnessLay:
                if (Build.VERSION.SDK_INT < 23) {
                    BrightnessSet();
                    return;
                } else if (Settings.System.canWrite(this.context)) {
                    BrightnessSet();
                    return;
                } else {
                    util.permissionDialog(this.context, 0);
                    return;
                }
            case R.id.nextBtn:
                startActivity(new Intent(this.context, MonitorActivity.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
                return;
            case R.id.skipBtn:
                if (Build.VERSION.SDK_INT < 23) {
                    funForNextActivity();
                    return;
                } else if (Settings.System.canWrite(this.context)) {
                    funForNextActivity();
                    return;
                } else {
                    util.permissionDialog(this.context, 0);
                    return;
                }
            case R.id.soundLay:
                if (Build.VERSION.SDK_INT < 23) {
                    SoundSet();
                    return;
                } else if (Settings.System.canWrite(this.context)) {
                    SoundSet();
                    return;
                } else {
                    util.permissionDialog(this.context, 0);
                    return;
                }
            case R.id.timeOutLay:
                if (Build.VERSION.SDK_INT < 23) {
                    TimeOutSet();
                    return;
                } else if (Settings.System.canWrite(this.context)) {
                    TimeOutSet();
                    return;
                } else {
                    util.permissionDialog(this.context, 0);
                    return;
                }
            default:
                return;
        }
    }

    public void BrightnessSet() {
        this.handler.post(this.appearChannelLay);
        this.handler.removeCallbacks(this.disappearChannelLay);
        this.channelLogo.setBackgroundResource(R.drawable.brightness);
        this.controlText.setText("Screen Brightness: ");
        int i = this.brig;
        if (i == 10) {
            this.controlTextValue.setText("Low");
            if (util.AutoBrightnessCheck(this.context) == 1) {
                util.ManualBrightnessCheck(this.context);
            }
            util.brightness1(35, 0.15f, this.context);
            this.brightnessImg.setImageResource(R.drawable.bright_low);
            this.brig = 20;
        } else if (i == 20) {
            this.controlTextValue.setText("Medium");
            if (util.AutoBrightnessCheck(this.context) == 1) {
                util.ManualBrightnessCheck(this.context);
            }
            util.brightness1(133, 0.65f, this.context);
            this.brightnessImg.setImageResource(R.drawable.bright_medium);
            this.brig = 30;
        } else if (i == 30) {
            this.controlTextValue.setText("Full");
            if (util.AutoBrightnessCheck(this.context) == 1) {
                util.ManualBrightnessCheck(this.context);
            }
            util.brightness1(255, 0.99f, this.context);
            this.brightnessImg.setImageResource(R.drawable.bright_full);
            this.brig = 90;
        } else if (i == 90) {
            this.controlTextValue.setText("Auto");
            util.setAutoBrightness(this.context);
            this.brightnessImg.setImageResource(R.drawable.bright_auto);
            this.brig = 10;
        }
    }

    public void TimeOutSet() {
        this.handler.post(this.appearChannelLay);
        this.handler.removeCallbacks(this.disappearChannelLay);
        this.channelLogo.setBackgroundResource(R.drawable.timeout);
        this.controlText.setText("Screen Timeout: ");
        int i = this.tim;
        if (i == 1) {
            this.controlTextValue.setText("10 Seconds");
            this.timeImg.setImageResource(R.drawable.timeout_10s);
            util.timeOut(10000, this.context);
            this.tim = 2;
        } else if (i == 2) {
            this.controlTextValue.setText("15 Seconds");
            this.timeImg.setImageResource(R.drawable.timeout_15s);
            util.timeOut(15000, this.context);
            this.tim = 3;
        } else if (i == 3) {
            this.controlTextValue.setText("30 Seconds");
            this.timeImg.setImageResource(R.drawable.timeout_30s);
            util.timeOut(30000, this.context);
            this.tim = 4;
        } else if (i == 4) {
            this.controlTextValue.setText("1 Minute");
            this.timeImg.setImageResource(R.drawable.timeout_1m);
            util.timeOut(60000, this.context);
            this.tim = 5;
        } else if (i == 5) {
            this.controlTextValue.setText("2 Minutes");
            this.timeImg.setImageResource(R.drawable.timeout_2m);
            util.timeOut(120000, this.context);
            this.tim = 6;
        } else if (i == 6) {
            this.controlTextValue.setText("3 Minutes");
            this.timeImg.setImageResource(R.drawable.timeout_3m);
            util.timeOut(180000, this.context);
            this.tim = 7;
        } else if (i == 7) {
            this.controlTextValue.setText("5 Minutes");
            this.timeImg.setImageResource(R.drawable.timeout_5m);
            util.timeOut(300000, this.context);
            this.tim = 8;
        } else if (i == 8) {
            this.controlTextValue.setText("10 Minutes");
            this.timeImg.setImageResource(R.drawable.timeout_10m);
            util.timeOut(600000, this.context);
            this.tim = 1;
        }
    }

    public void SoundSet() {
        this.handler.post(this.appearChannelLay);
        this.handler.removeCallbacks(this.disappearChannelLay);
        this.controlText.setText("Sound: ");
        int i = this.sond;
        if (i == 0) {
            this.channelLogo.setBackgroundResource(R.drawable.sound_off_white);
            this.controlTextValue.setText("OFF");
            util.silent(this.context);
            this.soundImg.setImageResource(R.drawable.sound_off);
            this.sond = 1;
        } else if (i == 1) {
            this.channelLogo.setBackgroundResource(R.drawable.sound_white);
            this.controlTextValue.setText("ON");
            util.normal(this.context);
            this.soundImg.setImageResource(R.drawable.sound_on);
            this.sond = 2;
        } else {
            this.channelLogo.setBackgroundResource(R.drawable.vibration_white);
            this.controlTextValue.setText("ON");
            util.soundvibrate(this.context);
            this.soundImg.setImageResource(R.drawable.vibration);
            this.sond = 0;
        }
    }

    public void bluetoothSet() {
        this.handler.post(this.appearChannelLay);
        this.handler.removeCallbacks(this.disappearChannelLay);
        this.channelLogo.setBackgroundResource(R.drawable.bluetooth_white);
        this.controlText.setText("Bluetooth: ");
        if (this.blu == 0) {
            this.controlTextValue.setText("OFF");
            this.blueImg.setImageResource(R.drawable.bluetooth_off);
            util.bluetoothOff();
            this.blu = 1;
            return;
        }
        this.controlTextValue.setText("ON");
        this.blueImg.setImageResource(R.drawable.bluetooth_on);
        util.bluetoothOn();
        this.blu = 0;
    }

    public void WifiSet() {
        this.handler.post(this.appearChannelLay);
        this.handler.removeCallbacks(this.disappearChannelLay);
        this.channelLogo.setBackgroundResource(R.drawable.wifi);
        if (this.wi == 0) {
            this.controlText.setText("WiFi: OFF");
            this.wifiImg.setImageResource(R.drawable.wifi_off);
            util.wifiOn(this.context, false);
            this.wi = 1;
            return;
        }
        this.controlText.setText("WiFi: ON");
        this.wifiImg.setImageResource(R.drawable.wifi_on);
        util.wifiOn(this.context, true);
        this.wi = 0;
    }

    public void concateZero(TextView textView, int i) {
        if (i > 10) {
            textView.setText("" + i);
            return;
        }
        textView.setText("0" + i);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 123 && iArr.length > 0 && iArr[0] == 0) {
            runOnUiThread(new Runnable() {

                public void run() {
                    Toast.makeText(BatterySavingActivity.this.getApplicationContext(), "Getting your Device Permissions", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void PowerSaverRadioFunction() {
        try {
            this.MaxpowerSaverRadio.setChecked(false);
            this.editor.putInt("RadioChk", 1);
            this.editor.commit();
            if (Build.VERSION.SDK_INT < 23) {
                util.setAutoBrightness(this.context);
            } else if (Settings.System.canWrite(getApplicationContext())) {
                util.setAutoBrightness(this.context);
            } else {
                Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
                intent.setData(Uri.parse("package:" + this.context.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            if (Build.VERSION.SDK_INT >= 23) {
                this.tim = 4;
                if (Settings.System.canWrite(this.context)) {
                    TimeOutSet();
                } else {
                    util.permissionDialog(this.context, 0);
                }
            } else {
                TimeOutSet();
            }
            util.bluetoothOff();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void MaxpowerSaverRadioFunction() {
        try {
            this.powerSaverRadio.setChecked(false);
            this.editor.putInt("RadioChk", 2);
            this.editor.commit();
            util.bluetoothOff();
            util.syncOnOff(false);
            if (Build.VERSION.SDK_INT < 23) {
                if (util.AutoBrightnessCheck(this.context) == 1) {
                    util.ManualBrightnessCheck(this.context);
                }
                util.brightness1(20, 0.1f, this.context);
            } else if (Settings.System.canWrite(getApplicationContext())) {
                if (util.AutoBrightnessCheck(this.context) == 1) {
                    util.ManualBrightnessCheck(this.context);
                }
                util.brightness1(20, 0.1f, this.context);
            } else {
                Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
                intent.setData(Uri.parse("package:" + this.context.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            if (Build.VERSION.SDK_INT >= 23) {
                this.tim = 4;
                if (Settings.System.canWrite(this.context)) {
                    TimeOutSet();
                } else {
                    util.permissionDialog(this.context, 0);
                }
            } else {
                TimeOutSet();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void funForNextActivity() {
        this.channelLogo.setBackgroundResource(0);
        this.channelLay.clearAnimation();
        this.channelLay.setVisibility(View.GONE);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - TimeUnit.MINUTES.toMillis(2) >= this.pref.getLong(util.CheckStateOfAlreadyBatteryBoost, 0)) {
            this.tim = 3;
            TimeOutSet();
            startActivity(new Intent(this.context, BatteryTester.class));
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            finish();
            return;
        }
        FinalAllActivity.backToNoHome = true;
        util.CheckFromWichActivityComming = 6;
       /* if (Utils.interstitialAd == null || !Utils.interstitialAd.isAdLoaded()) {
            startActivity(new Intent(this, OptimizeActivity.class));
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            finish();
            return;
        }
        Intent intent = new Intent(this.context, InterstitialAdsActivity.class);
        intent.putExtra(Utils.SaveStateOfReturnActivity, 0);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();*/

        startActivity(new Intent(this, FinalAllActivity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }

    public void switchActivity() {
        startActivity(new Intent(this, FinalAllActivity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }

    static /* synthetic */ float access$1002(BatterySavingActivity saverModeActivity, float f) {
        saverModeActivity.voltage = f;
        return f;
    }

    static /* synthetic */ int access$1100(BatterySavingActivity saverModeActivity) {
        return saverModeActivity.level;
    }

    static /* synthetic */ int access$1102(BatterySavingActivity saverModeActivity, int n) {
        saverModeActivity.level = n;
        return n;
    }

    static /* synthetic */ TextView access$1200(BatterySavingActivity saverModeActivity) {
        return saverModeActivity.batteryLifeTxt;
    }

    static /* synthetic */ ImageView access$1300(BatterySavingActivity saverModeActivity) {
        return saverModeActivity.batteryInnerImg;
    }

    static /* synthetic */ TextView access$1400(BatterySavingActivity saverModeActivity) {
        return saverModeActivity.time;
    }

    static /* synthetic */ TextView access$1500(BatterySavingActivity saverModeActivity) {
        return saverModeActivity.RemainingHours;
    }

    static /* synthetic */ TextView access$1600(BatterySavingActivity saverModeActivity) {
        return saverModeActivity.RemainingMintues;
    }

    static /* synthetic */ TextView access$1700(BatterySavingActivity saverModeActivity) {
        return saverModeActivity.RemainingMintues2;
    }

    static /* synthetic */ TextView access$1800(BatterySavingActivity saverModeActivity) {
        return saverModeActivity.RemainingHours2;
    }

    static /* synthetic */ TextView access$1900(BatterySavingActivity saverModeActivity) {
        return saverModeActivity.BatteryFullTxt;
    }

    static /* synthetic */ int access$700(BatterySavingActivity saverModeActivity) {
        return saverModeActivity.temperature;
    }

    static /* synthetic */ int access$702(BatterySavingActivity saverModeActivity, int n) {
        saverModeActivity.temperature = n;
        return n;
    }

    static /* synthetic */ int access$802(BatterySavingActivity saverModeActivity, int n) {
        saverModeActivity.finalTemp = n;
        return n;
    }

    static /* synthetic */ String access$902(BatterySavingActivity saverModeActivity, String string2) {
        saverModeActivity.technology = string2;
        return string2;
    }
}
