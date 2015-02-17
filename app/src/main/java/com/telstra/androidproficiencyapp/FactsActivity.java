package com.telstra.androidproficiencyapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.telstra.androidproficiencyapp.adapters.FactsRecyclerAdapter;
import com.telstra.androidproficiencyapp.beans.Facts;
import com.telstra.androidproficiencyapp.constants.Constants;


public class FactsActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout contentLayout;
    private FactsRecyclerAdapter factsRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);

        recyclerView = (RecyclerView) findViewById(R.id.facts_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        contentLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        contentLayout.setOnRefreshListener(this);
        contentLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        loadFactsData(true);

    }

    /**
     * Initializes the Async task and call server to load the facts data
     * @param showDialog parameter to indicate wither to show the progress loading dialog or not
     */
    private void loadFactsData(boolean showDialog) {
        AsyncHttpTask asyncHttpTask = new AsyncHttpTask(this, showDialog);
        asyncHttpTask.execute(Constants.FACTS_URL, false);
    }

    /**
     * Overrides the onRefresh() method for the
     * SwipeRefreshLayout to reload the data from the server
     */
    @Override
    public void onRefresh() {
        // The showDialog parameter is set to false as the swipeRefreshLayout has its own loading indicator
        loadFactsData(false);

        contentLayout.setRefreshing(false);
    }

    /**
     * Called from the AsyncHttpTask class after finished loading data from server
     * to populate the recycleView with the data
     *
     * @param factsResponse Facts object with the response data
     */
    public void populateListData(final Facts factsResponse) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setTitle(factsResponse.getTitle());
            }
        });

        factsRecyclerAdapter = new FactsRecyclerAdapter(FactsActivity.this, factsResponse);
        recyclerView.setAdapter(factsRecyclerAdapter);
        factsRecyclerAdapter.notifyDataSetChanged();

    }
}
