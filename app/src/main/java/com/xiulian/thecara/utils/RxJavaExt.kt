package com.xiulian.thecara.utils

import com.xiulian.thecara.entity.BaseResponse
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * 实现io->main切换
 * @param <T>
 * @return
 * */
fun <T> Single<T>.ioToMain(): Single<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun Completable.ioToMain(): Completable {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

/**
 * 实现io->main切换
 * 预处理数据
 *
 * @param <T>
 * @return
</T> */

fun <T> Single<BaseResponse<T>>.handleHttpResult(): Single<T> {
    return ioToMain()
        .flatMap {
            val code = it.code
            val data = it.data
            return@flatMap when {
                code.isNullOrBlank() -> Single.error<T>(Exception("网络请求错误，错误信息为空"))
                (code == "200" || code == "0") && data != null -> Single.just(data)
                code == "10006" -> {
                    clearUserInfo()
                    Single.error<T>(Exception(code))
                }
                else -> Single.error<T>(Exception(code))
            }
        }
}

/**
 * 实现io->main切换
 * 只关心返回码"200"，不关心请求数据
 *
 * @return
 */
fun <T> Single<BaseResponse<T>>.handleResultIgnoreData(): Completable {
    return ioToMain()
        .flatMap {
            val code = it.code
            return@flatMap when {
                code.isNullOrBlank() -> Single.error<T>(Exception("网络请求错误，错误信息为空"))
                (code == "200" || code == "0") -> Single.just(it.code)
                code == "10006" -> {
                    clearUserInfo()
                    Single.error<T>(Exception(code))
                }
                else -> Single.error<T>(Exception(code))
            }
        }.ignoreElement()
}

/**
 * 实现io->main切换
 * 因为后台接口格式返回的数据有时候不只是关心返回码"200"，还要关注特殊的返回码
 * 这里只判断响应是否成功，不对返回码判断预处理，此时返回的仍是BaseResponse对象
 * @return
 */
fun <T> Single<BaseResponse<T>>.handleResultIgnoreCode(): Single<BaseResponse<T>> {
    return ioToMain()
        .flatMap {
            val code = it.code
            val data = it.data
            return@flatMap when {
                code.isNullOrBlank() -> Single.error<BaseResponse<T>>(Exception("网络请求错误，错误信息为空"))
                data != null -> Single.just(it)
                code == "10006" -> {
                    clearUserInfo()
                    Single.error<BaseResponse<T>>(Exception(code))
                }
                else -> Single.error<BaseResponse<T>>(Exception(code))
            }
        }
}
