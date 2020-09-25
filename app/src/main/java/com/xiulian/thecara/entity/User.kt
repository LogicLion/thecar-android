package com.xiulian.thecara.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author wzh
 * @date 2019/8/16
 */
data class User(
    @SerializedName(value = "userId", alternate = ["accountId"])
    var userId: String = "",
    var nickName: String? = null,

    @SerializedName(value = "avatarUrl", alternate = ["url", "headIco"])
    var avatarUrl: String? = null,
    var phone: String = "",
    var sex: String? = null,

    var birthday: String? = null,
    var token: String? = null,
    var abstracts: String? = null,//刷新登录状态需要用的值
    var city: String? = null,
    var wechat: String? = null,

    @SerializedName(value = "isVip", alternate = ["vip"])
    var isVip: Boolean = false,
    var vipType: String? = null,

    var vipTitle: String? = null,

    @SerializedName(value = "vipValidDate", alternate = ["rt"])
    var vipValidDate: String? = null,

    //---新版本--
    var comments: String? = null,
    var auth: String? = null,
    var level: Int = 0,
    var isAccountType: Boolean = false,
    var focus: String? = null,
    var collection: String? = null,
    var identityState: Any? = null,
    var usWxInfo: Any? = null,
    var fans: String? = null,
    var timeCoin: String? = null,
    var kolType: Int = 0,
    var key: String? = null,
    var isRegister: Boolean = false,
    var usSysLabel: List<*>? = null,

    @SerializedName(value = "isFollowed", alternate = ["isFocus"])
    var isFollowed: Boolean = false,

    //微信的openId
    var openId: String? = null
) : Serializable {

    /**
     * userName : 10026
     * nickName : 10026
     * url : https://byair-1256706050.cos.ap-guangzhou.myqcloud.com/test/img/headImg/10026/headImg-20181101094352.jpg
     * phone : 15017137243
     * sex : 0
     * birthday : 1977-01-01
     * province : null
     * city : null
     * county : null
     * mail : null
     * qq : null
     * wechat : ox7S51arbaD3Mqf14BRHyVV7VD7s
     * wxUnionid : null
     * miniProgram : null
     * token : eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIzNzFiZDlkY2MyZDc0YzBmYWM1ZGEwMjQ3ZDU3Y2IzNyIsInBob25lIjoiMTUwMTcxMzcyNDMiLCJleHAiOjE1NjY1NDA5NTE5MjcsInVzZXJOYW1lIjoiMTAwMjYiLCJpYXQiOjE1NjU5MzYxNTE5Mjd9.8zhNGYEOa0oJ_gdTA27RlM8LZVgUGUka8SpFRRXPj54
     * abstracts : d3msatj4905ak02fcoqdmkrpjm1cssua
     * address : null
     * signature : null
     * wechatPublicPlatform : null
     * createTime : 2018-10-23T01:06:25.000+0000
     * vipType : 0
     * rt : null
     * vip : false
     * rd : null
     */


}
