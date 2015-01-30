package com.telstra.androidproficiencyapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FactsListRowHolder extends RecyclerView.ViewHolder {
    public ImageView thumbnail;
    public TextView tvTitle;
    public TextView tvDescription;

    public FactsListRowHolder(View view) {
        super(view);
        this.thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        this.tvDescription = (TextView) view.findViewById(R.id.tvDescription);
    }

}