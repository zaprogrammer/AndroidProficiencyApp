package com.telstra.androidproficiencyapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.telstra.androidproficiencyapp.FactsListRowHolder;
import com.telstra.androidproficiencyapp.R;
import com.telstra.androidproficiencyapp.beans.Facts;
import com.telstra.androidproficiencyapp.beans.Row;

public class FactsRecyclerAdapter extends RecyclerView.Adapter<FactsListRowHolder> {

    private Facts factItemList;
    private Context mContext;

    public FactsRecyclerAdapter(Context context, Facts factItemList) {
        this.factItemList = factItemList;
        this.mContext = context;
    }

    @Override
    public FactsListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup, false);
        FactsListRowHolder factsListRowHolder = new FactsListRowHolder(v);
        return factsListRowHolder;
    }

    @Override
    public void onBindViewHolder(FactsListRowHolder factListRowHolder, int i) {
        Row factItem = factItemList.getRows().get(i);

        Picasso.with(mContext).load(factItem.getImageHref())
                .resize(140, 120)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(factListRowHolder.thumbnail);

        factListRowHolder.tvTitle.setText(factItem.getTitle());
        factListRowHolder.tvDescription.setText(factItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return (null != factItemList ? factItemList.getRows().size() : 0);
    }
}