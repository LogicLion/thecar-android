package com.xiulian.thecara.constant

import com.xiulian.thecara.BuildConfig

/**
 * @author wzh
 * @date 2019/4/11
 */
object Const {
    //    const val BASE_URL = "http://test.guoh.com.cn:8080"
//    const val BASE_URL = "http://time.guoh.com.cn:8080"

    const val BASE_URL = BuildConfig.base_url

    const val HTTP_CONNECT_TIMEOUT = 10000L
    const val HTTP_READ_TIMEOUT = 10000L

    const val BASE_URL_HTTPS = BuildConfig.base_url_https

    const val PRICE_COMPARE = BASE_URL + "/h5web/dist/index.html#/ticketIndex"
//    const val PRICE_COMPARE = "http://test.guoh.com.cn:8080/h5web/dist/index.html#/ticketIndex"
}
