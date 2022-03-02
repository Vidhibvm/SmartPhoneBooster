package com.phonecleaner.fastbooster.safe;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;


import java.util.ArrayList;
import java.util.List;

public class MonitorActivity extends Activity {
    public static ArrayList<RunningItem> mList = new ArrayList<>();
    public static MonitorAdapterr madaptor;
    public static ArrayList<RunningItem> sortedList = new ArrayList<>();
 
    private RelativeLayout backLay;
    public ArrayList<RunningItem> checklist = new ArrayList<>();
    Context context;
    ActivityManager localActivityManager;
    PackageManager mPackManager;
    ActivityManager manager;
    private ListView monitorList;
    private ActivityManager.RunningServiceInfo pInfo2;
    List<ActivityManager.RunningAppProcessInfo> runningProcesses;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.monitor_list);
        this.context = this;

        this.monitorList = (ListView) findViewById(R.id.backrunninglist);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.monitorBackLay);
        this.backLay = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                MonitorActivity.this.BackLayFun();
            }
        });
        MonitorAdapterr monitorAdaptor = new MonitorAdapterr(this.context, util.mApps);
        madaptor = monitorAdaptor;
        this.monitorList.setAdapter((ListAdapter) monitorAdaptor);
        this.monitorList.setVisibility(View.VISIBLE);
    }

    public void onBackPressed() {
        BackLayFun();
    }

    public void BackLayFun() {
        startActivity(new Intent(this, BatterySavingActivity.class));
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        finish();
    }

    
    public void onResume() {
        super.onResume();
    }
}
