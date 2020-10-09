package com.xiulian.thecara.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * 用于数据层返回结果
 * @author wzh
 * @date 2019/12/11
 */
class BaseResponse<T> : Serializable {
    @SerializedName(value = "data", alternate = ["info"])
    var data: T? = null
    val code: String? = null
    val message: String? = null

}
