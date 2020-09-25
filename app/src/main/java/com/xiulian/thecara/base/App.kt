package com.xiulian.thecara.base

import android.util.Log
import com.mvp.base.base.MvpApp
import com.xiulian.thecara.utils.LogUtil

/**
 * @author wzh
 * @date 2019/7/25
 */
class App : MvpApp() {

    override fun init() {
        super.init()

        instance = this
        LogUtil.i("App","app初始化")

        initSDK()
    }

    companion object {
        @JvmStatic lateinit var instance: App
            private set
    }



    fun initSDK() {
        //启动bugly组件，bugly组件为腾讯提供的用于crash上报和分析的开放组件，如果您不需要该组件，可以自行移除
//        val strategy = CrashReport.UserStrategy(applicationContext)
//        strategy.setAppVersion(TXLiveBase.getSDKVersionStr())
//        CrashReport.initCrashReport(applicationContext, TCConstants.BUGLY_APPID, true, strategy)

        //设置rtmpsdk log回调，将log保存到文件
        //        TXLiveBase.setListener(new TCLog(getApplicationContext()));


        Log.w("TCLog", "app init sdk")
    }

}