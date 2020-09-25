package com.mvp.base.util.third;

import android.content.res.Resources;
import android.graphics.*;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

import java.security.MessageDigest;

/**
 * 只有右上，和右下设置圆角
 *在glide4.0上面centerCrop和圆角图片有冲突只能显示一个,让设置了centerCrop的图片也能设置圆角,
 * @author wzh
 * @date 2018/11/29
 */
public class GlideRightRoundTransform extends BitmapTransformation {

    private static float radius = 0f;

    public GlideRightRoundTransform() {
        this(4);
    }

    /**
     * @param dp 圆角半径，单位dp
     */
    public GlideRightRoundTransform(int dp) {
        super();
        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Bitmap bitmap = TransformationUtils.centerCrop(pool, toTransform, outWidth, outHeight);
        return roundCrop(pool, bitmap);
    }

    private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());

        float right = source.getWidth();
        float bottom = source.getHeight();
        float diameter = radius * 2;
        canvas.drawRoundRect(new RectF(right - diameter, 0f, right, bottom), radius, radius, paint);
        canvas.drawRect(new RectF(0f, 0f, right - radius, bottom), paint);

        return result;
    }

    public String getId() {
        return getClass().getName() + Math.round(radius);
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }



}
