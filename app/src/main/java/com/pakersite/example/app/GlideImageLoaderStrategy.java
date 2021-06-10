package com.pakersite.example.app;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.jess.arms.http.imageloader.BaseImageLoaderStrategy;

public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy<GlideImageConfig> {

    @Override
    public void loadImage(Context ctx, GlideImageConfig config) {
        Glide.with(ctx)
                .load(config.getUrl())
                .into(config.getImageView());
    }

    @Override
    public void clear(Context ctx, GlideImageConfig config) {

    }
}
