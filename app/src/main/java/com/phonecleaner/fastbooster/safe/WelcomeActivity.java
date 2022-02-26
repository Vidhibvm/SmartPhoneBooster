package com.phonecleaner.fastbooster.safe;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WelcomeActivity extends Activity {

    private static final boolean INCLUDE_SYSTEM_APPS = false;
    public static Map<String, Drawable> icons;
    public static float ramUsedByApps;
    private int checkVal = 0;
    Context context;
    Handler handler = new Handler();
    private Drawable icon;
    private CharSequence Applabel;
    PackageManager packageManager;
    ActivityManager activityManager;
    PackageInfo p;
    private ActivityManager.RunningServiceInfo pInfo2;
    private String pkgnames;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.context = this;
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_splash);


        handler.postDelayed(new Runnable() {

            public void run() {
                if (checkVal == 1) {
                    GOTONextActivity();
                }
                WelcomeActivity.this.checkVal = 2;
            }
        }, 3300);


        if (Build.VERSION.SDK_INT >= 26) {
            try {
                Utils.runningList.clear();
                Utils.runningList = loadInstalledApps(false);
                new LoadIconsTask().execute(Utils.runningList.toArray(new RunningItem[0]));
                Utils.runningList = sortbysize(Utils.runningList);
                if (checkVal == 2) {
                    GOTONextActivity();
                }
                checkVal = 1;
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        } else {
            new Longoperation().execute(new String[0]);
        }
    }


    public void startActivity(View view) {
        handler.postDelayed(new Runnable() {

            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, 200);
    }

    public static ArrayList<RunningItem> sortbysize(ArrayList<RunningItem> arrayList) {
        ArrayList arrayList2 = (ArrayList) arrayList.clone();
        Collections.sort(arrayList2, new ItemSizeCompare());
        return (ArrayList) arrayList2.clone();
    }


    private ArrayList<RunningItem> loadInstalledApps(boolean r8) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = getPackageManager();
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
                        p = packageInfo = (PackageInfo) list.get(i);
                        new File(packageInfo.applicationInfo.sourceDir).lastModified();
                        runningItem2 = new RunningItem();
                        try {
                            runningItem2.setLabel(p.applicationInfo.loadLabel(packageManager).toString());
                            runningItem2.setPak(p.packageName);
                            runningItem2.setInstallDir(p.applicationInfo.sourceDir);
                            runningItem2.getInstallDir();
                            runningItem2.setInstallSize(Utils.calculateSize(runningItem2.getInstallDir()));
                            runningItem2.setChk(true);
                            p.applicationInfo.loadDescription(packageManager);
                            break block8;
                        } catch (NullPointerException nullPointerException2) {
                            break block9;
                        } catch (NumberFormatException numberFormatException2) {
                            break block10;
                        }
                    }
                    break block8;
                }
            }
            runningItem = runningItem2;
            if (!p.packageName.equals((Object) "com.phonecleaner.fastbooster.safe")) {
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
            icons = new HashMap();
            PackageManager packageManager = getApplicationContext().getPackageManager();
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
                icons.put(runningItem.getPak(), drawable);
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
                Utils.InstallApp(context);
                Utils.installAppList.size();
                activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                packageManager = context.getPackageManager();
                if (Build.VERSION.SDK_INT >= 21) {
                    List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(Integer.MAX_VALUE);
                    if (runningServices != null) {
                        for (int i = 0; i < runningServices.size(); i++) {
                            pInfo2 = runningServices.get(i);
                            RunningItem runningItem = new RunningItem();
                            if (i == 10) {
                                runningItem.setPak("Ads");
                            } else {
                                try {
                                    packageInfo3 = getPackageManager().getPackageInfo(pInfo2.process, 0);
                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                    packageInfo3 = null;
                                }
                                if (packageInfo3 != null && !pInfo2.process.equalsIgnoreCase(BuildConfig.APPLICATION_ID)) {
                                    runningItem.setPak(pInfo2.process);
                                    if (pInfo2.pid == 0) {
                                        runningItem.setPid(pInfo2.uid);
                                    } else {
                                        runningItem.setPid(pInfo2.pid);
                                    }
                                    try {
                                        charSequence2 = packageManager.getApplicationLabel(packageManager.getApplicationInfo(runningServices.get(i).process, PackageManager.GET_META_DATA));
                                    } catch (PackageManager.NameNotFoundException e2) {
                                        e2.printStackTrace();
                                        charSequence2 = null;
                                    }
                                    runningItem.setLabel(charSequence2);
                                    try {
                                        runningItem.setIcon(packageManager.getApplicationIcon(packageManager.getApplicationInfo(runningServices.get(i).process, PackageManager.GET_META_DATA)));
                                    } catch (PackageManager.NameNotFoundException e3) {
                                        e3.printStackTrace();
                                    }
                                    if (Utils.duplicateCheck(Utils.mApps, pInfo2.process) == 1) {
                                        Utils.IgnorListforCheckBooster = new IgnorList_DataBase(context).getPakgListForBooster();
                                        if (Utils.IgnorListforCheckBooster.size() == 0) {
                                            try {
                                                Thread.sleep(50);
                                            } catch (InterruptedException e4) {
                                                e4.printStackTrace();
                                            }
                                            runningItem.setChk(true);
                                            Utils.mApps.add(runningItem);
                                            publishProgress(runningItem);
                                        } else if (Utils.contain(Utils.IgnorListforCheckBooster, pInfo2.process) != 1) {
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
                                    if (Utils.duplicateCheck(Utils.CoolerListmApps, pInfo2.process) == 1) {
                                        Utils.IgnorListforCheck = new IgnorList_DataBase(context).getPakgList();
                                        if (Utils.IgnorListforCheck.size() == 0) {
                                            try {
                                                Thread.sleep(50);
                                            } catch (InterruptedException e6) {
                                                e6.printStackTrace();
                                            }
                                            runningItem.setChk(true);
                                            Utils.CoolerListmApps.add(runningItem);
                                            publishProgress(runningItem);
                                        } else if (Utils.contain(Utils.IgnorListforCheck, pInfo2.process) != 1) {
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
                    List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
                    List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                    if (runningAppProcesses != null) {
                        for (int i2 = 0; i2 < runningAppProcesses.size(); i2++) {
                            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses.get(i2);
                            RunningItem runningItem2 = new RunningItem();
                            try {
                                packageInfo2 = getPackageManager().getPackageInfo(runningAppProcessInfo.processName, 0);
                            } catch (PackageManager.NameNotFoundException e8) {
                                e8.printStackTrace();
                                packageInfo2 = null;
                            }
                            if (packageInfo2 != null && !runningAppProcessInfo.processName.equalsIgnoreCase(BuildConfig.APPLICATION_ID)) {
                                runningItem2.setPak(runningAppProcessInfo.processName);
                                runningItem2.setPid(runningAppProcessInfo.pid);
                                try {
                                    charSequence = packageManager.getApplicationLabel(packageManager.getApplicationInfo(runningAppProcesses.get(i2).processName, PackageManager.GET_META_DATA));
                                } catch (PackageManager.NameNotFoundException e9) {
                                    e9.printStackTrace();
                                    charSequence = null;
                                }
                                runningItem2.setLabel(charSequence);
                                try {
                                    runningItem2.setIcon(packageManager.getApplicationIcon(packageManager.getApplicationInfo(runningAppProcesses.get(i2).processName, PackageManager.GET_META_DATA)));
                                } catch (PackageManager.NameNotFoundException e10) {
                                    e10.printStackTrace();
                                }
                                if (Utils.duplicateCheck(Utils.mApps, runningAppProcessInfo.processName) == 1) {
                                    Utils.IgnorListforCheckBooster = new IgnorList_DataBase(context).getPakgList();
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
                                    Utils.IgnorListforCheck = new IgnorList_DataBase(context).getPakgList();
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
                                packageInfo = getPackageManager().getPackageInfo(runningTaskInfo.topActivity.getPackageName(), 0);
                            } catch (PackageManager.NameNotFoundException e15) {
                                e15.printStackTrace();
                                packageInfo = null;
                            }
                            if (packageInfo != null && !runningTaskInfo.topActivity.getPackageName().equalsIgnoreCase(BuildConfig.APPLICATION_ID)) {
                                RunningItem runningItem3 = new RunningItem();
                                runningItem3.setPid(runningTaskInfo.id);
                                pkgnames = runningTaskInfo.topActivity.getPackageName();
                                runningItem3.setPak(pkgnames);
                                try {
                                    icon = packageManager.getApplicationIcon(packageManager.getApplicationInfo(runningTasks.get(i3).topActivity.getPackageName(), PackageManager.GET_META_DATA));
                                } catch (PackageManager.NameNotFoundException e16) {
                                    e16.printStackTrace();
                                }
                                runningItem3.setIcon(icon);
                                try {
                                    Applabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(runningTasks.get(i3).topActivity.getPackageName(), PackageManager.GET_META_DATA));
                                } catch (PackageManager.NameNotFoundException e17) {
                                    e17.printStackTrace();
                                }
                                runningItem3.setLabel(Applabel.toString());
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
                process_memory();
            } catch (NullPointerException e20) {
                e20.printStackTrace();
            }
            return null;
        }


        public void onPostExecute(String str) {
            super.onPostExecute(str);
            if (checkVal == 2) {
                GOTONextActivity();
            }
            checkVal = 1;
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

    public void GOTONextActivity() {
        handler.postDelayed(new Runnable() {

            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();

            }
        }, 2500);
    }


    public void onResume() {
        super.onResume();
    }
}
