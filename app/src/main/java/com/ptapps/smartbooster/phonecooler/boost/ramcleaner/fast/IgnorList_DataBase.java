package com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class IgnorList_DataBase extends SQLiteOpenHelper {
    public static final String BOOSTER_COLUMN_ID = "_id";
    public static final String BOOSTER_COLUMN_PAK = "package";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PAK = "package";
    private static final String DATABASE_CREATE = "create table ignoreListTable(_id integer primary key autoincrement, package text not null)";
    private static final String DATABASE_CREATE_BOOSTER = "create table ignoreListBoosterTable(_id integer primary key autoincrement, package text not null)";
    private static final String DATABASE_NAME = "Booster.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_BOOSTER_IGNORE_LIST = "ignoreListBoosterTable";
    public static final String TABLE_IGNORE_LIST = "ignoreListTable";
    SQLiteDatabase database = getWritableDatabase();

    public IgnorList_DataBase(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(DATABASE_CREATE);
        sQLiteDatabase.execSQL(DATABASE_CREATE_BOOSTER);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        String name = IgnorList_DataBase.class.getName();
        Log.w(name, "Upgrading database from version " + i + " to " + i2 + ", which will destroy all old data");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ignoreListTable");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ignoreListBoosterTable");
        onCreate(sQLiteDatabase);
    }

    @SuppressLint("Range")
    public ArrayList<RunningItem> getPakgList() {
        ArrayList<RunningItem> arrayList = new ArrayList<>();
        Cursor query = this.database.query(TABLE_IGNORE_LIST, null, null, null, null, null, null);
        if (query != null && query.getCount() > 0) {
            while (query.moveToNext()) {
                RunningItem runningItem = new RunningItem();
                runningItem.setPak(query.getString(query.getColumnIndex("package")));
                runningItem.setPid(query.getInt(query.getColumnIndex("_id")));
                arrayList.add(runningItem);
            }
        }
        return arrayList;
    }

    @SuppressLint("Range")
    public ArrayList<RunningItem> getPakgListForBooster() {
        ArrayList<RunningItem> arrayList = new ArrayList<>();
        Cursor query = this.database.query(TABLE_BOOSTER_IGNORE_LIST, null, null, null, null, null, null);
        if (query != null && query.getCount() > 0) {
            while (query.moveToNext()) {
                RunningItem runningItem = new RunningItem();
                runningItem.setPak(query.getString(query.getColumnIndex("package")));
                runningItem.setPid(query.getInt(query.getColumnIndex("_id")));
                arrayList.add(runningItem);
            }
        }
        return arrayList;
    }

    @SuppressLint("Range")
    public ArrayList<String> getPakgListpkg() {
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor query = this.database.query(TABLE_IGNORE_LIST, null, null, null, null, null, null);
        if (query != null && query.getCount() > 0) {
            while (query.moveToNext()) {
                arrayList.add(query.getString(query.getColumnIndex("package")));
            }
        }
        return arrayList;
    }

    public long insertPak(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("package", str);
        return this.database.insert(TABLE_IGNORE_LIST, null, contentValues);
    }

    public long deletePak(String str) {
        return (long) this.database.delete(TABLE_IGNORE_LIST, "package=?", new String[]{str});
    }

    public long insertPakBooster(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("package", str);
        return this.database.insert(TABLE_BOOSTER_IGNORE_LIST, null, contentValues);
    }

    public long deletePakBooster(String str) {
        return (long) this.database.delete(TABLE_BOOSTER_IGNORE_LIST, "package=?", new String[]{str});
    }

    public long deletePak(int i) {
        SQLiteDatabase sQLiteDatabase = this.database;
        return (long) sQLiteDatabase.delete(TABLE_IGNORE_LIST, "_id=?", new String[]{"" + i});
    }
}
