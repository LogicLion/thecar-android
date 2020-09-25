package com.mvp.base.base

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mvp.base.util.ToastTime
import com.mvp.base.util.showToast


abstract class MvpFragment : Fragment() {
    protected val TAG = javaClass.simpleName
    private var isCreateView: Boolean = false

    protected var mContentView: View? = null
    @Deprecated("为了封装做的妥协，请不要调用")
    var _presenter: IBasePresenter<out IBaseView>? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mContentView == null) {
            mContentView = inflater.inflate(setupLayoutId(), container, false)
            isCreateView = true
        } else {
            val parent = mContentView!!.parent
            if (parent is ViewGroup) {
                parent.removeView(mContentView)
            }
        }

        return mContentView
    }

    protected fun <T : View> findViewById(@IdRes id: Int): T {
        return mContentView!!.findViewById(id)
    }

    @LayoutRes
    abstract fun setupLayoutId(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupPresenter()
        if (isCreateView) {
            init(savedInstanceState)
            isCreateView = false
        }
    }

    abstract fun setupPresenter()

    abstract fun init(savedInstanceState: Bundle?)

    fun showLoading() {
        //实现请求接口等异步操作时的加载弹窗
    }

    fun showLoading(msg: String) {
        //实现请求接口等异步操作时的加载弹窗
    }

    fun showLoading(msg: String, cancelable: Boolean) {

    }

    fun showLoading(msg: String, cancelable: Boolean, isBackgroundTransparent: Boolean) {
    }


    fun hideLoading() {
        //请求接口等异步操作结束，隐藏加载弹窗
    }


    fun showToast(tip: String) {
        context?.showToast(tip, ToastTime.SHORT)
    }

    fun showToast(@StringRes resId: Int) {
        context?.showToast(getString(resId), ToastTime.SHORT)
    }

    /**
     * 绑定view到presenter上。当该fragment被销毁，在执行[onDestroy]时，会执行
     * presenter的[IBasePresenter.onDetachView]方法。该方法里面，会取消该presenter所有的rx
     * 的订阅。这样，就不用在担心异步任务完成后的回调里，执行UI更新操作，会导致空指针等问题。
     *
     * @throws IllegalArgumentException 如果该fragment没有实现presenter需要的view接口
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

    override fun onDestroyView() {
        _presenter?.onDetachView()
        //销毁顺序与初始化顺序应该相反，所以，放在最后面
        super.onDestroyView()
    }
}