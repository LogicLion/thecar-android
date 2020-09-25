package com.bixin.bixinexperience.utils

import java.text.SimpleDateFormat




/**
 * 时间戳转换成年月日
 */
fun Long.formatTimeStamp(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    return dateFormat.format(this)
}

/**
 * 时间戳转换成年月日时分秒
 */
fun Long.formatTimeStampToSecond(): String {
    val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
    return dateFormat.format(this)
}