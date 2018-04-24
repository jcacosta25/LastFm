package com.example.kon3050.lastfm.ui.base;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class CustomDataBindingAdapter {

    @BindingAdapter("imageUrl")
    public static void loadImage(final ImageView imageView, final String url) {
        if(!TextUtils.isEmpty(url)) {
            Picasso.get()
                    .load(url)
                    .fit()
                    .into(imageView);
        }
    }


}
