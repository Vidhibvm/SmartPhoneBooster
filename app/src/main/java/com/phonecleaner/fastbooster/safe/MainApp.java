package com.phonecleaner.fastbooster.safe;

import android.app.Application;
import android.graphics.Typeface;


public class MainApp extends Application {
    public static Typeface RobotoBold;
    public static Typeface RobotoLight;
    public static Typeface RobotoRegular;
    public static Typeface TitilliumWebRegular;

    public void onCreate() {
        super.onCreate();
        try {
            RobotoBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
            RobotoLight = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
            RobotoRegular = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        } catch (Throwable unused) {
        }
        try {
            Class.forName("android.os.AsyncTask");
        } catch (Throwable unused2) {
        }
    }
}
