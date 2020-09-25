package com.xiulian.thecara.base

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mvp.base.base.MvpFragment
import com.xiulian.thecara.R
import com.xiulian.thecara.widget.TitleBar

/**
 * @author wzh
 * @date 2019/8/2
 */
abstract class BaseFragment : MvpFragment() {
    override fun setupLayoutId(): Int = R.layout.fragment_base

    lateinit var titleBar: TitleBar
    lateinit var contentView: ViewGroup


    override fun init(savedInstanceState: Bundle?) {
        contentView = findViewById(R.id.content_view)
        titleBar = findViewById(R.id.title_bar)

        titleBar.setupActivity(activity as BaseActivity)
        LayoutInflater.from(activity!!).inflate(setupContentLayoutId(), contentView)
        initView(savedInstanceState)
    }


    abstract fun setupContentLayoutId(): Int
    abstract fun initView(savedInstanceState: Bundle?)


    /**
     * 设置标题栏不占空间,叠在内容区的上方
     */
    protected fun setTitleBarOverlap() {
        val params = contentView.layoutParams as CoordinatorLayout.LayoutParams
        params.behavior = null
    }
}
