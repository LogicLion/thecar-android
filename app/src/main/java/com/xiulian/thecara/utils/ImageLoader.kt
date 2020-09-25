package com.xiulian.thecara.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.mvp.base.util.third.GlideRightRoundTransform
import com.mvp.base.util.third.GlideRoundTransform
import com.xiulian.thecara.R
import java.io.File
import java.text.DecimalFormat


/**
 *  图片加载，图片处理
 * @author wzh
 * @date 2019/7/15
 */
object ImageLoader {
    private var headOptions: RequestOptions
    private var coverOptions: RequestOptions
    private var roundTransform: GlideRoundTransform
    private var rightRoundTransform: GlideRightRoundTransform

    init {

        //头像
        val circleOptions = RequestOptions()
        headOptions = circleOptions.error(R.drawable.mine_avatar)
            .placeholder(R.drawable.mine_avatar)
            .circleCrop()

        //封面
        val options = RequestOptions()
        //placeholder不能用.9图,否则在同时设置placeholder和淡入效果的情况下会显示异常
        coverOptions = options
//            .placeholder(R.drawable.bg_cover).error(R.drawable.bg_cover)

        //圆角矩形
        roundTransform = GlideRoundTransform(8)

        rightRoundTransform = GlideRightRoundTransform(10)

    }


    /**
     * 加载头像
     * @param context context
     * @param imageUrl 图片url
     * @param view 目标imageView
     */
    fun loadHeadImage(context: Context, imageUrl: String?, view: ImageView) {
        Glide.with(context).load(imageUrl).apply(headOptions).into(view)
    }

    /**
     * 加载封面图
     * @param context context
     * @param imageUrl 图片url
     * @param view 目标imageView
     */
    fun loadCoverImage(context: Context, imageUrl: String?, view: ImageView) {
        //用.9图的话,右边和下边务必黑线画满
        view.setBackgroundResource(R.drawable.bg_cover)
        Glide.with(context).load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade(200))
            .centerCrop()
            .apply(coverOptions)
            .into(view)
    }

    /**
     * 加载封面图--圆角
     * @param context context
     * @param imageUrl 图片url
     * @param view 目标imageView
     */
    fun loadCornerCoverImage(context: Context, imageUrl: String?, view: ImageView) {
        view.setBackgroundResource(R.drawable.bg_cover)
        Glide.with(context).load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade(200))
            .transform(roundTransform)
            .apply(coverOptions)
            .into(view)
    }

    /**
     * 加载封面图--只有右上和右下是圆角
     */
    fun loadRightCornerCoverImage(context: Context, imageUrl: String?, view: ImageView) {
        Glide.with(context)
            .load(imageUrl)
            .transform(rightRoundTransform)
            .into(view)
    }

    /**
     * 清除图片缓存
     */
    fun clearDiskCache(context: Context) {
        Glide.get(context).clearDiskCache()
    }

    /**
     * 获取缓存大小
     */
    fun getCacheSize(context: Context): String {
        val cacheDir = Glide.getPhotoCacheDir(context)
        val parentFile = cacheDir!!.parentFile
        val size = getDirSize(parentFile).toDouble()
        return byteConversionGBMBKB(size)
    }

    private fun getDirSize(dir: File?): Long {
        if (dir == null) {
            return 0
        }
        if (!dir.isDirectory) {
            return 0
        }
        var dirSize: Long = 0
        val files = dir.listFiles()
        for (file in files) {
            if (file.isFile) {
                dirSize += file.length()
            } else if (file.isDirectory) {
                dirSize += file.length()
                dirSize += getDirSize(file) // 递归调用继续统计
            }
        }
        return dirSize
    }

    private val GB = (1024L * 1024L * 1024L).toDouble()// 定义GB的计算常量
    private val MB = (1024L * 1024L).toDouble()// 定义MB的计算常量
    private val KB = 1024.0// 定义KB的计算常量

    private fun byteConversionGBMBKB(kSize: Double): String {
        val df = DecimalFormat("#.00")
        var temp = 0.0
        if (kSize / GB >= 1) {
            temp = kSize / GB
            return df.format(temp) + "G"
        } else if (kSize / MB >= 1) {
            temp = kSize / MB
            return df.format(temp) + "M"
        } else if (kSize / KB >= 1) {
            temp = kSize / KB
            return df.format(temp) + "K"
        }
        return kSize.toString() + "B"
    }


}
