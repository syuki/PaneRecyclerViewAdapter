package com.syuki.recyclerview.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class PaneRecyclerViewAdapter extends RecyclerView.Adapter<PaneRecyclerViewAdapter.ViewHolder> {
    public static int ITEM_VIEW_TYPE_HEADER = 310; // 被らない？値
    public static int ITEM_VIEW_TYPE_ITEM   = 311;
    public static int ITEM_VIEW_TYPE_FOOTER = 312;

    private RecyclerView.Adapter mAdapter;

    private View mHeaderView;
    private View mFooterView;

    private int mSpanCount;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView.ViewHolder mItemViewHolder;

        ViewHolder(View itemView) {
            super(itemView);
        }

        ViewHolder(RecyclerView.ViewHolder viewHolder) {
            super(viewHolder.itemView);
            mItemViewHolder = viewHolder;
        }

        RecyclerView.ViewHolder getItemViewHolder() {
            return mItemViewHolder;
        }
    }

    public PaneRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public int getItemViewType(int position) {
        if(isHeader(position)) {
            return ITEM_VIEW_TYPE_HEADER;
        }

        if(isFooter(position)) {
            return ITEM_VIEW_TYPE_FOOTER;
        }

        return ITEM_VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        int extraCount = 0;
        if(mHeaderView != null) extraCount += 1;
        if(mFooterView != null) extraCount += 1;
        return mAdapter.getItemCount() + extraCount;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_VIEW_TYPE_HEADER) {
            return new ViewHolder(mHeaderView);
        }

        if(viewType == ITEM_VIEW_TYPE_FOOTER) {
            return new ViewHolder(mFooterView);
        }

        RecyclerView.ViewHolder viewHolder = mAdapter.onCreateViewHolder(parent, viewType);
        return new ViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(isHeader(position)) return;
        if(isFooter(position)) return;
        int extraCount = mHeaderView != null ? 1 : 0;
        mAdapter.onBindViewHolder(holder.getItemViewHolder(), position - extraCount);
    }

    @Override public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
        mAdapter.registerAdapterDataObserver(observer);
    }

    @Override public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
        mAdapter.unregisterAdapterDataObserver(observer);
    }

    @Override public void onViewRecycled(ViewHolder holder) {
        mAdapter.onViewRecycled(holder);
        super.onViewRecycled(holder);
    }

    @Override public long getItemId(int position) {
        return mAdapter.getItemId(position);
    }

    private boolean isHeader(int position) {
        return mHeaderView != null && position == 0;
    }

    private boolean isFooter(int position) {
        int extraCount = mHeaderView != null ? 1 : 0;
        return mFooterView != null && position == mAdapter.getItemCount() + extraCount;
    }

    /**
     * GridLayoutの場合列揃え？用
     */
    public void setGridLayoutManager(GridLayoutManager gridLayoutManager) {
        mSpanCount = gridLayoutManager.getSpanCount();
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return PaneRecyclerViewAdapter.this.getSpanSize(position);
            }
        });
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
    }

    /**
     * 外からもGridLayoutManagerのSpanSizeLookupをしたい場合用
     */
    public int getSpanSize(int position) {
        if(isHeader(position) || isFooter(position)) {
            return mSpanCount;
        }
        return 1;
    }
}