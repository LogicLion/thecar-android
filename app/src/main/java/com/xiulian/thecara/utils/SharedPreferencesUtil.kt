package com.xiulian.thecara.utils

import android.content.Context
import com.xiulian.thecara.base.App
import com.xiulian.thecara.entity.User

/**
 * @author wzh
 * @date 2019/8/16
 */

private const val CONFIG = "config"

private const val USER_NICKNAME = "user_nickname"
private const val USER_ID = "user_id"
private const val USER_AVATAR = "user_avatar"
private const val USER_PHONE = "user_phone"
private const val USER_SEX = "user_sex"
private const val USER_BIRTHDAY = "user_birthday"
private const val USER_TOKEN = "user_token"
private const val USER_ABSTRACTS = "user_abstracts"
private const val USER_IS_VIP = "user_is_vip"
private const val USER_VIP_TYPE = "user_vip_type"
private const val USER_CITY = "user_city"
private const val USER_WECHAT = "user_wechat"
private const val VIP_TITLE = "vip_title"
private const val VIP_VALID_DATE = "vip_valid_date"

val app = App.instance

/**
 * 保存用户信息
 * @param context
 * @param user
 */
fun saveUserInfo(user: User) {
    val sp = app.getSharedPreferences(CONFIG, Context.MODE_PRIVATE)
    val editor = sp.edit()

    editor.putString(USER_ID, user.userId)
    editor.putString(USER_NICKNAME, user.nickName)
    editor.putString(USER_AVATAR, user.avatarUrl)
    editor.putString(USER_PHONE, user.phone)
    editor.putString(USER_BIRTHDAY, user.birthday)
    editor.putString(USER_SEX, user.sex)
    editor.putString(USER_ABSTRACTS, user.abstracts)
    editor.putString(USER_CITY, user.city)
    editor.putString(USER_WECHAT, user.wechat)
    editor.putBoolean(USER_IS_VIP, user.isVip)
    editor.putString(USER_VIP_TYPE, user.vipType)
    editor.putString(VIP_TITLE, user.vipTitle)
    editor.putString(VIP_VALID_DATE, user.vipValidDate)

    if (!user.token.isNullOrBlank()) {
        editor.putString(USER_TOKEN, user.token)
    }

    editor.commit()
}


/**
 * 获取用户登录的信息
 *
 * @param context
 * @return
 */
fun getUserInfo(): User {
    val sp = app.getSharedPreferences(CONFIG, Context.MODE_PRIVATE)

    val nickName = sp.getString(USER_NICKNAME, "")
    val userId = sp.getString(USER_ID, "")
    val url = sp.getString(USER_AVATAR, "")
    val phone = sp.getString(USER_PHONE, "")
    val sex = sp.getString(USER_SEX, "")
    val birthDay = sp.getString(USER_BIRTHDAY, "")
    val token = sp.getString(USER_TOKEN, "")
    val abstracts = sp.getString(USER_ABSTRACTS, "")
    val city = sp.getString(USER_CITY, "")
    val wechat = sp.getString(USER_WECHAT, "")
    val isVip = sp.getBoolean(USER_IS_VIP, false)
    val vipType = sp.getString(USER_VIP_TYPE, "")
    val vipTitle = sp.getString(VIP_TITLE, "")
    val vipValidDate = sp.getString(VIP_VALID_DATE, "")

    return User(
        userId,
        nickName,
        url,
        phone,
        sex,
        birthDay,
        token,
        abstracts,
        city,
        wechat,
        isVip,
        vipType,
        vipTitle,
        vipValidDate
    )
}

fun checkLogin(): Boolean {
    val sp = app.getSharedPreferences(CONFIG, Context.MODE_PRIVATE)
    val userId = sp.getString(USER_ID, "")
    return !userId.isNullOrBlank()
}

/**
 * 清除用户信息
 */
fun clearUserInfo() {
    val sp = app.getSharedPreferences(CONFIG, Context.MODE_PRIVATE)
    val editor = sp.edit()
    editor.clear()
    editor.commit()
}
