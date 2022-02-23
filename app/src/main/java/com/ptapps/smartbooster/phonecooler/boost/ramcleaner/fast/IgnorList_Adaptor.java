package com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IgnorList_Adaptor extends RecyclerView.Adapter<IgnorList_Adaptor.MyViewHolder> {
    Context context;
    ArrayList<RunningItem> list;
    LayoutInflater mInflator;

    IgnorList_Adaptor(Context context2, ArrayList<RunningItem> arrayList) {
        this.context = context2;
        this.mInflator = LayoutInflater.from(context2);
        this.list = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(this.mInflator.inflate(R.layout.ignorlist_item, (ViewGroup) null));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.appIcon.setBackgroundDrawable(this.list.get(i).getIcon());
        myViewHolder.title.setText(this.list.get(i).getLabel());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    /* access modifiers changed from: package-private */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView DeletChk;
        ImageView appIcon;
        TextView title;

        public MyViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.ignorTitleTxt);
            this.DeletChk = (ImageView) view.findViewById(R.id.ignorDeletImg);
            this.appIcon = (ImageView) view.findViewById(R.id.appIconList);
            this.title.setTypeface(AppAnaylatics.RobotoLight);
        }
    }
}
