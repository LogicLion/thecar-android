package com.xiulian.thecara.net;

import com.xiulian.thecara.entity.BaseResponse;
import com.xiulian.thecara.entity.User;
import com.xiulian.thecara.entity.VersionInfoBean;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author wzh
 * @date 2019/7/24
 */
public interface NetRequest {

    /**
     * 获取手机短信验证码
     */
    @POST("user/user/phoneValidat")
    Single<BaseResponse> getPhoneVerificationCode(@Body Map<String, String> params);

    /**
     * 获取用户信息
     */
    @POST("user/user/getUserInfo")
    Single<BaseResponse<User>> getUserInfo(@Body Map<String, String> params);


    /**
     * 版本信息
     * @return
     */
    @GET("api/owner/aiche/app/info/latest")
    Single<BaseResponse<VersionInfoBean>> appVersion(@Query("appType") int appType);

}
