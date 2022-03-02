package com.phonecleaner.fastbooster.safe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.ItemTouchHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MonitorAdapterr extends BaseAdapter {
    public static String OMG;
    public static int pos;
    private int checkNullVal;
    Context context;
    float f;
    private int initial_memory;
    ArrayList<RunningItem> mlist;
    int size1;

    public long getItemId(int i) {
        return 0;
    }

    public MonitorAdapterr(Context context2, ArrayList<RunningItem> arrayList) {
        this.context = context2;
        this.mlist = arrayList;
    }

    public int getCount() {
        return this.mlist.size();
    }

    public RunningItem getItem(int i) {
        return this.mlist.get(i);
    }

    class ViewHolder {
        RelativeLayout DarkAnimation;
        ImageView LightAnimation;
        ImageView image;
        Button kill;
        TextView label;
        TextView runningtext;
        TextView size;

        ViewHolder() {
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(R.layout.monitor_item, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.label = (TextView) view.findViewById(R.id.tvrunapplabel);
            viewHolder.image = (ImageView) view.findViewById(R.id.ivrunapp);
            viewHolder.size = (TextView) view.findViewById(R.id.appsize);
            viewHolder.LightAnimation = (ImageView) view.findViewById(R.id.yellowanimation);
            viewHolder.kill = (Button) view.findViewById(R.id.brunappkill);
            viewHolder.runningtext = (TextView) view.findViewById(R.id.tvRunningtext);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        RunningItem item = getItem(i);
        viewHolder.kill.setTag(Integer.valueOf(i));
        if (item.isChk()) {
            viewHolder.kill.setEnabled(true);
        } else {
            viewHolder.kill.setEnabled(false);
            viewHolder.runningtext.setText(R.string.stoped);
        }
        viewHolder.kill.setOnClickListener(new View.OnClickListener() {
            private String s1;

            public void onClick(View view) {
                try {
                    this.s1 = MonitorAdapterr.this.mlist.get(((Integer) view.getTag()).intValue()).getPak();
                    MonitorAdapterr.pos = ((Integer) view.getTag()).intValue();
                    MonitorAdapterr.OMG = this.s1;
                    Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.parse("package:" + this.s1));
                    ((Activity) MonitorAdapterr.this.context).startActivityForResult(intent, 4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        try {
           /* if (Build.VERSION.SDK_INT >= 26) {
                this.checkNullVal = (int) ((double) item.getSize());
            } else {
                this.checkNullVal = item.getSize();
            }*/
            this.checkNullVal = item.getSize();
            if (this.checkNullVal != 0) {
              /*  if (Build.VERSION.SDK_INT >= 26) {
                    this.size1 = (int) ((double) item.getSize());
                } else {
                    this.size1 = this.mlist.get(i).getSize();
                }*/
                this.size1 = this.mlist.get(i).getSize();
                float totalMemory = (((float) (this.size1 / 1024)) * 100.0f) / ((float) getTotalMemory());
                this.f = totalMemory;
                String format = String.format("%.1f", Float.valueOf(totalMemory));
                TextView textView = viewHolder.size;
                textView.setText("" + format + "%");
            }
            if (item.getLabel() == null) {
                viewHolder.label.setText(item.getPak());
            } else {
                viewHolder.label.setText(item.getLabel());
            }
            if (item.getIcon() == null) {
                viewHolder.image.setBackgroundDrawable(this.context.getResources().getDrawable(R.drawable.icon));
            } else {
                viewHolder.image.setBackgroundDrawable(item.getIcon());
            }
            ViewGroup.LayoutParams layoutParams = viewHolder.LightAnimation.getLayoutParams();
            if (this.f <= 1.0f) {
                layoutParams.width = 30;
            }
            if (this.f >= 1.0f && this.f < 2.0f) {
                layoutParams.width = 50;
            }
            if (this.f >= 2.0f && this.f < 3.0f) {
                layoutParams.width = 70;
            }
            if (this.f >= 3.0f && this.f < 4.0f) {
                layoutParams.width = 100;
            }
            if (this.f >= 4.0f && this.f < 5.0f) {
                layoutParams.width = 120;
            }
            if (this.f >= 5.0f && this.f < 6.0f) {
                layoutParams.width = 140;
            }
            if (this.f >= 6.0f && this.f < 7.0f) {
                layoutParams.width = 160;
            }
            if (this.f >= 7.0f && this.f < 8.0f) {
                layoutParams.width = 180;
            }
            if (this.f >= 8.0f && this.f < 9.0f) {
                layoutParams.width = ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION;
            }
            if (this.f >= 9.0f && this.f < 10.0f) {
                layoutParams.width = 220;
            }
            if (this.f >= 10.0f) {
                layoutParams.width = 240;
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return view;
    }

    public long getTotalMemory() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            String[] split = bufferedReader.readLine().split("\\s+");
            for (String str : split) {
            }
            this.initial_memory = Integer.valueOf(split[1]).intValue() / 1024;
            bufferedReader.close();
            return (long) this.initial_memory;
        } catch (IOException unused) {
            return -1;
        }
    }
}
