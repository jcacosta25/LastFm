package com.example.kon3050.lastfm.ui.utils


import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.kon3050.lastfm.R

class MarginDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val margin: Int = context.resources.getDimensionPixelSize(R.dimen.item_margin)

    override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        outRect.set(margin, margin, margin, margin)
    }
}