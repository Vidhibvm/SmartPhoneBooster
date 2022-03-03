package com.phonecleaner.fastbooster.safe;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.phonecleaner.fastbooster.safe.R;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class util {
    public static final String APP_ID = "app923be85a9ee7460396";
    public static int CheckFromWichActivityComming = 0;
    public static String CheckStateOfAlreadyBatteryBoost = "CheckStateOfAlreadyBatteryBoost";
    public static String CheckStateOfAlreadyCooled = "CheckStateOfAlreadyCooled";
    public static String CheckStateOfAlreadyPhoneBoost = "CheckStateOfAlreadyPhoneBoost";
    public static String CheckStateOfAlreadyWifiBoost = "CheckStateOfAlreadyWifiBoost";
    public static ArrayList<RunningItem> CoolerBackupListmApps = new ArrayList<>();
    public static ArrayList<RunningItem> CoolerListmApps = new ArrayList<>();
    public static final long Hour_1 = 3600000;
    public static ArrayList<RunningItem> IgnorListforCheck = new ArrayList<>();
    public static ArrayList<RunningItem> IgnorListforCheckBooster = new ArrayList<>();
    public static ArrayList<RunningItem> ListforDisplayIcons = new ArrayList<>();
    public static String SaveStateOfReturnActivity = "SaveStateOfReturnActivity";
    public static final String TAG = "AdColonyDemo";
    public static String TempAfterCoolVal = "TempValAfterCool";
    public static final String ZONE_ID = "vz47e36ab2300f40c6a5";
    static AudioManager aManager;
    static AudioManager am;
    static BluetoothAdapter bluetooth;
    static int bright;
    public static int checkAdLoaded = 0;
    static ConnectivityManager connManager;
    public static int counter = 0;
    public static int heavyapp = 0;
    public static int heavyappcounter = 0;
    public static ArrayList<RunningItem> installAppList = new ArrayList<>();
    public static boolean isBoosted = false;
    static boolean isEnabled;
    static int l = 0;
    public static ArrayList<RunningItem> mApps = new ArrayList<>();
    static NetworkInfo mWifi;
    static LocationManager manager;
    public static ArrayList<RunningItem> runningList = new ArrayList<>();
    public static boolean secondVisit = false;
    public static int sh;
    private static int stat;
    public static int timedrain = 0;

    public static float convertDpToPixel(float f, Context context) {
        return f * (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }


    public static boolean isAccessGranted(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            if ((Build.VERSION.SDK_INT > 19 ? ((AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE)).checkOpNoThrow("android:get_usage_stats", applicationInfo.uid, applicationInfo.packageName) : 0) == 0) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static void permissionDialog(final Context context, final int i) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(1);
        View inflate = LayoutInflater.from(context).inflate(R.layout.permission_dialog, (ViewGroup) null);
        Button button = (Button) inflate.findViewById(R.id.permissionBtn);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.crossImg);

        TextView textView = (TextView) inflate.findViewById(R.id.permissionSubTxt);
        TextView textView2 = (TextView) inflate.findViewById(R.id.permissionTitle);
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor edit = defaultSharedPreferences.edit();
        if (i == 1) {
            textView.setText(R.string.coolerPermissionTxt);
            textView2.setText(R.string.settingPermission);
        } else {
            textView.setText(R.string.settingPermissionTxt);
            textView2.setText(R.string.settingPermission0);
        }
        imageView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                dialog.cancel();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (i == 1) {
                    if (defaultSharedPreferences.getBoolean(context.getString(R.string.isAllowedToAccess), true)) {
                        edit.putBoolean(context.getString(R.string.isAllowedToAccess), false);
                        edit.commit();
                        Toast.makeText(context, context.getString(R.string.permissionGranted), Toast.LENGTH_SHORT).show();
                    }
                } else if (Build.VERSION.SDK_INT >= 23 && !Settings.System.canWrite(context)) {
                    Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
                    intent.setData(Uri.parse("package:" + context.getPackageName()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                dialog.cancel();
            }
        });
        dialog.setCancelable(false);
        dialog.setContentView(inflate);
        dialog.show();
    }

    public static void CustomToast(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.permission_toast, (ViewGroup) null);
        Toast toast = new Toast(context);
        toast.setGravity(80, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(inflate);
        toast.show();
    }




    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static NetworkInfo WifiStatus(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        connManager = connectivityManager;
        return connectivityManager.getNetworkInfo(1);
    }

    public static String formatSize(float f) {
        String str;
        if (f >= 1024.0f) {
            f /= 1024.0f;
            if (f >= 1024.0f) {
                f /= 1024.0f;
                str = "GB";
            } else {
                str = "MB";
            }
        } else {
            str = "KB";
        }
        return String.format(Locale.US, "%d %s", Integer.valueOf((int) f), str);
    }

    public static int contain(ArrayList<RunningItem> arrayList, String str) {
        if (arrayList.size() == 0) {
            l = 2;
        }
        int i = 0;
        while (true) {
            if (i >= arrayList.size()) {
                break;
            } else if (arrayList.get(i).getPak().equalsIgnoreCase(str)) {
                l = 1;
                sh = i;
                break;
            } else {
                l = 2;
                i++;
            }
        }
        return l;
    }

    public static String[] size(String str) {
        String[] strArr = new String[2];
        try {
            int i = 0;
            if (str.contains(",")) {
                String[] split = str.split(Pattern.quote(","));
                while (split.length > 0) {
                    strArr[i] = split[i];
                    i++;
                }
            } else {
                String[] split2 = str.split(Pattern.quote("."));
                while (split2.length > 0) {
                    strArr[i] = split2[i];
                    i++;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (Exception unused) {
        }
        return strArr;
    }

    public static void InstallApp(Context context) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < installedPackages.size(); i++) {
            PackageInfo packageInfo = installedPackages.get(i);
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            RunningItem runningItem = new RunningItem();
            runningItem.setPak(packageInfo.packageName);
            installAppList.add(runningItem);
        }
    }

    public static int duplicateCheck(ArrayList<RunningItem> arrayList, String str) {
        if (arrayList.size() == 0) {
            l = 1;
        }
        int i = 0;
        while (true) {
            if (i < arrayList.size()) {
                if (arrayList.get(i).getPak().equalsIgnoreCase(str)) {
                    l = 2;
                    break;
                }
                l = 1;
                sh = i;
                i++;
            } else {
                break;
            }
        }
        return l;
    }

    public static boolean isMobileDataEnable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            Method declaredMethod = Class.forName(connectivityManager.getClass().getName()).getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static int AutoBrightnessCheck(Context context) {
        int i = Settings.System.getInt(context.getContentResolver(), "screen_brightness_mode", 1);
        bright = i;
        return i;
    }

    public static void ManualBrightnessCheck(Context context) {
        Settings.System.putInt(context.getContentResolver(), "screen_brightness_mode", 0);
    }

    public static void setAutoBrightness(Context context) {
        Settings.System.putInt(context.getContentResolver(), "screen_brightness_mode", 1);
    }

    public static int brightneStatus(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "screen_brightness", -1);
    }

    public static LocationManager GPSStatus(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        manager = locationManager;
        return locationManager;
    }

    public static BluetoothAdapter BluetoothStatus() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetooth = defaultAdapter;
        return defaultAdapter;
    }

    public static boolean AirPlaneModeStatus(Context context) {
        boolean z = false;
        if (Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 1) {
            z = true;
        }
        isEnabled = z;
        return z;
    }

    public static AudioManager SoundModeStatus(Context context) {
        am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        aManager = audioManager;
        return audioManager;
    }

    public static int rotationstatus(Context context) {
        try {
            stat = Settings.System.getInt(context.getContentResolver(), "accelerometer_rotation");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return stat;
    }

    public static int timeOutvalue(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "screen_off_timeout", -1);
    }

    public static void wifiOn(Context context, boolean z) {
        try {
            ((WifiManager) context.getSystemService(Context.WIFI_SERVICE)).setWifiEnabled(z);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("MissingPermission")
    public static void bluetoothOn() {
        try {
            BluetoothAdapter.getDefaultAdapter().enable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("MissingPermission")
    public static void bluetoothOff() {
        try {
            BluetoothAdapter.getDefaultAdapter().disable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void gpsIntent(Context context) {
        Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void AirplaneIntent(Context context) {
        Intent intent = new Intent("android.settings.AIRPLANE_MODE_SETTINGS");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void mobileDataonOff(Context context, boolean z) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            Field declaredField = Class.forName(connectivityManager.getClass().getName()).getDeclaredField("mService");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(connectivityManager);
            Method declaredMethod = Class.forName(obj.getClass().getName()).getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(obj, Boolean.valueOf(z));
        } catch (Exception e) {
            Log.e("Error:", e.toString());
        }
    }

    public static void brightness1(int i, float f, Context context) {
        try {
            Settings.System.putInt(context.getContentResolver(), "screen_brightness", i);
            WindowManager.LayoutParams attributes = ((Activity) context).getWindow().getAttributes();
            attributes.screenBrightness = ((float) i) / 255.0f;
            ((Activity) context).getWindow().setAttributes(attributes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void timeOut(int i, Context context) {
        try {
            Settings.System.putInt(context.getContentResolver(), "screen_off_timeout", i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void syncOnOff(boolean z) {
        try {
            ContentResolver.setMasterSyncAutomatically(z);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void silent(Context context) {
        try {
            ((AudioManager) context.getSystemService(Context.AUDIO_SERVICE)).setRingerMode(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void normal(Context context) {
        try {
            ((AudioManager) context.getSystemService(Context.AUDIO_SERVICE)).setRingerMode(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void soundvibrate(Context context) {
        try {
            ((AudioManager) context.getSystemService(Context.AUDIO_SERVICE)).setRingerMode(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void airplanOnOff(Context context, boolean z, int i) {
        try {
            Settings.System.putInt(context.getContentResolver(), "airplane_mode_on", i);
            Intent intent = new Intent("android.intent.action.AIRPLANE_MODE");
            intent.putExtra("state", z);
            context.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setAutoOrientationEnabled(boolean z, Context context) {
        Settings.System.putInt(context.getContentResolver(), "accelerometer_rotation", z ? 1 : 0);
    }

    public static void hepticFeedbackOnOff(Context context, int i) {
        try {
            Settings.System.putInt(context.getContentResolver(), "haptic_feedback_enabled", i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Double getBatteryCapacity(Context context) {
        Object obj;
        double d;
        try {
            obj = Class.forName("com.android.internal.os.PowerProfile").getConstructor(Context.class).newInstance(context);
        } catch (Exception e) {
            e.printStackTrace();
            obj = null;
        }
        try {
            d = ((Double) Class.forName("com.android.internal.os.PowerProfile").getMethod("getAveragePower", String.class).invoke(obj, "battery.capacity")).doubleValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            d = 0.0d;
        }
        return Double.valueOf(d);
    }

    public static float dp2px(Resources resources, float f) {
        return (f * resources.getDisplayMetrics().density) + 0.5f;
    }

    public static float sp2px(Resources resources, float f) {
        return f * resources.getDisplayMetrics().scaledDensity;
    }

    public static String calculateSize(String str) {
        double d = 0;
        File file = new File(str);
        double d2 = 0.0d;
        String str2 = "Bytes";
        if (file.exists()) {
            d2 = (double) file.length();
            if (d2 > 1.073741824E9d) {
                Double.isNaN(d2);
                d = d2 / 1.073741824E9d;
                str = "GB";
            } else if (d2 > 1048576.0d) {
                Double.isNaN(d2);
                d = d2 / 1048576.0d;
                str = "MB";
            } else if (d2 > 1024.0d) {
                Double.isNaN(d2);
                d = d2 / 1024.0d;
                str = "KB";
            }
            if (d2 <= 1048576.0d) {
                return String.format(Locale.US, "%.1f %s", new Object[]{Double.valueOf(d), str});
            }
            return String.format(Locale.US, "%.0f %s", new Object[]{Double.valueOf(d), str});
        }
        str = str2;
        d = d2;
        if (d2 <= 1048576.0d) {
            return String.format(Locale.US, "%.0f %s", new Object[]{Double.valueOf(d), str});
        }
        return String.format(Locale.US, "%.1f %s", new Object[]{Double.valueOf(d), str});
    }

}
