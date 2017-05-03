package com.syuki.panerecyclerviewadapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syuki.recyclerview.adapter.PaneRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);

        List<String> mItems = new ArrayList<>();
        for (int i = 0; i < 50; i++) { mItems.add("item" + i); }
        MyAdapter adapter = new MyAdapter(this, mItems);
        PaneRecyclerViewAdapter paneRecyclerViewAdapter = new PaneRecyclerViewAdapter(adapter);
        TextView headerTextView = new TextView(this);
        headerTextView.setText("header view");
        TextView footerTextView = new TextView(this);
        footerTextView.setText("footer view");
        paneRecyclerViewAdapter.setHeaderView(headerTextView);
        paneRecyclerViewAdapter.setFooterView(footerTextView);

        int linerLayoutOrientation = mLayoutManager.getOrientation();
        DividerItemDecoration divider = new DividerItemDecoration(this, linerLayoutOrientation);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(paneRecyclerViewAdapter);
        mRecyclerView.addItemDecoration(divider);
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        LayoutInflater mInflater;
        List<String> mItems;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView itemText;

            public ViewHolder(View v){
                super(v);
                itemText = (TextView)v.findViewById(R.id.item_text);
            }
        }

        public MyAdapter(Context context, List<String> items) {
            mInflater = LayoutInflater.from(context);
            mItems = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mInflater.inflate(R.layout.item_nomal, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.itemText.setText(mItems.get(position));
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }
}
