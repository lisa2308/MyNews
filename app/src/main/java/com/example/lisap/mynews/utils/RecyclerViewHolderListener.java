package com.example.lisap.mynews.utils;

import android.support.v7.widget.RecyclerView;

public interface RecyclerViewHolderListener<T, VH extends RecyclerView.ViewHolder> {
    void onItemClicked(VH vh, T item, int pos);
}
