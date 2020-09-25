package com.xiulian.thecara.entity

import java.io.Serializable

/**
 * 响应实体
 * @author wzh
 * @date 2019/12/11
 */
class BaseResponse<T> : Serializable {
    var body: T? = null
    val code: String? = null
    val message: String? = null

}
