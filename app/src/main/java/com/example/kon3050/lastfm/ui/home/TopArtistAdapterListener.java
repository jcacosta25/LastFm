package com.example.kon3050.lastfm.ui.home;

import android.view.View;

public interface TopArtistAdapterListener<T> {
    public void onItemClick(View view, int position, T item);
}
