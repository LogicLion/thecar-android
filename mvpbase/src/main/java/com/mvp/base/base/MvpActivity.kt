package com.mvp.base.base

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.mvp.base.common.LoadingDialog
import com.mvp.base.util.ToastTime
import com.mvp.base.util.getColorCompat
import com.mvp.base.util.showToast

/**
 * @author TuFei
 * @date 18-11-17.
 */
abstract class MvpActivity : AppCompatActivity() {
    protected val TAG = javaClass.simpleName
    private var loadingDialog: LoadingDialog? = null

    @Deprecated("为了封装做的妥协，请不要调用")
    var _presenter: IBasePresenter<out IBaseView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "$TAG : onCreate()")
        //无法使用这种方式锁定竖屏。如果设定了这行代码，实现锁定竖屏，
        //一旦用户手机不锁定方向，横着打开界面。 界面会直接闪退。
        //请直接在manifest里面设置android:screenOrientation="portrait"
        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //设置修改状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            //设置状态栏的颜色，和你的app主题或者标题栏颜色设置一致就ok了
            //window.statusBarColor = getColorCompat(R.color.colorPrimary)
        }

        setContentView(setupLayoutId())
        setupPresenter()
        init(savedInstanceState)
    }

    @LayoutRes
    abstract fun setupLayoutId(): Int

    abstract fun setupPresenter()

    abstract fun init(savedInstanceState: Bundle?)

    /**
     * 绑定view到presenter上。当该activity被销毁，在执行[onDestroy]时，会执行
     * presenter的[IBasePresenter.onDetachView]方法。该方法里面，会取消该presenter所有的rx的订阅。这样，就不用在担心异步任务完成后的回调里，执行UI更新操作，会导致空指针等问题。
     *
     * @throws IllegalArgumentException 如果该activity没有实现presenter需要的view接口
     */
    inline fun <reified V : IBaseView, P : IBasePresenter<V>, T : P> attach(t: T) {
        when {
            this is V -> {
                _presenter = t
                t.onAttachView(this)
            }
            else -> throw  IllegalArgumentException(
                "${this.javaClass.simpleName} hadn't implement the interface " +
                        "${V::class.java.simpleName}.So it can't attach to ${t.javaClass.simpleName}"
            )
        }
    }


//    fun <V : IBaseView, P : IBasePresenter<V>, T : P> attact(t: T) {
//        if (this is IBaseView) {
//            _presenter = t
//            t.onAttachView(this)
//        }
//
//    }

    fun showLoading() {
        //实现请求接口等异步操作时的加载弹窗
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(this)
        }

        loadingDialog!!.setCancelable(true)
        loadingDialog!!.show()
    }

    fun showLoading(msg: String) {
        //实现请求接口等异步操作时的加载弹窗
    }

    fun showLoading(msg: String, cancelable: Boolean) {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(this)
        }
        loadingDialog!!.setCancelable(cancelable)
        loadingDialog!!.show()

    }

    fun showLoading(msg: String, cancelable: Boolean, isBackgroundTransparent: Boolean) {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(this)
        }

        loadingDialog!!.setCancelable(cancelable)
        if (isBackgroundTransparent) {
            loadingDialog!!.window?.setDimAmount(0.0f)
        }
        loadingDialog!!.show()
    }


    fun hideLoading() {
        //请求接口等异步操作结束，隐藏加载弹窗
        loadingDialog?.dismiss()
    }

    fun showToast(tip: String) {
        (this as Context).showToast(tip, ToastTime.SHORT)
    }

    fun showToast(@StringRes resId: Int) {
        (this as Context).showToast(getString(resId), ToastTime.SHORT)
    }

    override fun onDestroy() {
        Log.i(TAG, "$TAG : onDestroy()")
        _presenter?.onDetachView()
        //销毁顺序与初始化顺序应该相反，所以，放在最后面
        super.onDestroy()
    }

    fun <T : View> Activity.bindView(@IdRes res: Int): Lazy<T> {
        return lazy { findViewById<T>(res) }
    }

    /**
     * 设置状态栏不占空间
     */
    fun setStatusBarNotPlaceHolder() {
        //得到当前界面的装饰视图
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            //让应用主题内容占用系统状态栏的空间,注意:下面两个参数必须一起使用 （stable 牢固的）
            val option = decorView.systemUiVisibility
            decorView.systemUiVisibility =
                option or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            //设置状态栏颜色为透明
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    /**
     * 隐藏状态栏和文字
     */
    fun setStatusBarAndTextHide() {
        //得到当前界面的装饰视图
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
//            val option = View.SYSTEM_UI_FLAG_FULLSCREEN
//            decorView.systemUiVisibility = option

            decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_FULLSCREEN//隐藏状态栏
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    )
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            //设置状态栏颜色为透明
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    /**
     * 设置状态栏颜色
     */
    fun setStatusBarColor(@ColorRes color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = this.getColorCompat(color)
        }
    }

    /**
     * 设置状态栏文字颜色为黑色
     */
    fun setStatusBarTextColorBlack() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //设置状态栏文字图标为黑色
            val decorView = window.decorView
            val option = decorView.systemUiVisibility
            decorView.systemUiVisibility = option or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    /**
     * 设置状态栏文字颜色为白色
     */
    fun setStatusBarTextColorWhite() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //设置状态栏文字图标为黑色
            val decorView = window.decorView
            val option = decorView.systemUiVisibility
            decorView.systemUiVisibility = option or View.SYSTEM_UI_FLAG_VISIBLE
        }
    }
}