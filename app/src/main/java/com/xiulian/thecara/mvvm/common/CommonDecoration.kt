package com.xiulian.thecara.mvvm.common

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.mvp.base.util.dpToPx
import com.xiulian.thecara.base.App

/**
 * 通用recyclerView的decoration
 * @author wzh
 * @date 2019/8/14
 */
class CommonDecoration(
    var verticalSpacing: Int = 0,
    var horizontalSpacing: Int = 0,
    var topMargin: Int = 0,
    var bottomMargin: Int = 0,
    var leftMargin: Int = 0,
    var rightMargin: Int = 0

) : RecyclerView.ItemDecoration() {

    private var halfVerticalSpacing: Int
    private var halfHorizontalSpacing: Int

    init {
        verticalSpacing = convertDp(verticalSpacing)
        horizontalSpacing = convertDp(horizontalSpacing)
        topMargin = convertDp(topMargin)
        bottomMargin = convertDp(bottomMargin)
        leftMargin = convertDp(leftMargin)
        rightMargin = convertDp(rightMargin)

        halfVerticalSpacing = verticalSpacing / 2
        halfHorizontalSpacing = horizontalSpacing / 2

    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)

        //总数
        val itemCount = parent.adapter!!.itemCount

        when (val layoutManager = parent.layoutManager) {
            is GridLayoutManager -> {
                //获取列数
                val spanCount = layoutManager.spanCount

                //行数
                val lineCount = itemCount / spanCount + if (itemCount % spanCount == 0) 0 else 1

                if (position <= spanCount - 1) {
                    //第一行
                    outRect.top = topMargin
                } else {
                    outRect.top = halfVerticalSpacing
                }

                if (position > (lineCount - 1) * spanCount - 1) {
                    //最后一行
                    outRect.bottom = bottomMargin
                } else {
                    outRect.bottom = halfVerticalSpacing
                }

                if (position % spanCount == 0) {
                    //第一列
                    outRect.left = leftMargin
                } else {
                    outRect.left = halfHorizontalSpacing
                }

                if (position % spanCount == spanCount - 1) {
                    //最后一列
                    outRect.right = rightMargin
                } else {
                    outRect.right = halfHorizontalSpacing
                }
//
//                Log.i("间隔", "总列数：${spanCount},总行数：${lineCount}")
//                Log.i("间隔", "左：${outRect.left},右：${outRect.right},上:${outRect.top},下：${outRect.bottom}")

            }

            is LinearLayoutManager -> {
                if (position == 0) {
                    //第一个
                    outRect.top = topMargin
                } else {
                    outRect.top = verticalSpacing
                }
                if (position == itemCount - 1) {
                    //最后一个
                    outRect.bottom = bottomMargin
                }
                outRect.left = leftMargin
                outRect.right = rightMargin
            }


        }

    }

    private fun convertDp(value: Int): Int {
        val context = App.instance
        return context.dpToPx(value)
    }
}
