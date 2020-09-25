package com.mvp.base.common

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.mvp.base.R


class LoadingDialog(context: Context) : AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)

    }

    override fun onStart() {
        super.onStart()

        //背景透明
        window?.setBackgroundDrawableResource(R.color.transparent)
    }
}
