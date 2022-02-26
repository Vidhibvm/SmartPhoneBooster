package com.phonecleaner.fastbooster.safe;

import android.graphics.drawable.Drawable;

public class ListInstalledAppModel {
    String appName;
    public Drawable icon;
    private int id;
    int permissionsTotal;
    String pkgName;
    int position;
    boolean selected = false;

    public ListInstalledAppModel() {
    }

    public ListInstalledAppModel(int i, String str, String str2, int i2) {
        this.id = i;
        this.pkgName = str;
        this.appName = str2;
        this.permissionsTotal = i2;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public void setIcon(Drawable drawable) {
        this.icon = drawable;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public void setPkgName(String str) {
        this.pkgName = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public int getPermissionsTotal() {
        return this.permissionsTotal;
    }

    public void setPermissionsTotal(int i) {
        this.permissionsTotal = i;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }
}
