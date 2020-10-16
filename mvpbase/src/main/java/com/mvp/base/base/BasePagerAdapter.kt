package com.mvp.base.base

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 封装PagerAdapter
 * @author wzh
 * @date 2019/8/8
 */
 abstract class BasePagerAdapter<T>(@LayoutRes  val layoutId: Int) : PagerAdapter(), View.OnClickListener {

    protected lateinit var context: Context
    open var items = mutableListOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun isViewFromObject(view: View, p1: Any): Boolean {
        return view === p1
    }

    override fun getCount() = items.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    //解决viewpager通过notify但是数据不刷新的问题
    override fun getItemPosition(`object`: Any) = POSITION_NONE

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        context = container.context
        val view = LayoutInflater.from(context).inflate(layoutId, container, false)
        view.tag = position
        view.setOnClickListener(this)
        onBind(view, items[position], position)
        container.addView(view)
        return view
    }

    abstract fun onBind(containerView: View, item: T, position: Int)

    override fun onClick(v: View) {
        val position = v.tag as Int
        onItemClick(items[position], position)
    }

    open fun onItemClick(item: T, position: Int) {}

}