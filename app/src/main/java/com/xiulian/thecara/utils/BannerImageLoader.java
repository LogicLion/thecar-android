package com.xiulian.thecara.utils;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * @author wzh
 * @date 2020/10/18
 */
public class BannerImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        ImageUtil.INSTANCE.loadCoverImage(context, (String) path,imageView);
    }
}
