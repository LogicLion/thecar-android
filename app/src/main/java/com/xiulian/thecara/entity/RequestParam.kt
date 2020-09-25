package com.xiulian.thecara.entity

/**
 * 请求实体
 * @author wzh
 * @date 2019/12/10
 */
class RequestParam {
    var body: HashMap<String, String>? = null

    var global: ParamGlobal? = null

    class ParamGlobal {
        var appType: Int = 1
        var appVersion: String = ""
        var deviceMode: String = "2"
        var sellerId: String = ""
        var userId: String = ""
    }
}
