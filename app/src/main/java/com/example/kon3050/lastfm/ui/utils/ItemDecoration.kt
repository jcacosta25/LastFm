package com.example.kon3050.lastfm.ui.utils

import android.content.Context
import android.graphics.Rect
import android.support.annotation.DimenRes
import android.support.v7.widget.RecyclerView
import android.view.View






class ItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        outRect.left = space
        outRect.right = space
        outRect.top = space
        outRect.bottom = space

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = space
        } else {
            outRect.top = 0
        }
    }
}

class ItemOffsetDecoration(private val mItemOffset: Int) : RecyclerView.ItemDecoration() {

    constructor(context: Context, @DimenRes itemOffsetId: Int) : this(context.getResources().getDimensionPixelSize(itemOffsetId)) {}

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset)
    }
}


class GridSpacingItemDecoration(private val mSpanCount: Int, itemSize: Int) : RecyclerView.ItemDecoration() {
    private val mItemSize: Float = itemSize.toFloat()

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State?) {
        val position = parent.getChildLayoutPosition(view)
        val column = position % mSpanCount
        val parentWidth = parent.width
        val spacing = (parentWidth - mItemSize * mSpanCount).toInt() / (mSpanCount + 1)
        outRect.left = spacing - column * spacing / mSpanCount
        outRect.right = (column + 1) * spacing / mSpanCount

        if (position < mSpanCount) {
            outRect.top = spacing
        }
        outRect.bottom = spacing
    }
}