package com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.ActivityChooserView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplashActivity extends Activity {
    private static final boolean INCLUDE_SYSTEM_APPS = false;
    public static Map<String, Drawable> icons;
    public static float ramUsedByApps;
    private TextView agreeTxt;
    private int checkVal = 0;
    Context context;
    Handler handler = new Handler();
    private Drawable icon;
    private CharSequence labl;
    PackageManager mPackManager;
    ActivityManager manager;
    PackageInfo p;
    private ActivityManager.RunningServiceInfo pInfo2;
    private String pkgnames;
    private Button privacyButton;
    private TextView privacyPolicyTxt;
    private ImageView progressImage;
    private ImageView rocket;
    private ImageView splashBottom;
    LinearLayout splashBottomLay;
    private TextView splashSub;
    private ImageView splashTop;
    private TextView titleTxt;

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.context = this;
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_splash);
        this.rocket = (ImageView) findViewById(R.id.SplashRocket);
        this.progressImage = (ImageView) findViewById(R.id.progressImage);
        this.splashTop = (ImageView) findViewById(R.id.splashTop);
        this.splashBottom = (ImageView) findViewById(R.id.splashBottom);
        this.titleTxt = (TextView) findViewById(R.id.splashTitle);
        this.splashSub = (TextView) findViewById(R.id.splashSub);
        this.splashBottomLay = (LinearLayout) findViewById(R.id.splashBottomLay);
        this.privacyButton = (Button) findViewById(R.id.privacyButton);
        this.privacyPolicyTxt = (TextView) findViewById(R.id.privacyPolicyTxt);
        this.agreeTxt = (TextView) findViewById(R.id.agreeTxt);
        this.titleTxt.setTypeface(AppAnaylatics.RobotoRegular);
        this.splashSub.setTypeface(AppAnaylatics.RobotoRegular);
        this.privacyPolicyTxt.setTypeface(AppAnaylatics.RobotoRegular);
        this.agreeTxt.setTypeface(AppAnaylatics.RobotoRegular);
        this.privacyButton.setTypeface(AppAnaylatics.RobotoRegular);

        this.handler.postDelayed(new Runnable() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.SplashActivity.AnonymousClass1 */

            public void run() {
                SplashActivity.this.rocket.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this.context, R.anim.bounce_anim));
            }
        }, 1000);
        this.handler.postDelayed(new Runnable() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.SplashActivity.AnonymousClass2 */

            public void run() {
                Animation loadAnimation = AnimationUtils.loadAnimation(SplashActivity.this.context, R.anim.fade_in);
                SplashActivity.this.titleTxt.setVisibility(View.VISIBLE);
                SplashActivity.this.titleTxt.startAnimation(loadAnimation);
            }
        }, 2000);
        this.handler.postDelayed(new Runnable() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.SplashActivity.AnonymousClass3 */

            public void run() {
                Animation loadAnimation = AnimationUtils.loadAnimation(SplashActivity.this.context, R.anim.fade_in);
                SplashActivity.this.splashSub.setVisibility(View.VISIBLE);
                SplashActivity.this.splashSub.startAnimation(loadAnimation);
            }
        }, 2500);
        this.handler.postDelayed(new Runnable() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.SplashActivity.AnonymousClass4 */

            public void run() {
                Animation loadAnimation = AnimationUtils.loadAnimation(SplashActivity.this.context, R.anim.fade_in);
                SplashActivity.this.progressImage.setVisibility(View.VISIBLE);
                SplashActivity.this.progressImage.startAnimation(loadAnimation);
            }
        }, 2700);
        this.handler.postDelayed(new Runnable() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.SplashActivity.AnonymousClass5 */

            public void run() {
                Animation loadAnimation = AnimationUtils.loadAnimation(SplashActivity.this.context, R.anim.rotate);
                SplashActivity.this.progressImage.setVisibility(View.VISIBLE);
                SplashActivity.this.progressImage.startAnimation(loadAnimation);
            }
        }, 3000);
        this.handler.postDelayed(new Runnable() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.SplashActivity.AnonymousClass6 */

            public void run() {
                if (SplashActivity.this.checkVal == 1) {
                    SplashActivity.this.FunForVisiblePrivacyText();
                }
                SplashActivity.this.checkVal = 2;
            }
        }, 3300);
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                this.progressImage.clearAnimation();
                this.progressImage.setVisibility(View.GONE);
                Utils.runningList.clear();
                Utils.runningList = loadInstalledApps(false);
                new LoadIconsTask().execute(Utils.runningList.toArray(new RunningItem[0]));
                Utils.runningList = sortbysize(Utils.runningList);
                if (this.checkVal == 2) {
                    FunForVisiblePrivacyText();
                }
                this.checkVal = 1;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (NullPointerException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        } else {
            new Longoperation().execute(new String[0]);
        }
    }

    public void PrivacyClick(View view) {
        if (checkWifiConnect()) {
            startActivity(new Intent(this, PrivacyWebView.class));
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            return;
        }
        noInternetToast();
    }

    private boolean checkWifiConnect() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            if ((!(activeNetworkInfo.getType() == 1) && !(activeNetworkInfo.getType() == 0)) || !activeNetworkInfo.isConnected()) {
                return false;
            }
            return true;
        }
        return false;
    }

    private void noInternetToast() {
        View inflate = getLayoutInflater().inflate(R.layout.internet_toast_layut, (ViewGroup) findViewById(R.id.toast_layout_root));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(80, 0, 150);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(inflate);
        toast.show();
    }

    public void startActivity(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.push_right_out);
        loadAnimation.setDuration(200);
        this.splashTop.startAnimation(loadAnimation);
        this.splashTop.setVisibility(View.GONE);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this, R.anim.push_left_out);
        loadAnimation2.setDuration(200);
        this.splashBottom.startAnimation(loadAnimation2);
        this.splashBottom.setVisibility(View.GONE);
        this.handler.postDelayed(new Runnable() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.SplashActivity.AnonymousClass7 */

            public void run() {
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                SplashActivity.this.finish();
            }
        }, 200);
    }

    public static ArrayList<RunningItem> sortbysize(ArrayList<RunningItem> arrayList) {
        ArrayList arrayList2 = (ArrayList) arrayList.clone();
        Collections.sort(arrayList2, new ItemSizeCompare());
        return (ArrayList) arrayList2.clone();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x008a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private ArrayList<RunningItem> loadInstalledApps(boolean r8) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = this.getPackageManager();
        int i = 0;
        List list = packageManager.getInstalledPackages(0);
        RunningItem runningItem = null;
        while (i < list.size()) {
            RunningItem runningItem2;
            block8:
            {
                NumberFormatException numberFormatException;
                block10:
                {
                    block9:
                    {
                        PackageInfo packageInfo;
                        this.p = packageInfo = (PackageInfo) list.get(i);
                        new File(packageInfo.applicationInfo.sourceDir).lastModified();
                        runningItem2 = new RunningItem();
                        try {
                            runningItem2.setLabel(this.p.applicationInfo.loadLabel(packageManager).toString());
                            runningItem2.setPak(this.p.packageName);
                            runningItem2.setInstallDir(this.p.applicationInfo.sourceDir);
                            runningItem2.getInstallDir();
                            runningItem2.setInstallSize(Utils.calculateSize(runningItem2.getInstallDir()));
                            runningItem2.setChk(true);
                            this.p.applicationInfo.loadDescription(packageManager);
                            break block8;
                        } catch (NullPointerException nullPointerException2) {
                            break block9;
                        } catch (NumberFormatException numberFormatException2) {
                            break block10;
                        }
                    }
                    break block8;
                   /* catch(NumberFormatException numberFormatException3){
                    runningItem2 = runningItem;
                    numberFormatException = numberFormatException3;
                }*/
                }
            }
            runningItem = runningItem2;
            if (!this.p.packageName.equals((Object) "com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast")) {
                arrayList.add(runningItem);
            }
            ++i;
        }
        return arrayList;
    }

    private class LoadIconsTask extends AsyncTask<RunningItem, Void, Void> {
        
        public void onPostExecute(Void r1) {
        }

        private LoadIconsTask() {
        }

        
        public void onPreExecute() {
            super.onPreExecute();
        }

        
        public Void doInBackground(RunningItem... runningItemArr) {
            SplashActivity.icons = new HashMap();
            PackageManager packageManager = SplashActivity.this.getApplicationContext().getPackageManager();
            int length = runningItemArr.length;
            int i = 0;
            while (true) {
                Drawable drawable = null;
                if (i >= length) {
                    return null;
                }
                RunningItem runningItem = runningItemArr[i];
                String pak = runningItem.getPak();
                try {
                    Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(pak);
                    if (launchIntentForPackage != null) {
                        drawable = packageManager.getActivityIcon(launchIntentForPackage);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e("ERROR", "Unable to find icon for package '" + pak + "': " + e.getMessage());
                }
                SplashActivity.icons.put(runningItem.getPak(), drawable);
                i++;
            }
        }
    }

    public class Longoperation extends AsyncTask<String, RunningItem, String> {
        public Longoperation() {
        }

        
        public void onPreExecute() {
            super.onPreExecute();
        }

        
        public void onProgressUpdate(RunningItem... runningItemArr) {
            super.onProgressUpdate(runningItemArr);
        }

        
        public String doInBackground(String... strArr) {
            PackageInfo packageInfo;
            PackageInfo packageInfo2;
            CharSequence charSequence;
            PackageInfo packageInfo3;
            CharSequence charSequence2;
            try {
                Utils.mApps.clear();
                Utils.CoolerListmApps.clear();
                Utils.InstallApp(SplashActivity.this.context);
                Utils.installAppList.size();
                SplashActivity.this.manager = (ActivityManager) SplashActivity.this.getSystemService(Context.ACTIVITY_SERVICE);
                SplashActivity.this.mPackManager = SplashActivity.this.context.getPackageManager();
                if (Build.VERSION.SDK_INT >= 21) {
                    List<ActivityManager.RunningServiceInfo> runningServices = SplashActivity.this.manager.getRunningServices(Integer.MAX_VALUE);
                    if (runningServices != null) {
                        for (int i = 0; i < runningServices.size(); i++) {
                            SplashActivity.this.pInfo2 = runningServices.get(i);
                            RunningItem runningItem = new RunningItem();
                            if (i == 10) {
                                runningItem.setPak("Ads");
                            } else {
                                try {
                                    packageInfo3 = SplashActivity.this.getPackageManager().getPackageInfo(SplashActivity.this.pInfo2.process, 0);
                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                    packageInfo3 = null;
                                }
                                if (packageInfo3 != null && !SplashActivity.this.pInfo2.process.equalsIgnoreCase(BuildConfig.APPLICATION_ID)) {
                                    runningItem.setPak(SplashActivity.this.pInfo2.process);
                                    if (SplashActivity.this.pInfo2.pid == 0) {
                                        runningItem.setPid(SplashActivity.this.pInfo2.uid);
                                    } else {
                                        runningItem.setPid(SplashActivity.this.pInfo2.pid);
                                    }
                                    try {
                                        charSequence2 = SplashActivity.this.mPackManager.getApplicationLabel(SplashActivity.this.mPackManager.getApplicationInfo(runningServices.get(i).process, PackageManager.GET_META_DATA));
                                    } catch (PackageManager.NameNotFoundException e2) {
                                        e2.printStackTrace();
                                        charSequence2 = null;
                                    }
                                    runningItem.setLabel(charSequence2);
                                    try {
                                        runningItem.setIcon(SplashActivity.this.mPackManager.getApplicationIcon(SplashActivity.this.mPackManager.getApplicationInfo(runningServices.get(i).process, PackageManager.GET_META_DATA)));
                                    } catch (PackageManager.NameNotFoundException e3) {
                                        e3.printStackTrace();
                                    }
                                    if (Utils.duplicateCheck(Utils.mApps, SplashActivity.this.pInfo2.process) == 1) {
                                        Utils.IgnorListforCheckBooster = new IgnorList_DataBase(SplashActivity.this.context).getPakgListForBooster();
                                        if (Utils.IgnorListforCheckBooster.size() == 0) {
                                            try {
                                                Thread.sleep(50);
                                            } catch (InterruptedException e4) {
                                                e4.printStackTrace();
                                            }
                                            runningItem.setChk(true);
                                            Utils.mApps.add(runningItem);
                                            publishProgress(runningItem);
                                        } else if (Utils.contain(Utils.IgnorListforCheckBooster, SplashActivity.this.pInfo2.process) != 1) {
                                            try {
                                                Thread.sleep(50);
                                            } catch (InterruptedException e5) {
                                                e5.printStackTrace();
                                            }
                                            runningItem.setChk(true);
                                            Utils.mApps.add(runningItem);
                                            publishProgress(runningItem);
                                        }
                                    }
                                    if (Utils.duplicateCheck(Utils.CoolerListmApps, SplashActivity.this.pInfo2.process) == 1) {
                                        Utils.IgnorListforCheck = new IgnorList_DataBase(SplashActivity.this.context).getPakgList();
                                        if (Utils.IgnorListforCheck.size() == 0) {
                                            try {
                                                Thread.sleep(50);
                                            } catch (InterruptedException e6) {
                                                e6.printStackTrace();
                                            }
                                            runningItem.setChk(true);
                                            Utils.CoolerListmApps.add(runningItem);
                                            publishProgress(runningItem);
                                        } else if (Utils.contain(Utils.IgnorListforCheck, SplashActivity.this.pInfo2.process) != 1) {
                                            try {
                                                Thread.sleep(50);
                                            } catch (InterruptedException e7) {
                                                e7.printStackTrace();
                                            }
                                            runningItem.setChk(true);
                                            Utils.CoolerListmApps.add(runningItem);
                                            publishProgress(runningItem);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    List<ActivityManager.RunningTaskInfo> runningTasks = SplashActivity.this.manager.getRunningTasks(Integer.MAX_VALUE);
                    List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = SplashActivity.this.manager.getRunningAppProcesses();
                    if (runningAppProcesses != null) {
                        for (int i2 = 0; i2 < runningAppProcesses.size(); i2++) {
                            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses.get(i2);
                            RunningItem runningItem2 = new RunningItem();
                            try {
                                packageInfo2 = SplashActivity.this.getPackageManager().getPackageInfo(runningAppProcessInfo.processName, 0);
                            } catch (PackageManager.NameNotFoundException e8) {
                                e8.printStackTrace();
                                packageInfo2 = null;
                            }
                            if (packageInfo2 != null && !runningAppProcessInfo.processName.equalsIgnoreCase(BuildConfig.APPLICATION_ID)) {
                                runningItem2.setPak(runningAppProcessInfo.processName);
                                runningItem2.setPid(runningAppProcessInfo.pid);
                                try {
                                    charSequence = SplashActivity.this.mPackManager.getApplicationLabel(SplashActivity.this.mPackManager.getApplicationInfo(runningAppProcesses.get(i2).processName, PackageManager.GET_META_DATA));
                                } catch (PackageManager.NameNotFoundException e9) {
                                    e9.printStackTrace();
                                    charSequence = null;
                                }
                                runningItem2.setLabel(charSequence);
                                try {
                                    runningItem2.setIcon(SplashActivity.this.mPackManager.getApplicationIcon(SplashActivity.this.mPackManager.getApplicationInfo(runningAppProcesses.get(i2).processName, PackageManager.GET_META_DATA)));
                                } catch (PackageManager.NameNotFoundException e10) {
                                    e10.printStackTrace();
                                }
                                if (Utils.duplicateCheck(Utils.mApps, runningAppProcessInfo.processName) == 1) {
                                    Utils.IgnorListforCheckBooster = new IgnorList_DataBase(SplashActivity.this.context).getPakgList();
                                    if (Utils.IgnorListforCheckBooster.size() == 0) {
                                        try {
                                            Thread.sleep(50);
                                        } catch (InterruptedException e11) {
                                            e11.printStackTrace();
                                        }
                                        runningItem2.setChk(true);
                                        Utils.mApps.add(runningItem2);
                                        publishProgress(runningItem2);
                                    } else if (Utils.contain(Utils.IgnorListforCheckBooster, runningAppProcessInfo.processName) != 1) {
                                        try {
                                            Thread.sleep(50);
                                        } catch (InterruptedException e12) {
                                            e12.printStackTrace();
                                        }
                                        runningItem2.setChk(true);
                                        Utils.mApps.add(runningItem2);
                                        publishProgress(runningItem2);
                                    }
                                }
                                if (Utils.duplicateCheck(Utils.CoolerListmApps, runningAppProcessInfo.processName) == 1) {
                                    Utils.IgnorListforCheck = new IgnorList_DataBase(SplashActivity.this.context).getPakgList();
                                    if (Utils.IgnorListforCheck.size() == 0) {
                                        try {
                                            Thread.sleep(50);
                                        } catch (InterruptedException e13) {
                                            e13.printStackTrace();
                                        }
                                        runningItem2.setChk(true);
                                        Utils.CoolerListmApps.add(runningItem2);
                                        publishProgress(runningItem2);
                                    } else if (Utils.contain(Utils.IgnorListforCheck, runningAppProcessInfo.processName) != 1) {
                                        try {
                                            Thread.sleep(50);
                                        } catch (InterruptedException e14) {
                                            e14.printStackTrace();
                                        }
                                        runningItem2.setChk(true);
                                        Utils.CoolerListmApps.add(runningItem2);
                                        publishProgress(runningItem2);
                                    }
                                }
                            }
                        }
                    }
                    if (runningTasks != null) {
                        for (int i3 = 0; i3 < runningTasks.size(); i3++) {
                            ActivityManager.RunningTaskInfo runningTaskInfo = runningTasks.get(i3);
                            try {
                                packageInfo = SplashActivity.this.getPackageManager().getPackageInfo(runningTaskInfo.topActivity.getPackageName(), 0);
                            } catch (PackageManager.NameNotFoundException e15) {
                                e15.printStackTrace();
                                packageInfo = null;
                            }
                            if (packageInfo != null && !runningTaskInfo.topActivity.getPackageName().equalsIgnoreCase(BuildConfig.APPLICATION_ID)) {
                                RunningItem runningItem3 = new RunningItem();
                                runningItem3.setPid(runningTaskInfo.id);
                                SplashActivity.this.pkgnames = runningTaskInfo.topActivity.getPackageName();
                                runningItem3.setPak(SplashActivity.this.pkgnames);
                                try {
                                    SplashActivity.this.icon = SplashActivity.this.mPackManager.getApplicationIcon(SplashActivity.this.mPackManager.getApplicationInfo(runningTasks.get(i3).topActivity.getPackageName(), PackageManager.GET_META_DATA));
                                } catch (PackageManager.NameNotFoundException e16) {
                                    e16.printStackTrace();
                                }
                                runningItem3.setIcon(SplashActivity.this.icon);
                                try {
                                    SplashActivity.this.labl = SplashActivity.this.mPackManager.getApplicationLabel(SplashActivity.this.mPackManager.getApplicationInfo(runningTasks.get(i3).topActivity.getPackageName(), PackageManager.GET_META_DATA));
                                } catch (PackageManager.NameNotFoundException e17) {
                                    e17.printStackTrace();
                                }
                                runningItem3.setLabel(SplashActivity.this.labl.toString());
                                if (Utils.duplicateCheck(Utils.mApps, runningTaskInfo.topActivity.getPackageName()) == 1 && Utils.mApps.contains(runningItem3)) {
                                    try {
                                        Thread.sleep(50);
                                    } catch (InterruptedException e18) {
                                        e18.printStackTrace();
                                    }
                                    runningItem3.setChk(true);
                                    Utils.mApps.add(runningItem3);
                                    Utils.CoolerListmApps.add(runningItem3);
                                    Utils.ListforDisplayIcons.add(runningItem3);
                                }
                            }
                        }
                        try {
                            Thread.sleep(3300);
                        } catch (InterruptedException e19) {
                            e19.printStackTrace();
                        }
                    }
                }
                SplashActivity.this.process_memory();
            } catch (NullPointerException e20) {
                e20.printStackTrace();
            }
            return null;
        }

        
        public void onPostExecute(String str) {
            super.onPostExecute(str);
            if (SplashActivity.this.checkVal == 2) {
                SplashActivity.this.progressImage.clearAnimation();
                SplashActivity.this.progressImage.setVisibility(View.GONE);
                SplashActivity.this.FunForVisiblePrivacyText();
            }
            SplashActivity.this.checkVal = 1;
        }
    }

    public int process_memory() {
        try {
            ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            int[] iArr = new int[Utils.mApps.size()];
            for (int i = 0; i < Utils.mApps.size(); i++) {
                iArr[i] = Utils.mApps.get(i).getPid();
            }
            Debug.MemoryInfo[] processMemoryInfo = activityManager.getProcessMemoryInfo(iArr);
            for (int i2 = 0; i2 < processMemoryInfo.length; i2++) {
                ramUsedByApps += (float) processMemoryInfo[i2].getTotalPss();
                Utils.mApps.get(i2).setSize(processMemoryInfo[i2].getTotalPss());
                processMemoryInfo[i2].getTotalPss();
            }
        } catch (Throwable unused) {
        }
        return 0;
    }

    public void FunForVisiblePrivacyText() {
        this.progressImage.setVisibility(View.GONE);
        this.splashBottomLay.setVisibility(View.VISIBLE);
        this.handler.postDelayed(new Runnable() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.SplashActivity.AnonymousClass8 */

            public void run() {
                SplashActivity.this.privacyButton.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.push_left_in));
                SplashActivity.this.privacyButton.setVisibility(View.VISIBLE);
            }
        }, 150);
    }

    
    public void onResume() {
        super.onResume();
    }
}
