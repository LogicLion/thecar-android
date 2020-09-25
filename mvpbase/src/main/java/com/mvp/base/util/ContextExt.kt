package com.mvp.base.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast

/**
 * @author TuFei
 * @date 18-9-27.
 */

private var toast: Toast? = null

enum class ToastTime {
    LONG, SHORT
}

@SuppressLint("ShowToast")
fun Context.showToast(tip: String, toastTime: ToastTime) {
    val time = when (toastTime) {
        ToastTime.LONG -> Toast.LENGTH_LONG
        ToastTime.SHORT -> Toast.LENGTH_SHORT
    }
    toast?.apply {
        setText(tip)
    } ?: apply {
        toast = Toast.makeText(applicationContext, tip, time)
    }
    toast?.show()
}

@SuppressLint("ShowToast")
fun Context.showToast(tip: String) {
    toast?.apply {
        setText(tip)
    } ?: apply {
        toast = Toast.makeText(applicationContext, tip, Toast.LENGTH_SHORT)
    }
    toast?.show()
}

fun Context.getDrawableCompat(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)


/**
 * 通过ContextCompat.getColor获取color
 *
 * @param id ColorRes
 * @return int
 */
fun Context.getColorCompat(@ColorRes id: Int) = ContextCompat.getColor(this, id)

/**
 * 简写LayoutInflater.from(this).inflate(resource, null)
 *
 * @param resource layoutId
 * @return View
 */
fun Context.inflater(@LayoutRes resource: Int): View =
    LayoutInflater.from(this).inflate(resource, null)

/**
 * 简写LayoutInflater.from(this).inflate(resource, root, attachToRoot)
 *
 * @param resource layoutId
 * @param root root
 * @param attachToRoot attachToRoot
 * @return View
 */
fun Context.inflater(@LayoutRes resource: Int, root: ViewGroup, attachToRoot: Boolean = false): View =
    LayoutInflater.from(this).inflate(resource, root, attachToRoot)


fun Context.dpToPx(dp: Int): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f * if (dp >= 0) 1 else -1).toInt()
}

/**
 * 获取状态栏高度
 * @param context
 * @return
 */
fun Context.getStatusBarHeight(): Int {
    val resources = resources
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return resources.getDimensionPixelSize(resourceId)
}

/**
 * 获取手机屏幕宽度
 */
fun Context.getScreenWidth(): Int {

    val manager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val dm = DisplayMetrics()
    manager.defaultDisplay.getMetrics(dm)
    return dm.widthPixels
}

/**
 * 获取手机屏幕高度
 */
fun Context.getScreenHeight(): Int {

    val manager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val dm = DisplayMetrics()
    manager.defaultDisplay.getMetrics(dm)
    return dm.heightPixels
}

fun Context.intentStart(cls: Class<out Activity>) {
    startActivity(Intent(this, cls))
}