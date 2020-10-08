package com.xiulian.thecara.base

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mvp.base.base.MvpActivity
import com.xiulian.thecara.R
import com.xiulian.thecara.mvvm.MainActivity
import com.xiulian.thecara.widget.TitleBar

/**
 * 封装标题栏的activity
 * @author wzh
 * @date 2019/7/25
 */
abstract class BaseActivity : MvpActivity() {
    /**
     * 标题栏，请调用init()方法进行初始化
     */
    protected val titleBar: TitleBar by bindView(R.id.title_bar)
    private val contentView: ViewGroup by bindView(R.id.content_view)


    final override fun init(savedInstanceState: Bundle?) {
        titleBar.setupActivity(this)
        if (setupContentLayoutId() != 0) {
            LayoutInflater.from(this).inflate(setupContentLayoutId(), contentView)
        }
        initView(savedInstanceState)
    }


    final override fun setupLayoutId() = R.layout.activity_base

    abstract fun setupContentLayoutId(): Int
    abstract fun initView(savedInstanceState: Bundle?)


    /**
     * 设置标题栏不占空间,叠在内容区的上方
     */
    fun setTitleBarOverlap() {
        val params = contentView.layoutParams as CoordinatorLayout.LayoutParams
        params.behavior = null
    }

    /**
     * 回到MainActivity
     */
    fun backToMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }

//    protected fun checkLogin(): Boolean {
//        val user = getUserInfo()
//        return user.userId.isNotEmpty()
//    }


}