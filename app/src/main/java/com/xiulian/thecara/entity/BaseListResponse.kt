package com.xiulian.thecara.entity

import java.io.Serializable

/**
 * @author wzh
 * @date 2019/12/11
 */
data class BaseListResponse<T>(
    val info: List<T>?,
    val code:Int
):Serializable
