package com.xiulian.thecara.mvvm

import com.google.gson.Gson
import com.xiulian.thecara.base.App
import com.xiulian.thecara.entity.BannerInfo
import com.xiulian.thecara.entity.RequestParam
import com.xiulian.thecara.entity.User
import com.xiulian.thecara.entity.VersionInfoBean
import com.xiulian.thecara.net.NetRequest
import com.xiulian.thecara.net.RetrofitFactory
import com.xiulian.thecara.utils.getUserInfo
import com.xiulian.thecara.utils.handleHttpResult
import com.xiulian.thecara.utils.handleResultIgnoreData
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.RequestBody
import javax.inject.Inject


/**
 * mvp中的数据层-model
 * @author wzh
 * @date 2019/5/5
 */
object DataRepository  {


    var context = App.instance
    private val netRequest: NetRequest
        get() {
            return RetrofitFactory.createRetrofit()
        }

    private val netRequestWithToken: NetRequest
        get() {
            val user = getUserInfo()
            return RetrofitFactory().createRetrofit(user.token, user.abstracts)
        }

    private val netHttpsRequestWithToken: NetRequest
        get() {
            val user = getUserInfo()
            return RetrofitFactory().createRetrofitHttps(user.token, user.abstracts)
        }

    private val user: User
        get() = getUserInfo()


    /**
     * 根据请求参数map生成请求body
     */
    private fun createRequestBody(map: HashMap<String, String>): RequestBody {
        val requestEntity = RequestParam()
        val global = RequestParam.ParamGlobal()
        global.userId = getUserInfo().userId
        requestEntity.global = global
        requestEntity.body = map
        return FormBody.create(
            MediaType.parse("Content-Type:text/DM-; charset=utf-8"),
            Gson().toJson(requestEntity)
        )
    }


    /**
     * 获取手机短信验证码
     * @param areaCode 国家区号
     * @param phoneNumber 手机号码
     */
    fun getPhoneVerificationCode(areaCode: String, phoneNumber: String): Completable {

        val map = HashMap<String, String>()
        val sb = StringBuffer(phoneNumber)
        val str = when {
            //少于11位在后面补0
            phoneNumber.length < 11 -> {
                val count = 11 - phoneNumber.length
                for (i in 1..count) {
                    sb.append("0")
                }
                sb.toString()
            }
            //多余11位截取后面11位
            phoneNumber.length > 11 -> {
                val start = phoneNumber.length - 11
                sb.substring(start)
            }
            else -> sb.toString()
        }
//        var secretKey = AESUtil.AESEncode(str + """L$7%Md#${'$'}yF5aD""", "p313iJvI$phoneNumber")
//        for (i in 0..3) {
//            secretKey = MD5Utils.MD5Encode(secretKey, "utf8")
//        }

        map["areaCode"] = areaCode
        map["phone"] = phoneNumber
//        map["secretKey"] = secretKey
        return netRequest.getPhoneVerificationCode(map).handleResultIgnoreData()
    }


    /**
     * 获取用户信息
     */
    fun refreshUserInfo(): Single<User> {
        val map = HashMap<String, String>()
        map["loginType"] = "0"
        map["phone"] = user.phone
        map["type"] = "1"
        map["userName"] = user.userId
        map["wechat"] = user.wechat ?: ""
        return netRequestWithToken.getUserInfo(map).handleHttpResult()
    }


    /**
     * 获取版本信息
     */
    fun getAppVersion(): Single<VersionInfoBean> {
        return netRequest.appVersion(1).handleHttpResult()
    }


    /**
     * 获取banner列表
     */
    fun getBanner() :Single<List<BannerInfo>>{
        return netRequest.getBannerImage(3,440100).handleHttpResult()
    }

}


