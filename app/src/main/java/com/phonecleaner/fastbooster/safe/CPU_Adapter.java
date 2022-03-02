package com.phonecleaner.fastbooster.safe;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CPU_Adapter extends BaseAdapter {
    Context context;
    LayoutInflater mInflater;
    private long ramused;
    int totSiz;

    public long getItemId(int i) {
        return 0;
    }

    CPU_Adapter(Context context2) {
        this.context = context2;
        this.mInflater = LayoutInflater.from(context2);
    }

    public int getCount() {
        return util.CoolerListmApps.size();
    }

    public RunningItem getItem(int i) {
        return util.CoolerListmApps.get(i);
    }

    @SuppressLint("WrongConstant")
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.running_item, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.textView5);
            viewHolder.app_size = (TextView) view.findViewById(R.id.appSize);
            viewHolder.icon = (ImageView) view.findViewById(R.id.imageView2);
            viewHolder.prog1 = (ImageView) view.findViewById(R.id.prog1);
            viewHolder.prog2 = (ImageView) view.findViewById(R.id.prog2);
            viewHolder.prog3 = (ImageView) view.findViewById(R.id.prog3);
            viewHolder.prog4 = (ImageView) view.findViewById(R.id.prog4);
            viewHolder.prog5 = (ImageView) view.findViewById(R.id.prog5);
            viewHolder.chk = (CheckBox) view.findViewById(R.id.checkBox);
            viewHolder.title.setTypeface(MainApp.RobotoRegular);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        try {
            viewHolder.title.setText(util.CoolerListmApps.get(i).getLabel());
          /*  if (Build.VERSION.SDK_INT >= 26) {
                int s = (int) util.CoolerListmApps.get(i).getS();
                viewHolder.app_size.setText("" + util.formatSize((float) s));
            } else {
                viewHolder.app_size.setText("" + util.formatSize((float) util.CoolerListmApps.get(i).getSize()));
            }*/
            viewHolder.app_size.setText("" + util.formatSize((float) util.CoolerListmApps.get(i).getSize()));
            viewHolder.icon.setBackgroundDrawable(util.CoolerListmApps.get(i).getIcon());
           /* if (Build.VERSION.SDK_INT >= 26) {
                this.totSiz = (int) util.CoolerListmApps.get(i).getS();
            } else {
                this.totSiz = util.CoolerListmApps.get(i).getSize();
            }*/
            this.totSiz = util.CoolerListmApps.get(i).getSize();
            float totalMemory = (((float) (this.totSiz / 1024)) * 100.0f) / ((float) getTotalMemory());
            double d = (double) totalMemory;
            if (d <= 0.2d) {
                viewHolder.prog1.setVisibility(0);
                viewHolder.prog2.setVisibility(4);
                viewHolder.prog3.setVisibility(4);
                viewHolder.prog4.setVisibility(4);
                viewHolder.prog5.setVisibility(4);
            } else if (d <= 0.5d) {
                viewHolder.prog1.setVisibility(0);
                viewHolder.prog2.setVisibility(0);
                viewHolder.prog3.setVisibility(4);
                viewHolder.prog4.setVisibility(4);
                viewHolder.prog5.setVisibility(4);
            } else if (totalMemory <= 1.0f) {
                viewHolder.prog1.setVisibility(0);
                viewHolder.prog2.setVisibility(0);
                viewHolder.prog3.setVisibility(0);
                viewHolder.prog4.setVisibility(4);
                viewHolder.prog5.setVisibility(4);
            } else if (totalMemory <= 5.0f) {
                viewHolder.prog1.setVisibility(0);
                viewHolder.prog2.setVisibility(0);
                viewHolder.prog3.setVisibility(0);
                viewHolder.prog4.setVisibility(0);
                viewHolder.prog5.setVisibility(4);
            } else {
                viewHolder.prog1.setVisibility(0);
                viewHolder.prog2.setVisibility(0);
                viewHolder.prog3.setVisibility(0);
                viewHolder.prog4.setVisibility(0);
                viewHolder.prog5.setVisibility(0);
            }
            if (util.CoolerListmApps.get(i).isChk()) {
                viewHolder.chk.setChecked(true);
            } else {
                viewHolder.chk.setChecked(false);
            }
            viewHolder.title.setTypeface(MainApp.RobotoRegular);
            viewHolder.app_size.setTypeface(MainApp.RobotoRegular);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return view;
    }

    public class ViewHolder {
        private TextView app_size;
        private CheckBox chk;
        private ImageView icon;
        private ImageView prog1;
        private ImageView prog2;
        private ImageView prog3;
        private ImageView prog4;
        private ImageView prog5;
        private TextView title;

        public ViewHolder() {
        }
    }

    public long getTotalMemory() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            String[] split = bufferedReader.readLine().split("\\s+");
            for (String str : split) {
            }
            int intValue = Integer.valueOf(split[1]).intValue() / 1024;
            bufferedReader.close();
            return (long) intValue;
        } catch (IOException unused) {
            return -1;
        }
    }

    public long available() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) this.context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
    }
}
