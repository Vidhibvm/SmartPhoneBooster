package com.phonecleaner.fastbooster.safe;

import android.graphics.drawable.Drawable;

public class RunningItem {
    private int Pid;
    private boolean chk;
    private Drawable icon;
    private String installDir;
    private String installSize;
    private CharSequence label;
    private String pak;
    private double s;
    private int size;
    private double val;

    public RunningItem() {
    }

    public RunningItem(CharSequence charSequence, String str, Drawable drawable, boolean z, int i, int i2) {
        this.label = charSequence;
        this.pak = str;
        this.icon = drawable;
        this.chk = z;
        this.Pid = i;
        this.size = i2;
    }

    public double getS() {
        return this.s;
    }

    public String getInstallDir() {
        return this.installDir;
    }

    public void setInstallDir(String str) {
        if (str == null) {
            str = "";
        }
        this.installDir = str;
    }

    public CharSequence getLabel() {
        return this.label;
    }

    public void setLabel(CharSequence charSequence) {
        this.label = charSequence;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public void setIcon(Drawable drawable) {
        this.icon = drawable;
    }

    public boolean isChk() {
        return this.chk;
    }

    public void setChk(boolean z) {
        this.chk = z;
    }

    public String getPak() {
        return this.pak;
    }

    public void setPak(String str) {
        this.pak = str;
    }

    public int getPid() {
        return this.Pid;
    }

    public void setPid(int i) {
        this.Pid = i;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int i) {
        this.size = i;
    }

    public String getInstallSize() {
        return this.installSize;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setInstallSize(String string2) {
        String string3 = string2 != null ? string2 : "";
        this.installSize = string3;
        String string4 = string2.substring(0, string2.indexOf(" "));
        if (string4.contains((CharSequence)",")) {
            String string5;
            block7 : {
                String string6;
                String[] arrstring = string4.split(",");
                try {
                    String string7 = arrstring[0];
                    String string8 = arrstring[1];
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("");
                    stringBuilder.append(string7);
                    stringBuilder.append("");
                    stringBuilder.append(string8);
                    string5 = stringBuilder.toString();
                    break block7;
                }
                catch (Exception exception) {
                    string6 = arrstring[0];
                    exception.printStackTrace();
                }
                string5 = string6;
            }
            this.val = Double.parseDouble((String)string5);
        } else {
            this.val = Double.parseDouble((String)string2.substring(0, string2.indexOf(" ")));
        }
        if (string2.contains((CharSequence)"KB")) {
            this.s = this.val;
            return;
        }
        if (string2.contains((CharSequence)"MB")) {
            this.s = 1024.0 * this.val;
            return;
        }
        this.s = 1048576.0 * this.val;
    }

}
