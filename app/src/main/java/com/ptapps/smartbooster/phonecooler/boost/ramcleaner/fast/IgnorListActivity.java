package com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class IgnorListActivity extends Activity implements View.OnClickListener {
    IgnorList_Adaptor Iadaptor;
    public ArrayList<RunningItem> IgnorList = new ArrayList<>();
 
    LinearLayout adContainer;
    ApplicationInfo app;
    private RelativeLayout backLay;
    RelativeLayout bannerAdLay;
    Context context;
    RelativeLayout dummyBannerContainer;
    SharedPreferences.Editor editor;
    private TextView emptyText;
    private RelativeLayout header;
    Drawable icon;
    private RecyclerView ignorList;
    SharedPreferences pref;

    public interface ClickListener {
        void onClick(View view, int i);

        void onLongClick(View view, int i);
    }

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.context = this;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.pref = defaultSharedPreferences;
        this.editor = defaultSharedPreferences.edit();
        setContentView(R.layout.activity_ignor_list);
        this.bannerAdLay = (RelativeLayout) findViewById(R.id.bannerAdLay);
        this.dummyBannerContainer = (RelativeLayout) findViewById(R.id.dummy_banner_container);
        this.adContainer = (LinearLayout) findViewById(R.id.banner_container);

        this.header = (RelativeLayout) findViewById(R.id.ignorListHeader);
        this.backLay = (RelativeLayout) findViewById(R.id.ignorListBackLay);
        this.ignorList = (RecyclerView) findViewById(R.id.IgnorList);
        this.emptyText = (TextView) findViewById(R.id.emptyListTxt);
        this.ignorList.setLayoutManager(new LinearLayoutManager(this.context));
        this.backLay.setOnClickListener(this);
        this.emptyText.setTypeface(AppAnaylatics.RobotoLight);
        ArrayList<RunningItem> pakgList = new IgnorList_DataBase(this.context).getPakgList();
        this.IgnorList = pakgList;
        if (pakgList.size() > 0) {
            this.emptyText.setVisibility(View.INVISIBLE);
        }
        for (int i = 0; i < this.IgnorList.size(); i++) {
            try {
                this.icon = getPackageManager().getApplicationIcon(this.IgnorList.get(i).getPak());
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            this.IgnorList.get(i).setIcon(this.icon);
            try {
                this.app = this.context.getPackageManager().getApplicationInfo(this.IgnorList.get(i).getPak(), 0);
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
            try {
                this.IgnorList.get(i).setLabel(getPackageManager().getApplicationLabel(this.app));
            } catch (NullPointerException e3) {
                e3.printStackTrace();
            }
        }
        IgnorList_Adaptor ignorList_Adaptor = new IgnorList_Adaptor(this.context, this.IgnorList);
        this.Iadaptor = ignorList_Adaptor;
        this.ignorList.setAdapter(ignorList_Adaptor);
        this.ignorList.addOnItemTouchListener(new RecyclerTouchListener(this.context, this.ignorList, new ClickListener() {
            /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.IgnorListActivity.AnonymousClass1 */

            @Override // com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.IgnorListActivity.ClickListener
            public void onLongClick(View view, int i) {
            }

            @Override // com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.IgnorListActivity.ClickListener
            public void onClick(View view, int i) {
                new IgnorList_DataBase(IgnorListActivity.this.context).deletePak(IgnorListActivity.this.IgnorList.get(i).getPak());
                IgnorListActivity.this.IgnorList.get(i).setChk(true);
                Utils.CoolerListmApps.add(0, IgnorListActivity.this.IgnorList.get(i));
                IgnorListActivity.this.IgnorList.remove(i);
                IgnorListActivity.this.Iadaptor.notifyItemRemoved(i);
                if (IgnorListActivity.this.IgnorList.size() == 0) {
                    IgnorListActivity.this.emptyText.setVisibility(View.VISIBLE);
                }
            }
        }));
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
        private ClickListener clickListener;
        private GestureDetector gestureDetector;

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        }

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener2) {
            this.clickListener = clickListener2;
            this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                /* class com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast.IgnorListActivity.RecyclerTouchListener.AnonymousClass1 */

                public boolean onSingleTapUp(MotionEvent motionEvent) {
                    return true;
                }

                public void onLongPress(MotionEvent motionEvent) {
                    ClickListener clickListener;
                    View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                    if (findChildViewUnder != null && (clickListener = clickListener2) != null) {
                        clickListener.onLongClick(findChildViewUnder, recyclerView.getChildPosition(findChildViewUnder));
                    }
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            if (findChildViewUnder == null || this.clickListener == null || !this.gestureDetector.onTouchEvent(motionEvent)) {
                return false;
            }
            this.clickListener.onClick(findChildViewUnder, recyclerView.getChildPosition(findChildViewUnder));
            return false;
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.ignorListBackLay) {
            startActivity(new Intent(this.context, CoolingActivity.class));
            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            finish();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this.context, CoolingActivity.class));
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        finish();
    }

    
    public void onResume() {
        super.onResume();
    }
}
