package com.xiulian.thecara.widget

import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.mvp.base.util.getColorCompat
import com.mvp.base.util.getStatusBarHeight
import com.xiulian.thecara.R
import com.xiulian.thecara.base.App
import com.xiulian.thecara.base.BaseActivity

/**
 * 标题栏
 * @author wzh
 * @date 2019/9/26
 */
class TitleBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    Toolbar(context, attrs, defStyleAttr) {

    lateinit var activity: BaseActivity

    companion object {
        const val COMMON_STYLE = -1
        const val COMMON_DARK_STYLE = -2
        const val COMMON_OVERLAP_STYLE = -3
        const val COMMON_FRAGMENT_STYLE = -4
        const val BIG_TITLE_STYLE = -5
        const val SHARE_STYLE = -6
        const val TAB_STYLE = -7
        const val MINE_STYLE = -8
        const val SEARCH_STYLE = -9
    }

    var tvTitle: TextView? = null
    var tvSubtitle: TextView? = null
    var ivBack: ImageView? = null
    var tvStatusBar: TextView? = null
    var view: View? = null

    fun setupActivity(activity: BaseActivity) {
        this.activity = activity
    }

    /**
     * 根据指定样式初始化titleBar
     */
    fun init(style: Int = COMMON_STYLE) {
        when (style) {

            //通用样式
            COMMON_STYLE -> {
                view = LayoutInflater.from(context).inflate(R.layout.toolbar_title_view, this)
                tvStatusBar = findViewById(R.id.status_bar)
                tvSubtitle = findViewById(R.id.tv_subtitle)
                tvTitle = findViewById(R.id.tv_title)
                ivBack = findViewById(R.id.iv_back)

                activity.setStatusBarColor(R.color.color_white)
                activity.setStatusBarTextColorBlack()
                this.setBackgroundResource(R.color.color_white)
            }


        }

        ivBack?.setOnClickListener {
            activity.finish()
        }

    }

    override fun setTitle(@StringRes resId: Int) {
        tvTitle?.text = App.instance.getString(resId)
    }

    fun setTitle(title: String) {
        tvTitle?.text = title
    }

    fun setTitleBarBackgroundColor(@ColorRes color: Int) {
        view?.setBackgroundColor(context.getColorCompat(color))
    }

    fun setTitleHide() {
        tvTitle?.visibility = View.GONE
    }

    fun setTitleColor(@ColorRes color: Int) {
        tvTitle?.setTextColor(context.getColorCompat(color))
    }

    fun setSubtitleColor(@ColorRes color: Int) {
        tvSubtitle?.setTextColor(context.getColorCompat(color))
    }

    fun setSubtitle(subtitle: String) {
        tvSubtitle?.visibility = View.VISIBLE
        tvSubtitle?.text = subtitle
    }

    fun setSubtitleHide() {
        tvSubtitle?.visibility = View.GONE
    }

    fun setSubtitleShow() {
        tvSubtitle?.visibility = View.VISIBLE
    }

    fun setSubtitleClickListener(listener: OnClickListener) {
        tvSubtitle?.setOnClickListener(listener)
    }

    fun setBackClickListener(listener: OnClickListener) {
        ivBack?.setOnClickListener(listener)
    }


    fun setTitleBarHide() {
        visibility = View.GONE
    }

    fun setTitleBarShow() {
        visibility = View.VISIBLE
    }

    /**
     * 设置模拟的状态栏可见
     */
    fun setStatusBarViewVisible() {
        val layoutParams = tvStatusBar?.layoutParams
        layoutParams?.height = context.getStatusBarHeight()
        tvStatusBar?.visibility = View.VISIBLE
    }

}
